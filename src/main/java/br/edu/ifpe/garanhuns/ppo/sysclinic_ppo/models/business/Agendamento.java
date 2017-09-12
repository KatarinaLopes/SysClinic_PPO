/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
    
    @Column(nullable = false, length = 1)
    private String periodo;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Medico medico;
    
    private boolean realizada;
    
    @Column(nullable = false)
    private String tipo;

    @Deprecated
    public Agendamento() {
    }

    public Agendamento(int id, Date dataPrevista, String periodo, Medico medico, boolean realizada, String tipo) {
        this.id = id;
        this.dataPrevista = dataPrevista;
        this.periodo = periodo;
        this.medico = medico;
        this.realizada = realizada;
        this.tipo = tipo;
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

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }   
    
}
