/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.utils;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.exception.InternalException;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.context.ExternalContext;
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
public class LoginSessionUtilTest {

    private LoginSessionUtil loginSessionUtil;
    private FacesContext facesCtxt;
    private ExternalContext extCtxt;
    private Map<String, Object> sessionMap;

    public LoginSessionUtilTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        loginSessionUtil = new LoginSessionUtil();
        facesCtxt = mock(FacesContext.class);
        extCtxt = mock(ExternalContext.class);
        sessionMap = mock(Map.class);
        
        when(facesCtxt.getExternalContext()).thenReturn(extCtxt);
        when(facesCtxt.getExternalContext().getSessionMap()).
                thenReturn(sessionMap);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void devePassarSetarLogadoNaSessao() throws Exception {
        String logado = "logado";
        Object obj = new Object();

        when(facesCtxt.getExternalContext().getSessionMap().put(logado, obj)).
                thenReturn(obj);

        loginSessionUtil.setarLogadoNaSessao(logado, obj, facesCtxt);
    }

    @Test
    public void devePassarValidar() throws InternalException {
        String logado = "logado";
        Object object = new Object();
        
        loginSessionUtil.validar(logado, object, facesCtxt);
    }

    @Test
    public void deveFalharValidarStringNull() {
        Object obj = new Object();
        String mensagem = "";

        try {
            loginSessionUtil.validar(null, obj, facesCtxt);
        } catch (InternalException ex) {
            mensagem = ex.getMessage();
        }

        assertEquals(InternalException.ERRO_INTERNO, mensagem);
    }

    @Test
    public void deveFalharValidarObjectNull() {
        String logado = "logado";
        String mensagem = "";

        try {
            loginSessionUtil.validar(logado, null, facesCtxt);
        } catch (InternalException ex) {
            mensagem = ex.getMessage();
        }

        assertEquals(InternalException.ERRO_INTERNO, mensagem);
    }

    @Test
    public void deveFalharValidarFcsCntxtNull() {
        String logado = "logado";
        Object obj = new Object();
        String mensagem = "";

        try {
            loginSessionUtil.validar(logado, obj, null);
        } catch (InternalException ex) {
            mensagem = ex.getMessage();
        }

        assertEquals(InternalException.ERRO_INTERNO, mensagem);
    }

    @Test
    public void deveFalharValidarStringEmpty() {
        Object obj = new Object();
        String mensagem = "";

        try {
            loginSessionUtil.validar("", obj, facesCtxt);
        } catch (InternalException ex) {
            mensagem = ex.getMessage();
        }

        assertEquals(InternalException.ERRO_INTERNO, mensagem);
    }
    
    @Test
    public void devePassarValidar_2Args() throws InternalException{
        String logado = "logado";
        
        loginSessionUtil.validar(logado, facesCtxt);
    }

    @Test
    public void deveFalharValidar_2ArgsStringEmpty() {
        String mensagem = "";

        try {
            loginSessionUtil.validar("", facesCtxt);
        } catch (InternalException ex) {
            mensagem = ex.getMessage();
        }

        assertEquals(InternalException.ERRO_INTERNO, mensagem);
    }

    @Test
    public void deveFalharValidar_2ArgsStringNull() {
        String mensagem = "";

        try {
            loginSessionUtil.validar(null, facesCtxt);
        } catch (InternalException ex) {
            mensagem = ex.getMessage();
        }

        assertEquals(InternalException.ERRO_INTERNO, mensagem);
    }

    public void deveFalharValidar_2ArgsFcsCntxtNull() {
        String logado = "logado";
        String mensagem = "";

        try {
            loginSessionUtil.validar(logado, null);
        } catch (InternalException ex) {
            mensagem = ex.getMessage();
        }

        assertEquals(InternalException.ERRO_INTERNO, mensagem);
    }

    @Test
    public void devePassarRemoverLogadoNaSessao() throws Exception {
        String logado = "logado";
        Object obj = new Object();
        
        when(facesCtxt.getExternalContext().getSessionMap().remove(logado)).
                thenReturn(obj);
        
        loginSessionUtil.removerLogadoNaSessao(logado, facesCtxt);
    }

    @Test
    public void devePassarRetornarLogado() throws Exception {
        String logado = "logado";
        Object obj = new Object();
        
        when(facesCtxt.getExternalContext().getSessionMap().get(logado)).
                thenReturn(obj);
        
        loginSessionUtil.retornarLogado(logado, facesCtxt);
    }

}
