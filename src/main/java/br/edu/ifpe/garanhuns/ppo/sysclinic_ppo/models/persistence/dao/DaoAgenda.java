/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agenda;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.utils.HibernateUtil;

/**
 *
 * @author Katarina
 */
public class DaoAgenda  {
    
    
    public Agenda recuperar(){
        return (Agenda) HibernateUtil.getInstance().recover("from Agenda").
                get(0);
    }
    
    public void atualizar(){
        Agenda agenda = recuperar();
        HibernateUtil.getInstance().update(agenda);
    }
}
