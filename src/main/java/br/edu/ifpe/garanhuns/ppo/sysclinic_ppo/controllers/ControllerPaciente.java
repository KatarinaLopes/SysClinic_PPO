/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.controllers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoPaciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.DaoGenerico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.validators.Operacoes;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.validators.Validacoes;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Katarina
 */
@ManagedBean
@SessionScoped
public class ControllerPaciente implements ControllerGenerico<Paciente, Integer> {

    private DaoGenerico pacientes = new DaoPaciente();

    @ManagedProperty("#{pacienteLogado}")
    private Paciente pacienteLogado;

    @ManagedProperty("#{pacientes}")
    private List<Paciente> pacientesCadastrados;
    
    @ManagedProperty("#{pacienteSelecionado}")
    private Paciente pacienteSelecionado;

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
    
    

    public Paciente getPacienteLogado() {
        return pacienteLogado;
    }

    public void setPacienteLogado(Paciente pacienteLogado) {
        this.pacienteLogado = pacienteLogado;
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
        
        if(msg == null){
            return true;
        }
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
        
        return false;
    }

    public String cadastrar(Paciente c, String senha) {
        //System.out.println(c.getSenha());
        
        c.setDataAdmissao(new Date(System.currentTimeMillis()));
        
        if (!validarPaciente(c, senha)) {
          return null;
        }

        //c.setDataAdmissao(new Date(System.currentTimeMillis()));

        try {
            pacientes.persistir(c);
        } catch (ConstraintViolationException cve) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "O CPF informado já está cadastrado!",
            null));
            return null;
        }

        return "login_paciente.xhtml";
    }

    @Override
    public Paciente recuperar(Integer i) {
        return (Paciente) pacientes.recuperar(i);
    }

    @Override
    public void atualizar(Paciente c) {
        pacientes.atualizar(c);
    }

    @PostConstruct
    public void recuperarTodos() {
        pacientesCadastrados = pacientes.recuperarTodos();
    }

    public String fazerLogin(String login, String senha) {
        Paciente p = null;

        try {
            p = (Paciente) pacientes.recuperarPorAtributo("cpf", login);

            if (p.getSenha().equals(senha)) {
                pacienteLogado = p;

                HttpSession s = (HttpSession) FacesContext.getCurrentInstance().
                        getExternalContext().getSession(true);

                //s.setMaxInactiveInterval(30000);
                s.setAttribute("pacienteLogado", pacienteLogado);

                return "home_paciente.xhtml";
            }

        } catch (IndexOutOfBoundsException e) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage("O login está incorreto"));
            return null;
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("A senha está incorreta"));

        return null;
    }

    public String fazerLogout() {
        HttpSession s = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

        s.removeAttribute("pacienteLogado");

        pacienteLogado = null;

        return "login_paciente.xhtml";
    }
    
    public void incluirAgendamento(Agendamento a){
        System.out.println(a.getPaciente().getNome());
        System.out.println(a.getMedico().getNome());
        System.out.println(a.getDataPrevista());
        
        //a.getPaciente().incluirAgendamento(a.getDataPrevista(), a.getMedico(), 1);
        
    }
}
