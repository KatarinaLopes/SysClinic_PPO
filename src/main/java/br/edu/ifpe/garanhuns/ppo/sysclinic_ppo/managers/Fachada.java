/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.managers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agenda;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoAgenda;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoFuncionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoMedico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoPaciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.DaoGenerico;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author Katarina
 */ 
public class Fachada {
    
    private DaoGenerico daoPaciente;
    
    private DaoGenerico daoMedico;
    
    private DaoGenerico daoFuncionario;
    
    private DaoAgenda daoAgenda;
    
    private PacienteManager pacienteManager;
    
    private MedicoManager medicoManager;
    
    private FuncionarioManager funcionarioManager;
    
    private AgendaManager agendaManager;
    
    private static Fachada myself;
    
    private Fachada(){
        daoPaciente = new DaoPaciente();
        daoMedico = new DaoMedico();
        daoFuncionario = new DaoFuncionario();
        daoAgenda = new DaoAgenda();
        
        pacienteManager = new PacienteManager((DaoPaciente) daoPaciente);
        medicoManager = new MedicoManager((DaoMedico) daoMedico);
        funcionarioManager = new FuncionarioManager((DaoFuncionario) 
                daoFuncionario);
        agendaManager = new AgendaManager(daoAgenda);
    }
    
    public static Fachada getInstance(){
        if(myself == null)
            myself = new Fachada();
        return myself;
    }

    public PacienteManager getPacienteManager() {
        return pacienteManager;
    }

    public MedicoManager getMedicoManager() {
        return medicoManager;
    }

    public FuncionarioManager getFuncionarioManager() {
        return funcionarioManager;
    }

    public AgendaManager getAgendaManager() {
        return agendaManager;
    }

    public DaoGenerico getDaoPaciente() {
        return daoPaciente;
    }

    public void setDaoPaciente(DaoGenerico daoPaciente) {
        this.daoPaciente = daoPaciente;
    }

    public DaoGenerico getDaoMedico() {
        return daoMedico;
    }

    public void setDaoMedico(DaoGenerico daoMedico) {
        this.daoMedico = daoMedico;
    }

    public DaoGenerico getDaoFuncionario() {
        return daoFuncionario;
    }

    public void setDaoFuncionario(DaoGenerico daoFuncionario) {
        this.daoFuncionario = daoFuncionario;
    }

    public DaoAgenda getDaoAgenda() {
        return daoAgenda;
    }

    public void setDaoAgenda(DaoAgenda daoAgenda) {
        this.daoAgenda = daoAgenda;
    }

        
}