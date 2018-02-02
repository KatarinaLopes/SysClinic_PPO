/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.exception;

/**
 *
 * @author Katarina
 */
public class InternalException extends Exception{
    public static final String ERRO_INTERNO = "Erro interno. Recarregue a "
            + "página e tente novamente";

    public InternalException() {
    }

    public InternalException(String message) {
        super(message);
    }
    
    
}
