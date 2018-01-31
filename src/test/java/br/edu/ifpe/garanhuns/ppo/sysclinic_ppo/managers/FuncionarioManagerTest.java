/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.managers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Funcionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoFuncionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.DaoGenerico;
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
public class FuncionarioManagerTest {
    
    private FuncionarioManager funcionarioManager;
    private DaoGenerico daoFuncionarios;
    
    public FuncionarioManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        daoFuncionarios = mock(DaoFuncionario.class);
        funcionarioManager = new FuncionarioManager
        ((DaoFuncionario) daoFuncionarios);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void deveTestarValidarPassandoNoTeste() {
        String senha = "123";
        String confirmacaoSenha = "123";
        int matricula = 1234;
        
        Funcionario funcionario = new Funcionario();
        
        funcionario.setMatricula(matricula);
        funcionario.setSenha(senha);
        
        when(daoFuncionarios.recuperarPorAtributo("matricula", matricula)).
                thenReturn(null);
        
        funcionarioManager.validar(funcionario, confirmacaoSenha);
        
    }
    
    @Test
    public void deveTestarValidarSenhasDiferentesFalhando(){
        String senha = "123";
        String confirmacaoSenha = "1234";
        int matricula = 1234;
        String mensagem = "";
        
        Funcionario funcionario = new Funcionario();
        
        funcionario.setMatricula(matricula);
        funcionario.setSenha(senha);
        
        when(daoFuncionarios.recuperarPorAtributo("matricula", matricula)).
                thenReturn(null);
        
        try{
            funcionarioManager.validar(funcionario, confirmacaoSenha);
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("As senhas não correspondem!", mensagem);
    }
    
    @Test
    public void deveTestarValidarPacienteExistenteFalhando(){
        String senha = "123";
        String confirmacaoSenha = "123";
        int matricula = 1234;
        String mensagem = "";
        
        Funcionario funcionario = new Funcionario();
        
        funcionario.setMatricula(matricula);
        funcionario.setSenha(senha);
        
        when(daoFuncionarios.recuperarPorAtributo("matricula", matricula)).
                thenReturn(funcionario);
        
        try{
            funcionarioManager.validar(funcionario, confirmacaoSenha);
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("Este funcionário já está cadastrado, verifique se a "
                + "matrícula está correto", mensagem);
    }


    @Test
    public void testCadastrar() throws Exception {
    }
    
}
