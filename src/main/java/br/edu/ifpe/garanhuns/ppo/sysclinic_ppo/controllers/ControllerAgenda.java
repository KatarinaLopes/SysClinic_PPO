/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.controllers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agenda;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Horario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoAgenda;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.DaoGenerico;
import com.google.gson.Gson;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Katarina
 */
@ManagedBean
@SessionScoped
public class ControllerAgenda implements ControllerGenerico<Agenda, Integer> {

    private DaoGenerico agendas = new DaoAgenda();
    
    private Agendamento agendamento = new Agendamento();
    
    private List<Horario> horarios;
    
    private List<Integer> dias;
    
    private String diasJson;    

    @Override
    public void cadastrar(Agenda c) {
        agendas.persistir(c);
    }

    @Override
    public Agenda recuperar(Integer i) {
        return (Agenda) agendas.recuperar(i);
    }

    public Agenda recuperarPorAtributo(String atributo, Object value){
        return (Agenda) agendas.recuperarPorAtributo(atributo, value);
    }
    
    @Override
    public void atualizar(Agenda c) {
        agendas.atualizar(c);
    }

    public List<Agenda> recuperarTodos() {
        return agendas.recuperarTodos();
    }
   
    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }
    
    public void carregarDias(Medico m){
        System.out.println(m);
        horarios = m.getHorarios();
        dias = m.pegarDiasLivres();
        System.out.println(dias);
        diasJson = pegarDiasLivres();
        System.out.println(diasJson);
    }
    
    public String pegarDiasLivres(){
        Gson jsonParser = new Gson();
        
        String jsonDias = jsonParser.toJson(dias);
        
        System.out.println(dias);
        
        return jsonDias;
    }
    
    
    public void incluirAgendamento() {

        Agenda agenda1 = recuperar(3);
        
        agendamento.setMedico(agenda1.getMedico());
        
        atualizar(agenda1);
        
        //Agenda agenda = 
        //       recuperarPorAtributo("medico_id", 1);

        /*List<Agenda> agendas = recuperarTodos();
        
        Agenda agenda = null;
        
        for (Agenda agenda1 : agendas) {
            if(agenda1.getMedico().getId() == agendamento.getMedico().getId()){
                agenda = agenda1;
                break;
            }
        }*/
        
        Agendamento a = agendamento;
        
        agenda1.getAgendamentos().add(a);

        atualizar(agenda1);
    }

}
