/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.managers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agenda;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.
        dao.DaoMedico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.
        manager.DaoGenerico;
import java.util.ArrayList;
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
public class MedicoManagerTest {
    
    private MedicoManager medicoManager;
    private DaoGenerico daoMedicos;
    
    public MedicoManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        daoMedicos = mock(DaoMedico.class);
        medicoManager = new MedicoManager((DaoMedico) daoMedicos);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void deveTestarValidarPassandoNoTeste() {
        int matricula = 1234;
        String conselho = "CONS/1234";
        
        Medico medico = new Medico();
        medico.setMatricula(matricula);
        medico.setConselho(conselho);
          
        when(daoMedicos.recuperarPorAtributo("matricula", matricula)).
                thenReturn(null);
        when(daoMedicos.recuperarPorAtributo("conselho", conselho)).
                thenReturn(null);
        
        medicoManager.validar(medico);
    }
    
    @Test
    public void deveTestarValidarMatriculaExistenteFalhando(){
        int matricula = 1234;
        String conselho = "CONS/1234";
        String mensagem = "";
        
        Medico medico = new Medico();
        medico.setMatricula(matricula);
        medico.setConselho(conselho);
        
        when(daoMedicos.recuperarPorAtributo("matricula", matricula)).
                thenReturn(medico);
        when(daoMedicos.recuperarPorAtributo("conselho", conselho)).
                thenReturn(null);
        
        try{
            medicoManager.validar(medico);
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("Este médico já está cadastrado, verifique se a "
                + "matrícula está correta", mensagem);
    }
    
    @Test
    public void deveTestarValidarPassandoConselhoExistenteFalhando(){
        int matricula = 1234;
        String conselho = "CONS/1234";
        String mensagem = "";
        
        Medico medico = new Medico();
        medico.setMatricula(matricula);
        medico.setConselho(conselho);
        
        when(daoMedicos.recuperarPorAtributo("matricula", matricula)).
                thenReturn(null);
        when(daoMedicos.recuperarPorAtributo("conselho", conselho)).
                thenReturn(medico);
        
        try{
            medicoManager.validar(medico);
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("Este número de registro do conselho já está cadastrado", 
                mensagem);
    }
    
    @Test
    public void deveTestarMarcarAgendamentoPassandoNoTeste() {
        Medico medico = new Medico();
        medico.setAgenda(new Agenda(new ArrayList<Agendamento>()));
        Agendamento agendamento = new Agendamento();
        
        int tamnahoAnterior = medico.getAgenda().getAgendamentos().size();
        
        medicoManager.marcarAgendamento(medico, agendamento);
        
        int tamanhoAtual = medico.getAgenda().getAgendamentos().size();
        
        assertEquals(tamnahoAnterior+1, tamanhoAtual);
        
    }
    
    @Test
    public void deveTestarMarcarAgendamento10AgendamentosPassandoNoTeste(){
        Medico medico = new Medico();
        medico.setAgenda(new Agenda(new ArrayList<Agendamento>()));
        Agendamento agendamento = new Agendamento();
        
        int tamanhoAnterior = medico.getAgenda().getAgendamentos().size();
        
        for (int i = 0; i < 10; i++) {
            medicoManager.marcarAgendamento(medico, agendamento);
        }
        
        int tamanhoAtual = medico.getAgenda().getAgendamentos().size();
        
        assertEquals(tamanhoAnterior+10, tamanhoAtual);
    }
    
    @Test
    public void deveTestarMarcarAgendamentoAgendamentoNullFalhando(){
        Medico medico = new Medico();
        medico.setAgenda(new Agenda(new ArrayList<Agendamento>()));
        String mensagem = "";
        
        try{
            medicoManager.marcarAgendamento(medico, null);
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("Agendamento ou médico são inválidos, recarregue a "
                + "página e tente novamente", mensagem);
    }
    
    @Test
    public void deveTestarMarcarAgendamentoMedicoNullFalhando(){
        Agendamento agendamento = new Agendamento();
        String mensagem = "";
        
        try{
            medicoManager.marcarAgendamento(null, agendamento);
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("Agendamento ou médico são inválidos, recarregue a "
                + "página e tente novamente", mensagem);
    }
    
    @Test
    public void deveTestarMarcarAgendamentoMedicoAgendamentoNullFalhando(){
        String mensagem = "";
        
        try{
            medicoManager.marcarAgendamento(null, null);
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("Agendamento ou médico são inválidos, recarregue a "
                + "página e tente novamente", mensagem);
    }

    @Test
    public void testRetornarDiasLivres() {
    }

    @Test
    public void testRetornarHorariosLivres() {
    }

    @Test
    public void testRetornarAgendamentosConcluidos_Paciente() {
    }

    @Test
    public void testRetornarAgendamentosConcluidos_0args() {
    }
    
}
