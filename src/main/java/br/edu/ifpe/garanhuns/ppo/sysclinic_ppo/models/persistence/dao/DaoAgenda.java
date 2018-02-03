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
    
    public void cadastrar(Agenda agenda){
        HibernateUtil.getInstance().persist(agenda);
    }
    
    public Agenda recuperar(int id){
        try {
            return (Agenda) HibernateUtil.getInstance().
                    recover("from Agenda where id = " + id).get(0);
        } catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }
    
    public void atualizar(Agenda agenda){
        HibernateUtil.getInstance().update(agenda);
    }
}
