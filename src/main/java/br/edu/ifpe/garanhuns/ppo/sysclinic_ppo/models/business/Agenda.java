/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 *
 * @author Katarina
 */
@Embeddable
public class Agenda implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
            orphanRemoval = true)
    private List<Agendamento> agendamentos;

    @Deprecated
    public Agenda() {
    }
    
    public Agenda(List<Agendamento> agendamentos){
        this.agendamentos = agendamentos;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    /*public boolean verificarSeDataEPossivel(Date data) {

        Calendar calendar = new GregorianCalendar();

        calendar.setTime(data);

        for (Horario horario : medico.getHorarios()) {
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

        if (agendamentos.isEmpty() || agendamentos == null) {
            return false;
        }

        int qtde = 0;
        int qtdeDeAten = medico.pegarHorario(data).getLimiteDeAgendamentos();
        Date dia = agendamentos.get(0).getPeriodo();
        for (Agendamento agendamento1 : agendamentos) {

            if (!agendamento1.isRealizada()
                    && agendamento1.getDataPrevista().equals(data)
                    && agendamento1.getPeriodo().equals(dia)) {

                qtde++;
            }

            dia = agendamento1.getPeriodo();
        }
        

        return qtde < qtdeDeAten;

    }

    public boolean verificarSeDiaEstaLivre(int dia) {
        int qtde = 0;
        Calendar calendar = new GregorianCalendar();

        for (Agendamento agendamento1 : agendamentos) {
            calendar.setTime(agendamento1.getDataPrevista());
            if ((calendar.get(Calendar.DAY_OF_WEEK) - 1) == dia) {
                qtde++;
            }
        }
        
        int limite = retornarHorario(dia).getLimiteDeAgendamentos();

        return qtde < limite;
    }
*/
    public void adicionarAgendamento(Agendamento a) {

        agendamentos.add(a);
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
    
    public List<Agendamento> retornarAgendamentosNaoConcluidos(){
        List<Agendamento> agendamentosNaoConcluidos = new ArrayList<>();
        
        for (Agendamento agendamento : agendamentos) {
            if(!agendamento.isRealizada()){
                agendamentosNaoConcluidos.add(agendamento);
            }
        }
        
        return agendamentosNaoConcluidos;
    }

    public void atualizarAgendamentoHorario(Date antigo, Date novo) {
        System.out.println(antigo + " f "
                + novo);

        for (Agendamento agendamento : agendamentos) {
            System.out.println(agendamento.getPeriodo());
            if (agendamento.getPeriodo().equals(antigo) && 
                    !agendamento.isRealizada()) {
                agendamento.setPeriodo(novo);
            }
        }
    }
    
    public List<Paciente> listarPacientesAgendados(){
        List<Paciente> pacientesMarcados = new ArrayList<>();
        
        for (Agendamento agendamento : agendamentos) {
            pacientesMarcados.add(agendamento.getPaciente());
        }
        
        return pacientesMarcados;
    }
    
    public List<Agendamento> listarPacientesAgendados(Date 
            horarioInicial){
        List<Agendamento> pacientesAgendados = new ArrayList<>();
        
        for (Agendamento agendamento : agendamentos) {
            if(agendamento.getPeriodo().equals(horarioInicial) && 
                    !agendamento.isRealizada()){
                
                pacientesAgendados.add(agendamento);
            }
        }
        
        return pacientesAgendados;
    }

    public List<Agendamento> retornarAgendamentosPendentesPacientes(
            Paciente p){
        List<Agendamento> agendamentosPendentes = new ArrayList<>();
        
        for (Agendamento agendamento : agendamentos) {
            if(!agendamento.isRealizada() && agendamento.getPaciente().
                    getId() == p.getId()){
                agendamentosPendentes.add(agendamento);
            }
        }
        
        return agendamentosPendentes;
    }
    
    public List<Agendamento> retornarAgendamentosPendentes(){
        List<Agendamento> agendamentosPendentes = new ArrayList<>();
        
        for (Agendamento agendamento : agendamentos) {
            if(!agendamento.isRealizada()){
                agendamentosPendentes.add(agendamento);
            }
        }
        
        return agendamentosPendentes;
    }
}
