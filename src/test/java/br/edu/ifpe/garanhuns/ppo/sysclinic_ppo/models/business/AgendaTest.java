/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business;

import com.sun.jmx.remote.internal.ArrayQueue;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Katarina
 */
public class AgendaTest {
    
    public AgendaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetAgendamentos() {
    }

    @Test
    public void testSetAgendamentos() {
    }

    @Test
    public void deveTestarAdicionarAgendamento() {
        Agenda agenda = new Agenda(new ArrayList<Agendamento>());
        
        agenda.adicionarAgendamento(mock(Agendamento.class));
        agenda.adicionarAgendamento(mock(Agendamento.class));
        
        int tamanho = agenda.getAgendamentos().size();
        
        assertEquals(2, tamanho);
    }

    @Test
    public void testRetornarAgendamentosConcluidos() {
        Agenda a = new Agenda(new ArrayList<Agendamento>());
        
        Paciente p = mock(Paciente.class);
        
        a.getAgendamentos().add(new Agendamento(0, new Date(), p , 
                new Date(), true));
        a.getAgendamentos().add(new Agendamento(0, new Date(), p, 
                new Date(), false));
        
        List<Agendamento> recuperados = a.retornarAgendamentosConcluidos();
        
        assertEquals(1, recuperados.size());
    }
    
    @Test
    public void testRetornarAgendamentosConcluidos2Pacientes() {
        Agenda a = new Agenda(new ArrayList<Agendamento>());
        
        Paciente p = mock(Paciente.class);
        Paciente paciente2 = mock(Paciente.class);
        
        a.getAgendamentos().add(new Agendamento(0, new Date(), p , 
                new Date(), true));
        a.getAgendamentos().add(new Agendamento(0, new Date(), p, 
                new Date(), false));
         a.getAgendamentos().add(new Agendamento(0, new Date(), paciente2 , 
                new Date(), true));
        a.getAgendamentos().add(new Agendamento(0, new Date(), paciente2, 
                new Date(), false));
        
        List<Agendamento> recuperados = a.retornarAgendamentosConcluidos();
        
        assertEquals(2, recuperados.size());
    }
    
    @Test
    public void deveTestarRetornarAgendamentosConcluidosPacientes(){
        Agenda a = new Agenda(new ArrayList<Agendamento>());
        
        Paciente p = new Paciente(1, null, null, null, null, null, null, null,
                null, null, null);
        Paciente paciente2 = new Paciente(2, null, null, null, null, null, 
                null, null, null, null, null);
        
        a.getAgendamentos().add(new Agendamento(0, new Date(), p , 
                new Date(), true));
        a.getAgendamentos().add(new Agendamento(0, new Date(), p, 
                new Date(), false));
         a.getAgendamentos().add(new Agendamento(0, new Date(), paciente2 , 
                new Date(), true));
        a.getAgendamentos().add(new Agendamento(0, new Date(), paciente2, 
                new Date(), false));
        
        List<Agendamento> recuperados = 
                a.retornarAgendamentosConcluidosPacientes(p);
        
        assertEquals(1, recuperados.size());
    }
    
    @Test
    public void devePassarAtualizarAgendamentoHorario(){
        Date horario1 = new Date();
        Date horario2 = new Date(120);
        Date horario3 = new Date(200);
        Date horarioAtualizado = new Date(300);
        
        Agenda agenda = new Agenda(new ArrayList<Agendamento>());
        Agendamento agendamento1 = new Agendamento(1, null, null, horario1, 
                false);
        Agendamento agendamento2 = new Agendamento(2, null, null, horario2, 
                false);
        Agendamento agendamento3 = new Agendamento(3, null, null, horario3, 
                false);
        agenda.getAgendamentos().add(agendamento1);
        agenda.getAgendamentos().add(agendamento2);
        agenda.getAgendamentos().add(agendamento3);
        
        agenda.atualizarAgendamentoHorario(horario1, horarioAtualizado);
        
        Agendamento atualizado = agenda.getAgendamentos().get(0);
        
        assertEquals(1, atualizado.getId());
        assertEquals(horarioAtualizado, atualizado.getPeriodo());
        
    }
    
