/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.controllers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.jsf.services.MedicoService;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.jsf.services.PacienteService;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Horario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoMedico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoPaciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.DaoGenerico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.hibernateutil.HibernateUtil;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.validators.Operacoes;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.validators.Validacoes;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
@ManagedBean(name="controllerPaciente", eager = true)
@SessionScoped
public class ControllerPaciente implements ControllerGenerico<Paciente, Integer> {

    private DaoGenerico pacientes = new DaoPaciente();

    @ManagedProperty("#{pacienteLogado}")
    private Paciente pacienteLogado;

    @ManagedProperty("#{pacientes}")
    private List<Paciente> pacientesCadastrados = new ArrayList();
    
    //@ManagedProperty("#{pacienteSelecionado}")
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
        
        HttpSession s = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(true);
        
        s.setAttribute("pacientesCadastrados", pacientesCadastrados);
        
        //pacientesCadastrados = pacienteService.getPacientesCadastrados();
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

                
                
                return "restricted/home_paciente.xhtml";
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
        HttpSession s = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(true);

        s.removeAttribute("pacienteLogado");

        pacienteLogado = null;
        
        String p = FacesContext.getCurrentInstance().getExternalContext().
                getRealPath("");

        return "login_paciente.xhtml";
    }
    
    /*public String incluirAgendamento(){
        
        Paciente p = recuperar(1);
        
        p.incluirAgendamento(agendamento);
        
        atualizar(p);
        
        return "agendamentos_feitos.xhtml";
    }*/
    
    public void procurarPaciente(String cpf){
        
        //System.out.println(1);
        
        //Paciente p = null;
        
        //System.out.println(2 + " dg " + p);
        
        for (Paciente pacientesCadastrado : pacientesCadastrados) {
            
          //  System.out.println(3);
            
            if(pacientesCadastrado.getCpf().equals(cpf)){
                //System.out.println(4);
                
                return;
                
            }
            
        }
        
        
                  
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                                    "Paciente não cadastrado ou CPF "
                                            + "inválido"));
        
    }
    
    public Paciente procurarPaciente(int id){
        recuperarTodos();
        
        for (Paciente pacientesCadastrado : pacientesCadastrados) {
            if(pacientesCadastrado.getId() == id){
                return pacientesCadastrado;
            }
            
        }
        
        return null;
    }
}
