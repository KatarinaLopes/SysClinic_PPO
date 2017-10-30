/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Katarina
 */
public class PacienteTest {
    
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
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testarIncluirAgendamento(){
        Paciente p = new Paciente(0, new Date(), "Nome", "F", new Date(), 
                "(99)9999-9999", null, "email@wmdb", "111.111.111-11", "123", 
                new ArrayList<Agendamento>());
        
        Date data = new GregorianCalendar(2017, 9, 30).getTime();
        
        System.out.println(data);
        
        List horarios = new ArrayList();
        
        horarios.add(new Horario
        (1, new Date(3600000), new Date(7200000)));
        
        horarios.add(new Horario
        (2, new Date(3600000), new Date(7200000)));
        
        horarios.add(new Horario(3, new Date(3600000), new Date(7200000)));
              
        Medico medico = new Medico(0, 12345, new Date(), "Nome", "F", null, 
                "(99)9999-9999", "CREMEPE 123", "dbdb", horarios, 
                new ArrayList());
        
        p.incluirAgendamento(data, medico, 1);
        
        assertEquals(1, p.getAgendamentos().size());
        
        for (int i = 0; i < 14; i++) {
            p.getAgendamentos().add(new Agendamento());
        }
        
        p.incluirAgendamento(data, medico, 1);
    }
}
