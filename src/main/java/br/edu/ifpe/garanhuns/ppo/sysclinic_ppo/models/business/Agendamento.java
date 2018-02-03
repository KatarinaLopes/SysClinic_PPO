/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * EN-US
 * Class that represents the medical appointments
 * 
 * PT-BR
 * Classe que representa os agendamentos médicos
 * @author Katarina
 */
@Entity
public class Agendamento implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Temporal(TemporalType.DATE)
    private Date dataPrevista;
    @OneToOne
    private Paciente paciente;
    @OneToOne
    private Medico medico;
    @Temporal(TemporalType.TIME)
    private Date periodo;
    private boolean realizada = false;

    @Deprecated
    public Agendamento() {
    }

    public Agendamento(int id, Date dataPrevista, Paciente paciente, 
            Medico medico, Date periodo, boolean realizada) {
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

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.dataPrevista);
        hash = 53 * hash + Objects.hashCode(this.paciente);
        hash = 53 * hash + Objects.hashCode(this.periodo);
        hash = 53 * hash + (this.realizada ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Agendamento other = (Agendamento) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.realizada != other.realizada) {
            return false;
        }
        if (!Objects.equals(this.dataPrevista, other.dataPrevista)) {
            return false;
        }
        if (!Objects.equals(this.paciente, other.paciente)) {
            return false;
        }
        if (!Objects.equals(this.medico, other.medico)) {
            return false;
        }
        if (!Objects.equals(this.periodo, other.periodo)) {
            return false;
        }
        return true;
    }

    
 
    
}
