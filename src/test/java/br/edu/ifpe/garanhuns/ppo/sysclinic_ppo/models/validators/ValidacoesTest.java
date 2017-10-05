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
        boolean r = Validacoes.validarCpf("123U");
        
        assertFalse(r);
        
        boolean r1 = Validacoes.validarCpf(null);
        
        assertFalse(r1);
    }
    
    @Test
    public void deveTestarValidarSenhasPassandoDuasSenhasIguaisRetornandoTrue(){
        boolean r = Validacoes.validarSenhas("123", "123");
        
        assertTrue(r);
    }
    
    @Test
    public void deveTestarValidarSenhasPassandoDuasSenhasDiferentesRetornandoFalse(){
        boolean r = Validacoes.validarSenhas("123", "1234");
        
        assertFalse(r);
        
        boolean r1 = Validacoes.validarSenhas(null, "123");
        
        assertFalse(r1);
        
        boolean r2 = Validacoes.validarSenhas("123", null);
        
        assertFalse(r2);
    }

    @Test
    public void deveTestarValidarTelefonePassandoTelefoneCorreto8digitosRetornandoTrue() {
        boolean resultado = Validacoes.validarTelefone("(99)9999-9999");
        
        assertTrue(resultado);
    }
    
    @Test
    public void deveTestarValidarTelefonePassandoTelefoneCorreto9digitosRetornandoTrue(){
        boolean resultado = Validacoes.validarTelefone("(99)9-9999-9999");
        
        assertTrue(resultado);
    }
    
    @Test
    public void deveTestarValidarTelefonePassandoTelefoneErrado8digitosRetornandoFalse(){
        boolean resultado = Validacoes.validarTelefone("(99)1235-1234");
        
        assertFalse(resultado);
    }
    
    @Test
    public void deveTestarValidarTelefonePassandoTelefoneErrado9digitosRetornandoFalse(){
        boolean res1 = Validacoes.validarTelefone("(99)8-9999-9999");
        
        assertFalse(res1);
        
        boolean res2 = Validacoes.validarTelefone("(99)9-1234-3455");
        
        assertFalse(res2);
    }
    
    @Test
    public void deveTestarValidarTelefonePassandoTelefoneSemCaracteresRetornandoFalse(){
        boolean r1 = Validacoes.validarTelefone("9999999999");
        
        assertFalse(r1);
        
        boolean r2 = Validacoes.validarTelefone("99999999999");
        
        assertFalse(r2);
    }
    
    @Test
    public void deveTestarValidarTelefonePassandoTelefoneComTamanhoErradoRetornandoFalse(){
        boolean r1 = Validacoes.validarTelefone("9999999999999");
        
        assertFalse(r1);
        
        boolean r2 = Validacoes.validarTelefone("999999999999999");
        
        assertFalse(r2);
        
        boolean r3 = Validacoes.validarTelefone("(99)9-9999-999");
        
        assertFalse(r3);
        
        boolean r4 = Validacoes.validarTelefone("(99)9999-999");
        
        assertFalse(r4);
        
        boolean r5 = Validacoes.validarTelefone(null);
        
        assertFalse(r5);
    }
    
    @Test
    public void deveTestarValidarNomePassandoNomeValidoRetornandoTrue(){
        boolean r1 = Validacoes.validarNome("Katarina Lopes");
        
        assertTrue(r1);
        
        boolean r2 = Validacoes.validarNome("Recepcionista");
        
        assertTrue(r2);
        
        boolean r3 = Validacoes.validarNome("João da Silva Prado");
        
        assertTrue(r3);
        
    }
    
    @Test
    public void deveTestarValidarNomePassandoNomeInvalidoRetornandoFalse(){
        boolean r1 = Validacoes.validarNome("M4ria K4t4rina");
        
        assertFalse(r1);
        
        boolean r2 = Validacoes.validarNome(null);
        
        assertFalse(r2);
        
        
    }
   
    @Test
    public void deveTestarValidarSexoPassandoSexoValidoRetornandoTrue(){
        boolean r1 = Validacoes.validarSexo("F");
        
        assertTrue(r1);
        
        boolean r2 = Validacoes.validarSexo("M");
        
        assertTrue(r2);
    }
    
    @Test
    public void deveTestarValidarSexoPassandoSexoInvalidoRetornandoFalse(){
        boolean r1 = Validacoes.validarSexo("");
        
        assertFalse(r1);
        
        boolean r2 = Validacoes.validarSexo("1");
        
        assertFalse(r2);
        
        boolean r3 = Validacoes.validarSexo("a");
        
        assertFalse(r3);
        
        boolean r4 = Validacoes.validarSexo("ADnf");
        
        assertFalse(r4);
        
        boolean r5 = Validacoes.validarSexo(null);
        
    }
    
    @Test
    public void deveTestarValidarEmailPassandoEmailValidoRetornandoTrue(){
        boolean r1 = Validacoes.validarEmail("email@email.com");
        
        assertTrue(r1);
        
        boolean r2 = Validacoes.validarEmail("e.m-a_il@email.ext.ext1.com.br");
        
        assertTrue(r2);
    }
    
    @Test
    public void deveTestarValidarEmailPassandoEmailInvalidoRetornandoFalse(){
        boolean r1 = Validacoes.validarEmail("");
        
        assertFalse(r1);
        
        boolean r2 = Validacoes.validarEmail("and.dfnjf");
        
        assertFalse(r2);
        
        boolean r3 = Validacoes.validarEmail(null);
        
        assertFalse(r3);
    }
    
}
