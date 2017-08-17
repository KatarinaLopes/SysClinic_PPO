/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.controllers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoPaciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.DaoGenerico;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Katarina
 */
@ManagedBean
public class ControllerPaciente implements ControllerGenerico<Paciente, Integer>{

    private DaoGenerico pacientes = new DaoPaciente();
    
    @ManagedProperty("#{pacienteLogado}")
    private Paciente pacienteLogado;

    @ManagedProperty("#{pacientes}")
    private List<Paciente> pacientesCadastrados;
    
    public Paciente getPacienteLogado() {
        return pacienteLogado;
    }

    public void setPacienteLogado(Paciente pacienteLogado) {
        this.pacienteLogado = pacienteLogado;
    }

    public List<Paciente> getPacientesCadastrados() {
        return pacientesCadastrados;
    }

    public void setPacientesCadastrados(List<Paciente> pacientesCadastrados) {
        this.pacientesCadastrados = pacientesCadastrados;
    }
    
    @Override
    public void cadastrar(Paciente c) {
        pacientes.persistir(c);
    }

    @Override
    public Paciente recuperar(Integer i) {
        return (Paciente) pacientes.recuperar(i);
    }

    @Override
    public void atualizar(Paciente c) {
        pacientes.atualizar(c);
    }

    @PostConstruct
    public void recuperarTodos() {
       pacientesCadastrados = pacientes.recuperarTodos();
    }
    
}
