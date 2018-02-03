/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.managers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoAgenda;

/**
 *
 * @author Katarina
 */
public class AgendaManager {
    
    private DaoAgenda daoAgenda;
    
    public AgendaManager (DaoAgenda daoAgenda){
        this.daoAgenda = daoAgenda;
    }
    
    public void atualizar(){
        daoAgenda.atualizar();
    }
    
    /**
     *
     * 
     * @param agendamento 
     */
    public void marcarAgendamento(Agendamento agendamento) {
        if(agendamento == null){
            throw new IllegalArgumentException("Agendamento inválido, "
                    + "recarregue a página e tente novamente");
        }
        
    }
}
