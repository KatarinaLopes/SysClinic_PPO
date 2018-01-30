/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.beans;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Funcionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoFuncionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.exception.DaoException;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Katarina
 */
public class LoginFuncionario {
    private Funcionario funcionarioLogado;   
    
    public void setFuncionarioLogado(Funcionario funcionarioLogado) {
        this.funcionarioLogado = funcionarioLogado;
    }

    public Funcionario getFuncionarioLogado() {
        return funcionarioLogado;
    }

    /**
     * EN-US Does the login for the given Strings (cpf and senha)
     *
     * PT-BR Faz o login para o cpf e a senha dadas
     *
     * @param matricula
     * @param senha
     * @param daoFuncionario
     * @throws DaoException
     */
    public void login(int matricula, String senha, 
            DaoFuncionario daoFuncionario) throws DaoException {

        if (matricula == 0 || senha == null || senha.isEmpty()) {
            throw new IllegalArgumentException("Matrícula e senha não podem "
                    + "estar vazios");
        }

        Funcionario funcionario = daoFuncionario.
                recuperarPorAtributo("matricula", matricula);

        if (funcionario == null) {
            throw new DaoException(DaoException.MATRICULA_INEXISTENTE);
        }

        if (funcionario.getSenha().equals(senha)) {
            funcionarioLogado = funcionario;
            //setarFuncionarioLogadoNaSessao();

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

        if (funcionarioLogado != null) {
            funcionarioLogado = null;
            //tirarFuncionarioLogadoDaSessao();
        }else{
            throw new IllegalStateException("Não há funcionário logado para "
                    + "esta operação");
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
    public boolean existeFuncionarioLogado() {
        return funcionarioLogado != null;
    }

    /**
     * EN-US
     * Verifies if the logged funcionario is an admin or not
     * 
     * PT-BR
     * Verifica se o funcionario logado é um administrador ou não
     * 
     * @return true if it is, false if it isn't | true se for, false se não for
     */
    public boolean logadoEAdministrador() throws IllegalStateException{
        if(funcionarioLogado == null){
            throw new IllegalStateException("Não há funcionário logado para "
                    + "a operação");
        }
        
        return funcionarioLogado.isAdministrador();
    }
    
    public void setarFuncionarioLogadoNaSessao() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().
                put("funcionarioLogado",funcionarioLogado);
    }

    public void tirarFuncionarioLogadoDaSessao() {
        FacesContext.getCurrentInstance().getExternalContext().
                getSessionMap().remove("funcionarioLoga");
    }
}
