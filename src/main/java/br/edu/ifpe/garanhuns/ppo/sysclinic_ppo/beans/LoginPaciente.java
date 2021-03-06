/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.beans;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoPaciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.
        exception.DaoException;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.
        DaoGenerico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.exception.InternalException;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.utils.LoginSessionUtil;
import javax.faces.context.FacesContext;

/**
 * EN-US 
 * Class that contains all the logic for the login/logout process
 *
 * PT-BR 
 * Classe que contem toda a lógica para o processo de login/logout
 *
 * @author Katarina
 */
public class LoginPaciente {

    private Paciente pacienteLogado;
    private final DaoGenerico daoPacientes;
    private LoginSessionUtil loginSessionUtil;

    public LoginPaciente(DaoPaciente daoPacientes, 
            LoginSessionUtil loginSessionUtil) {
        this.daoPacientes = daoPacientes;
        this.loginSessionUtil = loginSessionUtil;
    }

    public void setPacienteLogado(Paciente pacienteLogado) {
        this.pacienteLogado = pacienteLogado;
    }

    public Paciente getPacienteLogado() {
        return pacienteLogado;
    }
    
    /**
     * EN-US
     * Validates if the CPF and senha aren't null or empty
     * 
     * PT-BR
     * Valida se o CPF e senha não são nulos ou vazios
     * @param cpf
     * @param senha 
     */
    public void validar(String cpf, String senha){
        if (cpf == null || cpf.isEmpty() || senha == null || senha.isEmpty()){
            throw new IllegalArgumentException("CPF e senha não podem "
                    + "estar vazios");
        }
    }

    /**
     * EN-US 
     * Does the login for the given Strings (cpf and senha)
     *
     * PT-BR 
     * Faz o login para o cpf e a senha dadas
     *
     * @param cpf representing the login | representando o login
     * @param senha representing the password | representando a senha
     * @param fc
     * @throws DaoException if there's no Paciente with the given CPF in DB
     * @throws IllegalArgumentException if the senha is incorrect
     * @throws InternalException
     */
    public void login(String cpf, String senha, FacesContext fc) 
            throws DaoException, IllegalArgumentException, InternalException{

        validar(cpf, senha);

        Paciente paciente = (Paciente) daoPacientes.
                recuperarPorAtributo("cpf", cpf);

        if (paciente == null) {
            throw new DaoException(DaoException.CPF_INEXISTENTE);
        }

        if (paciente.getSenha().equals(senha)) {
            loginSessionUtil.setarLogadoNaSessao("pacienteLogado", paciente,
                    fc);
            pacienteLogado = paciente;

        } else {
            throw new IllegalArgumentException("As senhas não correspondem!");
        }
    }

    /**
     * EN-US 
     * Does the logout of the current logged paciente, by setting
     * pacienteLogado as null
     *
     * PT-BR 
     * Faz logout do paciente logado, ao setar pacienteLogado como nulo
     * 
     * @param fc
     * @throws InternalException
     */
    public void logout(FacesContext fc) throws InternalException {

       loginSessionUtil.removerLogadoNaSessao("pacienteLogado", fc);
       pacienteLogado = null;
    }

    /**
     * EN-US 
     * Verifies if there is a not null pacienteLogado
     *
     * PT-BR 
     * Verifica se existe um pacienteLogado não nulo
     *
     * @return true if there is, false if there isn't | true, se tem; false, se
     * não tem
     */
    public boolean existePacienteLogado() {
        return pacienteLogado != null;
    }

}
