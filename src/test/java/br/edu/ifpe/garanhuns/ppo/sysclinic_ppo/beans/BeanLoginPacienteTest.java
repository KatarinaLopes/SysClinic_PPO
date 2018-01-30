/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.beans;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoPaciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.exception.DaoException;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.DaoGenerico;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

/**
 *
 * @author Katarina
 */
public class BeanLoginPacienteTest {
    
    BeanLoginPaciente beanLoginPaciente;
    DaoGenerico daoPaciente;
    
    public BeanLoginPacienteTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        beanLoginPaciente = new BeanLoginPaciente();
        daoPaciente = mock(DaoPaciente.class);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetPacienteLogado() {
    }

    @Test
    public void deveTestarLoginPassandoNoTeste() throws Exception {
        String cpf = "111.111.111-11";
        String senha = "123";
        
        Paciente paciente = new Paciente();
        
        paciente.setCpf(cpf);
        paciente.setSenha(senha);
        
        when(daoPaciente.recuperarPorAtributo("cpf", cpf)).
                thenReturn(paciente);
        
        beanLoginPaciente.login(cpf, senha, (DaoPaciente) daoPaciente);
        
        assertNotNull(beanLoginPaciente.getPacienteLogado());
        assertEquals(paciente, beanLoginPaciente.getPacienteLogado());
    }

    @Test
    public void deveTestarLoginLancandoDAOExceptionCPF(){
        String cpf = "111.111.111-11";
        String senha = "123";
        String mensagem = "";
        
        when(daoPaciente.recuperarPorAtributo("cpf", cpf)).thenReturn(null);
        
        try{
            beanLoginPaciente.login(cpf, senha, (DaoPaciente) daoPaciente);
            fail();
        }catch(DaoException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals(DaoException.CPF_INEXISTENTE, mensagem);
        
    }
    
    @Test
    public void deveTestarLoginLancadoDAOExceptionSenha(){
        String cpf = "111.111.111-11";
        String senhaCorreta = "123";
        String mensagem = "";
        String senhaIncorreta = "124";
        
        Paciente paciente = new Paciente();
        
        paciente.setCpf(cpf);
        paciente.setSenha(senhaCorreta);
        
        when(daoPaciente.recuperarPorAtributo("cpf", cpf)).
                thenReturn(paciente);
        
        try{
            beanLoginPaciente.login(cpf, senhaIncorreta, 
                    (DaoPaciente) daoPaciente);
            fail();
        }catch(DaoException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals(DaoException.SENHA_NAO_CORRESPONDE, mensagem);
    }
    
    @Test
    public void deveTestarLoginLancandoIllegalArgumentExceptionCPFNull() 
            throws DaoException{
        String mensagem = "";
        
        try{
            beanLoginPaciente.login(null, "123", (DaoPaciente) daoPaciente);
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("CPF e senha não podem estar vazios", mensagem);
    }
    
    @Test
    public void deveTestarLoginLancandoIllegalArgumentExceptionCPFEmpty() 
            throws DaoException{
        String mensagem = "";
        
        try{
            beanLoginPaciente.login("", "123", (DaoPaciente) daoPaciente);
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("CPF e senha não podem estar vazios", mensagem);
    }
    
    @Test
    public void deveTestarLoginLancandoIllegalArgumentExceptionSenhaNull() 
            throws DaoException{
        String mensagem = "";
        
        try{
            beanLoginPaciente.login("111.111.111-11", null, 
                    (DaoPaciente) daoPaciente);
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("CPF e senha não podem estar vazios", mensagem);
    }
    
    @Test
    public void deveTestarLoginLancandoIllegalArgumentExceptionSenhaEmpty() throws DaoException{
        String mensagem = "";
        
        try{
            beanLoginPaciente.login("111.111.111-11", "", 
                    (DaoPaciente) daoPaciente);
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("CPF e senha não podem estar vazios", mensagem);
    }
    
    @Test
    public void deveTestarLogoutPassandoNoTeste() {
        
        beanLoginPaciente.setPacienteLogado(new Paciente());
        
        assertNotNull(beanLoginPaciente.getPacienteLogado());
        
        beanLoginPaciente.logout();
        
        assertNull(beanLoginPaciente.getPacienteLogado());
    }

    @Test
    public void deveTestarLogoutFalhando(){
        String mensagem = "";
        
        try{
            beanLoginPaciente.logout();
            fail();
        }catch(IllegalStateException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("Não há paciente logado para esta operação", mensagem);
    }
    @Test
    public void deveTestarExistePacienteLogadoRetornandoTrue() {
        beanLoginPaciente.setPacienteLogado(new Paciente());
        
        assertTrue(beanLoginPaciente.existePacienteLogado());
    }
    
    @Test
    public void deveTestarRetornandoFalse(){
        beanLoginPaciente.setPacienteLogado(null);
        
        assertFalse(beanLoginPaciente.existePacienteLogado());
    }
}
