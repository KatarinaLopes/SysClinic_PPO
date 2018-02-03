/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.controllers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.managers.AgendaManager;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoAgenda;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Katarina
 */
@ManagedBean
@SessionScoped
public class ControllerAgenda implements Serializable{

    private final AgendaManager agendaManager;

    public ControllerAgenda() {
        agendaManager = new AgendaManager(new DaoAgenda());
    }

    public String salvarAgendamento(Agendamento agendamento) {
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage fm;
        String retorno = null;

        try {
            agendaManager.marcarAgendamento(agendamento);
            agendaManager.atualizar();
            fm = new FacesMessage("Sucesso!", "O agendamento foi marcado com "
                    + "sucesso!");
            retorno = "/acoes/agendamentos_pendentes.xhtml?"
                    + "faces-redirect=true";
        } catch (IllegalArgumentException ex) {
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
                    ex.getMessage());
        }

        fc.addMessage(null, fm);
        return retorno;
    }

}
