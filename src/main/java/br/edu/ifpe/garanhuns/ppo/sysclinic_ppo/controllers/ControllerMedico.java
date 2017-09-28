/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.controllers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Horario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoFuncionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoMedico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.DaoGenerico;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Katarina
 */
@ManagedBean
@SessionScoped
public class ControllerMedico {
    
    private final DaoGenerico daoMedico = new DaoMedico();
    
    @ManagedProperty("#{medicosRegistrados}")
    private List medicosRegistrados;
    
    @ManagedProperty("#{medicosRegistrados}")
    private Medico medicoSelecionado;
    
    @ManagedProperty("#{horarios}")
    private List<Horario> horarios = new ArrayList();

    public List getMedicosRegistrados() {
        return medicosRegistrados;
    }

    public void setMedicosRegistrados(List medicosRegistrados) {
        this.medicosRegistrados = medicosRegistrados;
    }

    public Medico getMedicoSelecionado() {
        return medicoSelecionado;
    }

    public void setMedicoSelecionado(Medico medicoSelecionado) {
        this.medicoSelecionado = medicoSelecionado;
    }
    
    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }
    
    
    public String cadastrar(Medico c, String conselho, int numero) {
        String numeroString = String.valueOf(numero);
        
        String conselhoMedico = conselho + " " + numeroString;
        
        
        System.out.println(conselhoMedico);
        
        c.setConselho(conselhoMedico);
        
        daoMedico.persistir(c);
        
        return "apresentar_medicos.xhtml";
    }
    
    
    public Medico recuperar(Integer i) {
        return (Medico) daoMedico.recuperar(i);
    }

    
    public void atualizar(Medico c) {
        daoMedico.atualizar(c);
    }
    
    public void deletar(Medico c){
        daoMedico.deletar(c);
    }
    
    @PostConstruct
    public void recuperarTodos(){
        medicosRegistrados = daoMedico.recuperarTodos();
    }
    
    public void salvarHorario(String dia, Date inicio, Date fim){
        horarios.add(new Horario(dia, inicio, fim));
    }
}
