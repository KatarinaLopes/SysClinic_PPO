/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.exception;

/**
 *
 * @author Katarina
 */
public class DaoException extends Exception{
    public static final String CPF_INEXISTENTE = "Este CPF não está cadastrado";
    public static final String LOGIN_NAO_EXISTE = "Este login não está "
            + "cadastrado";
    public static final String SENHA_NAO_CORRESPONDE = "A senha está "
            + "incorreta";

    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }
    
}
