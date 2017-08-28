/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.DaoGenerico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.hibernateutil.HibernateUtil;
import java.util.List;

/**
 *
 * @author Katarina
 */
public class DaoPaciente implements DaoGenerico<Paciente, Integer> {

    @Override
    public void persistir(Paciente c) {
        HibernateUtil.getInstance().persist(c);
    }

    @Override
    public Paciente recuperar(Integer i) {
        Paciente p;

        try {
            p = (Paciente) HibernateUtil.getInstance().
                    recover("from Paciente where id=" + i).get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }

        return p;
    }
    
    @Override
    public Paciente recuperarPorAtributo(String atributo, String value){
        
        return (Paciente) HibernateUtil.getInstance().
                recover("from Paciente where cpf=" + "'" + value + "'").get(0);
    }

    @Override
    public void atualizar(Paciente c) {
        HibernateUtil.getInstance().update(c);
    }

    @Override
    public void deletar(Paciente c) {
        HibernateUtil.getInstance().delete(c);
    }

    @Override
    public List recuperarTodos() {
        List pacientes;

        try {
            pacientes = HibernateUtil.getInstance().recover("from Paciente");
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
        
        return pacientes;

    }
}
