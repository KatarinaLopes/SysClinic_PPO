/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Katarina
 */
@Entity
public class Agenda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
            orphanRemoval = true)
    private List<Agendamento> agendamentos;

    @Deprecated
    public Agenda() {
    }

    public Agenda(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    public boolean dataEstaDisponivel(Date data, Date horarioInicial,
            Medico medico) {
        int vagasPreenchidas = retornarAgendamentos(medico, data,
                horarioInicial).size();
        int limiteDeVagas = medico.retornarLimiteDeVagas(data, horarioInicial);

        return vagasPreenchidas < limiteDeVagas;
    }

    public List<Agendamento> retornarAgendamentos(Medico medico, Date data,
            Date horarioInicial) {
        List<Agendamento> agendamentosMedico = new ArrayList<>();

        for (Agendamento agendamento : agendamentos) {
            if (!agendamento.isRealizada()
                    && agendamento.getMedico().equals(medico)
                    && agendamento.getDataPrevista().equals(data)
                    && agendamento.getPeriodo().equals(horarioInicial)) {
                agendamentosMedico.add(agendamento);
            }
        }

        return agendamentosMedico;
    }

    public void adicionarAgendamento(Agendamento a) {
        validarAgendamento(a);
        agendamentos.add(a);
    }

    public boolean excluirAgendamento(Agendamento a) {
        return agendamentos.remove(a);
    }

    public boolean existeAgendamento(Agendamento agendamento) {
        return agendamentos.contains(agendamento);
    }

    public List<Agendamento> retornarAgendamentosConcluidos() {
        List<Agendamento> agendamentosConcluidos = new ArrayList<>();

        for (Agendamento agendamento : agendamentos) {
            if (agendamento.isRealizada() == true) {
                agendamentosConcluidos.add(agendamento);
            }
        }

        return agendamentosConcluidos;
    }

    public List<Agendamento> retornarAgendamentosConcluidosPacientes(Paciente p) {
        List<Agendamento> agendamentosConcluidos = new ArrayList<>();

        for (Agendamento agendamento : agendamentos) {
            if (agendamento.isRealizada()
                    && agendamento.getPaciente().getId() == p.getId()) {
                agendamentosConcluidos.add(agendamento);
            }
        }

        return agendamentosConcluidos;
    }

    public List<Agendamento> retornarAgendamentosNaoConcluidos() {
        List<Agendamento> agendamentosNaoConcluidos = new ArrayList<>();

        for (Agendamento agendamento : agendamentos) {
            if (!agendamento.isRealizada()) {
                agendamentosNaoConcluidos.add(agendamento);
            }
        }

        return agendamentosNaoConcluidos;
    }

    public void atualizarAgendamentoHorario(Date antigo, Date novo,
            Medico medico) {
        for (Agendamento agendamento : agendamentos) {
            if (agendamento.getMedico().equals(medico)
                    && agendamento.getPeriodo().equals(antigo)
                    && !agendamento.isRealizada()) {
                agendamento.setPeriodo(novo);
            }
        }
    }

    public List<Agendamento> retornarAgendamentos(Medico medico) {
        List<Agendamento> agendamentosPorMedico = new ArrayList<>();
        for (Agendamento agendamento : agendamentos) {
            if (agendamento.getMedico().equals(medico)) {
                agendamentosPorMedico.add(agendamento);
            }
        }

        return agendamentosPorMedico;
    }

    public List<Paciente> listarPacientesAgendados() {
        List<Paciente> pacientesMarcados = new ArrayList<>();

        for (Agendamento agendamento : agendamentos) {
            pacientesMarcados.add(agendamento.getPaciente());
        }

        return pacientesMarcados;
    }

    public List<Agendamento> listarPacientesAgendados(Date horarioInicial) {
        List<Agendamento> pacientesAgendados = new ArrayList<>();

        for (Agendamento agendamento : agendamentos) {
            if (agendamento.getPeriodo().equals(horarioInicial)
                    && !agendamento.isRealizada()) {

                pacientesAgendados.add(agendamento);
            }
        }

        return pacientesAgendados;
    }

    public List<Agendamento> retornarAgendamentosPendentesPacientes(
            Paciente p) {
        List<Agendamento> agendamentosPendentes = new ArrayList<>();

        for (Agendamento agendamento : agendamentos) {
            if (!agendamento.isRealizada() && agendamento.getPaciente().
                    getId() == p.getId()) {
                agendamentosPendentes.add(agendamento);
            }
        }

        return agendamentosPendentes;
    }

    public List<Agendamento> retornarAgendamentosPendentes() {
        List<Agendamento> agendamentosPendentes = new ArrayList<>();

        for (Agendamento agendamento : agendamentos) {
            if (!agendamento.isRealizada()) {
                agendamentosPendentes.add(agendamento);
            }
        }

        return agendamentosPendentes;
    }

    public List<Agendamento> retornarAgendamentosDataAtual() {
        Date date = new Date();

        DateFormat df = new SimpleDateFormat("dd/MM/YYYY");

        String dateHoje = df.format(date);

        List<Agendamento> agendamentosDataAtual = new ArrayList<>();

        for (Agendamento agendamento : agendamentos) {
            String dateAgendamento = df.format(agendamento.getDataPrevista());
            if (dateAgendamento.equals(dateHoje)) {
                agendamentosDataAtual.add(agendamento);
            }
        }

        return agendamentosDataAtual;
    }

    public void atualizarDataAgendamento(Agendamento agendamento,
            Date novaData, Date novoHorario)
            throws IllegalArgumentException {

        if (agendamento == null || novaData == null || novoHorario == null) {
            throw new IllegalArgumentException("Agendamento, data ou horário "
                    + "não podem estar vazios");
        }

        int posicaoAgendamento = agendamentos.indexOf(agendamento);
        Agendamento agendamentoRecuperado = agendamentos.
                get(posicaoAgendamento);

        agendamentoRecuperado.setDataPrevista(novaData);
        agendamentoRecuperado.setPeriodo(novoHorario);

        agendamentos.set(posicaoAgendamento, agendamentoRecuperado);
    }

    public List<Date> retornarDatasPossiveis(int qtdeDatas, int diaNovo) {

        List<Date> datas = new ArrayList<>(qtdeDatas);

        Calendar calendar = Calendar.getInstance();
        int diaHoje = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        int diasDeIntervalo = diaHoje < diaNovo ? diaNovo - diaHoje
                : diaHoje - diaNovo;

        calendar.add(Calendar.DATE, diasDeIntervalo);

        Date dataInicial = calendar.getTime();
        datas.add(dataInicial);

        for (int i = 1; i < qtdeDatas; i++) {
            calendar.setTime(dataInicial);
            calendar.add(Calendar.DATE, 7);
            dataInicial = calendar.getTime();

            datas.add(dataInicial);
        }

        return datas;

    }

    public List<Agendamento> retornarAgendamentos(int dia, Medico medico,
            boolean realizada) {
        List<Agendamento> agendamentosDiaMedico = new ArrayList<>();

        Calendar calendar = new GregorianCalendar();

        for (Agendamento agendamento : agendamentos) {

            if (agendamento.getMedico().equals(medico)
                    && agendamento.isRealizada() == realizada) {

                calendar.setTime(agendamento.getDataPrevista());

                int diaAgendamento = calendar.get(Calendar.DAY_OF_WEEK) - 1;

                if (diaAgendamento == dia) {
                    agendamentosDiaMedico.add(agendamento);
                }
            }
        }

        return agendamentosDiaMedico;
    }

    public void remarcarAgendamento(int diaAnterior, Horario horarioNovo,
            Medico medico) {

        List<Agendamento> agendamentosDiaMedico = retornarAgendamentos(
                diaAnterior, medico, false);

        if (agendamentosDiaMedico.size() == 0) {
            return;
        }

        int qtde = agendamentosDiaMedico.size();
        int limite = horarioNovo.getLimiteDeAgendamentos();

        int diasNecessarios = 0;

        if (qtde < limite) {
            diasNecessarios = 1;
        } else if (qtde % limite == 0) {
            diasNecessarios = qtde / limite;
        } else {
            diasNecessarios = (int) (qtde / limite) + 1;
        }

        List<Date> datas = retornarDatasPossiveis(qtde, horarioNovo.getDia());

        int posicao = 0;
        int qtdeAgendada = 0;
        for (Agendamento agendamento : agendamentosDiaMedico) {
            atualizarDataAgendamento(agendamento, datas.get(posicao),
                    horarioNovo.getHorarioInicial());
            qtdeAgendada++;

            if (qtdeAgendada == limite) {
                posicao++;
            }
        }
    }
    

    public void validarAgendamento(Agendamento agendamento) {
        
        if(agendamento == null){
            throw new IllegalArgumentException("Agendamento inválido");
        }
        
        if (agendamento.getDataPrevista() == null) {
            throw new IllegalArgumentException("Data está inválida.");
        }

        if (agendamento.getMedico() == null) {
            throw new IllegalArgumentException("Médico está inválido.");
        }

        if (agendamento.getPaciente() == null) {
            throw new IllegalArgumentException("Paciente está inválido.");
        }

        if (agendamento.getPeriodo() == null) {
            throw new IllegalArgumentException("Horário está inválido.");
        }

        if (existeAgendamento(agendamento)) {
            throw new IllegalArgumentException("Já existe um agendamento "
                    + "igual a este");
        }
    }
}
