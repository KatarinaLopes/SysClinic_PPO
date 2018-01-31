/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.beans;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.
        DaoPaciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.
        exception.DaoException;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.validators.Operacoes;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * EN-US
 * Class that contains all the logic for the login/logout process
 * 
 * PT-BR
 * Classe que contem toda a lógica para o processo de login/logout
 * @author Katarina
 */
public class LoginPaciente {

    private Paciente pacienteLogado;   
    
    public void setPacienteLogado(Paciente pacienteLogado) {
        this.pacienteLogado = pacienteLogado;
    }

    public Paciente getPacienteLogado() {
        return pacienteLogado;
    }

    /**
     * EN-US Does the login for the given Strings (cpf and senha)
     *
     * PT-BR Faz o login para o cpf e a senha dadas
     *
     * @param cpf
     * @param senha
     */
    public void login(String cpf, String senha, DaoPaciente daoPaciente)
            throws DaoException{

        if (cpf == null || cpf.isEmpty() || senha == null || senha.isEmpty()) {
            throw new IllegalArgumentException("CPF e senha não podem "
                    + "estar vazios");
        }

        Paciente paciente = daoPaciente.recuperarPorAtributo("cpf", cpf);

        if (paciente == null) {
            throw new DaoException(DaoException.CPF_INEXISTENTE);
        }

        if (paciente.getSenha().equals(senha)) {
            pacienteLogado = paciente;
            //setarPacienteLogadoNaSessao();

        } else {
            throw new DaoException(DaoException.SENHA_NAO_CORRESPONDE);
        }
    }

    /**
     * EN-US Does the logout of the current logged paciente
     *
     * PT-BR Faz logout do paciente logado
     */
    public void logout() throws IllegalStateException{

        if (pacienteLogado != null) {
            pacienteLogado = null;
            //tirarPacienteLogadoDaSessao();
        }else{
            throw new IllegalStateException("Não há paciente logado para esta "
                    + "operação");
        }

    }

    /**
     * EN-US
     * Verifies if there is a not null pacienteLogado
     * 
     * PT-BR
     * Verifica se existe um pacienteLogado não nulo
     * @return true if there is, false if there isn't | true, se tem; 
     * false, se não tem
     */
    public boolean existePacienteLogado() {
        return pacienteLogado != null;
    }

    public void setarPacienteLogadoNaSessao() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().
                put("pacienteLogado", pacienteLogado);
    }

    public void tirarPacienteLogadoDaSessao() {
        FacesContext.getCurrentInstance().getExternalContext().
                getSessionMap().remove("pacienteLogado");
    }
}
