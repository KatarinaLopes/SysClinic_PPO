/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.beans;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.
        dao.DaoPaciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.
        exception.DaoException;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.
        dao.manager.DaoGenerico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.exception.InternalException;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.utils.LoginSessionUtil;
import javax.faces.context.FacesContext;
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
public class LoginPacienteTest {
    
    LoginPaciente loginPaciente;
    DaoGenerico daoPaciente;
    FacesContext fc;
    LoginSessionUtil loginSessionUtil;
    
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
        daoPaciente = mock(DaoPaciente.class);
        loginSessionUtil = mock(LoginSessionUtil.class);
        loginPaciente = new LoginPaciente((DaoPaciente) daoPaciente, 
                loginSessionUtil);
        fc = mock(FacesContext.class);
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
        when(loginSessionUtil.
                setarLogadoNaSessao("pacienteLogado", senha, fc)).
                thenReturn(paciente);
        
        loginPaciente.login(cpf, senha, fc);
        
        assertNotNull(loginPaciente.getPacienteLogado());
        assertEquals(paciente, loginPaciente.getPacienteLogado());
    }

    @Test
    public void deveTestarLoginLancandoDAOExceptionCPF() 
            throws IllegalArgumentException, InternalException {
        String cpf = "111.111.111-11";
        String senha = "123";
        String mensagem = "";
        
        when(daoPaciente.recuperarPorAtributo("cpf", cpf)).thenReturn(null);
        
        try{
            loginPaciente.login(cpf, senha, fc);
            fail();
        }catch(DaoException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals(DaoException.CPF_INEXISTENTE, mensagem);
        
    }
    
    @Test
    public void deveTestarLoginLancadoIlgArgmtExceptionSenha() 
            throws DaoException, InternalException {
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
            loginPaciente.login(cpf, senhaIncorreta, fc);
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("As senhas não correspondem!", mensagem);
    }
    
    @Test
    public void deveTestarLoginLancandoIllegalArgumentExceptionCPFNull() 
            throws DaoException, InternalException {
        String mensagem = "";
        
        try{
            loginPaciente.login(null, "123", fc);
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("CPF e senha não podem estar vazios", mensagem);
    }
    
    @Test
    public void deveTestarLoginLancandoIllegalArgumentExceptionCPFEmpty() 
            throws DaoException, InternalException {
        String mensagem = "";
        
        try{
            loginPaciente.login("", "123", fc);
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("CPF e senha não podem estar vazios", mensagem);
    }
    
    @Test
    public void deveTestarLoginLancandoIllegalArgumentExceptionSenhaNull() 
            throws DaoException, InternalException {
        String mensagem = "";
        
        try{
            loginPaciente.login("111.111.111-11", null, fc);
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("CPF e senha não podem estar vazios", mensagem);
    }
    
    @Test
    public void deveTestarLoginLancandoIllegalArgumentExceptionSenhaEmpty() 
            throws DaoException, InternalException {
        String mensagem = "";
        
        try{
            loginPaciente.login("111.111.111-11", "", fc);
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("CPF e senha não podem estar vazios", mensagem);
    }
    
    @Test
    public void deveTestarLogoutPassandoNoTeste() throws InternalException {
        
        Paciente paciente = mock(Paciente.class);
        
        loginPaciente.setPacienteLogado(paciente);
        
        assertNotNull(loginPaciente.getPacienteLogado());
        
        when(loginSessionUtil.removerLogadoNaSessao("pacienteLogado", fc)).
                thenReturn(paciente);
        
        loginPaciente.logout(fc);
        
        assertNull(loginPaciente.getPacienteLogado());
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
    
    @Test
    public void deveTestarValidarPassandoNoTeste(){
        String cpf = "111.111.111-11";
        String senha = "123";
        
        loginPaciente.validar(cpf, senha);
    }
    
    @Test
    public void deveTestarValidarComCPFNullFalhando(){
        String senha = "123";
        String mensagem = "";
        
        try{
            loginPaciente.validar(null, senha);
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("CPF e senha não podem estar vazios", mensagem);
    }
    
    @Test
    public void deveTestarValidarComCPFEmptyFalhando(){
        String senha = "123";
        String mensagem = "";
        
        try{
            loginPaciente.validar("", senha);
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("CPF e senha não podem estar vazios", mensagem);
    }
    
    @Test
    public void deveTestarValidarComSenhaNullFalhando(){
        String cpf = "111.111.111-11";
        String mensagem = "";
        
        try{
            loginPaciente.validar(cpf, null);
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("CPF e senha não podem estar vazios", mensagem);
    }
    
    @Test
    public void deveTestarValidarComSenhaEmptyFalhando(){
        String cpf = "111.111.111-11";
        String mensagem = "";
        
        try{
            loginPaciente.validar(cpf, "");
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("CPF e senha não podem estar vazios", mensagem);
    }
    
    @Test
    public void deveTestarValidarComSenhaEmptyCPFEmptyFalhando(){
        String mensagem = "";
        
        try{
            loginPaciente.validar("", "");
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("CPF e senha não podem estar vazios", mensagem);
    }
    
    @Test
    public void deveTestarValidarComSenhaEmptyCPFNullFalhando(){
        String mensagem = "";
        
        try{
            loginPaciente.validar(null, "");
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("CPF e senha não podem estar vazios", mensagem);
    }
    
    @Test
    public void deveTestarValidarComSenhaNullCPFNullFalhando(){
        String mensagem = "";
        
        try{
            loginPaciente.validar(null, null);
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("CPF e senha não podem estar vazios", mensagem);
    }
    
    @Test
    public void deveTestarValidarComSenhaNullCPFEmptyFalhando(){
        String mensagem = "";
        
        try{
            loginPaciente.validar("", null);
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("CPF e senha não podem estar vazios", mensagem);
    }
}
