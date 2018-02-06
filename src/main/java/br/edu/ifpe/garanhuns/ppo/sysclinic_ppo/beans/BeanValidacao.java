/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.beans;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.validators.Validacoes;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Katarina
 */
@ManagedBean
@ViewScoped
public class BeanValidacao {
    
    private String regexNome = "[aA-zZàÀ-úÚ\\s]{1,70}";
    
    private String mensagemNome = "Nome não aceito";
    
    private String regexSexo = "[F|M]{1}";
    
    private String regexDate = "(0[1-9]|(1|2)[0-9]|3[0-1])\\(0[1-9]|1[0-2])"
            + "\\(2|1)[0-9]{3}";
    
    private String mensagemData = "Data não aceita";
    
    private String regexTelefone = "\\([0-9]{2}\\)[2-9][0-9]{3,4}\\-[0-9]{4}";
    
    private String mensagemTelefone = "Telefone não aceito";
    
    private String regexEmail = "[a-zA-Z0-9\\s-_.]{1,}@[a-zA-Z0-9\\s-_.]{1,}"
            + "[a-zA-Z0-9]{1,}";
    
    private String mensagemEmail = "Email inválido";
    
    private String mensagemCpf = "CPF inválido";
    
    public String getRegexNome() {
        return regexNome;
    }

    public void setRegexNome(String regexNome) {
        this.regexNome = regexNome;
    }

    public String getRegexSexo() {
        return regexSexo;
    }

    public void setRegexSexo(String regexSexo) {
        this.regexSexo = regexSexo;
    }

    public String getRegexDate() {
        return regexDate;
    }

    public void setRegexDate(String regexDate) {
        this.regexDate = regexDate;
    }

    public String getRegexTelefone() {
        return regexTelefone;
    }

    public void setRegexTelefone(String regexTelefone) {
        this.regexTelefone = regexTelefone;
    }

    public String getMensagemTelefone() {
        return mensagemTelefone;
    }

    public void setMensagemTelefone(String mensagemTelefone) {
        this.mensagemTelefone = mensagemTelefone;
    }

    public String getRegexEmail() {
        return regexEmail;
    }

    public void setRegexEmail(String regexEmail) {
        this.regexEmail = regexEmail;
    }

    public String getMensagemEmail() {
        return mensagemEmail;
    }

    public void setMensagemEmail(String mensagemEmail) {
        this.mensagemEmail = mensagemEmail;
    }
    
    public String getMensagemNome() {
        return mensagemNome;
    }

    public void setMensagemNome(String mensagemNome) {
        this.mensagemNome = mensagemNome;
    }

    public String getMensagemData() {
        return mensagemData;
    }

    public void setMensagemData(String mensagemData) {
        this.mensagemData = mensagemData;
    }

    public String getMensagemCpf() {
        return mensagemCpf;
    }

    public void setMensagemCpf(String mensagemCpf) {
        this.mensagemCpf = mensagemCpf;
    }
    
    public void validarCPF(String cpf){
        if(!Validacoes.validarCpf(cpf)){
            throw new ValidatorException(new FacesMessage("CPF inválido"));
        }
    }
    
    
}
