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
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoMedico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.DaoGenerico;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Katarina
 */
public class MedicoManager {

    private final DaoGenerico daoMedicos;

    public MedicoManager(DaoMedico daoMedicos) {
        this.daoMedicos = daoMedicos;
    }

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

    public void marcarAgendamento(Medico medico, Agendamento agendamento) {
        medico.getAgenda().adicionarAgendamento(agendamento);
    }

    public String retornarDiasLivres(Medico medico) {
        String diasLivres = new Gson().toJson(medico.pegarDiasLivres());

        return diasLivres;
    }

    public List<Horario> retornarHorariosLivres(Medico medico, Date data) {
        List<Horario> horarios = medico.pegarHorariosLivres(data);

        return horarios;
    }

    public List<Agendamento> retornarAgendamentosConcluidos(Paciente paciente) {
        List<Agendamento> agendamentosConcluidos = new ArrayList();
        List<Medico> medicos = recuperarTodos();

        for (Medico medico : medicos) {
            agendamentosConcluidos.addAll(medico.getAgenda().
                    retornarAgendamentosConcluidosPacientes(paciente));
        }

        return agendamentosConcluidos;
    }    
    

    public List<Agendamento> retornarAgendamentosConcluidos() {
        List<Agendamento> agendamentosConclidos = new ArrayList<>();
        List<Medico> medicos = recuperarTodos();

        for (Medico medico : medicos) {
            agendamentosConclidos.addAll(medico.getAgenda().
                    retornarAgendamentosConcluidos());
        }

        return agendamentosConclidos;
    }

}
