/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.validators;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Katarina
 */
public class ValidacoesTest {
    
    public ValidacoesTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void deveTestarValidarCpfPassandoCpfValido1RetornandoTrue() {
        boolean r = Validacoes.validarCpf("705.643.774-57");
        
        assertTrue(r);
    }
    
    @Test
    public void deveTestarValidarCpfPassandoCpfValidoIniciadoPor0(){
        boolean r = Validacoes.validarCpf("025.464.524-01");
        
        assertTrue(r);
    }
    
    @Test
    public void deveTestarValidarCpfPassandoCpfInvalidoEmTamanhoRetornandoFalse(){
        boolean r = Validacoes.validarCpf("1234");
        
        assertFalse(r);
    }
    
    @Test
    public void deveTestarValidarCpfPassandoCpfInvalidoComDigito1IncorretoRetornandoFalse(){
        boolean r = Validacoes.validarCpf("705.643.774-47");
        
        assertFalse(r);
    }
    
    @Test
    public void deveTestarValidarCpfPassandoCpfInvalidoComDigito2IncorretoRetornandoFalse(){
        boolean r = Validacoes.validarCpf("705.643.774-56");
        
        assertFalse(r);
    }
    
    @Test
    public void deveTestarValidarCpfPassandoCpfInvalidoContendoCaracteresRetornandoFalse(){
        boolean r = Validacoes.validarCpf("123.123.dge-sU");
        
        assertFalse(r);
        
        boolean r1 = Validacoes.validarCpf(null);
        
        assertFalse(r1);
        
    }
    
}
