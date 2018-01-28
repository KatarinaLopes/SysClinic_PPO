package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.managers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Mensagem;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoPaciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.DaoGenerico;
import java.util.ArrayList;
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

    private static FeedManager feedManager = FeedManager.getInstance();
    
    private static PacienteManager myself = null;
    
    private PacienteManager(){
        
    }
    
    public static PacienteManager getInstance(){
        if(myself == null)
            myself = new PacienteManager();
        return myself;
    }
    
    public void inserirMensagemDeExclusaoNoFeed(List<Agendamento> agendamentos,
            Medico m){
        feedManager.inserirMensagemDeExclusao(agendamentos, m); 
        
        atualizarListaDePacientes();
        //atualizar(rolPacientes.keySet());
    }
    
    public void inserirMensagemDeAlteracaoDeHorarioNoFeed(
            List<Agendamento> agendamentos, Date horarioNovo, Medico m){
        
        feedManager.inserirMensagemDeAtualizacaoDeHorario(agendamentos, 
                horarioNovo, m);
        
        atualizarListaDePacientes();
        
    }
    
    public void excluirMensagem(Paciente p, Mensagem m){
        feedManager.excluirMensagem(m, p);
        
    }
    
    public List<Mensagem> retornarTodasAsMensagens(Paciente p){
        return p.getFeed().getMensagens();
    }
    
    public void atualizar(Paciente p){
        daoPaciente.atualizar(p);
    }
    
    public void atualizarListaDePacientes(){
        for (Paciente paciente : feedManager.getPacientesAAtualizar()) {
            daoPaciente.atualizar(paciente);
        }
    }
}
