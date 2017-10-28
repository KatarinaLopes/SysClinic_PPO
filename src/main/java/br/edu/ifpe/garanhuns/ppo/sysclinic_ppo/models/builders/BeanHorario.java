/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.builders;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Horario;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Katarina
 */
@ManagedBean
@RequestScoped
public class BeanHorario implements BuilderGenerico<Horario>{
    private String diaDaSemana;
    private Date horarioInicial;
    private Date horarioFinal;

    public String getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(String diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public Date getHorarioInicial() {
        return horarioInicial;
    }

    public void setHorarioInicial(Date horarioInicial) {
        this.horarioInicial = horarioInicial;
    }

    public Date getHorarioFinal() {
        return horarioFinal;
    }

    public void setHorarioFinal(Date horarioFinal) {
        this.horarioFinal = horarioFinal;
    }

    @Override
    public Horario build() {
        return new Horario(diaDaSemana, horarioInicial, horarioFinal, 0);
    }
    
    
}
