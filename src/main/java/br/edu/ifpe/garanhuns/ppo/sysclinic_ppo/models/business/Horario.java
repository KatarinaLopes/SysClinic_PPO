/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Katarina
 */
@Embeddable
public class Horario implements Serializable{   
    @Column(nullable = false)
    private int dia;
   
    @Temporal(TemporalType.TIME)
    private Date horarioInicial;
    @Temporal(TemporalType.TIME)
    private Date horarioFinal;
    
    @Column(nullable = false)
    private int limiteDeAgendamentos;

    @Deprecated
    public Horario() {
    }

    public Horario(int dia, Date horarioInicial, Date horarioFinal, int limiteDeAgendamentos) {
        this.dia = dia;
        this.horarioInicial = horarioInicial;
        this.horarioFinal = horarioFinal;
        this.limiteDeAgendamentos = limiteDeAgendamentos;
    }
  
    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public Date getHorarioInicial() {
        return horarioInicial;
    }

    public void setHorarioInicial(Date horarioInicial) {
        this.horarioInicial = horarioInicial;
    }

    public Date getHorarioFinal() {
        return horarioFinal;
    }

    public void setHorarioFinal(Date horarioFinal) {
        this.horarioFinal = horarioFinal;
    }    

    public int getLimiteDeAgendamentos() {
        return limiteDeAgendamentos;
    }

    public void setLimiteDeAgendamentos(int limiteDeAgendamentos) {
        this.limiteDeAgendamentos = limiteDeAgendamentos;
    }
      
}
