/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.
        DaoGenerico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.utils.HibernateUtil;
import java.util.List;

/**
 *
 * @author Katarina
 */
public class DaoMedico implements DaoGenerico<Medico, Integer> {

    @Override
    public void persistir(Medico c) {
        HibernateUtil.getInstance().persist(c);
    }

    @Override
    public Medico recuperar(Integer i) {
        try {
            return (Medico) HibernateUtil.getInstance().
                    recover("from Medico where id = " + i).get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /*@Override
    public Medico recuperarPorAtributo(String atributo, String value) {
        return null;
    }*/
    public Medico recuperarPorAtributo(String atributo, Object value) {
        try {

            if (value.getClass() == String.class) {

                return (Medico) HibernateUtil.getInstance().
                        recover("from Medico where " + atributo + " = '" + value + "'").get(0);
            }
            return (Medico) HibernateUtil.getInstance().
                    recover("from Medico where " + atributo + " = " + value + "").get(0);

        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public void atualizar(Medico c) {
        HibernateUtil.getInstance().update(c);
    }

    @Override
    public void deletar(Medico c) {
        HibernateUtil.getInstance().delete(c);
    }
    
    public void deletarAgendamento(Agendamento a){
        HibernateUtil.getInstance().delete(a);
    }

    @Override
    public List recuperarTodos() {
        try {
            return HibernateUtil.getInstance().recover("from Medico");
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
    
    public boolean podeExcluirMedico(Medico m){
        List<Medico> medicos = recuperarTodos();
        
        return medicos.size() > 1;
    }

}
