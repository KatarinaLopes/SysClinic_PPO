/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.jsf.services;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Horario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoMedico;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Katarina
 */
@ManagedBean(name = "medicoService", eager = true)
@ApplicationScoped
public class MedicoService {
    
    private List<Medico> medicos;

    private List<Horario> horarios;
    
    public List<Medico> getMedicosCadastrados() {
        return medicos;
    }

    @PostConstruct
    public void setMedicos() {
        medicos = new DaoMedico().recuperarTodos();
    }
    
    public List<Horario> carregarHorarios(Medico m, Date data){
        System.out.println(data + "a" + m);
        horarios = m.pegarHorariosLivres(data);
        return horarios;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }
    
    
}
