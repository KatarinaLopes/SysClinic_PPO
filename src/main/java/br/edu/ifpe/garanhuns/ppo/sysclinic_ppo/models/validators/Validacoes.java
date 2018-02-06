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
}
