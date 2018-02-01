/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.builders;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Katarina
 */
@ManagedBean
@ViewScoped
public class BuilderAgendamento {
    private Paciente paciente;
    private Medico medico;
    private Date dataPrevista;
    private Date horarioPrevisto;

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Date getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(Date dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public Date getHorarioPrevisto() {
        return horarioPrevisto;
    }

    public void setHorarioPrevisto(Date horarioPrevisto) {
        this.horarioPrevisto = horarioPrevisto;
    }
    
    public Agendamento build(){
        return new Agendamento(0, dataPrevista, paciente, dataPrevista, false);
    }
}
