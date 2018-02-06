/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.validators;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Funcionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Katarina
 */
public class Operacoes {
    
    public static String criptografarSenha(String senha)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest mess = MessageDigest.getInstance("MD5");
        byte[] criptografados = mess.digest(senha.getBytes("UTF-8"));

        StringBuilder string = new StringBuilder();

        for (byte criptografado : criptografados) {

            string.append(String.format("%02X", 0xFF & criptografado));

        }

        return string.toString();
    }


}
