/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.builders;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Feed;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Mensagem;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Katarina
 */
@ManagedBean
@RequestScoped
public class BuilderPaciente implements BuilderGenerico<Paciente>{
    private int id;
    private Date dataAdmissao;
    
    @NotNull
    @Pattern(regexp = "[a-zA-Z\\sà-ùÀ-Ù]{0,}")
    private String nome;
     
    @NotNull @Pattern(regexp = "^[F | M]{1}")
    private String sexo;
    
    @NotNull @Past
    private Date dataNascimento;
    
    @NotNull @Pattern(regexp = "^\\([0-9]{2}\\)[2-9][0-9]{3,4}\\-[0-9]{4}")
    private String telefoneContato;
    
    @Pattern(regexp = "^\\([0-9]{2}\\)[2-9][0-9]{3,4}\\-[0-9]{4}")
    private String celular;
    
    @Pattern(regexp = "[a-zA-Z0-9\\s-_.]{1,}@[a-zA-Z0-9\\s-_.].[a-zA-"
                    + "Z0-9.]{1,}")
    private String email;
    
    @NotNull
    private String cpf;
    
    @NotNull
    private String senha;
    private List<Agendamento> agendamentos = new LinkedList();

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

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }
    
    @Override
    public Paciente build() {
        return new Paciente(id, dataAdmissao, nome, sexo, dataNascimento, 
                telefoneContato, celular, email, cpf, senha, new Feed(
                        new ArrayList<Mensagem>()));
    }
    
}
