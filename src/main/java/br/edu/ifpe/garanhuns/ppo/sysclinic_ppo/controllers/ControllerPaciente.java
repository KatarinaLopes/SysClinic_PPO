/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.controllers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.beans.LoginPaciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.
        DaoPaciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.
        DaoGenerico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.validators.Operacoes;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.managers.PacienteManager;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Mensagem;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.
        exception.DaoException;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.
        exception.InternalException;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.utils.LoginSessionUtil;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Katarina
 */
@ManagedBean(name = "controllerPaciente", eager = true)
@SessionScoped
public class ControllerPaciente implements 
        ControllerGenerico<Paciente, Integer> {

    private final DaoGenerico daoPacientes = new DaoPaciente();

    //@ManagedProperty("#{pacienteSelecionado}")
    private Paciente pacienteSelecionado;

    private final LoginPaciente loginPaciente;

    private final PacienteManager pacienteManager;
    
    private Paciente pacienteClonado;

    public ControllerPaciente() {
        loginPaciente = new LoginPaciente((DaoPaciente) daoPacientes, 
                new LoginSessionUtil());
        pacienteManager = new PacienteManager((DaoPaciente) daoPacientes);
    }

    public Paciente getPacienteSelecionado() {
        HttpSession s = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(true);

        return (Paciente) s.getAttribute("pacienteSelecionado");
    }

    public void setPacienteSelecionado(Paciente pacienteSelecionado) {
        this.pacienteSelecionado = pacienteSelecionado;

        HttpSession s = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().
                getSession(true);

        s.setAttribute("pacienteSelecionado", this.pacienteSelecionado);
    }

    public Paciente getPacienteClonado() {
        return pacienteClonado;
    }

    public void setPacienteClonado(Paciente pacienteClonado) {
        this.pacienteClonado = pacienteClonado;
    }
    
    

    @Override
    @Deprecated
    public void cadastrar(Paciente c) {

    }

    /**
     * EN-US
     * Receives a Paciente and a confirm password. The confirm password and 
     * the Paciente's password are cryptographed before being sent to be 
     * persisted in the DB
     * 
     * PT-BR
     * Recebe um Paciente e uma senha de confirmação. A senha de confirmação
     * e a senha do paciente são criptografadas antes de serem enviados para 
     * serem persistidos no BD
     * 
     * @param paciente representing the Paciente | representando o Paciente
     * @param senhaConfirmacao representing the confirm password | 
     * representando a senha de confirmação
     * @return 
     */
    public String cadastrar(Paciente paciente, String senhaConfirmacao) {
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage fm;
        String retorno = null;

        try {
            String senhaConfirmacaoCriptografada = Operacoes.
                    criptografarSenha(senhaConfirmacao);
            paciente.setSenha(Operacoes.criptografarSenha(paciente.
                    getSenha()));
            
            pacienteManager.cadastrar(paciente, senhaConfirmacaoCriptografada);
            
            fm = new FacesMessage("Sucesso", 
                    "O cadastro foi efetuado com sucesso");
            
            retorno = "/acoes/novo_agendamento.xhtml?faces-redirect=true";
        } catch (IllegalArgumentException ex) {
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
                    ex.getMessage());
        }catch(NoSuchAlgorithmException | UnsupportedEncodingException ex){
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", 
                    "Recarregue a página e tente novamente");
        }

        fc.addMessage(null, fm);
        return retorno;
    }

    /**
     * EN-US
     * Retrieves from the DB with the given id
     * 
     * PT-BR
     * Recupera do BD com o id dado
     * @param id
     * @return Paciente if it exists, null if it doesn't | Paciente, se 
     * existir, null se não existir
     */
    @Override
    public Paciente recuperar(Integer id) {
        return pacienteManager.recuperar(id);
    }

    /**
     * EN-US
     * Updates the given Paciente
     * 
     * PT-BR
     * Atualiza o paciente dado
     * @param paciente representing the paciente | representando o paciente 
     */
    public void atualizar(Paciente paciente) {
       pacienteManager.atualizar(paciente);
    }

    /**
     * EN-US
     * Retrieves all from DB
     * 
     * PT-BR
     * Recupera todos do BD
     * @return all Patients | todos os pacientes
     */
    public List<Paciente> recuperarTodos() {
        return pacienteManager.recuperarTodos();

    }

    /**
     * EN-US
     * Does the login by sending the login and a confirm password by 
     * cryptographing it and validating it. If there's success, the login page
     * will be sent. Else, the method returns null
     * 
     * PT-BR
     * Faz o login ao mandar o login e a confirmação de senha, 
     * criptografando-a e validando-a. Se tiver sucesso, a página de login será
     * enviada. Se não, o método retornará null
     * 
     * @param login representing the login | representando o login
     * @param senhaConfirmacao representing the confirm password | 
     * representando a senha de confirmação 
     * @return 
     */
    public String fazerLogin(String login, String senhaConfirmacao){
        FacesContext fc = FacesContext.getCurrentInstance();
        String detail = "";
        String retorno = null;

        try {
            String senhaCriptografada = Operacoes.
                    criptografarSenha(senhaConfirmacao);
            loginPaciente.login(login, senhaCriptografada, fc);
            retorno = "/pacientes/home_paciente.xhtml?faces-redirect=true";
        } catch (DaoException | IllegalArgumentException | 
                InternalException ex) {
            detail = ex.getMessage();
        }catch(NoSuchAlgorithmException | UnsupportedEncodingException ex){
            detail = "Recarregue a página e tente novamente";
        }
        
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Erro", detail));
        return retorno;
    }

    /**
     * EN-US
     * Returns if there's a logged Paciente
     * 
     * PT-BR
     * Retorna se houver um paciente logados
     * @return true,if there is, false if there isn't | true se tem, false, 
     * se não tem
     */
    public boolean existePacienteLogado() {
        return loginPaciente.existePacienteLogado();
    }

    /**
     *EN-US
     * Returns the current logged Paciente
     * 
     * PT-BR
     * Retorna o paciente logado atualmente
     * 
     * @return Paciente if there is, null if there isn't. | Paciente, se tem, 
     * null, se não tem
     */
    public Paciente retornarPacienteLogado() {
        return loginPaciente.getPacienteLogado();
    }

    /**
     * EN-US
     * Does the logout
     *
     * PT-BR 
     * Faz o logout
     *
     * @return página de login
     */
    public String fazerLogout() {
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage fm;
        String retorno = null;
        
        try {
            loginPaciente.logout(FacesContext.getCurrentInstance());
            fm = new FacesMessage("Sucesso!", 
                    "Você saiu com sucesso da aplicação!");
            retorno = "/login/login_paciente.xhtml?faces-redirect=true";
        } catch (InternalException ex) {
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", 
                    ex.getMessage());
        }
        
        fc.addMessage(null, fm);
        return retorno;
    }

    public void incluirMensagensDeExclusaoDeAgendamento(Medico excluido) {

        pacienteManager.inserirMensagemDeExclusaoParaTodosPacientes(excluido);

    }

    public void incluirMensagemDeAlteracaoDeHorario(Medico medico, 
            Date novoHorario) {
        
        pacienteManager.inserirMensagemDeAtualizacaoDeHorario(medico, 
                novoHorario);
        
    }

    public List<Mensagem> exibirMensagens() {
        return pacienteManager.exibirMensagens(retornarPacienteLogado());
    }

    public void excluirMensagem(Mensagem m) {
        pacienteManager.excluirMensagens(m, retornarPacienteLogado());
        atualizar(retornarPacienteLogado());
    }

    public void alterarSenha(Paciente paciente, String senhaAntiga, 
            String senhaNova, String confirmacao){
        
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage fm;
        
    
        try {
            pacienteManager.
                    alterarSenha(paciente, senhaAntiga, senhaNova, 
                            confirmacao);
            pacienteManager.atualizar(paciente);
            fm = new FacesMessage("Sucesso!", "A senha foi alterada com "
                    + "sucesso!");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", 
                    "Recarregue a página e tente novamente");
        }catch(IllegalArgumentException ex){
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", 
                    ex.getMessage());
        }
        
        fc.addMessage(null, fm);
    }
}