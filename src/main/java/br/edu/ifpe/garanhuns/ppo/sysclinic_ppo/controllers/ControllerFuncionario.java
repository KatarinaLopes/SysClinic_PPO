/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.controllers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Funcionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoFuncionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.DaoGenerico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.validators.Validacoes;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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

    @Deprecated
    @Override
    public void cadastrar(Funcionario c) {

    }

    public String cadastrar(Funcionario c, String senha) {
        if(!Validacoes.validarSenhas(c.getSenha(), senha)){
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, 
                            "As senhas não correspondem", null));
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
    public void recuperarTodos(){
        funcionariosRegistrados = funcionarios.recuperarTodos();
    }
    
    public String logout(){
        return "login_intranet.xhtml";
    }
}
