/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoPaciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.hibernateutil.HibernateUtil;
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
public class PacienteTest {
    Paciente paciente;
    
    public PacienteTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        paciente = (Paciente) HibernateUtil.getInstance().
                recover("from Paciente where id = 1").get(0);
    }
    
    @After
    public void tearDown() {
    }


    @Test(expected = IllegalArgumentException.class)
    public void deveTestarRealizarAgendamentoPassandoAgendamentoNulo() {
        Agendamento agendamento = null;
        
        paciente.realizarAgendamento(agendamento);
    }
    
    @Test
    public void deveTestarRealizarAgendamentoPassandoAgendamentoCorreto(){
        Agendamento agendamento = new Agendamento();
   
        int tamanhoInicial = paciente.getAgendamentos().size();
        
        assertEquals(2, tamanhoInicial, 0);
        
        paciente.realizarAgendamento(agendamento);
        
        int tamanhoAtual = paciente.getAgendamentos().size();
        
        int diferenca = tamanhoAtual - tamanhoInicial;
        
        assertEquals(1, diferenca);
        
        new DaoPaciente().atualizar(paciente);
        
        Agendamento agendamentoRecuperado = (Agendamento) 
                HibernateUtil.getInstance().
                recover("from Agendamento").
                get(0);
        
        assertNotNull(agendamentoRecuperado);
    }
}
