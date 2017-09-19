/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.DaoGenerico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.hibernateutil.HibernateUtil;
import java.util.List;

/**
 *
 * @author Katarina
 */
public class DaoMedico implements DaoGenerico<Medico, Integer>{

    @Override
    public void persistir(Medico c) {
        HibernateUtil.getInstance().persist(c);
    }

    @Override
    public Medico recuperar(Integer i) {
        return (Medico) HibernateUtil.getInstance().
                recover("from Medico where id = " + i).get(0);
    }

    @Override
    public Medico recuperarPorAtributo(String atributo, String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizar(Medico c) {
        HibernateUtil.getInstance().update(c);
    }

    @Override
    public void deletar(Medico c) {
        HibernateUtil.getInstance().delete(c);
    }

    @Override
    public List recuperarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
