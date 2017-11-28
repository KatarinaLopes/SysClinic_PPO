/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agenda;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.DaoGenerico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.hibernateutil.HibernateUtil;
import java.util.List;

/**
 *
 * @author Katarina
 */
public class DaoAgenda implements DaoGenerico<Agenda, Integer>{

    @Override
    public void persistir(Agenda c) {
        HibernateUtil.getInstance().persist(c);
    }

    @Override
    public Agenda recuperar(Integer i) {
        return (Agenda) HibernateUtil.getInstance().
                recover("from Agenda where id = " + i).get(0);
    }

    @Override
    public Agenda recuperarPorAtributo(String atributo, Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizar(Agenda c) {
        HibernateUtil.getInstance().update(c);
    }

    @Override
    public void deletar(Agenda c) {
        HibernateUtil.getInstance().delete(c);
    }

    @Override
    public List recuperarTodos() {
        return HibernateUtil.getInstance().recover("from Agenda");
    }
    
}
