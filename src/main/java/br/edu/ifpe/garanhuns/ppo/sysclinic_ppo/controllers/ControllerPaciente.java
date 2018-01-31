/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.controllers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.beans.LoginPaciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.jsf.services.MedicoService;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.jsf.services.PacienteService;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Horario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Mensagem;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoMedico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoPaciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.DaoGenerico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.hibernateutil.HibernateUtil;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.validators.Operacoes;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.validators.Validacoes;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.managers.FeedManager;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.managers.PacienteManager;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.exception.DaoException;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.Hibernate;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Katarina
 */
@ManagedBean(name = "controllerPaciente", eager = true)
@SessionScoped
public class ControllerPaciente implements ControllerGenerico<Paciente, Integer> {

    private DaoGenerico pacientes = new DaoPaciente();

    //@ManagedProperty("#{pacienteSelecionado}")
    private Paciente pacienteSelecionado;

    private LoginPaciente loginPaciente;

    private PacienteManager pacienteManager;

    public ControllerPaciente() {
        loginPaciente = new LoginPaciente();
        pacienteManager = new PacienteManager((DaoPaciente) pacientes);
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

    @Override
    @Deprecated
    public void cadastrar(Paciente c) {

    }

    public String cadastrar(Paciente c, String senha)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //System.out.println(c.getSenha());

        try {
            String senhaConfirmacaoCriptografada = Operacoes.
                    criptografarSenha(senha);
            c.setSenha(Operacoes.criptografarSenha(c.getSenha()));
            
            pacienteManager.cadastrar(c, senhaConfirmacaoCriptografada);
            return "/login/login_paciente.xhtml?faces-redirect=true";
        } catch (IllegalArgumentException ex) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(ex.getMessage()));
        }

        return null;
    }

    @Override
    public Paciente recuperar(Integer i) {
        return pacienteManager.recuperar(i);
    }

    public void atualizar(Paciente c) {
       pacienteManager.atualizar(c);
    }

    public List<Paciente> recuperarTodos() {
        return pacienteManager.recuperarTodos();

    }

    /**
     *
     */
    public String fazerLogin(String login, String senha) throws
            NoSuchAlgorithmException, UnsupportedEncodingException {
        FacesContext fc = FacesContext.getCurrentInstance();

        try {
            String senhaCriptografada = Operacoes.criptografarSenha(senha);
            loginPaciente.login(login, senhaCriptografada, 
                    (DaoPaciente) pacientes);
            loginPaciente.setarPacienteLogadoNaSessao();
            return "/pacientes/home_paciente.xhtml?faces-redirect=true";
        } catch (DaoException ex) {
            fc.addMessage(null, new FacesMessage(ex.getMessage()));
        }
        return null;
    }

    public boolean existePacienteLogado() {
        return loginPaciente.existePacienteLogado();
    }

    public Paciente retornarPacienteLogado() {
        return loginPaciente.getPacienteLogado();
    }

    /**
     * EN-US Does the logout
     *
     * PT-BR Faz o logout
     *
     * @return página de login
     */
    public String fazerLogout() {

        FacesContext fc = FacesContext.getCurrentInstance();

        try {
            loginPaciente.logout();
            loginPaciente.tirarPacienteLogadoDaSessao();
        } catch (IllegalStateException ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao fazer logout",
                    "Recarregue a página e tente novamente");

            FacesContext.getCurrentInstance().addMessage(null, fm);

            return null;
        }

        return "/login/login_paciente.xhtml?faces-redirect=true";
    }
    
    public Paciente procurarPaciente(int id) {
        return pacienteManager.recuperar(id);
    }

    public void incluirMensagensDeExclusaoDeAgendamento(Medico m) {
        System.out.println(m);

        List<Agendamento> pacientesMarcados = m.getAgenda().getAgendamentos();

        pacienteManager.inserirMensagemDeExclusaoNoFeed(pacientesMarcados, m);

        //FeedManager.inserirMensagemDeExclusao(pacientesMarcados, m);
    }

    public void incluirMensagemDeAlteracaoDeHorario(Medico m,
            Date horarioAnterior, Date horarioNovo) {

        List<Paciente> pacientesAgendados = m.getAgenda().
                listarPacientesMarcados(horarioAnterior);

        //FeedManager.inserirMensagemDeAtualizacaoDeHorario(pacientesAgendados, 
        //      horarioAnterior, horarioNovo, m);
    }

    public List<Mensagem> exibirMensagens() {
        return pacienteManager.retornarTodasAsMensagens(retornarPacienteLogado());
    }

    public void excluirMensagem(Mensagem m) {
        //PacienteManager.getInstance().excluirMensagem(pacienteLogado, m);
        //PacienteManager.getInstance().atualizar(pacienteLogado);
    }

}
