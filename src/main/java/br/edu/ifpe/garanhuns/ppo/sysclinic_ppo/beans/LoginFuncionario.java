/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.beans;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Funcionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoFuncionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.
        exception.DaoException;
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
     * @param daoFuncionario, representing the DB where the data is going to 
     * be retrieved from | representando o BD de onde os dados serão 
     * recuperados
     * @throws DaoException
     */
    public void login(int matricula, String senha,
            DaoFuncionario daoFuncionario) throws DaoException {

        validar(matricula, senha);
        
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
     * EN-US 
     * Does the logout of the current logged paciente
     *
     * PT-BR 
     * Faz logout do paciente logado
     */
    public void logout() {
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
    

    /**
     * EN-US
     * Sets the funcionarioLogado in the session
     * 
     * PT-BR
     * Seta o funcionarioLogado na sessão
     */
    public void setarFuncionarioLogadoNaSessao() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().
                put("funcionarioLogado", funcionarioLogado);
    }

    /**
     * EN-US
     * 
     */
    public void tirarFuncionarioLogadoDaSessao() {
        FacesContext.getCurrentInstance().getExternalContext().
                getSessionMap().remove("funcionarioLogado");
    }
}
