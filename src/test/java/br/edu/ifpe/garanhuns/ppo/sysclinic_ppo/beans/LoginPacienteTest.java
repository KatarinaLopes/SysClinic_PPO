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
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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
public class LoginPacienteTest {
    
    LoginPaciente loginPaciente;
    DaoGenerico daoPaciente;
    
    public LoginPacienteTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        loginPaciente = new LoginPaciente();
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
        
        loginPaciente.login(cpf, senha, (DaoPaciente) daoPaciente);
        
        assertNotNull(loginPaciente.getPacienteLogado());
        assertEquals(paciente, loginPaciente.getPacienteLogado());
    }

    @Test
    public void deveTestarLoginLancandoDAOExceptionCPF() {
        String cpf = "111.111.111-11";
        String senha = "123";
        String mensagem = "";
        
        when(daoPaciente.recuperarPorAtributo("cpf", cpf)).thenReturn(null);
        
        try{
            loginPaciente.login(cpf, senha, (DaoPaciente) daoPaciente);
            fail();
        }catch(DaoException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals(DaoException.CPF_INEXISTENTE, mensagem);
        
    }
    
    @Test
    public void deveTestarLoginLancadoDAOExceptionSenha() {
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
            loginPaciente.login(cpf, senhaIncorreta, 
                    (DaoPaciente) daoPaciente);
            fail();
        }catch(DaoException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals(DaoException.SENHA_NAO_CORRESPONDE, mensagem);
    }
    
    @Test
    public void deveTestarLoginLancandoIllegalArgumentExceptionCPFNull() 
            throws DaoException {
        String mensagem = "";
        
        try{
            loginPaciente.login(null, "123", (DaoPaciente) daoPaciente);
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("CPF e senha não podem estar vazios", mensagem);
    }
    
    @Test
    public void deveTestarLoginLancandoIllegalArgumentExceptionCPFEmpty() 
            throws DaoException {
        String mensagem = "";
        
        try{
            loginPaciente.login("", "123", (DaoPaciente) daoPaciente);
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("CPF e senha não podem estar vazios", mensagem);
    }
    
    @Test
    public void deveTestarLoginLancandoIllegalArgumentExceptionSenhaNull() 
            throws DaoException {
        String mensagem = "";
        
        try{
            loginPaciente.login("111.111.111-11", null, 
                    (DaoPaciente) daoPaciente);
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("CPF e senha não podem estar vazios", mensagem);
    }
    
    @Test
    public void deveTestarLoginLancandoIllegalArgumentExceptionSenhaEmpty() 
            throws DaoException {
        String mensagem = "";
        
        try{
            loginPaciente.login("111.111.111-11", "", 
                    (DaoPaciente) daoPaciente);
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("CPF e senha não podem estar vazios", mensagem);
    }
    
    @Test
    public void deveTestarLogoutPassandoNoTeste() {
        
        loginPaciente.setPacienteLogado(new Paciente());
        
        assertNotNull(loginPaciente.getPacienteLogado());
        
        loginPaciente.logout();
        
        assertNull(loginPaciente.getPacienteLogado());
    }

    @Test
    public void deveTestarLogoutFalhando(){
        String mensagem = "";
        
        try{
            loginPaciente.logout();
            fail();
        }catch(IllegalStateException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("Não há paciente logado para esta operação", mensagem);
    }
    @Test
    public void deveTestarExistePacienteLogadoRetornandoTrue() {
        loginPaciente.setPacienteLogado(new Paciente());
        
        assertTrue(loginPaciente.existePacienteLogado());
    }
    
    @Test
    public void deveTestarRetornandoFalse(){
        loginPaciente.setPacienteLogado(null);
        
        assertFalse(loginPaciente.existePacienteLogado());
    }
}
