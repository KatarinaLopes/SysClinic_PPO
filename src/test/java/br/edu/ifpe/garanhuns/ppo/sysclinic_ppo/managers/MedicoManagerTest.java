/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.managers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
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
    
    MedicoManager medicoManager;
    
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
        medicoManager = new MedicoManager();
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
          
        
    }

    @Test
    public void testCadastrar() {
    }
    
}
