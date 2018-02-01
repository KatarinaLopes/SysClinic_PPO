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
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
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
public class ControllerMedico {

    private final DaoGenerico daoMedico = new DaoMedico();

    private Medico medicoSelecionado;

    private List<Horario> horarios = new ArrayList();

    private Horario horarioSelecionado;

    private String diasDisponiveis;

    //@ManagedProperty("#{horariosLivres}")
    private List<Horario> horariosLivres = new ArrayList();

    private List<Agendamento> agendamentosConcluidos = new ArrayList<>();

    private Agendamento agendamentoSelecionado = new Agendamento();
    
    private final MedicoManager medicoManager;
    
    public ControllerMedico(){
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

    public List<Agendamento> getAgendamentosConcluidos() {
        //agendamentosConcluidos = retornarAgendamentosConcluidos();
        
        return agendamentosConcluidos;
    }

    public void setAgendamentosConcluidos(List<Agendamento> agendamentosConcluidos) {
        this.agendamentosConcluidos = agendamentosConcluidos;
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

    public String salvarAgendamento(int idMedico, Date data, Horario periodo) {

        Medico m = medicoManager.recuperar(idMedico);

        Paciente p = (Paciente) ((HttpSession) FacesContext.
                getCurrentInstance().
                getExternalContext().getSession(true)).
                getAttribute("pacienteLogado");

        m.getAgenda().getAgendamentos().add(new Agendamento(0, data, p,
                periodo.getHorarioInicial(), false));

        atualizar(m);

        return "/pacientes/home_paciente.xhtml?faces-redirect=true";
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

    public List<Agendamento> retornarAgendamentosConcluidos() {

        Paciente p = (Paciente) ((HttpSession) FacesContext.
                getCurrentInstance().
                getExternalContext().getSession(true)).
                getAttribute("pacienteLogado");

        List<Agendamento> agendamentosConclidos = new ArrayList<>();

        if (p != null) {
            for (Medico medicosRegistrado : medicoManager.recuperarTodos()) {
                agendamentosConclidos.addAll(medicosRegistrado.
                        getAgenda().
                        retornarAgendamentosConcluidosPacientes(p));
            }

        } else {

            for (Medico medicosRegistrado : medicoManager.recuperarTodos()) {
                agendamentosConclidos.addAll(medicosRegistrado.getAgenda().
                        retornarAgendamentosConcluidos());
            }
        }
        
        return agendamentosConclidos;

    }
 
    public void atualizarHorario(Medico m, Horario h, int dia, Date inicial, 
            Date dFinal, int limite) {
        
        Horario antigo = h;
              
        Horario novo = new Horario(dia, inicial , dFinal, limite); 
        
        System.out.println(inicial + " g g " + 
                novo.getHorarioInicial());
        
        m.atualizarHorario(h, novo);
        
        /*PacienteManager.getInstance().inserirMensagemDeAlteracaoDeHorarioNoFeed(m.
                getAgenda().listarPacientesAgendados(h.getHorarioInicial()), 
                novo.getHorarioInicial(), m);
        */
        atualizar(m);
        
        //PacienteManager.getInstance().atualizarListaDePacientes();
        
        //m.getAgenda().atualizarAgendamentoHorario(antigo, novo);
        
    }
}
