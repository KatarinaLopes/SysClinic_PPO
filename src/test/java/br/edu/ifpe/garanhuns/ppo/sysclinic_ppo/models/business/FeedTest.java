/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business;

import java.util.ArrayList;
import java.util.Date;
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
public class FeedTest {
    
    private Feed feed;
    
    public FeedTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        feed = new Feed(new ArrayList<Mensagem>());
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void deveTestarIncluirMensagemNaoExistentePassando() {
        
        Mensagem mensagem = new Mensagem(new Date(), "Mensagem de teste");
        
        feed.incluirMensagem(mensagem);
        
        
    }

    @Test
    public void testExcluirMensagem() {
    }

    @Test
    public void testIncluirMensagensExclusaoDeAgendamento() {
    }

    @Test
    public void testIncluirMensagensAlteracaoDeHorario() {
    }
    
}
