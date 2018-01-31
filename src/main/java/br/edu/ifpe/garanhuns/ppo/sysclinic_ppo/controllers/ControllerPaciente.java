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

    @ManagedProperty("#{pacientes}")
    private List<Paciente> pacientesCadastrados = new ArrayList();

    //@ManagedProperty("#{pacienteSelecionado}")
    private Paciente pacienteSelecionado;

    private LoginPaciente loginPaciente;

    private PacienteManager pacienteManager;

    public ControllerPaciente() {
        loginPaciente = new LoginPaciente();
        pacienteManager = new PacienteManager();
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

    public List<Paciente> getPacientesCadastrados() {
        return pacientesCadastrados;
    }

    public void setPacientesCadastrados(List<Paciente> pacientesCadastrados) {
        this.pacientesCadastrados = pacientesCadastrados;
    }

    @Override
    @Deprecated
    public void cadastrar(Paciente c) {

    }

    public boolean validarPaciente(Paciente c, String senha) {
        String msg = Operacoes.validarPaciente(c, senha);

        if (msg == null) {
            return true;
        }

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(msg));

        return false;
    }

    public String cadastrar(Paciente c, String senha) {
        //System.out.println(c.getSenha());

        try {
            pacienteManager.cadastrar(c, senha);
            return "/login/login_paciente.xhtml?faces-redirect=true";
        } catch (IllegalArgumentException ex) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(ex.getMessage()));
        }
        
        return null;
    }

    @Override
    public Paciente recuperar(Integer i) {
        return (Paciente) pacientes.recuperar(i);
    }

    public void atualizar(Paciente c) {
        pacientes.atualizar(c);
    }

    @PostConstruct
    public void recuperarTodos() {
        pacientesCadastrados = pacientes.recuperarTodos();

        HttpSession s = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(true);

        s.setAttribute("pacientesCadastrados", pacientesCadastrados);

        //pacientesCadastrados = pacienteService.getPacientesCadastrados();
    }

    /**
     *
     */
    public String fazerLogin(String login, String senha) {
        FacesContext fc = FacesContext.getCurrentInstance();

        try {
            loginPaciente.login(login, senha, (DaoPaciente) pacientes);
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

    /*public String incluirAgendamento(){
        
        Paciente p = recuperar(1);
        
        p.incluirAgendamento(agendamento);
        
        atualizar(p);
        
        return "agendamentos_feitos.xhtml";
    }*/
    public void procurarPaciente(String cpf) {

        //System.out.println(1);
        //Paciente p = null;
        //System.out.println(2 + " dg " + p);
        for (Paciente pacientesCadastrado : pacientesCadastrados) {

            //  System.out.println(3);
            if (pacientesCadastrado.getCpf().equals(cpf)) {
                //System.out.println(4);

                return;

            }

        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                "Paciente não cadastrado ou CPF "
                + "inválido"));

    }

    public Paciente procurarPaciente(int id) {
        recuperarTodos();

        for (Paciente pacientesCadastrado : pacientesCadastrados) {
            if (pacientesCadastrado.getId() == id) {
                return pacientesCadastrado;
            }

        }

        return null;
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
