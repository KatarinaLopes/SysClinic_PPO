/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.dia;
        hash = 79 * hash + Objects.hashCode(this.horarioInicial);
        hash = 79 * hash + Objects.hashCode(this.horarioFinal);
        hash = 79 * hash + this.limiteDeAgendamentos;
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
        final Horario other = (Horario) obj;
        if (this.dia != other.dia) {
            return false;
        }
        if (this.limiteDeAgendamentos != other.limiteDeAgendamentos) {
            return false;
        }
        if (!Objects.equals(this.horarioInicial, other.horarioInicial)) {
            return false;
        }
        if (!Objects.equals(this.horarioFinal, other.horarioFinal)) {
            return false;
        }
        return true;
    }
      
    
}
