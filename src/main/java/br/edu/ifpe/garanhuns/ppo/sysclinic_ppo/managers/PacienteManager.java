package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.managers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Mensagem;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoPaciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.DaoGenerico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.validators.Operacoes;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.hibernate.exception.ConstraintViolationException;

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

    private DaoGenerico daoPaciente;

    private static FeedManager feedManager = FeedManager.getInstance();

    public PacienteManager() {
        daoPaciente = new DaoPaciente();
    }

    public void cadastrarPaciente(Paciente paciente, String confirmacaoSenha)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String senha = paciente.getSenha();

        if (!senha.equals(confirmacaoSenha)) {
            throw new IllegalArgumentException("As senhas não correspondem!");
        }
       
        paciente.setDataAdmissao(new Date(System.currentTimeMillis()));
       
        Paciente existente = (Paciente) daoPaciente.
                recuperarPorAtributo("cpf", paciente.getCpf());
        
        if(existente != null){
            throw new IllegalArgumentException("Este paciente já está "
                    + "cadastrado, verifique se o CPF está correto");
        }
                
        //String senhaCriptografada = Operacoes.criptografarSenha(senha);

        //paciente.setSenha(senhaCriptografada);

        daoPaciente.persistir(paciente);

    }
    
    public List<Paciente> recuperarTodos(){
        return daoPaciente.recuperarTodos();
    }

    public void inserirMensagemDeExclusaoNoFeed(List<Agendamento> agendamentos,
            Medico m) {
        feedManager.inserirMensagemDeExclusao(agendamentos, m);

        atualizarListaDePacientes();
        //atualizar(rolPacientes.keySet());
    }

    public void inserirMensagemDeAlteracaoDeHorarioNoFeed(
            List<Agendamento> agendamentos, Date horarioNovo, Medico m) {

        feedManager.inserirMensagemDeAtualizacaoDeHorario(agendamentos,
                horarioNovo, m);

        atualizarListaDePacientes();

    }

    public void excluirMensagem(Paciente p, Mensagem m) {
        feedManager.excluirMensagem(m, p);

    }

    public List<Mensagem> retornarTodasAsMensagens(Paciente p) {
        return p.getFeed().getMensagens();
    }

    public void atualizar(Paciente p) {
        daoPaciente.atualizar(p);
    }

    public void atualizarListaDePacientes() {
        for (Paciente paciente : feedManager.getPacientesAAtualizar()) {
            daoPaciente.atualizar(paciente);
        }
    }
}
