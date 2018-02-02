/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.beans;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Funcionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.
        dao.DaoFuncionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.
        exception.DaoException;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.
        manager.DaoGenerico;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.validation.ElementKind;
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
public class LoginFuncionarioTest {
    
    private LoginFuncionario loginFuncionario;
    private DaoGenerico daoFuncionario;
    private FacesContext facesContext;
    
    
    public LoginFuncionarioTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        daoFuncionario = mock(DaoFuncionario.class);
        loginFuncionario = new LoginFuncionario((DaoFuncionario) 
                daoFuncionario);
        facesContext = mock(FacesContext.class);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetPacienteLogado() {
    }

    @Test
    public void deveTestarLoginPassandoNoTeste() throws Exception {
        int matricula = 1234;
        String senha = "123";
                
        Funcionario funcionario = new Funcionario();
        
        funcionario.setMatricula(matricula);
        funcionario.setSenha(senha);
        
        when(daoFuncionario.recuperarPorAtributo("matricula", matricula)).
                thenReturn(funcionario);
        
        loginFuncionario.login(matricula, senha);
        
        assertNotNull(loginFuncionario.getFuncionarioLogado());
        assertEquals(funcionario, loginFuncionario.getFuncionarioLogado());
    }

    @Test
    public void deveTestarLoginLancandoDAOExceptionMatricula() throws Exception{
        int matricula = 1234;
        String senha = "123";
        String mensagem = "";
        
        when(daoFuncionario.recuperarPorAtributo("matricula", matricula)).
                thenReturn(null);
        
        try{
            loginFuncionario.login(matricula, senha);
            fail();
        }catch(DaoException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals(DaoException.MATRICULA_INEXISTENTE, mensagem);
        
    }
    
    @Test
    public void deveTestarLoginLancadoDAOExceptionSenha() throws Exception{
        int matricula = 1234;
        String senhaCorreta = "123";
        String mensagem = "";
        String senhaIncorreta = "124";
        
        Funcionario funcionario = new Funcionario();
        
        funcionario.setMatricula(matricula);
        funcionario.setSenha(senhaCorreta);
        
        when(daoFuncionario.recuperarPorAtributo("matricula", matricula)).
                thenReturn(funcionario);
        
        try{
            loginFuncionario.login(matricula, senhaIncorreta);
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("As senhas não correspondem!", mensagem);
    }
    
    @Test
    public void deveTestarLoginLancandoIllegalArgumentExceptionMatricula0() 
            throws DaoException, Exception{
        String mensagem = "";
        
        try{
            loginFuncionario.login(0, "123");
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("Matrícula e senha não podem estar vazios", mensagem);
    }
    
    @Test
    public void deveTestarLoginLancandoIllegalArgumentExceptionSenhaNull() 
            throws DaoException {
        String mensagem = "";
        
        try{
            loginFuncionario.login(1234, null);
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("Matrícula e senha não podem estar vazios", mensagem);
    }
    
    @Test
    public void deveTestarLoginLancandoIllegalArgumentExceptionSenhaEmpty() 
            throws DaoException {
        String mensagem = "";
        
        try{
            loginFuncionario.login(1234, "");
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("Matrícula e senha não podem estar vazios", mensagem);
    }
    
    @Test
    public void deveTestarLogoutPassandoNoTeste() {
        
        loginFuncionario.setFuncionarioLogado(new Funcionario());
        
        assertNotNull(loginFuncionario.getFuncionarioLogado());
        
        loginFuncionario.logout();
        
        assertNull(loginFuncionario.getFuncionarioLogado());
    }

    
    @Test
    public void deveTestarExistePacienteLogadoRetornandoTrue() {
        loginFuncionario.setFuncionarioLogado(new Funcionario());
        
        assertTrue(loginFuncionario.existeFuncionarioLogado());
    }
    
    @Test
    public void deveTestarRetornandoFalse(){
        loginFuncionario.setFuncionarioLogado(null);
        
        assertFalse(loginFuncionario.existeFuncionarioLogado());
    }
    
    @Test
    public void deveTestarExisteLogadoAdministradorRetornandoTrue(){
        Funcionario funcionario = new Funcionario();
        
        funcionario.setAdministrador(true);
        
        loginFuncionario.setFuncionarioLogado(funcionario);
        
        boolean retorno = loginFuncionario.existeLogadoAdministrador();
        
        assertTrue(retorno);
        
    }
    
    @Test
    public void deveTestarExisteLogadoAdiministradorRetornandoFalse(){
        loginFuncionario.setFuncionarioLogado(new Funcionario());
        
        boolean retorno = loginFuncionario.existeLogadoAdministrador();
        
        assertFalse(retorno);
    }
    
    @Test
    public void 
         deveTestarExisteLogadoAdministradorFuncionarioNuloRetornandoFalse(){
       
        loginFuncionario.setFuncionarioLogado(null);
       
        boolean retorno = loginFuncionario.existeLogadoAdministrador();
        
        assertFalse(retorno);
    }
 
    @Test
    public void deveTestarExisteLogadoNaoAdministradorRetornandoTrue(){
        loginFuncionario.setFuncionarioLogado(new Funcionario());
        
        boolean retorno = loginFuncionario.existeLogadoNaoAdministrador();
        
        assertTrue(retorno);
    }
    
    @Test
    public void deveTestarExisteLogadoNaAdministradorRetornandoFalse(){
        Funcionario funcionario = new Funcionario();
        
        funcionario.setAdministrador(true);
        
        boolean retorno = loginFuncionario.existeLogadoNaoAdministrador();
        
        assertFalse(retorno);
    }
    
    @Test
    public void deveTestarExisteLogadoNaoAdministradorRetornandoFalse(){
        boolean retorno = loginFuncionario.existeLogadoNaoAdministrador();
        
        assertFalse(retorno);
    }
    
    @Test
    public void deveTestarValidarPassandoNoTeste() {
        int matricula = 1234;
        String senha = "123";
        
        loginFuncionario.validar(matricula, senha);
    }

    @Test
    public void deveTestarValidarPassandoMatricula0Falhando() 
            throws Exception {
        String senha = "123";
        String mensagem = "";
        
        try{
            loginFuncionario.validar(0, senha);
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("Matrícula e senha não podem estar vazios", mensagem);
    }

    @Test
    public void deveTestarValidarPassandoSenhaNulaFalhando() {
        int matricula = 1234;
        String mensagem = "";
        
        try{
            loginFuncionario.validar(matricula, null);
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("Matrícula e senha não podem estar vazios", mensagem);
    }

    @Test
    public void deveTestarValidarPassandoSenhaEmpty() {
        int matricula = 1234;
        String mensagem = "";
        
        try{
            loginFuncionario.validar(matricula, "");
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("Matrícula e senha não podem estar vazios", mensagem);
    }

    @Test
    public void deveTestarValidarPassandoMatricula0SenhaNull() {
        String mensagem = "";
        
        try{
            loginFuncionario.validar(0, null);
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("Matrícula e senha não podem estar vazios", mensagem);
    }

    @Test
    public void deveTestarValidarPassandoMatricula0SenhaEmpty() {
        String mensagem = "";
        
        try{
            loginFuncionario.validar(0, "");
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("Matrícula e senha não podem estar vazios", mensagem);
    }

    @Test
    public void deveTestarSetarFuncionarioLogadoNaSessaoPassando() {
        Funcionario funcionarioLogado = mock(Funcionario.class);
        
        ExternalContext externalContext = mock(ExternalContext.class);
        Map<String, Object> sessionMap = mock(Map.class);
        
        when(facesContext.getExternalContext()).thenReturn(externalContext);
        when(facesContext.getExternalContext().getSessionMap()).
                thenReturn(sessionMap);
        
        when(facesContext.getExternalContext().getSessionMap().
               put("funcionarioLogado", funcionarioLogado)).
                thenReturn(funcionarioLogado);
        
        loginFuncionario.setarFuncionarioLogadoNaSessao();
    }
    
    @Test
    public void deveTestarSetarFuncionarioLogadoNaSessaoFalhando(){ 
        Funcionario funcionarioLogado = mock(Funcionario.class);
        String mensagem = "";
        ExternalContext externalContext = mock(ExternalContext.class);
        Map<String, Object> sessionMap = mock(Map.class);
        
        when(facesContext.getExternalContext()).thenReturn(externalContext);
        when(facesContext.getExternalContext().getSessionMap()).
                thenReturn(sessionMap);
        
        when(facesContext.getExternalContext().getSessionMap().
                put("funcionarioLogado", funcionarioLogado)).
                thenThrow(IllegalStateException.class);
        
        try{
            loginFuncionario.setFuncionarioLogado(funcionarioLogado);
            loginFuncionario.setarFuncionarioLogadoNaSessao();
            fail();
        }catch(IllegalStateException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("Ocorreu um erro. Recarregue a página e tente novamente", 
                mensagem);
    }

    @Test
    public void testTirarFuncionarioLogadoDaSessao() {
        
    }
    
}
