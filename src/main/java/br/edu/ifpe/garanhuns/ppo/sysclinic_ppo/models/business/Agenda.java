/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Katarina
 */
@Embeddable
public class Agenda implements Serializable{
    
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Agendamento> agendamentos = new ArrayList<>();

    @Deprecated
    public Agenda() {
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

    /*
    public List<Integer> pegarDiasLivres() {
        List<Integer> dias = new ArrayList();

        for (Horario horario : medico.getHorarios()) {

            dias.add(horario.getDia());
        }

        return dias;
    }

    public List<Horario> pegarHorariosLivres(Date data) {

        Calendar c = new GregorianCalendar();
        c.setTime(data);
        int dia = c.get(Calendar.DAY_OF_WEEK) - 1;
        List<Horario> horariosDisponiveis = new ArrayList();

        for (Horario horario : medico.getHorarios()) {

            if (dia == horario.getDia()) {
                //if (verificarSeDiaEstaLivre(dia)) {
                    horariosDisponiveis.add(horario);
                //}
            }
        }

        return horariosDisponiveis;
    }
    
    public Horario retornarHorario(int dia){
        for (Horario horario : medico.getHorarios()) {
            if(horario.getDia() == dia){
                return horario;
               
            }
            
        }
        
        return null;
    }*/
    
    public void adicionarAgendamento(Agendamento a){
        
        agendamentos.add(a);
    }
    
}
