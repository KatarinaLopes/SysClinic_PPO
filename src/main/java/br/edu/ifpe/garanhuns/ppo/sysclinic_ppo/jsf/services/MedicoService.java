/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.jsf.services;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoMedico;
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

    public List<Medico> getMedicosCadastrados() {
        return medicos;
    }

    @PostConstruct
    public void setMedicos() {
        medicos = new DaoMedico().recuperarTodos();
    }
    
    
    
}
