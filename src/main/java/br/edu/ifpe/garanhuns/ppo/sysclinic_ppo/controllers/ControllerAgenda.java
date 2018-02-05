/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.controllers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.managers.Fachada;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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

    private Agendamento agendamentoSelecionado;

    private Agendamento agendamentoSelecionadoClone;

    private ScheduleEvent eventAgendamento = new DefaultScheduleEvent();

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

    public Agendamento getAgendamentoSelecionadoClone() {
        return agendamentoSelecionadoClone;
    }

    public void setAgendamentoSelecionadoClone(Agendamento agendamentoSelecionadoClone) {
        this.agendamentoSelecionadoClone = agendamentoSelecionadoClone;
    }
    
    public String salvarAgendamento(Agendamento agendamento) {
        Fachada fachada = Fachada.getInstance();
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage fm;
        String retorno = null;

        try {
            fachada.getAgendaManager().marcarAgendamento(agendamento);
            fachada.getAgendaManager().atualizar();
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
        return Fachada.getInstance().getAgendaManager().isPrimeiroAcesso();
    }

    public void verificarCadastrarNovaAgenda() {
        Fachada.getInstance().getAgendaManager().
                verificarCadastrarNovaAgenda();
    }

    public List<Agendamento> retornarAgendamentosConcluidos(Paciente 
            pacienteLogado) {
        return Fachada.getInstance().getAgendaManager().
                alternarRetornarAgendamentosConcluidos(pacienteLogado);
    }

    public List<Agendamento> retornarAgendamentosPendentes(Paciente pacienteLogado) {
        return Fachada.getInstance().getAgendaManager().
                alternarAgendamentosPendentes(pacienteLogado);
    }

    public void excluirAgendamento(Agendamento a) {
        FacesContext fc = FacesContext.getCurrentInstance();
        String retorno = Fachada.getInstance().getAgendaManager().
                excluirAgendamento(a);
        FacesMessage fm = new FacesMessage(retorno);
        fc.addMessage(null, fm);
    }

    public List<Agendamento> retornarAgendamentosDataAtual() {
        return Fachada.getInstance().getAgendaManager().
                retornarAgendamentosDataAtual();
    }

    public void atualizarHorariosAgendamentos(Date horarioAntigo,
            Date horarioNovo, Medico medico) {
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage fm;
        Fachada fachada = Fachada.getInstance();
        
        
        fachada.getAgendaManager().atualizarHorariosAgendamentos(horarioAntigo,
                horarioNovo, medico);
        fachada.getAgendaManager().atualizar();

        fm = new FacesMessage("Sucesso!",
                "Os horarios dos agendamentos foram alteradas com sucesso!");

        fc.addMessage(null, fm);
    }

    public ScheduleModel retornarScheduleAgendamentos(int tipo,
            Paciente pacienteLogado) {
        return Fachada.getInstance().getAgendaManager().
                retornarScheduleAgendamentos(tipo, pacienteLogado);
    }

    public void atualizarStatusAgendamento(Agendamento agendamento) {
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage fm;
        
        Fachada fachada = Fachada.getInstance();

        fachada.getAgendaManager().atualizarStatusAgendamento(agendamento);
        fachada.getAgendaManager().atualizar();
        fm = new FacesMessage("Sucesso!",
                "O status do agendamento foi alterado com sucesso.");

        fc.addMessage(null, fm);
    }

    public String atualizarDataAgendamento(Agendamento agendamento) {

             
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage fm;
        String retorno = null;
        Fachada fachada = Fachada.getInstance();
        try {
            fachada.getAgendaManager().remarcar(agendamento.getDataPrevista(),
                    agendamento.getPeriodo(), agendamento);
            fachada.getAgendaManager().atualizar();

            fm = new FacesMessage("Sucesso!",
                    "O agendamento foi remarcado com sucesso!");

            retorno = "/acoes/agendamentos_pendentes.xhtml?"
                    + "faces-redirect=true";
        } catch (IllegalArgumentException ex) {
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
                    ex.getMessage());
        }

        fc.addMessage(null, fm);
        return retorno;
    }

    public void remarcarAgendamentos(int diaAnterior, int diaNovo, 
            Date horarioNovo, Medico medico) {
        
        System.out.println(diaAnterior + " y " + diaNovo);

        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage fm;

        Fachada fachada = Fachada.getInstance();
        
        try {
            fachada.getAgendaManager().remarcar(diaAnterior, diaNovo,
                    horarioNovo, medico);
            fachada.getAgendaManager().atualizar();
            fm = new FacesMessage("Sucesso!", "Os agendamentos foram "
                    + "remarcados com sucesso.");
        } catch (IllegalArgumentException ex) {
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", 
                    ex.getMessage());
        }
        
        fc.addMessage(null, fm);
    }
}
