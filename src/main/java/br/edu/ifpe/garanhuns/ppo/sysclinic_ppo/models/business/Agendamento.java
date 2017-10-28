/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Katarina
 */
@Entity
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Temporal(TemporalType.DATE)
    private Date dataPrevista;
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    private Paciente paciente;
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    private Medico medico;
    private boolean realizada;

    @Deprecated
    public Agendamento() {
    }

    public Agendamento(int id, Date dataPrevista, Paciente paciente, Medico medico, boolean realizada) {
        this.id = id;
        this.dataPrevista = dataPrevista;
        this.paciente = paciente;
        this.medico = medico;
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

    public boolean isRealizada() {
        return realizada;
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }
    
    
    
    
    
    
}
