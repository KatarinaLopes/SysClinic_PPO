/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.builders;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.jsf.services.MedicoService;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.jsf.services.PacienteService;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Horario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import com.google.gson.Gson;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Katarina
 */
@ManagedBean
@RequestScoped
public class BeanAgendamento implements BuilderGenerico<Agendamento>{
    private int id;
    private Date dataPrevista;
    private Paciente paciente;
    private Medico medico;
    private Date periodo;
    private boolean realizada = false;

    @ManagedProperty("#{pacienteService}")
    private PacienteService pacienteService;
    
    private List<Paciente> pacientes;
    
    @ManagedProperty("#{medicoService}")
    private MedicoService medicoService;
    
    private List<Medico> medicos;
    
    private List<Horario> horarios;
    
    private List<Integer> dias;
    
    private String diasJson;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(Date dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public boolean isRealizada() {
        return realizada;
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }

    public Date getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Date periodo) {
        this.periodo = periodo;
    }

    public PacienteService getPacienteService() {
        return pacienteService;
    }

    public void setPacienteService(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public MedicoService getMedicoService() {
        return medicoService;
    }

    public void setMedicoService(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public List<Integer> getDias() {
        return dias;
    }

    public void setDias(List<Integer> dias) {
        this.dias = dias;
    }

    public String getDiasJson() {
        return diasJson;
    }

    public void setDiasJson(String diasJson) {
        this.diasJson = diasJson;
    }
    
    
    
    @PostConstruct
    public void init(){
        pacientes = pacienteService.getPacientesCadastrados();
        
        medicos = medicoService.getMedicosCadastrados();
        
        horarios = medicoService.getHorarios();
    }

    
    public void carregarHorarios(Medico m){
        System.out.println(m);
        horarios = m.getHorarios();
        dias = m.pegarDiasLivres();
        System.out.println(dias);
        diasJson = pegarDiasLivres();
        System.out.println(diasJson);
    }
    
    public boolean verificarSeDataEPossivel(Date data){
        
        return medico.verificarSeDataEPossivel(data);
    }
    
    public boolean habilitarData(){
        return medico == null;
    }
    
    public boolean habilitarPeriodo(){
        boolean b = dataPrevista == null;
        return b;
    }
    
    public void carregarDias(Medico m){
        dias = m.pegarDiasLivres();
    }
    
    public String pegarDiasLivres(){
        Gson jsonParser = new Gson();
        
        String jsonDias = jsonParser.toJson(dias);
        
        System.out.println(dias);
        
        return jsonDias;
    }
    
    @Override
    public Agendamento build() {
        return new Agendamento(id, dataPrevista, paciente, medico, periodo, 
                realizada);
    }
    
    
}
