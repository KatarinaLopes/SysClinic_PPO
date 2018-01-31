/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.managers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoMedico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.DaoGenerico;
import java.util.List;

/**
 *
 * @author Katarina
 */
public class MedicoManager {
    
    private DaoGenerico daoMedicos;
    
    public MedicoManager(){
        daoMedicos = new DaoMedico();
    }
    
    public void validar(Medico medico){
        Medico existente = (Medico) daoMedicos.
                recuperarPorAtributo("matricula", medico.getMatricula());
        
        if(existente != null){
            throw new IllegalArgumentException("Este médico já está "
                + "cadastrado, verifique se a matrícula está "
                + "correta");
           
        }
    
        existente = (Medico) daoMedicos.
                recuperarPorAtributo("conselho", medico.getConselho());
    
        if(existente != null){
            throw new IllegalArgumentException("Este número de registro de "
                    + "conselho já está cadastrado");
        }
        
       
    }
    
    public void cadastrar(Medico medico, String conselho, int numeroConselho){
        medico.setConselho(conselho + "/" + numeroConselho);
        validar(medico);
        daoMedicos.persistir(medico);
    }
    
    public List<Medico> recuperarTodos(){
        return daoMedicos.recuperarTodos();
    }
}
