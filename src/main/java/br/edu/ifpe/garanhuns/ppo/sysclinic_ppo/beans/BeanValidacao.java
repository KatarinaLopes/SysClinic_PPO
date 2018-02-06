/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Katarina
 */
@ManagedBean
@RequestScoped
public class BeanValidacao implements Serializable{
    
    private final String regexNome = "[aA-zZàÀ-úÚ\\s]{1,70}";
    
    private final String mensagemNome = "Nome não aceito";
    
    private final String regexSexo = "[F|M]{1}";
    
    private final String regexDate = "(0[1-9]|(1|2)[0-9]|3[0-1])\\(0[1-9]|1[0-2])"
            + "\\(2|1)[0-9]{3}";
    
    private final String mensagemData = "Data não aceita";
    
    private final String regexTelefone = "\\([0-9]{2}\\)[2-9][0-9]{3,4}\\-[0-9]{4}";
    
    private final String mensagemTelefone = "Telefone não aceito";
    
    private final String regexEmail = "[a-zA-Z0-9\\s-_.]{1,}@[a-zA-Z0-9\\s-_.]{1,}"
            + "[a-zA-Z0-9]{1,}";
    
    private final String mensagemEmail = "Email inválido";
    
    private final String mensagemCpf = "CPF inválido";
    
    private final String regexCrm = "CRM\\/((S[P,C,E])|BA|(M[G,A,S,T])|(A[M,L,C,P])|(P[R,A,E,B,I])|DF|(R[S,J,N,R,O])|GO|ES|CE|TO)\\s[0-9]{4,}";
    
    private final String mensagemCrm = "CRM inválido";
    
    private final String regexMatricula = "[0-9]{1,}";
    
    private final String mensagemMatricula = "Matrícula inválida";

    public String getRegexNome() {
        return regexNome;
    }

    public String getMensagemNome() {
        return mensagemNome;
    }

    public String getRegexSexo() {
        return regexSexo;
    }

    public String getRegexDate() {
        return regexDate;
    }

    public String getMensagemData() {
        return mensagemData;
    }

    public String getRegexTelefone() {
        return regexTelefone;
    }

    public String getMensagemTelefone() {
        return mensagemTelefone;
    }

    public String getRegexEmail() {
        return regexEmail;
    }

    public String getMensagemEmail() {
        return mensagemEmail;
    }

    public String getMensagemCpf() {
        return mensagemCpf;
    }

    public String getRegexCrm() {
        return regexCrm;
    }

    public String getMensagemCrm() {
        return mensagemCrm;
    }

    public String getRegexMatricula() {
        return regexMatricula;
    }

    public String getMensagemMatricula() {
        return mensagemMatricula;
    }
    
   
    
}

