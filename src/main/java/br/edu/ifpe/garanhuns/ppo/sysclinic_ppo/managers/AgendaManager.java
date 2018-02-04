/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.managers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agenda;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoAgenda;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author Katarina
 */
public class AgendaManager {
    
    private final DaoAgenda daoAgenda;
    private Agenda agenda;
    private boolean primeiroAcesso;
    
    
    public AgendaManager (DaoAgenda daoAgenda){
        this.daoAgenda = daoAgenda;
        agenda = recuperar(1);
    }

    public boolean isPrimeiroAcesso() {
        primeiroAcesso = recuperar(1) == null;
        return primeiroAcesso;
    }

    public void setPrimeiroAcesso(boolean primeiroAcesso) {
        this.primeiroAcesso = primeiroAcesso;
    }
    
    public void atualizar(){
        daoAgenda.atualizar(agenda);
    }
    
    public void cadastrar(Agenda agenda){
        daoAgenda.cadastrar(agenda);
    }
    
    public Agenda recuperar(int id){
        return daoAgenda.recuperar(id);
    }
    
    /**
     *
     * 
     * @param agendamento 
     */
    public void marcarAgendamento(Agendamento agendamento) {
        if(agendamento == null){
            throw new IllegalArgumentException("Agendamento inválido, "
                    + "recarregue a página e tente novamente");
        }
        
        agenda.adicionarAgendamento(agendamento);
    }
    
    public void verificarCadastrarNovaAgenda(){
        Agenda agenda = recuperar(1);
        if(agenda == null){
            Agenda novaAgenda = new Agenda(new ArrayList<Agendamento>());
            cadastrar(novaAgenda);
        }
    }
    
    /**
     * 
     * @param paciente
     * @return 
     */
    public List<Agendamento> retornarAgendamentosConcluidos(Paciente paciente) {
        List<Agendamento> agendamentosConcluidos = agenda.
                retornarAgendamentosConcluidosPacientes(paciente);
        
        return agendamentosConcluidos;
    }    
    
    /**
     * 
     * @return 
     */
    public List<Agendamento> retornarAgendamentosConcluidos() {
        List<Agendamento> agendamentosConclidos = agenda.
                retornarAgendamentosConcluidos();
        return agendamentosConclidos;
    }
    
    public List<Agendamento> retornarAgendamentosPendentes(){
        return agenda.retornarAgendamentosPendentes();
    }
    
    public List<Agendamento> retornarAgendamentosPendentes(Paciente p){
        return agenda.retornarAgendamentosPendentesPacientes(p);
    }
    
    public String excluirAgendamento(Agendamento a){
        boolean removeu = agenda.excluirAgendamento(a);
        String mensagem;
        
        
        if(removeu){
            atualizar();
            mensagem = "Agendamento removido com sucesso!";
        }else{
            mensagem = "Falha ao tentar remover agendamento. "
                    + "Recarregue a página e tente novamente.";
        }
        
        return mensagem;
    }
    
    public List<Agendamento> retornarAgendamentosDataAtual(){
        return agenda.retornarAgendamentosDataAtual();
    }
    
    public void atualizarHorariosAgendamentos(Date horarioNovo, 
            Date horarioAntigo, Medico medico){
        agenda.atualizarAgendamentoHorario(horarioAntigo, horarioNovo, 
                medico);
    }
}
