/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.converter;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.jsf.services.MedicoService;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Horario;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Katarina
 */
@FacesConverter("horariosConverter")
public class HorarioConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && !value.isEmpty()){
            MedicoService service = (MedicoService) context.
                    getExternalContext().
                    getApplicationMap().get("medicoService");
            
            
            for (int i = 0; i < service.getHorarios().size(); i++) {
               int posicao = service.getHorarios().indexOf(value);
               
               if(posicao != -1){
                   return service.getHorarios().get(posicao);
               }
               /*if(service.getHorarios().get(Integer.parseInt(value)) != null){
                   return service.getHorarios().get(Integer.parseInt(value));
               }*/
            }
        }
            return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
       if(value != null){
           return String.valueOf(((Horario) value).getHorarioInicial());
       }
       
       return null;
    }
    
}
