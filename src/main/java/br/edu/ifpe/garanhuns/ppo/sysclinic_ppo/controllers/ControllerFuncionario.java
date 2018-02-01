/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.controllers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.beans.LoginFuncionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.managers.FuncionarioManager;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Funcionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.
        DaoFuncionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.
        exception.DaoException;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.validators.Operacoes;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Katarina
 */
@ManagedBean
@SessionScoped
public class ControllerFuncionario implements 
        ControllerGenerico<Funcionario, Integer> {

    private DaoFuncionario funcionarios = new DaoFuncionario();

    @ManagedProperty(value = "#{funcionarioSelecionado}")
    private Funcionario funcionarioSelecionado;

    private LoginFuncionario loginFuncionario;
    
    private FuncionarioManager funcionarioManager;

    public ControllerFuncionario(){
        loginFuncionario = new LoginFuncionario();
        funcionarioManager = new FuncionarioManager(funcionarios);
    }

    public Funcionario getFuncionarioSelecionado() {
        return funcionarioSelecionado;
    }

    public void setFuncionarioSelecionado(Funcionario funcionarioSelecionado) {
        this.funcionarioSelecionado = funcionarioSelecionado;
    }

    @Deprecated
    @Override
    public void cadastrar(Funcionario c) {

    }

    /**
     * EN-US
     * Cryptographes a password confirmation and send it with a Funcionario to
     * be persisted in the database
     * 
     * PT-BR
     * Criptografa uma confirmação de senha e a envia junto com um Funcionario
     * para ser persistida na database
     * 
     * @param funcionario Funcionario to be saved | Funcionario que será 
     * cadastrado
     * @param senhaConfirmacao Password confirmation | Confirmação de senha
     * @return apresentar_funcionarios.xhtml if succesful or null if not |
     * apresentar_funcionarios.xhtml se tiver sucesso ou null se não tiver
     */
    public String cadastrar(Funcionario funcionario, String senhaConfirmacao){
        
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage fm;       
        String retorno = null;
        
        try {
            String senhaCriptografada = Operacoes.
                    criptografarSenha(senhaConfirmacao);
            funcionario.setSenha(Operacoes.criptografarSenha(funcionario.
                    getSenha()));
            
            funcionarioManager.cadastrar(funcionario, senhaCriptografada);
            
            fm = new FacesMessage("Sucesso!", "O funcionário foi cadastrado "
                    + "com sucesso!");
            
            retorno = "/administrador/apresentar_funcionarios.xhtml?faces-"
                    + "redirect=true";
            
        } catch(IllegalArgumentException ex){
            
            fm = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro", 
                    ex.getMessage());
            
        } catch(NoSuchAlgorithmException | UnsupportedEncodingException ex){
            
            fm = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro", 
                    "Recarregue a página e tente novamente.");
        }
        
        fc.addMessage(null, fm);
        return retorno;
    }

    /**
     * EN-US
     * Retrives the Funcionario with the given id from the database
     * 
     * PT-BR
     * Recupera o Funcionario com a id dada.
     * 
     * @param id id of the Funcionario | id do funcionario
     * @return O funcionario ou nulo, se este não exister
     */
    @Override
    public Funcionario recuperar(Integer id) {
        return funcionarioManager.recuperar(id);
    }

    /**
     * EN-US
     * Updates the given Funcionario in the DB
     * 
     * PT-BR
     * Atualiza o dado funcionário no BD
     * @param funcionario The funcionario to be updated | O funcionario a ser
     * atualizado
     */
    public void atualizar(Funcionario funcionario) {
        funcionarioManager.atualizar(funcionario);
    }

    
    /**
     * EN-US
     * Deletes the Funcionario from the DB
     * 
     * PT-BR
     * Exclui um funcionário do banco de dados
     *
     * @param funcionario representing the funcionario to be deleted | 
     * representando funcionário a ser excluído
     */
    public void deletar(Funcionario funcionario) {

        if (existeMaisDeUm(funcionario.isAdministrador())) {
            funcionarios.deletar(funcionario);
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta",
                            "Impossível excluir este funcionário. O sistema "
                            + "precisa de um administrador e "
                            + "um funcionário para as ações"));
        }
    }

    /**
     * EN-US
     * Retrieves all the saved funcionario from the DB
     * 
     * PT-BR
     * Recupera todos os funcionarios do BD
     * @return all saved funcionarios | todos os funcionarios salvos
     */
    public List<Funcionario> recuperarTodos() {
        return funcionarioManager.recuperarTodos();
    }

    /**
     * EN-US
     * Verifies if there's more than one funcionario whether it's an 
     * administrador or not
     * 
     * PT-BR
     * Verifica se tem mais de um funcionario, sendo ele administrador ou não
     * @param admin defines if it's an administrador | define se é um 
     * administrador
     * @return true if there is, false if there isn't | true, se tem, false, 
     * se não tem
     */
    public boolean existeMaisDeUm(boolean admin){
        return funcionarioManager.existeMaisDeUm(admin);
    }
    
    /**
     * EN-US
     * Sends the login and the cryptographed password given to the login method
     * 
     * PT-BR
     * Envia o login e a senha criptografada dada para o método de login
     * @param login representing the login | representando o login
     * @param senha representing the password | representando a senha
     * @return home page if succesful, null if unsuccesful | página inicial se 
     * sucedido, nulo se não sucedido
     */
    public String fazerLogin(Integer login, String senha) {
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage fm;
        String retorno = null;
        
        try {
            String senhaCriptografada = Operacoes.criptografarSenha(senha);
            loginFuncionario.login(login, senhaCriptografada,
                    (DaoFuncionario) funcionarios);
            loginFuncionario.setarFuncionarioLogadoNaSessao();

            fm = new FacesMessage("Sucesso!", "Você será redirecionado em "
                    + "alguns instantes");
            retorno = pegarPaginaDeRedirecionamento();
        } catch (DaoException | IllegalArgumentException ex) {
            fm = new FacesMessage(FacesMessage.SEVERITY_FATAL,
                    "Erro", ex.getMessage());
        }catch(NoSuchAlgorithmException | UnsupportedEncodingException ex){
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", 
                    "Recarregue a página e tente novamente.");
        }

        fc.addMessage(null, fm);
        return retorno;
    }

    private String pegarPaginaDeRedirecionamento() {
        if (loginFuncionario.existeLogadoAdministrador()) {
            return "/administrador/home_admin.xhtml?faces-redirect=true";
        } else {
            return "/funcionarios/home_funcionario.xhtml?faces-redirect=true";
        }
    }
    
    public Funcionario pegarFuncionarioLogado(){
        return loginFuncionario.getFuncionarioLogado();
    }
    
    public boolean existeLogadoAdministrador(){
        return loginFuncionario.existeLogadoAdministrador();
    }

    public boolean existeLogadoNaoAdministrador(){
        return loginFuncionario.existeLogadoNaoAdministrador();
    }
    
    public String logout() {
            loginFuncionario.logout();
            loginFuncionario.tirarFuncionarioLogadoDaSessao();

            return "/login/login_intranet.xhtml?faces-redirect=true";
    }

    public void exibirAlertaDeMudanca() {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso",
                        "Administradores podem cadastrar, alterar e excluir "
                        + "médicos e funcionários, mas não têm acesso "
                        + "ao sistema de agendamentos. Mude o "
                        + "privilégio apenas se necessário"));
    }

}
