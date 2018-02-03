/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.managers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Horario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.
        dao.DaoMedico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.
        manager.DaoGenerico;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EN-US
 * Class to manage all operations related to Medico
 * 
 * PT-BR
 * Classe para gerenciar operações relacionadas a Medico
 * @author Katarina
 */
public class MedicoManager {

    private final DaoGenerico daoMedicos;

    public MedicoManager(DaoMedico daoMedicos) {
        this.daoMedicos = daoMedicos;
    }

    /**
     * EN-US
     * 
     * 
     * @param medico
     * @throws IllegalArgumentException 
     */
    public void validar(Medico medico) throws IllegalArgumentException {
        Medico existente = (Medico) daoMedicos.
                recuperarPorAtributo("matricula", medico.getMatricula());

        if (existente != null) {
            throw new IllegalArgumentException("Este médico já está "
                    + "cadastrado, verifique se a matrícula está "
                    + "correta");

        }

        existente = (Medico) daoMedicos.
                recuperarPorAtributo("conselho", medico.getConselho());

        if (existente != null) {
            throw new IllegalArgumentException("Este número de registro do "
                    + "conselho já está cadastrado");
        }

    }

    public void cadastrar(Medico medico, String conselho, int numeroConselho)
            throws IllegalArgumentException {
        medico.setConselho(conselho + "/" + numeroConselho);
        validar(medico);
        daoMedicos.persistir(medico);
    }

    public Medico recuperar(int id) {
        return (Medico) daoMedicos.recuperar(id);
    }

    public void atualizar(Medico medico) {
        daoMedicos.atualizar(medico);
    }

    public void deletar(Medico medico) {
        daoMedicos.deletar(medico);
    }

    public List<Medico> recuperarTodos() {
        return daoMedicos.recuperarTodos();
    }

    /**
     *
     * 
     * @param medico
     * @param agendamento 
     */
    public void marcarAgendamento(Medico medico, Agendamento agendamento) {
        if(agendamento == null || medico == null){
            throw new IllegalArgumentException("Agendamento ou médico são "
                    + "inválidos, recarregue a página e tente novamente");
        }
        
        medico.getAgenda().adicionarAgendamento(agendamento);
    }

    /**
     * 
     * @param medico
     * @return 
     */
    public String retornarDiasLivres(Medico medico) {
        String diasLivres = new Gson().toJson(medico.pegarDiasLivres());

        return diasLivres;
    }

    /**
     * 
     * @param medico
     * @param data
     * @return 
     */
    public List<Horario> retornarHorariosLivres(Medico medico, Date data) {
        List<Horario> horarios = medico.pegarHorariosLivres(data);

        return horarios;
    }

    /**
     * 
     * @param paciente
     * @return 
     */
    public List<Agendamento> retornarAgendamentosConcluidos(Paciente paciente) {
        List<Agendamento> agendamentosConcluidos = new ArrayList();
        List<Medico> medicos = recuperarTodos();

        for (Medico medico : medicos) {
            agendamentosConcluidos.addAll(medico.getAgenda().
                    retornarAgendamentosConcluidosPacientes(paciente));
        }

        return agendamentosConcluidos;
    }    
    
    /**
     * 
     * @return 
     */
    public List<Agendamento> retornarAgendamentosConcluidos() {
        List<Agendamento> agendamentosConclidos = new ArrayList<>();
        List<Medico> medicos = recuperarTodos();

        for (Medico medico : medicos) {
            agendamentosConclidos.addAll(medico.getAgenda().
                    retornarAgendamentosConcluidos());
        }

        return agendamentosConclidos;
    }
    
    /*public List<Map.Entry<Agendamento, Medico>>
        retornarAgendamentosDataAtual(){
        
        List<Medico> medicos = recuperarTodos();
        List<Agendamento> agendamentos = new ArrayList<>();
        HashMap<Agendamento, Medico> agendamentosPorMedico = 
                new HashMap<>();
        
        for (Medico medico : medicos) {
            for (Object object : medico.getAgenda().) {
                
            }
            agendamentos = medico.getAgenda().retornarAgendamentosDataAtual();
            agendamentosPorMedico.put(medico, agendamentos);
        }
    }*/

}
