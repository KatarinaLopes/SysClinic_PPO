/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.controllers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.managers.MedicoManager;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.managers.PacienteManager;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Horario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoFuncionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoMedico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoPaciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.DaoGenerico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.hibernateutil.HibernateUtil;
import com.google.gson.Gson;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.component.calendar.Calendar;

/**
 *
 * @author Katarina
 */
@ManagedBean
@SessionScoped
public class ControllerMedico {

    private final DaoGenerico daoMedico = new DaoMedico();

    @ManagedProperty("#{medicosRegistrados}")
    private Medico medicoSelecionado;

    //@ManagedProperty("#{horarios}")
    private List<Horario> horarios = new ArrayList();

    private Horario horarioSelecionado;

    private String diasDisponiveis;

    //@ManagedProperty("#{horariosLivres}")
    private List<Horario> horariosLivres = new ArrayList();

    private List<Agendamento> agendamentosConcluidos = new ArrayList<>();

    private Agendamento agendamentoSelecionado = new Agendamento();
    
    private MedicoManager medicoManager;
    
    public ControllerMedico(){
        medicoManager = new MedicoManager();
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
        System.out.println(horarioSelecionado);
        return horarioSelecionado;
    }

    public void setHorarioSelecionado(Horario horarioSelecionado) {
        System.out.println("dfg");
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

    public String cadastrar(Medico c, String conselho, int numero) {
        try{
            medicoManager.cadastrar(c, conselho, numero);
            return "/funcionarios/apresentar_medicos.xhtml?faces-redirect=true";
        }catch(IllegalArgumentException ex){
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(ex.getMessage()));
        }
        
        return null;
    }

    public Medico recuperar(Integer i) {
        return (Medico) daoMedico.recuperar(i);
    }

    public void atualizar(Medico c) {
        daoMedico.atualizar(c);
    }

    public void deletar(Medico c) {
        System.out.println(c);
        daoMedico.deletar(c);
        
    }

    public List<Medico> recuperarTodos() {
        return medicoManager.recuperarTodos();
        //agendamentosConcluidos = retornarAgendamentosConcluidos();
    }

    public void salvarHorario(int dia, Date inicio, Date fim, int limite) {
        horarios.add(new Horario(dia, inicio, fim, limite));
    }

    public void excluirHorario(Horario h) {
        System.out.println("antes");
        horarios.remove(h);
        System.out.println("depois");
    }

    /*public void salvarAgendamento(Agendamento a){
         Medico medico = a.getMedico();
         
         medico.getAgendamento().add(a);
         
         atualizar(medico);
    }*/
    public Medico procurarMedico(int id) {
        for (Medico medicosRegistrado : medicoManager.recuperarTodos()) {
            if (medicosRegistrado.getId() == id) {
                return medicosRegistrado;
            }
        }

        return null;
    }

    public String salvarAgendamento(int idPaciente, int idMedico, Date data,
            Horario periodo) {

        Medico m = procurarMedico(idMedico);

        System.out.println(m);

        Paciente p = new ControllerPaciente().procurarPaciente(idPaciente);

        //System.out.println(p + " je " + data);
        //DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        m.getAgenda().getAgendamentos().add(new Agendamento(0, data, p, m,
                periodo.getHorarioInicial(), false));

        atualizar(m);

        return "/acoes/agendamentos_pendentes.xhtml?faces-redirect=true";
    }

    public String salvarAgendamento(int idMedico, Date data, Horario periodo) {

        Medico m = procurarMedico(idMedico);

        Paciente p = (Paciente) ((HttpSession) FacesContext.
                getCurrentInstance().
                getExternalContext().getSession(true)).
                getAttribute("pacienteLogado");

        m.getAgenda().getAgendamentos().add(new Agendamento(0, data, p, m,
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
    public void retornarDiasLivres(int idM) {
        System.out.println("1");

        Medico m = procurarMedico(idM);

        System.out.println(m);

        Gson gson = new Gson();

        System.out.println("3");

        diasDisponiveis = gson.toJson(m.pegarDiasLivres());

        HttpSession sess = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(true);

        sess.setAttribute("medicoAgendamento", m);

        System.out.println("4");
    }

    public void carregarHorariosLivres(Date data, int idM) {
        Medico m = (Medico) ((HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(true)).
                getAttribute("medicoAgendamento");

        System.out.println(m + " d " + data);

        horariosLivres = m.pegarHorariosLivres(data);
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
