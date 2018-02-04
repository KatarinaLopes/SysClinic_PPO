/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.controllers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.managers.AgendaManager;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agenda;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoAgenda;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author Katarina
 */
@ManagedBean
@SessionScoped
public class ControllerAgenda implements Serializable {

    private final AgendaManager agendaManager;

    private Agendamento agendamentoSelecionado;
    
    private ScheduleEvent eventAgendamento = new DefaultScheduleEvent();

    public ControllerAgenda() {
        agendaManager = new AgendaManager(new DaoAgenda());
    }

    public Agendamento getAgendamentoSelecionado() {
        return agendamentoSelecionado;
    }

    public void setAgendamentoSelecionado(Agendamento agendamentoSelecionado) {
        this.agendamentoSelecionado = agendamentoSelecionado;
    }

    public ScheduleEvent getEventAgendamento() {
        return eventAgendamento;
    }

    public void setEventAgendamento(ScheduleEvent eventAgendamento) {
        this.eventAgendamento = eventAgendamento;
    }
    
    

    public String salvarAgendamento(Agendamento agendamento) {
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage fm;
        String retorno = null;

        try {
            agendaManager.marcarAgendamento(agendamento);
            agendaManager.atualizar();
            fm = new FacesMessage("Sucesso!", "O agendamento foi marcado com "
                    + "sucesso!");
            retorno = "/acoes/agendamentos_pendentes.xhtml?"
                    + "faces-redirect=true";
        } catch (IllegalArgumentException ex) {
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
                    ex.getMessage());
        }

        fc.addMessage(null, fm);
        return retorno;
    }

    public boolean ehPrimeiroAcesso() {
        return agendaManager.isPrimeiroAcesso();
    }

    public void verificarCadastrarNovaAgenda() {
        agendaManager.verificarCadastrarNovaAgenda();
    }

    public List<Agendamento> retornarAgendamentosConcluidos(Paciente 
            pacienteLogado) {
        return agendaManager.
                alternarRetornarAgendamentosConcluidos(pacienteLogado);
    }

    public List<Agendamento> retornarAgendamentosPendentes() {

        return agendaManager.retornarAgendamentosPendentes();
    }

    public List<Agendamento> retornarAgendamentosPendentes(Paciente p) {

        return agendaManager.retornarAgendamentosPendentes(p);
    }

    public void excluirAgendamento(Agendamento a) {
        FacesContext fc = FacesContext.getCurrentInstance();
        String retorno = agendaManager.excluirAgendamento(a);
        FacesMessage fm = new FacesMessage(retorno);
        fc.addMessage(null, fm);
    }

    public List<Agendamento> retornarAgendamentosDataAtual() {
        return agendaManager.retornarAgendamentosDataAtual();
    }

    public void atualizarHorariosAgendamentos(Date horarioAntigo,
            Date horarioNovo, Medico medico) {
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage fm;

        agendaManager.atualizarHorariosAgendamentos(horarioAntigo,
                horarioNovo, medico);
        agendaManager.atualizar();

        fm = new FacesMessage("Sucesso!",
                "Os horarios dos agendamentos foram alteradas com sucesso!");

        fc.addMessage(null, fm);
    }
    
    public ScheduleModel retornarScheduleAgendamentos(int tipo, 
            Paciente pacienteLogado){
        return agendaManager.retornarScheduleAgendamentos(tipo, 
                pacienteLogado);
    }
     
}
    
