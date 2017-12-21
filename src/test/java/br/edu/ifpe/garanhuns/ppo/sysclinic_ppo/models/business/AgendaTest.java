/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business;

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
    public void testAdicionarAgendamento() {
    }

    @Test
    public void testRetornarAgendamentosConcluidos() {
        Agenda a = new Agenda();
        
        Medico m = mock(Medico.class);
        Paciente p = mock(Paciente.class);
        
        a.getAgendamentos().add(new Agendamento(0, new Date(), p , m, 
                new Date(), true));
        a.getAgendamentos().add(new Agendamento(0, new Date(), p, m, 
                new Date(), false));
        
        List<Agendamento> recuperados = a.retornarAgendamentosConcluidos();
        
        assertEquals(1, recuperados.size());
    }
    
}
