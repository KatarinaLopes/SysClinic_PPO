/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.controllers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.managers.Fachada;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Horario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Katarina
 */
@ManagedBean
@SessionScoped
public class ControllerMedico implements Serializable{
    
    private Medico medicoSelecionado;

    private List<Horario> horarios = new ArrayList();

    private Horario horarioSelecionado;

    private String diasDisponiveis;

    private List<Horario> horariosLivres;

    private List<Agendamento> agendamentosPendentes = new ArrayList<>();

    private Agendamento agendamentoSelecionado;
        
    public ControllerMedico(){
        horariosLivres = new ArrayList();
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

    public Horario getHorarioSelecionado() {
        return horarioSelecionado;
    }

    public void setHorarioSelecionado(Horario horarioSelecionado) {
        this.horarioSelecionado = horarioSelecionado;
    }

    public String getDiasDisponiveis() {
        String dias = diasDisponiveis;
        diasDisponiveis = null;
        return dias;
    }

    public void setDiasDisponiveis(String diasDisponiveis) {
        this.diasDisponiveis = diasDisponiveis;
    }

    public List<Horario> getHorariosLivres() {
        return horariosLivres;
    }

    public void setHorariosLivres(List<Horario> horariosLivres) {
        this.horariosLivres = horariosLivres;
    }

    public List<Agendamento> getAgendamentosPendentes() {
        //agendamentosConcluidos = retornarAgendamentosConcluidos();
        
        return agendamentosPendentes;
    }

    public void setAgendamentosPendentes(List<Agendamento> agendamentosConcluidos) {
        this.agendamentosPendentes = agendamentosConcluidos;
    }

    public Agendamento getAgendamentoSelecionado() {
        return agendamentoSelecionado;
    }

    public void setAgendamentoSelecionado(Agendamento agendamentoSelecionado) {
        this.agendamentoSelecionado = agendamentoSelecionado;
    }

    /**
     * EN-US
     * Persists the given Medico. If an error occurs, a message will be sent.
     * 
     * PT-BR
     * Persiste o Medico dado. Se um erro ocorrer, uma mensagem será mandada.
     * 
     * @param medico
     * @param conselho
     * @param numero
     * @return 
     */
    public String cadastrar(Medico medico) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage fm;
        String retorno = null;
        
        try{
            Fachada.getInstance().getMedicoManager().cadastrar(medico);
            fm = new FacesMessage("Sucesso", "O médico foi cadastrado com "
                    + "sucesso!" );
            retorno = "/funcionarios/apresentar_medicos.xhtml?"
                    + "faces-redirect=true";
        }catch(IllegalArgumentException ex){
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", 
                    ex.getMessage());
        }
        
        facesContext.addMessage(null, fm);
        return retorno;
    }

    public Medico recuperar(Integer i) {
        return Fachada.getInstance().getMedicoManager().recuperar(i);
    }

    public void atualizar(Medico c) {
        Fachada.getInstance().getMedicoManager().atualizar(c);
    }

    public void deletar(Medico c) {

       Fachada.getInstance().getMedicoManager().deletar(c);
    }

      public List<Medico> recuperarTodos() {
        return Fachada.getInstance().getMedicoManager().recuperarTodos();
   }

   

    /**
     * Adiciona ao atributo diasDisponiveis os dias da semana (representados por
     * números) disponíveis para o médico, como Json, para ser lido pela página
     *
     * @param m
     * @return
     */
    public void carregarDiasLivres(Medico medico) {
        diasDisponiveis = Fachada.getInstance().getMedicoManager().
                retornarDiasLivres(medico);
    }

    public void carregarHorariosLivres(Date data, Medico medico) {
        horariosLivres = Fachada.getInstance().getMedicoManager().
                retornarHorariosLivres(medico, data);
    }

    public void atualizarHorario(Medico medico, Horario antigo, Horario novo){  
        int diaAntigo = antigo.getDia();
        int diaNovo = novo.getDia();
        
        Fachada.getInstance().getMedicoManager().atualizarHorario(medico, 
                antigo, novo);
        atualizar(medico);
        
        System.out.println(diaAntigo + " dj " + diaNovo);
        
        Fachada.getInstance().getAgendaManager().
                remarcar(diaAntigo, novo, medico);
        
        Fachada.getInstance().getAgendaManager().atualizar();
        
        Fachada.getInstance().getPacienteManager().
                inserirMensagemDeAtualizacaoDeHorario(medico, 
                        novo.getHorarioInicial());
    }
    
}
