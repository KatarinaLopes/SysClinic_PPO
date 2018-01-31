/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.managers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoMedico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.DaoGenerico;
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
    public void testCadastrar() {
    }

    @Test
    public void testRecuperarTodos() {
    }
    
}
