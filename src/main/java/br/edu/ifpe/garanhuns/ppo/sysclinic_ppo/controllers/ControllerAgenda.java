/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.controllers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agenda;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoAgenda;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.DaoGenerico;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Katarina
 */
@ManagedBean
@SessionScoped
public class ControllerAgenda implements ControllerGenerico<Agenda, Integer> {

    private DaoGenerico agendas = new DaoAgenda();

    @Override
    public void cadastrar(Agenda c) {
        agendas.persistir(c);
    }

    @Override
    public Agenda recuperar(Integer i) {
        return (Agenda) agendas.recuperar(i);
    }

    @Override
    public void atualizar(Agenda c) {
        agendas.atualizar(c);
    }

    public List<Agenda> recuperarTodos() {
        return agendas.recuperarTodos();
    }

    public void incluirAgendamento(Agendamento a) {

        Agenda agenda1 = recuperar(2);
        
        a.setMedico(agenda1.getMedico());

        agenda1.getAgendamentos().add(a);

        atualizar(agenda1);
    }

}
