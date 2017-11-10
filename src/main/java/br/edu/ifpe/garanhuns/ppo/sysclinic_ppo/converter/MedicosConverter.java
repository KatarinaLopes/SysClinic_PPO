/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.converter;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.jsf.services.MedicoService;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Katarina
 */
@FacesConverter("medicosConverter")
public class MedicosConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("1");
        if (value != null) {
            System.out.println("2");
            MedicoService service = (MedicoService) context.
                    getExternalContext().getApplicationMap().
                    get("medicoService");
            //System.out.println(value);
            for (Medico medicosCadastrado : service.getMedicosCadastrados()) {
                if(medicosCadastrado.getId() == Integer.parseInt(value)){
                    return medicosCadastrado;
                }
            }
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null){
            return String.valueOf(((Medico) value).getId());
        }
        return null;
    }
}