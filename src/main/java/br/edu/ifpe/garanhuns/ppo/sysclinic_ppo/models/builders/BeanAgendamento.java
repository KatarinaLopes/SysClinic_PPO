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
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoMedico;
import com.google.gson.Gson;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Katarina
 */
@ManagedBean
@RequestScoped
public class BeanAgendamento implements BuilderGenerico<Agendamento> {
    private Paciente paciente= new Paciente();
    private Medico medico = new Medico();
    private Date dataPrevista = new Date();
    private Date periodoInicial = new Date();
    
    private Paciente pacienteSelecionado;

    public Paciente getPaciente() {
        //System.out.println(paciente);
        
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
        
        pacienteSelecionado = this.paciente;
        
        System.out.println(paciente + " d " + this.paciente);
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Date getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(Date dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public Date getPeriodoInicial() {
        return periodoInicial;
    }

    public void setPeriodoInicial(Date periodoInicial) {
        this.periodoInicial = periodoInicial;
    }

    @Override
    public Agendamento build() {
        /*HttpSession sess = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(true);
        
        Paciente selecionado = (Paciente) sess.getAttribute("paciente");*/
        
        System.out.println(pacienteSelecionado);
        
        return new Agendamento(0, dataPrevista, pacienteSelecionado, medico, 
                periodoInicial, false);
    }
    
}
