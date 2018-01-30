/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.controllers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.beans.LoginFuncionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Funcionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoFuncionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoPaciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.exception.DaoException;
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

    private DaoFuncionario funcionarios = new DaoFuncionario();

    @ManagedProperty(value = "#{funcionariosRegistrados}")
    private List<Funcionario> funcionariosRegistrados;

    @ManagedProperty(value = "#{funcionarioSelecionado}")
    private Funcionario funcionarioSelecionado;

    private boolean podeExcluirOuAlterar;

    private LoginFuncionario loginFuncionario;

    public ControllerFuncionario(){
        loginFuncionario = new LoginFuncionario();
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

    public boolean isPodeExcluirOuAlterar() {
        return podeExcluirOuAlterar;
    }

    public void setPodeExcluirOuAlterar(boolean podeExcluirOuAlterar) {
        this.podeExcluirOuAlterar = podeExcluirOuAlterar;
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

        /*try {
            String s = criptografarSenha(senha);
            c.setSenha(s);
        } catch (NullPointerException ex) {
            return null;
        }*/
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

    //@Override
    public void atualizar(Funcionario c) {
        funcionarios.atualizar(c);
    }

    /**
     * Exclui um funcionário do banco de dados
     *
     * @param f representando funcionário a ser excluído
     */
    public void deletar(Funcionario f) {

        if (funcionarios.podeExcluirOuAlterar(f.isAdministrador())) {
            funcionarios.deletar(f);
        } else {
            System.err.println("else");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta",
                            "Impossível excluir este funcionário. O sistema "
                            + "precisa de um administrador e "
                            + "um funcionário para as ações"));
        }
    }

    //@PostConstruct
    public List<Funcionario> recuperarTodos() {
        return funcionarios.recuperarTodos();
    }

    public String fazerLogin(Integer login, String senha) {
        FacesContext fc = FacesContext.getCurrentInstance();

        try {
            loginFuncionario.login(login, senha,
                    (DaoFuncionario) funcionarios);
            loginFuncionario.setarFuncionarioLogadoNaSessao();

            return pegarPaginaDeRedirecionamento();
        } catch (DaoException | IllegalArgumentException ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Ocorreu um erro", ex.getMessage());

            fc.addMessage(":form-login-funcionarios:messages-intranet", fm);
        }

        return null;
    }

    private String pegarPaginaDeRedirecionamento() {
        if (loginFuncionario.existeLogadoAdministrador()) {
            return "/administrador/home_admin.xhtml?faces-redirect=true";
        } else {
            return "/funcionarios/home_funcionario.xhtml?faces-redirect=true";
        }
    }
    
    public Funcionario pegarFuncionarioLogado(){
        return loginFuncionario.getFuncionarioLogado();
    }
    
    public boolean existeLogadoAdministrador(){
        return loginFuncionario.existeLogadoAdministrador();
    }

    public boolean existeLogadoNaoAdministrador(){
        return loginFuncionario.existeLogadoNaoAdministrador();
    }
    
    public String logout() {
            loginFuncionario.logout();
            loginFuncionario.tirarFuncionarioLogadoDaSessao();

            return "/login/login_intranet.xhtml?faces-redirect=true";
    }

    /*public boolean podeExcluirFuncionario(Funcionario f) {
        int qtdeFuncionario = 0;
        int qtdeAdministrador = 0;

        System.out.println(f);
        
        for (Funcionario funcionariosRegistrado : funcionariosRegistrados) {
            if (funcionariosRegistrado.isAdministrador()) {
                qtdeAdministrador++;
            } else {
                qtdeFuncionario++;
            }
        }

        return f.isAdministrador() ? qtdeAdministrador > 1
                : qtdeFuncionario > 1;
    }*/
    public void exibirAlertaDeMudanca() {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso",
                        "Administradores podem cadastrar, alterar e excluir "
                        + "médicos e funcionários, mas não têm acesso "
                        + "ao sistema de agendamentos. Mude o "
                        + "privilégio apenas se necessário"));
    }

    public void getPodeExcluirOuAlterar(Funcionario f) {
        System.out.println(f.getNome());

        podeExcluirOuAlterar = !funcionarios.
                podeExcluirOuAlterar(f.isAdministrador());

        System.out.println(podeExcluirOuAlterar);

        /*if(podeExcluirOuAlterar){
            return true;
        }
        
        return false;*/
    }
}
