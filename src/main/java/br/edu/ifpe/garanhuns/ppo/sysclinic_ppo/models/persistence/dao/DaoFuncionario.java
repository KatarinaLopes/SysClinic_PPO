/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Funcionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.DaoGenerico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.hibernateutil.HibernateUtil;
import java.util.List;

/**
 *
 * @author Katarina
 */
public class DaoFuncionario implements DaoGenerico<Funcionario, Integer> {

    @Override
    public void persistir(Funcionario c) {
        HibernateUtil.getInstance().persist(c);
    }

    @Override
    public Funcionario recuperar(Integer i) {
        try {
            return (Funcionario) HibernateUtil.getInstance().
                    recover("from Funcionario where id = " + i).get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public Funcionario recuperarPorAtributo(String atributo, Object value) {
        try {
            if (value.getClass() == String.class) {
                return (Funcionario) HibernateUtil.getInstance().
                        recover("from Funcionario where " + atributo + " = '" + value + "'").get(0);
            }

            return (Funcionario) HibernateUtil.getInstance().
                    recover("from Funcionario where " + atributo + " = " + value).
                    get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public void atualizar(Funcionario c) {
        HibernateUtil.getInstance().update(c);
    }

    @Override
    public void deletar(Funcionario c) {
        HibernateUtil.getInstance().delete(c);
    }

    @Override
    public List recuperarTodos() {
        try {
            return HibernateUtil.getInstance().recover("from Funcionario");
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public boolean podeExcluirOuAlterar(Funcionario f){
        
        int qtdeFuncionario = 0;
        int qtdeAdministrador = 0;
        
        List<Funcionario> funcionarios = recuperarTodos();
        
        for (Funcionario funcionario : funcionarios) {
            if(funcionario.isAdministrador()){
                qtdeAdministrador++;
            }else{
                qtdeFuncionario++;
            }
        }
        
        return f.isAdministrador() ? qtdeAdministrador > 1 : 
                qtdeFuncionario > 1;
    }
    
    
}
