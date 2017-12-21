/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.controllers;

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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
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
    private List<Medico> medicosRegistrados;
    
    @ManagedProperty("#{medicosRegistrados}")
    private Medico medicoSelecionado;
    
    //@ManagedProperty("#{horarios}")
    private List<Horario> horarios = new ArrayList();

    //@ManagedProperty("#{horarioSelecionado}")
    private Horario horarioSelecionado = new Horario(); 
    
    private String diasDisponiveis;

    //@ManagedProperty("#{horariosLivres}")
    private List<Horario> horariosLivres = new ArrayList();
    
    private List<Agendamento> agendamentosConcluidos = new ArrayList<>();
    
    public List<Medico> getMedicosRegistrados() {
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
        return agendamentosConcluidos;
    }

    public void setAgendamentosConcluidos(List<Agendamento> agendamentosConcluidos) {
        this.agendamentosConcluidos = agendamentosConcluidos;
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
        agendamentosConcluidos = retornarAgendamentosConcluidos();
    }
    
    public void salvarHorario(int dia, Date inicio, Date fim, int limite){
        horarios.add(new Horario(dia, inicio, fim, limite));
    }
    
    public void excluirHorario(Horario h){
        System.out.println("antes");
        horarios.remove(h);
        System.out.println("depois");
    }
    
    /*public void salvarAgendamento(Agendamento a){
         Medico medico = a.getMedico();
         
         medico.getAgendamento().add(a);
         
         atualizar(medico);
    }*/
    
    
    public Medico procurarMedico(int id){
        for (Medico medicosRegistrado : medicosRegistrados) {
            if(medicosRegistrado.getId() == id){
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
        
        return "home_admin.xhtml";
    }
    
    public String salvarAgendamento(int idMedico, Date data, Horario periodo){
        
        Medico m = procurarMedico(idMedico);
        
        Paciente p = (Paciente) ((HttpSession) FacesContext.
                getCurrentInstance().
                getExternalContext().getSession(true)).getAttribute("pacienteLogado");
        
        m.getAgenda().getAgendamentos().add(new Agendamento(0, data, p, m, 
                periodo.getHorarioInicial(), false));
         
        atualizar(m);
        
        return "home_paciente.xhtml";
    }
    
    /**
     * Adiciona ao atributo diasDisponiveis os dias da semana (representados
     * por números) disponíveis para o médico, como Json, para ser lido pela 
     * página
     * @param m
     * @return 
     */
    public void retornarDiasLivres(int idM){
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
    
    public void carregarHorariosLivres(Date data, int idM){
        Medico m = (Medico) ((HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(true)).
                getAttribute("medicoAgendamento");
        
        System.out.println(m + " d " + data);
        
        horariosLivres = m.pegarHorariosLivres(data);
    }
    
    public List<Agendamento> retornarAgendamentosConcluidos(){
        List<Agendamento> agendamentosConcluidos = new ArrayList<>();
        
        for (Medico medicosRegistrado : medicosRegistrados) {
            agendamentosConcluidos.addAll(medicosRegistrado.getAgenda().
                    retornarAgendamentosConcluidos());
        }
        
        return agendamentosConcluidos;
    }
}
