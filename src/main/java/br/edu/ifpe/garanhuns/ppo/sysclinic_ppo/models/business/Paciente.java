/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Katarina
 */
@Entity
public class Paciente implements Serializable, Cloneable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Temporal(TemporalType.DATE)
    private Date dataAdmissao;

    @Column(nullable = false, length = 70)
    private String nome;
    @Column(nullable = false, length = 1)
    private String sexo;

    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @Column(nullable = false, length = 16)
    private String telefoneContato;
    @Column(length = 16)
    private String celular;
    @Column
    private String email;

    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(nullable = false)
    private String senha;

    //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //private List<Agendamento> agendamentos;

    @Embedded
    private Feed feed;
    
    @Deprecated
    public Paciente() {
    }

    public Paciente(int id, Date dataAdmissao, String nome, String sexo, 
            Date dataNascimento, String telefoneContato, String celular, 
            String email, String cpf, String senha, Feed feed) {
        this.id = id;
        this.dataAdmissao = dataAdmissao;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.telefoneContato = telefoneContato;
        this.celular = celular;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
        //this.agendamentos = agendamentos;
        this.feed = feed;
    }

    public int getId() {
        return id;
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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefoneContato() {
        return telefoneContato;
    }

    public void setTelefoneContato(String telefoneContato) {
        this.telefoneContato = telefoneContato;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    /*public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }
    
    public void incluirAgendamento(Agendamento a){
        agendamentos.add(a);
    }*/

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        hash = 37 * hash + Objects.hashCode(this.dataAdmissao);
        hash = 37 * hash + Objects.hashCode(this.nome);
        hash = 37 * hash + Objects.hashCode(this.sexo);
        hash = 37 * hash + Objects.hashCode(this.dataNascimento);
        hash = 37 * hash + Objects.hashCode(this.cpf);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Paciente other = (Paciente) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.sexo, other.sexo)) {
            return false;
        }
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        if (!Objects.equals(this.dataAdmissao, other.dataAdmissao)) {
            return false;
        }
        if (!Objects.equals(this.dataNascimento, other.dataNascimento)) {
            return false;
        }
        return true;
    }

 }
