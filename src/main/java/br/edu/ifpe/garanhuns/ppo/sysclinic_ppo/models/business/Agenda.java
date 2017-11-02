/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Katarina
 */
@Entity
public class Agenda {
    @Id
    private int id;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Agendamento> agendamentos;

    @Deprecated
    public Agenda() {
    }
    
    public Agenda(int id, List<Agendamento> agendamentos) {
        this.id = id;
        this.agendamentos = agendamentos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }
    
    
}
