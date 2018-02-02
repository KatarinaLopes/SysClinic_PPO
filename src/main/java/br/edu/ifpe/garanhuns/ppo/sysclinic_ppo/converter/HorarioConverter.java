/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.converter;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.controllers.ControllerMedico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Horario;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Katarina
 */
@FacesConverter("horariosConverter")
public class HorarioConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, 
            String value) {
        if (value != null && value.trim().length() > 0) {
            ControllerMedico ctrlMedico = (ControllerMedico) context.
                    getExternalContext().getSessionMap().
                    get("controllerMedico");

            List<Horario> horarios = ctrlMedico.getHorariosLivres();
            
            for (Horario horario : horarios) {
                if(String.valueOf(horario.getHorarioInicial()).equals(value)){
                    return horario;
                }
            }
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, 
            Object value) {
        if (value != null) {
            
            return String.valueOf(((Horario) value).getHorarioInicial());
        }
        return null;
    }

}
