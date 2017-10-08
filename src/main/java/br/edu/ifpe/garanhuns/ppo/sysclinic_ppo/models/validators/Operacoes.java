/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.validators;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Katarina
 */
public class Operacoes {
   
    public static String criptografarSenha(String senha) 
            throws NoSuchAlgorithmException, UnsupportedEncodingException{
        MessageDigest mess = MessageDigest.getInstance("SHA-256");
        byte[] criptografados = mess.digest(senha.getBytes("UTF-8"));
        
        StringBuilder string = new StringBuilder();
        
        for (byte criptografado : criptografados) {
            
            string.append(String.format("%02X", 0xFF & criptografado));
            
        }
        
        return string.toString();
    }
    
    /*public static String validarPaciente(Paciente p){
        if(!Validacoes.validarNome(p.getNome())){
            return "Nome inválido";
        }
        if(!Validacoes.validarSexo(p.getSexo())){
            
        }
        
        return null;
    }*/
    
    public static void main(String[] args) throws NoSuchAlgorithmException, 
            UnsupportedEncodingException {
        System.out.println(Operacoes.criptografarSenha("1"));
    }
}
