/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.managers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Funcionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoFuncionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.DaoGenerico;
import java.util.List;
import org.hibernate.exception.ConstraintViolationException;

/**
 * EN-US Class to manage all operations related to Funcionario
 *
 * PT-BR Classe para gerenciar todas as operações relacionadas a Funcionario
 *
 * @author Katarina
 */
public class FuncionarioManager {

    private final DaoGenerico daoFuncionarios;

    public FuncionarioManager(DaoFuncionario daoFuncionarios) {
        this.daoFuncionarios = daoFuncionarios;
    }

    /**
     * EN-US Method to validate the given Funcionario, if the passwords are
     * equal and if there's already a Funcionario with the same matricula in DB
     * If the passwords aren't equal or there's already a Funcionario
     * registered, a IllegalArgumentException will be thrown
     *
     *
     * PT-BR Método para validar o Funcionario dado, se as senha são iguais e se
     * já existe um Funcionario com a mesma matrícula no BD Se as senhas não são
     * iguais ou já tem um Funcionario cadastrado, uma IllegalArgumentException
     * será lançada
     *
     * @param funcionario representing the Funcionario to be validated |
     * representando o funcionário para ser validado
     * @param senhaConfirmacao representing the password to be compared |
     * representando a senha que será comparada
     * @throws IllegalArgumentException
     */
    public void validar(Funcionario funcionario, String senhaConfirmacao)
            throws IllegalArgumentException {
        String senhaFuncionario = funcionario.getSenha();

        if (!senhaFuncionario.equals(senhaConfirmacao)) {
            throw new IllegalArgumentException("As senhas não correspondem!");
        }

        Funcionario existente = (Funcionario) daoFuncionarios.
                recuperarPorAtributo("matricula", funcionario.getMatricula());

        if (existente != null) {
            throw new IllegalArgumentException("Este funcionário já está "
                    + "cadastrado, verifique se a matrícula está correto");
        }
    }

    /**
     * EN-US Validates the Funcionario and then persists it
     *
     * PT-BR Valida o Funcionario e depois o persiste
     *
     * @param funcionario representing the Funcionario to be persisted |
     * representando o funcionario ser persistido
     * @param confirmacaoSenha representing the password to be compared |
     * representando a senha ser comparada
     */
    public void cadastrar(Funcionario funcionario, String confirmacaoSenha) {
        validar(funcionario, confirmacaoSenha);
        daoFuncionarios.persistir(funcionario);
    }

    /**
     * EN-US Retrieves a Funcionario from DB with the given id
     *
     * PT-BR Recupera um funcionario do BD com a id dada
     *
     * @param id
     * @return a Funcionario, if exists, null if it doesn't | um Funcionario, se
     * existir, null se não
     */
    public Funcionario recuperar(int id) {
        return (Funcionario) daoFuncionarios.recuperar(id);
    }

    /**
     * EN-US Updates the given Funcionario
     *
     * PT-BR Atualiza o funcionario dado
     *
     * @param funcionario representing the Funcionario | representando o
     * funcionario
     */
    public void atualizar(Funcionario funcionario) {
        try {
            daoFuncionarios.atualizar(funcionario);
        } catch (ConstraintViolationException ex) {
            throw new IllegalArgumentException("A matrícula especificada já "
                    + "está cadastrada");
        }
    }

    /**
     * EN-US Deletes the given Funcionario from the DB
     *
     * PT-BR Deleta o funcionario dado do BD
     *
     * @param funcionario representing the Funcionario | representando o
     * funcionario
     */
    public void deletar(Funcionario funcionario) {
        daoFuncionarios.deletar(funcionario);
    }

    /**
     * EN-US Retrieves all Funcionario from DB
     *
     * PT-BR Recupera todos os Funcionario do BD
     *
     * @return all Funcionario | todos os Funcionario
     */
    public List<Funcionario> recuperarTodos() {
        return daoFuncionarios.recuperarTodos();
    }

    /**
     * EN-US Verifies if there's more than one Funcionario, according to it's
     * privilege (if Administrador or not)
     *
     * PT-BR Verifica se existe mais de um Funcionario, de acordo com seu
     * privilégio (se administrador ou não)
     *
     * @param administrador representing the status | representando o status
     * @return true if there is, false if there isn't | true se tem, false se
     * não tem
     */
    public boolean existeMaisDeUm(boolean administrador) {
        int qtdeFuncionario = 0;
        int qtdeAdministrador = 0;

        List<Funcionario> funcionarios = daoFuncionarios.recuperarTodos();

        for (Funcionario funcionario : funcionarios) {
            if (funcionario.isAdministrador()) {
                qtdeAdministrador++;
            } else {
                qtdeFuncionario++;
            }
        }

        if (administrador) {
            return qtdeAdministrador > 1;
        } else {
            return qtdeFuncionario > 1;
        }
    }

    /**
     * EN-US Returns an alert message about changing a Funcionario privilege
     *
     * PT-BR Retorna uma mensagem de alerta sobre mudar o privilégio de um
     * Funcionario
     *
     * @return an alert message | uma mensagem de alerta
     */
    public String retornarMensagemDeAlerta() {
        return "Administradores podem cadastrar, alterar e excluir "
                + "médicos e funcionários, mas não têm acesso "
                + "ao sistema de agendamentos. Mude o "
                + "privilégio apenas se necessário";
    }
}
