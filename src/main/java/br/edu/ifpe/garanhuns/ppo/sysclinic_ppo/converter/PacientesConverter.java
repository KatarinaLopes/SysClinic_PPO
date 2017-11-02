/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.converter;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.jsf.services.PacienteService;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Katarina
 */

@FacesConverter("pacientesConverter")
public class PacientesConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && value.trim().length() > 0){
            PacienteService service = (PacienteService) context.
                    getExternalContext().getApplicationMap().
                    get("pacienteService");
            System.out.println(Integer.parseInt(value));
            return service.getPacientesCadastrados().
                    get(Integer.parseInt(value));
        }
        
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null){
            return String.valueOf(((Paciente) value).getId());
        }
        return null;
    }
    
}
