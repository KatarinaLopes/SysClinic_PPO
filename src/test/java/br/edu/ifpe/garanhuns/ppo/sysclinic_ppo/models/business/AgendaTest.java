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
    
    Medico medico;
    Medico medico2;
    Agenda agenda;
    Date data;
    Date data2;
    Date horario;
    Date horario2;
    
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
        medico = new Medico();
        medico.setMatricula(1231);
        medico2 = new Medico();
        medico2.setMatricula(1234);
        Paciente paciente = new Paciente();
        data = new Date();
        data2 = new Date(300);
        horario = new Date();
        horario2 = new Date(300);
        
        agenda = new Agenda(new ArrayList<Agendamento>());
        
        Agendamento agendamentoDataHorarioNaoRealizada = new Agendamento(1, 
                data, paciente, medico, horario, false);
        Agendamento agendamentoDataHorario2NaoRealizada = new Agendamento(2, 
                data, paciente, medico, horario2, false);
        Agendamento agendamentoData2HorarioNaoRealizada = new Agendamento(3, 
                data2, paciente, medico, horario, false);
        Agendamento agendamentoData2Horario2NaoRealizada = new Agendamento(4, 
                data2, paciente, medico, horario2, false);
        Agendamento agendamentoDataHorarioRealizada = new Agendamento(5, 
                data, paciente, medico, horario, true);
        Agendamento agendamentoDataHorario2Realizada = new Agendamento(6, 
                data, paciente, medico, horario2, true);
        Agendamento agendamentoData2HorarioRealizada = new Agendamento(7, 
                data2, paciente, medico, horario, true);
        Agendamento agendamentoData2Horario2Realizada = new Agendamento(8, 
                data2, paciente, medico, horario2, true);
         Agendamento agendamentoDataHorarioNaoRealizadaMedico2 = 
                new Agendamento(9, data, paciente, medico2, horario, false);
        Agendamento agendamentoDataHorario2NaoRealizadaMedico2 = 
                new Agendamento(10, 
                data, paciente, medico, horario2, false);
        Agendamento agendamentoData2HorarioNaoRealizadaMedico2 = 
                new Agendamento(11, data2, paciente, medico2, horario, false);
        Agendamento agendamentoData2Horario2NaoRealizadaMedico2 = 
                new Agendamento(12, data2, paciente, medico2, horario2, false);
        Agendamento agendamentoDataHorarioRealizadaMedico2= 
                new Agendamento(13, data, paciente, medico2, horario, true);
        Agendamento agendamentoDataHorario2RealizadaMedico2 = 
                new Agendamento(14,data, paciente, medico2, horario2, true);
        Agendamento agendamentoData2HorarioRealizadaMedico2 = 
                new Agendamento(15, data2, paciente, medico2, horario, true);
        Agendamento agendamentoData2Horario2RealizadaMedico2 = 
                new Agendamento(16, data2, paciente, medico2, horario2, true);
        
        agenda.getAgendamentos().add(agendamentoDataHorarioNaoRealizada);
        agenda.getAgendamentos().add(agendamentoDataHorario2NaoRealizada);
        agenda.getAgendamentos().add(agendamentoData2HorarioNaoRealizada);
        agenda.getAgendamentos().add(agendamentoData2Horario2NaoRealizada);
        agenda.getAgendamentos().add(agendamentoDataHorarioRealizada);
        agenda.getAgendamentos().add(agendamentoData2HorarioRealizada);
        agenda.getAgendamentos().add(agendamentoData2HorarioRealizada);
        agenda.getAgendamentos().add(agendamentoData2Horario2Realizada);
        agenda.getAgendamentos().
                add(agendamentoDataHorarioNaoRealizadaMedico2);
        agenda.getAgendamentos().
                add(agendamentoDataHorario2NaoRealizadaMedico2);
        agenda.getAgendamentos().
                add(agendamentoData2HorarioNaoRealizadaMedico2);
        agenda.getAgendamentos().
                add(agendamentoData2Horario2NaoRealizadaMedico2);
        agenda.getAgendamentos().
                add(agendamentoDataHorarioRealizadaMedico2);
        agenda.getAgendamentos().add(agendamentoData2HorarioRealizadaMedico2);
        agenda.getAgendamentos().add(agendamentoData2HorarioRealizadaMedico2);
        agenda.getAgendamentos().
                add(agendamentoData2Horario2RealizadaMedico2);
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
    

    /*@Test
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
*/    

    @Test
    public void devePassarDataEstaDisponivel() {
        
        
    }

    @Test
    public void testRetornarAgendamentos() {
        
        
        List<Agendamento> retorno = agenda.
                retornarAgendamentos(medico, data, horario);
        
        assertEquals(1, retorno.size());
        
    }

    @Test
    public void devePassarAtualizarDataAgendamento() {
        int idAgendamento1 = 1;
        int idAgendamento2 = 2;
        Date novaData = new Date(10);
        Date novoPeriodo = new Date(10);
        
        agenda.atualizarDataAgendamento(idAgendamento1, novaData, novoPeriodo);
        agenda.atualizarDataAgendamento(idAgendamento2, novaData, horario2);
        
        Agendamento agendamento1 = agenda.getAgendamentos().get(0);
        Agendamento agendamento2 = agenda.getAgendamentos().get(1);
        
        assertEquals(idAgendamento1, agendamento1.getId());
        assertEquals(idAgendamento2, agendamento2.getId());
        
        assertEquals(novaData, agendamento1.getDataPrevista());
        assertEquals(novaData, agendamento2.getDataPrevista());
        
        assertEquals(novoPeriodo, agendamento1.getPeriodo());
        assertEquals(horario2, agendamento2.getPeriodo());
        
    }
    
    @Test
    public void deveLancarExcecaoIdInvalidoAtualizarDataAgendamento(){
        int idAgendamento = 17;
        Date novaData = new Date(10);
        Date novoPeriodo = new Date(10);
        String message = "";
        
        try{
            agenda.atualizarDataAgendamento(idAgendamento, novaData, 
                    novoPeriodo);
            fail();
        }catch(IllegalArgumentException ex){
            message = ex.getMessage();
        }
        
        assertEquals("Este agendamento não está cadastrado", message);
    }
    
    @Test
    public void deveLancarExcecaoId0AtualizarDataAgendamento(){
        int idAgendamento = 0;
        Date novaData = new Date(10);
        Date novoPeriodo = new Date(10);
        String message = "";
        
        try{
            agenda.atualizarDataAgendamento(idAgendamento, novaData, 
                    novoPeriodo);
            fail();
        }catch(IllegalArgumentException ex){
            message = ex.getMessage();
        }
        
        assertEquals("Agendamento, data ou horário não podem estar vazios", 
                message);
    }
    
    @Test
    public void deveLancarExcecaoDataNulaAtualizarDataAgendamento(){
        int idAgendamento = 16;
        Date novaData = null;
        Date novoPeriodo = new Date(10);
        String message = "";
        
        try{
            agenda.atualizarDataAgendamento(idAgendamento, novaData, 
                    novoPeriodo);
            fail();
        }catch(IllegalArgumentException ex){
            message = ex.getMessage();
        }
        
        assertEquals("Agendamento, data ou horário não podem estar vazios", 
                message);
    }@Test
    public void deveLancarExcecaoHorarioNuloAtualizarDataAgendamento(){
        int idAgendamento = 16;
        Date novaData = new Date(10);
        Date novoPeriodo = null;
        String message = "";
        
        try{
            agenda.atualizarDataAgendamento(idAgendamento, novaData, 
                    novoPeriodo);
            fail();
        }catch(IllegalArgumentException ex){
            message = ex.getMessage();
        }
        
        assertEquals("Agendamento, data ou horário não podem estar vazios", 
                message);
    }
    
    @Test
    public void deveLancarExcecaoId1NegativoAtualizarDataAgendamento(){
        int idAgendamento = -1;
        Date novaData = new Date(10);
        Date novoPeriodo = new Date(10);
        String message = "";
        
        try{
            agenda.atualizarDataAgendamento(idAgendamento, novaData, 
                    novoPeriodo);
            fail();
        }catch(IllegalArgumentException ex){
            message = ex.getMessage();
        }
        
        assertEquals("Agendamento, data ou horário não podem estar vazios", 
                message);
    }
}
