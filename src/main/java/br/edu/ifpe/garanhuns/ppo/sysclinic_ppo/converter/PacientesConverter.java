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

/**
 *
 * @author Katarina
 */

@FacesConverter("pacientesConverter")
public class PacientesConverter implements Converter{
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println(value + " rnhf " + component);
        
        if(value != null && value.trim().length() > 0){
            
            System.out.println(value);
            PacienteService service = (PacienteService) context.
                    getExternalContext().getApplicationMap().
                    get("pacienteService");
            
            List<Paciente> pacientes = new ArrayList();
            
            pacientes.add(new Paciente());
            pacientes.add(new Paciente());
            
            Paciente selecionado = null;
            
            //System.out.println(service.getPacientesCadastrados().get(0).getId());
            for (Paciente pacientesCadastrado : pacientes) {
                if(pacientesCadastrado.getId() == Integer.parseInt(value)){
                    System.out.println(pacientesCadastrado);
                    selecionado = pacientesCadastrado;
                }        
            }
            
            /*Paciente p = service.getPacientesCadastrados().get(0);
            
            return p;*/
            
            return selecionado;
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
