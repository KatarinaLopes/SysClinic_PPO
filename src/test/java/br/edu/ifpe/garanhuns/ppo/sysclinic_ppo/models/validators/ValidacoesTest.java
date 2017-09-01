/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.validators;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Katarina
 */
public class ValidacoesTest {
    
    public ValidacoesTest() {
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
    }
}
