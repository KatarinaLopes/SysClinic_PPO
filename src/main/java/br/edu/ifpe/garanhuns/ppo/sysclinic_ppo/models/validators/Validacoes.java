/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.validators;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import java.util.Date;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Katarina
 */
public class Validacoes {
    
    /*private Validacoes(){
        
    }*/

    public static boolean validarCpf(String cpf) {

        String cpfNovo;

        try {
            cpfNovo = cpf.replace(".", "").replace("-", "");
        } catch (NullPointerException ex) {
            return false;
        }

        //System.err.println(cpfNovo);
        if (cpfNovo.length() != 11) {
            return false;
        }
        if (cpfNovo.matches("[0-9]{0,}[a-zA-ZÀ-ú]{1,}")) {
            return false;
        }

        char[] cCpf = cpfNovo.toCharArray();
        int[] iCpf = new int[11];

        for (int i = 0; i < cCpf.length; i++) {
            iCpf[i] = Integer.parseInt(String.valueOf(cCpf[i]));
        }

        int fator1 = 10, fator2 = 11, resultado1 = 0, resultado2 = 0;
        for (int i = 0; i < 10; i++) {
            int numero1 = iCpf[i];
            int numero2 = iCpf[i];

            resultado1 += (numero1 * fator1);
            resultado2 += (numero2 * fator2);

            fator1--;
            fator2--;
        }

        resultado1 -= iCpf[9];

        int digito1 = 11 - (resultado1 % 11);
        int digito2 = 11 - (resultado2 % 11);

        digito1 = digito1 < 10 ? digito1 : 0;
        digito2 = digito2 < 10 ? digito2 : 0;

        return digito1 == iCpf[9] && digito2 == iCpf[10];
    }

    public static boolean validarSenhas(String senha, String confirmacao) {
        try {
            return senha.equals(confirmacao);
        } catch (NullPointerException ex) {
            return false;
        }
    }

    public static boolean validarTelefone(String telefone) {

        try {
            return telefone.
                    matches("^\\([0-9]{2}\\)[2-9][0-9]{3,4}\\-[0-9]{4}");
        } catch (NullPointerException ex) {
            return false;
        }

    }

    //Ainda não foi testado
    public static boolean validarNome(String nome) {
        try {
            if (nome.equals("") || nome.equals(" ")) {
                return false;
            }
            //Regex da internet

            return nome.matches("[a-zA-Z\\sà-ùÀ-Ù]{0,}");
        } catch (NullPointerException ex) {
            return false;
        }
    }

    public static boolean validarSexo(String sexo) {

        try {
            return sexo.equals("F") || sexo.equals("M");
        } catch (NullPointerException ex) {
            return false;
        }
    }

    public static boolean validarEmail(String email) {
        try {
            return email.matches("[a-zA-Z0-9\\s-_.]{1,}@[a-zA-Z0-9\\s-_.].["
                    + "a-zA-Z0-9.]{1,}");
        } catch (NullPointerException ex) {
            return false;
        }
    }

    /*public static boolean validarCrm(String crm){
        try{
            return crm.matches("[0-9]{3,9}/CRM-[AC,AL,AM,AP,BA,CE,DF,ES,GO,MA,"
                    + "MG,MS,MT,PA,PB,PE,PI,PR,RJ,RN,RO,RR,RS,SC,SE,SP,TO]");
        }catch(NullPointerException ex){
            return false;
        }
    }*/
}
