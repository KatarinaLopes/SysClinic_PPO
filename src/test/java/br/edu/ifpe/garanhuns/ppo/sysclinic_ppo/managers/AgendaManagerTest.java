/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.managers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agenda;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoAgenda;
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
public class AgendaManagerTest {
    
    private AgendaManager agendaManager;
    private DaoAgenda daoAgenda;
    
    public AgendaManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        daoAgenda = mock(DaoAgenda.class);
        agendaManager = new AgendaManager(daoAgenda);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testIsPrimeiroAcesso() {
    }

    @Test
    public void testSetPrimeiroAcesso() {
    }

    @Test
    public void testAtualizar() {
    }

    @Test
    public void testCadastrar() {
    }

    @Test
    public void testRecuperar() {
    }

    @Test
    public void devePassarMarcarAgendamento() {
        Agenda agenda = new Agenda();
        Agendamento agendamento = new Agendamento();
        
        when(daoAgenda.recuperar(1)).thenReturn(agenda);
        
        assertEquals(0, agenda.getAgendamentos().size());
        
        agendaManager.marcarAgendamento(agendamento);
        
        assertEquals(1, agenda.getAgendamentos().size());
    }

    @Test
    public void testVerificarCadastrarNovaAgenda() {
    }

    @Test
    public void testRetornarAgendamentosConcluidos_Paciente() {
    }

    @Test
    public void testRetornarAgendamentosConcluidos_0args() {
    }

    @Test
    public void testAlternarRetornarAgendamentosConcluidos() {
    }

    @Test
    public void testRetornarAgendamentosPendentes_0args() {
    }

    @Test
    public void testRetornarAgendamentosPendentes_Paciente() {
    }

    @Test
    public void testAlternarAgendamentosPendentes() {
    }

    @Test
    public void testExcluirAgendamento() {
    }

    @Test
    public void testRetornarAgendamentosDataAtual() {
    }

    @Test
    public void testAtualizarHorariosAgendamentos() {
    }

    @Test
    public void testRetornarScheduleAgendamentos() {
    }

    @Test
    public void testComporSchedule() {
    }

    @Test
    public void testAtualizarStatusAgendamento() {
    }

    @Test
    public void testRemarcar_3args_1() {
    }

    @Test
    public void testRemarcar_3args_2() {
    }
    
}
