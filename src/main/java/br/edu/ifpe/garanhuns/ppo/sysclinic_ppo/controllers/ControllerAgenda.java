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
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoAgenda;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoMedico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoPaciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.DaoGenerico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.hibernateutil.HibernateUtil;
import com.google.gson.Gson;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Katarina
 */
@ManagedBean
@SessionScoped
public class ControllerAgenda implements ControllerGenerico<Agenda, Integer> {

    private DaoAgenda agendas = new DaoAgenda();

    @ManagedProperty("#{agendamento}")
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

    public Agenda recuperarPorAtributo(String atributo, Object value) {
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
        //System.out.println(agendamento.getPaciente().getNome());

        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {

        this.agendamento = agendamento;
        //System.out.println(agendamento.getPaciente().getNome());
    }

    public void carregarDias(Medico m) {
        System.out.println(m);
        horarios = m.getHorarios();
        dias = m.pegarDiasLivres();
        System.out.println(dias);
        diasJson = pegarDiasLivres();
        System.out.println(diasJson);
    }

    public String pegarDiasLivres() {
        Gson jsonParser = new Gson();

        String jsonDias = jsonParser.toJson(dias);

        System.out.println(dias);

        return jsonDias;
    }

    public String incluirAgendamento(int idPaciente) {
        
        
        return "agendamentos_hoje.xhtml";
    }

}
