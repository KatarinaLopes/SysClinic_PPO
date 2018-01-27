/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.managers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Feed;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Mensagem;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoPaciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.DaoGenerico;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.apache.tomcat.jni.Library;

/**
 *
 * @author Katarina
 */
public final class FeedManager {

    private Feed feed;
    
    private List<Paciente> pacientesAAtualizar = new ArrayList<>();

    
    private static FeedManager myself;
    
    private FeedManager(){
        
    }   
    
    public static FeedManager getInstance(){
        if(myself == null)
            myself = new FeedManager();
        return myself;
    }

    public List<Paciente> getPacientesAAtualizar() {
        return pacientesAAtualizar;
    }

    public void setPacientesAAtualizar(List<Paciente> pacientesAAtualizar) {
        this.pacientesAAtualizar = pacientesAAtualizar;
    }
    
    

    public void inserirMensagemDeExclusao(HashMap<Paciente, Date> rolPacientes,
            Medico medico) {

        List<Paciente> pacientes = new ArrayList<>();
        pacientes.addAll(rolPacientes.keySet());

        Date[] datasPrevistas = (Date[]) rolPacientes.values().toArray();

        int i = 0;

        for (Paciente paciente : pacientes) {
            paciente.getFeed().incluirMensagensExclusaoDeAgendamento(medico,
                    datasPrevistas[i]);

            i++;

        }

    }

    public void inserirMensagemDeAtualizacaoDeHorario(List<Agendamento> 
            agendamentos, Date horarioNovo, Medico medico) {

        for (Agendamento agendamento : agendamentos) {
            agendamento.getPaciente().getFeed().
                    incluirMensagensAlteracaoDeHorario(
                    agendamento.getDataPrevista(), horarioNovo, medico);
            popularListaPaciente(agendamento.getPaciente());
        }
    }

    public void excluirMensagem(Mensagem m, Paciente p) {
        p.getFeed().excluirMensagem(m);

    }

    public static List<Mensagem> exibirMensagens(Paciente p) {
        return p.getFeed().getMensagens();
    }

    private void popularListaPaciente(Paciente p){
        if(!pacientesAAtualizar.contains(p)){
            pacientesAAtualizar.add(p);
        }
    }
    
    public void resetarListaDePacientes(){
        pacientesAAtualizar.removeAll(pacientesAAtualizar);
    }
}
