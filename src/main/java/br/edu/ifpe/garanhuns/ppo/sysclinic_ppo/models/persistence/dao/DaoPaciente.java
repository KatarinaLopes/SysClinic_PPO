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
import org.hibernate.exception.ConstraintViolationException;

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
        // p;

        try {
            return (Paciente) HibernateUtil.getInstance().
                    recover("from Paciente where id=" + i).get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public Paciente recuperarPorAtributo(String atributo, Object value) {
        try {

            if (value.getClass() == String.class) {
                return (Paciente) HibernateUtil.getInstance().
                        recover
        ("from Paciente where " + atributo + "='" + value + "'").get(0);
            }

            return (Paciente) HibernateUtil.getInstance().
                    recover("from Paciente where " + atributo + "=" + value)
                    .get(0);

        } catch (IndexOutOfBoundsException e) {
            return null;
        }
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
