/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.controllers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Funcionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoFuncionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.DaoGenerico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.validators.Operacoes;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.validators.Validacoes;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Katarina
 */
@ManagedBean
@SessionScoped
public class ControllerFuncionario implements ControllerGenerico<Funcionario, Integer> {

    private DaoGenerico funcionarios = new DaoFuncionario();

    @ManagedProperty(value = "#{funcionarioLogado}")
    private Funcionario funcionarioLogado;

    @ManagedProperty(value = "#{funcionariosRegistrados}")
    private List<Funcionario> funcionariosRegistrados;

    @ManagedProperty(value = "#{funcionarioSelecionado}")
    private Funcionario funcionarioSelecionado;

    public Funcionario getFuncionarioLogado() {
        return funcionarioLogado;
    }

    public void setFuncionarioLogado(Funcionario funcionarioLogado) {
        this.funcionarioLogado = funcionarioLogado;
    }

    public List<Funcionario> getFuncionariosRegistrados() {
        return funcionariosRegistrados;
    }

    public void setFuncionariosRegistrados(List<Funcionario> funcionariosRegistrados) {
        this.funcionariosRegistrados = funcionariosRegistrados;
    }

    public Funcionario getFuncionarioSelecionado() {
        return funcionarioSelecionado;
    }

    public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
        this.funcionarioSelecionado = funcionarioSelecionado;
    }

    @Deprecated
    @Override
    public void cadastrar(Funcionario c) {

    }

    public String criptografarSenha(String senha) {
        String novaSenha;

        try {
            novaSenha = Operacoes.criptografarSenha(senha);
        } catch (NoSuchAlgorithmException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Ocorreu um erro"));
            return null;
        } catch (UnsupportedEncodingException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Ocorreu um erro"));

            return null;
        }

        return novaSenha;
    }

    public String cadastrar(Funcionario c, String senha) {

        if (!Validacoes.validarSenhas(c.getSenha(), senha)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "As senhas não correspondem", null));
            return null;
        }

        try {
            String s = criptografarSenha(senha);
            c.setSenha(s);
        } catch (NullPointerException ex) {
            return null;
        }

        try {

            funcionarios.persistir(c);
        } catch (ConstraintViolationException cve) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
                    "O funcionário já foi cadastrado!",
                    null));

            return null;
        }

        return "apresentar_funcionarios.xhtml";
    }

    @Override
    public Funcionario recuperar(Integer i) {
        return (Funcionario) funcionarios.recuperar(i);
    }

    @Override
    public void atualizar(Funcionario c) {
        funcionarios.atualizar(c);
    }

    public void deletar(Funcionario f) {
        funcionarios.deletar(f);
    }

    @PostConstruct
    public void recuperarTodos() {
        funcionariosRegistrados = funcionarios.recuperarTodos();
    }

    public String fazerLogin(Integer login, String senha) {
        try {
            Funcionario func = (Funcionario) funcionarios.
                    recuperarPorAtributo("matricula", String.valueOf(login));

            if (func.getSenha().equals(criptografarSenha(senha))) {
                funcionarioLogado = func;

                HttpSession sess = (HttpSession) FacesContext.getCurrentInstance().
                        getExternalContext().getSession(true);

                sess.setAttribute("funcionarioLogado", funcionarioLogado);

            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL,
                                "A senha está incorreta", null));
                return null;
            }

            return alternarLogin(func);
        } catch (IndexOutOfBoundsException ie) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
                    "Esta matrícula não está cadastrada no sistema", null));
        }

        return null;
    }

    private String alternarLogin(Funcionario f) {
        if (f.isAdministrador()) {
            return "home_admin.xhtml";
        }

        return "home_funcionario.xhtml";
    }

    public String logout() {
        HttpSession ses = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(true);

        funcionarioLogado = null;

        ses.removeAttribute("funcionarioLogado");

        return "login_intranet.xhtml";
    }
}
