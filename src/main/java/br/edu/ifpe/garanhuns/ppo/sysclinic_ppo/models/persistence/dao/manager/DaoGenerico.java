/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager;

import java.util.List;

/**
 *
 * @author Katarina
 */
public interface DaoGenerico <C, I> {
    
    public abstract void persistir(C c);
    public abstract C recuperar(I i);
    public abstract C recuperarPorAtributo(String atributo, String value);
    public abstract void atualizar(C c);
    public abstract void deletar(C c);
    public abstract List recuperarTodos();
    
}
