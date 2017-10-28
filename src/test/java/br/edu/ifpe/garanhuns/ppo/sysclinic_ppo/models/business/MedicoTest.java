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
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void deveTestarPegarDatasLivres(){
        Horario h = new Horario("Seg", new Date(3600000), new Date(7200000),0);
        Horario h1 = new Horario("Ter", new Date(3600000), new Date(7200000),2);
        Horario h2 = new Horario("Sex", new Date(3600000), new Date(7200000),
                15);
        
        List<Horario> horarios = new ArrayList<>();
        
        horarios.add(h);
        horarios.add(h1);
        horarios.add(h2);
        
        medico.setHorarios(horarios);
        
        List<String> dias = new ArrayList();
        
        //String senha = ""+this.senha+"";
        
        
                                                
    }
    
}
