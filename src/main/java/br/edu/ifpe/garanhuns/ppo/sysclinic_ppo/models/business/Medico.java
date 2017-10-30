/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Katarina
 */
@Entity
public class Medico {
    @Id
    @GeneratedValue
    private int id;
    
    @Column(nullable = false, unique = true)
    private int matricula;
    @Temporal(TemporalType.DATE)
    private Date dataAdmissao;
    @Column(nullable = false, length = 70)
    private String nome;
    @Column(nullable = false, length = 1)
    private String sexo;
    
    private String email;
    
    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false, unique = true)
    private String conselho;
    
    @Column(nullable = false, length = 20)
    private String especialidade;
    
    @ElementCollection
    private List<Horario> horarios;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Agendamento> agendamento;
    
    @Deprecated
    public Medico() {
    }   

    public Medico(int id, int matricula, Date dataAdmissao, String nome, 
            String sexo, String email, String telefone, String conselho, 
            String especialidade, List<Horario> horarios, 
            List<Agendamento> agendamentos) {
        this.id = id;
        this.matricula = matricula;
        this.dataAdmissao = dataAdmissao;
        this.nome = nome;
        this.sexo = sexo;
        this.email = email;
        this.telefone = telefone;
        this.conselho = conselho;
        this.especialidade = especialidade;
        this.horarios = horarios;
        this.agendamento = agendamentos;
    }

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

    public List<Agendamento> getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(List<Agendamento> agendamento) {
        this.agendamento = agendamento;
    }
    
    
    
    public List pegarDatasLivres(){
        return null;
    }
}
