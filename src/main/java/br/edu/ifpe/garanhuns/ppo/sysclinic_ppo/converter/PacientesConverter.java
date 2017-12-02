/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.converter;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.jsf.services.PacienteService;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Katarina
 */

@FacesConverter("pacientesConverter")
public class PacientesConverter implements Converter{
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        //System.out.println(value);
        
        if(value != null && value.trim().length() > 0){
            
            HttpSession s = (HttpSession) FacesContext.getCurrentInstance().
                    getExternalContext().getSession(true);
            
            List<Paciente> pacientes = (List<Paciente>) 
                    s.getAttribute("pacientesCadastrados");
            
            for (Paciente paciente : pacientes) {
                if(paciente.getId() == Integer.parseInt(value)){
                    return paciente;
                }
            }
            
        }
        
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        //System.out.println(value);
        
        if(value != null){
            return String.valueOf(((Paciente) value).getId());
        }
        return null;
    }
    
    
}
