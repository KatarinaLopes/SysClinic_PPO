/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.builders;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agenda;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Horario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Katarina
 */
@ManagedBean
@RequestScoped
public class BuilderMedico implements BuilderGenerico<Medico>{
    private int id;
    private int matricula;
    private Date dataAdmissao;
    private String nome;
    private String sexo;
    
    private String email;
 
    private String telefone;

    private String conselho;
    
    private String especialidade;
  
    private static List<Horario> horarios = new ArrayList<>();
    
    private List<Agendamento> agendamentos;
    
    private int limiteDeAgendamentos;

    private Agenda ag;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getConselho() {
        return conselho;
    }

    public void setConselho(String conselho) {
        this.conselho = conselho;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    public int getLimiteDeAgendamentos() {
        return limiteDeAgendamentos;
    }

    public void setLimiteDeAgendamentos(int limiteDeAgendamentos) {
        this.limiteDeAgendamentos = limiteDeAgendamentos;
    }
    
    public void adicionarHorarios(int dia, Date horarioInicial,
            Date horarioFinal){
        
        System.out.println("dnjfr");
        
        horarios.add(new Horario(dia, horarioInicial, horarioFinal, 2));
    }

    @Override
    public Medico build() {
        return new Medico(id, matricula, dataAdmissao, nome, sexo, email, 
                telefone, conselho, especialidade, horarios,ag);
    }
   
}
