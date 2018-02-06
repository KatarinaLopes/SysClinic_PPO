/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
public class MedicoTest {

    private Medico medico;

    public MedicoTest() {
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

        Horario h = new Horario(1, new Date(3600000), new Date(7200000), 15);
        Horario h1 = new Horario(2, new Date(3600000), new Date(7200000), 1);
        Horario h2 = new Horario(5, new Date(3600000), new Date(7200000), 3);

        List<Horario> horarios = new ArrayList<>();

        horarios.add(h);
        horarios.add(h1);
        horarios.add(h2);

        medico.setHorarios(horarios);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void deveTestarPegarDatasLivres() {

    }

    @Test
    public void testVerificarSeDataEstaLivre() {

        /*Agendamento a = new Agendamento(0, new Date(2017, 11, 13), 
                new Paciente(),medico,new Date(3600000), false);
        Agendamento a1 = new Agendamento(0, new Date(2017, 11, 13), 
                new Paciente(),medico,new Date(3600000), false);
        
        List agendamentos = new ArrayList();
        
        agendamentos.add(a);
        agendamentos.add(a1);
        
        medico.setAgendamento(agendamentos);
        
        boolean resultado = medico.
                verificarSeDataEstaLivre(new Date(2017, 11, 13));
        
        assertFalse(resultado);
        
        resultado = medico.verificarSeDataEstaLivre(new Date(2017, 11, 15));
        
        assertFalse(resultado);
        
        resultado = medico.verificarSeDataEstaLivre(null);
        
        assertFalse(resultado);
         */
    }

    @Test
    public void deveTestarRetornarLimiteDeVagasRetornandoNao0() {
        Date data = new Date(300);
                
        Date horarioInicial = new Date(300);
        Date horarioInicial2 = new Date(310);
        Date horarioFinal = new Date(100);
        
        Medico medico = new Medico();
        
        Horario horario1 = new Horario(4, horarioInicial, horarioFinal, 12);
        Horario horario2 = new Horario(1, horarioInicial, horarioFinal, 11);
        Horario horario3 = new Horario(4, horarioInicial2, horarioFinal, 10);
        
        List<Horario> horarios = new ArrayList<>();
        
        horarios.add(horario1);
        horarios.add(horario2);
        horarios.add(horario3);
        
        medico.setHorarios(horarios);
        
        int vagas = medico.retornarLimiteDeVagas(data, horarioInicial);
        
        assertEquals(0, vagas);
        
    }
    
    @Test
    public void deveTestarRetornarLimiteDeVagasRetornando0() {
        Date data = new Date(300);
                
        Date horarioInicial = new Date(300);
        Date horarioInicial2 = new Date(310);
        Date horarioFinal = new Date(100);
        
        Medico medico = new Medico();
        
        Horario horario1 = new Horario(3, horarioInicial, horarioFinal, 12);
        Horario horario2 = new Horario(1, horarioInicial, horarioFinal, 11);
        Horario horario3 = new Horario(3, horarioInicial2, horarioFinal, 10);
        
        List<Horario> horarios = new ArrayList<>();
        
        horarios.add(horario1);
        horarios.add(horario2);
        horarios.add(horario3);
        
        medico.setHorarios(horarios);
        
        int vagas = medico.retornarLimiteDeVagas(data, horarioInicial);
        
        assertEquals(12, vagas);
        
    }

}
