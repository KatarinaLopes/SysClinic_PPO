package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.managers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Horario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Mensagem;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoPaciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.DaoGenerico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.validators.Operacoes;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * EN-US Class to manage all operations related to Paciente
 *
 * PT-BR Classe para gerenciar todas as operações relacionadas a Paciente
 *
 * @author Katarina
 */
public class PacienteManager implements Serializable {

    private final DaoGenerico daoPaciente;

    public PacienteManager(DaoPaciente daoPacientes) {
        this.daoPaciente = daoPacientes;
    }

    /**
     * EN-US Validates if there's already a Paciente with the same CPF in the DB
     * and if the confirm password is equal to the Paciente's password
     *
     * PT-BR Valida se já existe um paciente com o mesmo CPF no BD e se a senha
     * de confirmação é igual à senha do Paciente
     *
     * @param paciente
     * @param confirmacaoSenha
     * @throws IllegalArgumentException if the passwords aren't equal or if the
     * Paciente already exists | se as senhas não são iguais ou se o paciente já
     * existe
     */
    public void validarCadastrar(Paciente paciente, String confirmacaoSenha) {
        String senha = paciente.getSenha();

        if (!senha.equals(confirmacaoSenha)) {
            throw new IllegalArgumentException("As senhas não correspondem!");
        }

        Paciente existente = (Paciente) daoPaciente.
                recuperarPorAtributo("cpf", paciente.getCpf());

        if (existente != null) {
            throw new IllegalArgumentException("Este paciente já está "
                    + "cadastrado, verifique se o CPF está correto");
        }
    }

    /**
     * EN-US Persists the given Paciente in the DB, after validating and setting
     * the admission date to today
     *
     * PT-BR Persiste o Paciente no BD, depois de validar e setar a data de
     * admissão para hoje
     *
     * @param paciente
     * @param confirmacaoSenha
     */
    public void cadastrar(Paciente paciente, String confirmacaoSenha) {
        validarCadastrar(paciente, confirmacaoSenha);
        paciente.setDataAdmissao(new Date(System.currentTimeMillis()));
        daoPaciente.persistir(paciente);
    }

    /**
     * EN-US Retrives all from the database
     *
     * PT-BR Recupera todos do banco
     *
     * @return all Pacientes | todos os pacientes
     */
    public List<Paciente> recuperarTodos() {
        return daoPaciente.recuperarTodos();
    }

    /**
     * EN-US Retrieves a Paciente from DB with the given id
     *
     * PT-BR Recupera um Paciente do BD com a id dada
     *
     * @param id
     * @return Paciente if it exists, null if it doesn't | Paciente se existir,
     * null se não existir
     */
    public Paciente recuperar(int id) {
        return (Paciente) daoPaciente.recuperar(id);
    }

    /**
     * EN-US Updates the given Paciente from the DB
     *
     * PT-BR Atualiza um dado Paciente do BD
     *
     * @param paciente
     */
    public void atualizar(Paciente paciente) {
        daoPaciente.atualizar(paciente);
    }

    public void inserirMensagemDeExclusaoParaTodosPacientes(Medico excluido) {
        List<Agendamento> agendamentos = excluido.getAgenda().
                getAgendamentos();

        Paciente anterior = null;
        Paciente atual = null;

        for (Agendamento agendamento : agendamentos) {

            atual = agendamento.getPaciente();

            atual.getFeed().incluirMensagensExclusaoDeAgendamento(excluido,
                    agendamento.getDataPrevista());
            atualizar(atual);
        }
    }

    public void inserirMensagemDeAtualizacaoDeHorario(Medico medico,
            Date novoHorario) {

        List<Agendamento> agendamentos = medico.getAgenda().
                listarPacientesAgendados(novoHorario);

        for (Agendamento agendamento : agendamentos) {

            Paciente paciente = agendamento.getPaciente();

            paciente.getFeed().incluirMensagensAlteracaoDeHorario(agendamento.getDataPrevista(), novoHorario, medico);
            atualizar(paciente);

        }
    }

    public List<Mensagem> exibirMensagens(Paciente paciente) {
        return paciente.getFeed().getMensagens();
    }

    public void excluirMensagens(Mensagem mensagem, Paciente paciente) {
        paciente.getFeed().excluirMensagem(mensagem);
    }

    public boolean verificarCorrespondenciaSenha(String senha,
            String senhaConfirmacao) {
        return senha.equals(senhaConfirmacao);
    }

    public void alterarSenha(Paciente paciente, String senhaAntiga,
            String senhaNova, String confirmacao)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {

        String antigaCriptografada = Operacoes.criptografarSenha(senhaAntiga);

        if (!verificarCorrespondenciaSenha(paciente.getSenha(), 
                antigaCriptografada)) {
            throw new IllegalArgumentException("A senha antiga está "
                    + "incorreta");
        }
        
        if(!verificarCorrespondenciaSenha(senhaNova, confirmacao)){
            throw new IllegalArgumentException("A senha nova e a confirmação "
                    + "não correspondem!");
        }
        
        String novaCriptografada = Operacoes.criptografarSenha(senhaNova);
        
        paciente.setSenha(novaCriptografada);
        
    }
}