    @Test
    public void devePassarAtualizarAgendamentoHorarioConcluido(){
        Date horario1 = new Date();
        Date horario2 = new Date(200);
        Date horarioAtualizado = new Date(300);
        
        Agenda agenda = new Agenda(new ArrayList<Agendamento>());
        Agendamento agendamento1 = new Agendamento(1, null, null, horario1, 
                false);
        Agendamento agendamento2 = new Agendamento(2, null, null, horario1, 
                true);
        Agendamento agendamento3 = new Agendamento(3, null, null, horario2, 
                false);
        agenda.getAgendamentos().add(agendamento1);
        agenda.getAgendamentos().add(agendamento2);
        agenda.getAgendamentos().add(agendamento3);
        
        agenda.atualizarAgendamentoHorario(horario1, horarioAtualizado);
        
        Agendamento atualizado = agenda.getAgendamentos().get(0);
        Agendamento naoAtualizado = agenda.getAgendamentos().get(1);
        
        assertEquals(1, atualizado.getId());
        assertEquals(horarioAtualizado, atualizado.getPeriodo());
        assertEquals(2, naoAtualizado.getId());
        assertEquals(horario1, naoAtualizado.getPeriodo());
        
    }
    
    @Test
    public void deveTestarListarPacientesAgendados(){
        Paciente paciente = mock(Paciente.class);
        
        Agenda agenda = new Agenda(new ArrayList<Agendamento>());
        Agendamento agendamento1 = new Agendamento(1, null, paciente, null, 
                false);
        Agendamento agendamento2 = new Agendamento(2, null, paciente, null, 
                true);
        Agendamento agendamento3 = new Agendamento(3, null, paciente, null, 
                false);
        agenda.getAgendamentos().add(agendamento1);
        agenda.getAgendamentos().add(agendamento2);
        agenda.getAgendamentos().add(agendamento3);
        
        List retorno = agenda.listarPacientesAgendados();
        
        assertNotNull(retorno);
        assertEquals(3, retorno.size());
    }
    
    @Test
    public void deveTestarListarPacientesAgendados_1Arg(){
        Paciente paciente = mock(Paciente.class);
        
        Date horario1 = new Date();
        Date horario2 = new Date(200);
        
        Agenda agenda = new Agenda(new ArrayList<Agendamento>());
        Agendamento agendamento1 = new Agendamento(1, null, paciente, horario1, 
                false);
        Agendamento agendamento2 = new Agendamento(2, null, paciente, horario1, 
                true);
        Agendamento agendamento3 = new Agendamento(3, null, paciente, horario2, 
                false);
        agenda.getAgendamentos().add(agendamento1);
        agenda.getAgendamentos().add(agendamento2);
        agenda.getAgendamentos().add(agendamento3);
        
        List retorno = agenda.listarPacientesAgendados(horario1);
        
        assertNotNull(retorno);
        assertEquals(1, retorno.size());
    }
    
    @Test
    public void deveTestarRetornarAgendamentosNaoConcluidos(){
        Agenda a = new Agenda(new ArrayList<Agendamento>());
        
        Paciente p = new Paciente(1, null, null, null, null, null, null, null,
                null, null, null);
        Paciente paciente2 = new Paciente(2, null, null, null, null, null, 
                null, null, null, null, null);
        
        a.getAgendamentos().add(new Agendamento(0, new Date(), p , 
                new Date(), true));
        a.getAgendamentos().add(new Agendamento(0, new Date(), p, 
                new Date(), false));
         a.getAgendamentos().add(new Agendamento(0, new Date(), paciente2 , 
                new Date(), true));
        a.getAgendamentos().add(new Agendamento(0, new Date(), paciente2, 
                new Date(), false));
        
        List<Agendamento> recuperados = a.retornarAgendamentosNaoConcluidos();
        
        assertEquals(2, recuperados.size());
    }
    
}
