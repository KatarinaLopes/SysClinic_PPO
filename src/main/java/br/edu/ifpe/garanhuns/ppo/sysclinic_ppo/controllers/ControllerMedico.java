/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.controllers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoFuncionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoMedico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.DaoGenerico;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Katarina
 */
@ManagedBean
@SessionScoped
public class ControllerMedico {
    
    private final DaoGenerico daoMedico = new DaoMedico();
    
    @ManagedProperty("#{'medicosRegistrados'}")
    private List medicosRegistrados;

    public List getMedicosRegistrados() {
        return medicosRegistrados;
    }

    public void setMedicosRegistrados(List medicosRegistrados) {
        this.medicosRegistrados = medicosRegistrados;
    }
    
    
    public String cadastrar(Medico c) {
        daoMedico.persistir(c);
        
        return "apresentar_medicos.xhtml";
    }
    
    
    public Medico recuperar(Integer i) {
        return (Medico) daoMedico.recuperar(i);
    }

    
    public void atualizar(Medico c) {
        daoMedico.atualizar(c);
    }
    
    public void deletar(Medico c){
        daoMedico.deletar(c);
    }
    
    @PostConstruct
    public void recuperarTodos(){
        medicosRegistrados = daoMedico.recuperarTodos();
    }
}
