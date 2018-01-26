package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.managers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Mensagem;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoPaciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.DaoGenerico;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Katarina
 */
public class PacienteManager {

    private DaoGenerico daoPaciente = new DaoPaciente();

    private FeedManager feedManager = new FeedManager();
    
    public void inserirMensagemDeExclusaoNoFeed(HashMap<Paciente, Date> 
            rolPacientes, Medico m){
        feedManager.inserirMensagemDeExclusao(rolPacientes, m);
        
        atualizar(rolPacientes.keySet());
    }
    
    public void inserirMensagemDeAlteracaoDeHorarioNoFeed(
            HashMap<Paciente,Date> pacientes, Date horarioNovo, Medico m){
        feedManager.inserirMensagemDeAtualizacaoDeHorario(pacientes, 
                horarioNovo, m);
        
        atualizar(pacientes.keySet());
    }
    
    public void excluirMensagem(Paciente p, Mensagem m){
        feedManager.excluirMensagem(m, p);
        
        atualizar(p);
    }
    
    public void atualizar(Paciente p){
        daoPaciente.atualizar(p);
    }
    
    public void atualizar(Collection<Paciente> pacientes){
        for (Paciente paciente : pacientes) {
            daoPaciente.atualizar(paciente);
        }
    }
}
