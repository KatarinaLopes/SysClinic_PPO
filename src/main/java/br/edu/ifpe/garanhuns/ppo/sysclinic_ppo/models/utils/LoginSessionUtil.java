/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.utils;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.exception.InternalException;
import javax.faces.context.FacesContext;

/**
 *
 * @author Katarina
 */
public class LoginSessionUtil {
    
    public static void setarLogadoNaSessao(String nome, Object valor, 
            FacesContext fc) throws InternalException{
        
        if(nome == null || nome.isEmpty() || valor == null || fc == null){
            throw new InternalException(InternalException.ERRO_INTERNO);
        }
        
        fc.getExternalContext().getSessionMap().put(nome, valor);
    }
    
    public static void removerLogadoNaSessao(String nome, FacesContext fc) 
            throws InternalException{
        if(nome == null || nome.isEmpty() || fc == null){
            throw new InternalException(InternalException.ERRO_INTERNO);
        }
        
        fc.getExternalContext().getSessionMap().remove(nome);
    }
    
    public static Object retornarLogado(String nome, FacesContext fc) 
            throws InternalException{
        if(nome == null || nome.isEmpty() || fc == null){
            throw new InternalException(InternalException.ERRO_INTERNO);
        }
        
        return fc.getExternalContext().getSessionMap().get(nome);
    }
}
