/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Katarina
 */
@Entity
public class Agendamento implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Temporal(TemporalType.DATE)
    private Date dataPrevista;
    @OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;
    @OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;
    
    @Temporal(TemporalType.TIME)
    private Date periodo;
    private boolean realizada;

    @Deprecated
    public Agendamento() {
    }

    public Agendamento(int id, Date dataPrevista, Paciente paciente, Medico medico, Date periodo, boolean realizada) {
        this.id = id;
        this.dataPrevista = dataPrevista;
        this.paciente = paciente;
        this.medico = medico;
        this.periodo = periodo;
        this.realizada = realizada;
    }

    public int getId() {
        return id;
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

    public Date getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Date periodo) {
        this.periodo = periodo;
    }
    
    public boolean isRealizada() {
        return realizada;
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }
    
}
