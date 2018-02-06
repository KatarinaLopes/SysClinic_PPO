
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.validator;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.validators.Validacoes;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.primefaces.validate.ClientValidator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Katarina
 */
@FacesValidator("validadorCPF")
public class ValidadorCpf implements Validator, ClientValidator{
    
    @Override
    public void validate(FacesContext context, UIComponent component, 
            Object value) throws ValidatorException {
        
        if(value == null){
            return;
        }
        
        if(!Validacoes.validarCpf(String.valueOf(value))){
            throw new ValidatorException(new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Erro", "CPF inválido"));
        }
    }

    @Override
    public Map<String, Object> getMetadata() {
        return null;
    }

    @Override
    public String getValidatorId() {
        return "validadorCPF";
    }
   
    
}
