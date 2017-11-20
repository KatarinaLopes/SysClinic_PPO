/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class Medico implements Serializable {

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

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Horario> horarios;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.id;
        hash = 61 * hash + this.matricula;
        hash = 61 * hash + Objects.hashCode(this.conselho);
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
        final Medico other = (Medico) obj;
        return true;
    }

    public boolean verificarSeDataEPossivel(Date data) {

        Calendar calendar = new GregorianCalendar();

        calendar.setTime(data);

        for (Horario horario : horarios) {
            if (horario.getDia() == calendar.get(Calendar.DAY_OF_WEEK)) {
                return true;
            }
        }

        return false;

    }

    public boolean verificarSeDataEstaLivre(Date data) {

        if (data == null) {
            return false;
        }

        if (!verificarSeDataEPossivel(data)) {
            return false;
        }

        if (agendamento.isEmpty() || agendamento == null) {
            return false;
        }

        int qtde = 0;
        Date dia = agendamento.get(0).getPeriodo();
        for (Agendamento agendamento1 : agendamento) {

            if (!agendamento1.isRealizada()
                    && agendamento1.getDataPrevista().equals(data)
                    && agendamento1.getPeriodo().equals(dia)) {

                qtde++;
            }

            dia = agendamento1.getPeriodo();
        }

        return qtde < 2;

    }

    public List<Integer> pegarDiasLivres() {
        List<Integer> dias = new ArrayList();

        for (Horario horario : horarios) {
            dias.add(horario.getDia());
        }

        return dias;
    }

    public List<Horario> pegarHorariosLivres(Date data) {
       
        Calendar c = new GregorianCalendar();
        c.setTime(data);
        int dia = c.get(Calendar.DAY_OF_WEEK) -1;
        List<Horario> horariosDisponiveis = new ArrayList();
        
        for (Horario horario : this.horarios) {
            if (dia == horario.getDia()) {
                horariosDisponiveis.add(horario);
            }
        }
        
        return horariosDisponiveis;
    }
}
