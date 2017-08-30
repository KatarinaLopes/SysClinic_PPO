/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.controllers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Funcionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoFuncionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.DaoGenerico;
import java.util.List;
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

    @ManagedProperty(value = "#{funcionarios}")
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

        try {
            funcionarios.persistir(c);
        } catch (ConstraintViolationException cve) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
                    "O funcionário já foi cadastrado! Se deseja alterá-lo, "
                    + "vá para a <h:link value='Alterar paciente' outcome:'alterar_paciente.xhtml'",
                    null));
            
            return null;
        }
        
        return "apresentar-funcionarios.xhtml";
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
}
