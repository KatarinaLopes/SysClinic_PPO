/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.controllers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.managers.MedicoManager;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Horario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoMedico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.
        DaoGenerico;
import java.io.Serializable;
import java.util.ArrayList;
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

    private final DaoGenerico daoMedico = new DaoMedico();
    
    private Medico medicoSelecionado;

    private List<Horario> horarios = new ArrayList();

    private Horario horarioSelecionado;

    private String diasDisponiveis;

    private List<Horario> horariosLivres;

    private Agendamento agendamentoSelecionado;
    
    private final MedicoManager medicoManager;
    
    public ControllerMedico(){
        horariosLivres = new ArrayList();
        medicoManager = new MedicoManager((DaoMedico) daoMedico);
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
    public String cadastrar(Medico medico, String conselho, int numero) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage fm;
        String retorno = null;
        
        try{
            medicoManager.cadastrar(medico, conselho, numero);
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
        return medicoManager.recuperar(i);
    }

    public void atualizar(Medico c) {
        medicoManager.atualizar(c);
    }

    public void deletar(Medico c) {
        medicoManager.deletar(c);
    }

    public List<Medico> recuperarTodos() {
        return medicoManager.recuperarTodos();
   }

    public String salvarAgendamento(Agendamento agendamento, Medico medico) {
        medico.getAgenda().getAgendamentos().add(agendamento);

        atualizar(medico);

        return "/acoes/agendamentos_pendentes.xhtml?faces-redirect=true";
    }

    /**
     * Adiciona ao atributo diasDisponiveis os dias da semana (representados por
     * números) disponíveis para o médico, como Json, para ser lido pela página
     *
     * @param m
     * @return
     */
    public void carregarDiasLivres(Medico medico) {
        diasDisponiveis = medicoManager.retornarDiasLivres(medico);
    }

    public void carregarHorariosLivres(Date data, Medico medico) {
        horariosLivres = medicoManager.retornarHorariosLivres(medico, data);
    }

    public List<Agendamento> retornarAgendamentosConcluidos
        (Paciente paciente){
        return medicoManager.retornarAgendamentosConcluidos(paciente);
    }
        
    public List<Agendamento> retornarAgendamentosConcluidos(){
        return medicoManager.retornarAgendamentosConcluidos();
    }

    public void atualizarHorario(Medico medico, Horario antigo, Horario novo){
       
        medico.atualizarHorario(antigo, novo);
        
        atualizar(medico);
        
    }
    
    /*public List<Agendamento> retornarAgendamentosPendentes(){
        Paciente p = (Paciente) ((HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(true)).
                getAttribute("pacienteLogado");
        
        List<Agendamento> agendamentosPendentes = new ArrayList<>();
        
        if(p != null){
            for (Medico medicosRegistrado : medicosRegistrados) {
                agendamentosPendentes.addAll(medicosRegistrado.getAgenda().
                        retornarAgendamentosPendentesPacientes(p));
            }
        }else{
            for (Medico medicosRegistrado : medicosRegistrados) {
                agendamentosPendentes.addAll(medicosRegistrado.getAgenda().
                        retornarAgendamentosPendentes());
            }
        }
        
        return agendamentosPendentes;
    }*/
    
}
