/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.beans;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Funcionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.
        dao.DaoFuncionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.
        dao.manager.DaoGenerico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.
        exception.DaoException;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.exception.InternalException;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.utils.LoginSessionUtil;
import java.io.Serializable;
import javax.faces.context.FacesContext;

/**
 * EN-US
 * Class to manage the login of Funcionario
 * 
 * PT-BR
 * Classe para gerenciar o login de Funcionario
 * @author Katarina
 */
public class LoginFuncionario implements Serializable{

    private Funcionario funcionarioLogado;
    private final DaoGenerico daoFuncionarios;
    private final LoginSessionUtil loginSessionUtil;

    public LoginFuncionario(DaoFuncionario daoFuncionarios, 
            LoginSessionUtil loginSessionUtil){
        this.daoFuncionarios = daoFuncionarios;
        this.loginSessionUtil = loginSessionUtil;
    }
    
    public void setFuncionarioLogado(Funcionario funcionarioLogado) {
        this.funcionarioLogado = funcionarioLogado;
    }

    public Funcionario getFuncionarioLogado() {
        return funcionarioLogado;
    }
    
    public void validar(int matricula, String senha){
        if(matricula == 0 || senha == null || senha.isEmpty()){
            throw new IllegalArgumentException("Matrícula e senha não podem "
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
     * @param matricula, representing the login | representando o login
     * @param senha, representing the password | representando a senha
     * @param fc
     * @throws DaoException
     * @throws InternalException
     */
    public void login(int matricula, String senha, FacesContext fc) 
            throws DaoException, InternalException {

        validar(matricula, senha);
        
        Funcionario funcionario = (Funcionario) daoFuncionarios.
                recuperarPorAtributo("matricula", matricula);

        if (funcionario == null) {
            throw new DaoException(DaoException.MATRICULA_INEXISTENTE);
        }
        
        if (funcionario.getSenha().equals(senha)) {
            loginSessionUtil.setarLogadoNaSessao("funcionarioLogado", 
                    funcionario, fc);
            funcionarioLogado = funcionario;

        } else {
            throw new IllegalArgumentException("As senhas não correspondem!");
        }
    }

    /**
     * EN-US 
     * Does the logout of the current logged paciente
     *
     * PT-BR 
     * Faz logout do paciente logado
     * @param fc
     * @throws InternalException
     */
    public void logout(FacesContext fc) throws InternalException {
        loginSessionUtil.removerLogadoNaSessao("funcionarioLogado", fc);
        funcionarioLogado = null;

    }

    /**
     * EN-US 
     * Verifies if there is a not null pacienteLogado
     *
     * PT-BR 
     * Verifica se existe um pacienteLogado não nulo
     *
     * @return true if there is, false if there isn't | true, se tem; false, 
     * se não tem
     */
    public boolean existeFuncionarioLogado() {
        return funcionarioLogado != null;
    }

    /**
     * EN-US 
     * Verifies if the logged funcionario is an administrador or not
     *
     * PT-BR 
     * Verifica se o funcionario logado é um administrador ou não
     *
     * @return true if it is, false if it isn't | true se for, false se não 
     * for
     */
    public boolean existeLogadoAdministrador() {

        return funcionarioLogado != null && funcionarioLogado.
                isAdministrador();
    }

    /**
     * EN-US
     * Verifies if there's a logged funcionario and if there is, if this 
     * funcionario isn't an administrador
     * 
     * PT-BR
     * Verifica se tem um funcionario logado e se tiver, se este funcionario
     * não é um administrador
     * 
     * @return true if there is a non administrador logged in, false if there 
     * isn't | true se tem um não administrador logado, false, se não tem
     */
    public boolean existeLogadoNaoAdministrador(){
        return funcionarioLogado != null && 
                !funcionarioLogado.isAdministrador();
    }  
    
}
