/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Funcionario;
import java.util.Date;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import org.hibernate.exception.ConstraintViolationException;
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
public class DaoFuncionarioTest {
    
    private DaoFuncionario daoFuncionario;
    private static int tamanho;
    
    public DaoFuncionarioTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        daoFuncionario = new DaoFuncionario();
    }
    
    @After
    public void tearDown() {
    }
    
    /*

    @Test(expected = ConstraintViolationException.class)
    public void devePersistir() {
        tamanho = daoFuncionario.recuperarTodos().size();
        
        Funcionario funcionario = new Funcionario(0, 12345+tamanho, 
                new Date(System.currentTimeMillis()), "FuncionarioTest", "F", 
                null, "(99)9999-9999", "CargoTeste", false, "123");
        
        daoFuncionario.persistir(funcionario);
        
        int tamanhoNovo = daoFuncionario.recuperarTodos().size();
        
        assertEquals(++tamanho, tamanhoNovo);
        
        daoFuncionario.persistir(funcionario);
    }

    @Test
    public void deveTestarRecuperar() {
        Funcionario funcionarioExistente = daoFuncionario.recuperar(1);
        
        assertNotNull(funcionarioExistente);
        
        Funcionario funcionarioNaoExistente = daoFuncionario.recuperar(1000);
        
        assertNull(funcionarioNaoExistente);
    }

    @Test
    public void testRecuperarPorAtributo() {
        
        Funcionario funcionario = daoFuncionario.recuperarPorAtributo("nome", 
                "FuncionarioTest");
        
        assertNotNull(funcionario);
        
        funcionario = daoFuncionario.recuperarPorAtributo("nome", "vsg2");
        
        assertNull(funcionario);
        
        int valor = tamanho;
        
        funcionario = daoFuncionario.recuperarPorAtributo("matricula", 
                12345+(valor-1));
        
        assertNotNull(funcionario);
        
        funcionario = daoFuncionario.recuperarPorAtributo("matricula", 000);
        
        assertNull(funcionario);
    }

    @Test
    public void testAtualizar() {
        Funcionario funcionarioAntigo = daoFuncionario.recuperar(1);
        
        funcionarioAntigo.setNome("FuncionarioTesteUpdate");
        
        daoFuncionario.atualizar(funcionarioAntigo);
        
        Funcionario funcionarioNovo = daoFuncionario.recuperar(1);
        
        assertEquals("FuncionarioTesteUpdate", funcionarioNovo.getNome());
    }

    @Test
    public void testDeletar() {
        int valor = tamanho;
        
        Funcionario funcionarioAntigo = (Funcionario) daoFuncionario.
                recuperarTodos().get(valor-1);
        
        int id = funcionarioAntigo.getId();
        
        daoFuncionario.deletar(funcionarioAntigo);
        
        Funcionario funcionarioNovo = daoFuncionario.recuperar(id);
        
        assertNull(funcionarioNovo);
    }

    @Test
    public void testRecuperarTodos() {
        List funcionarios = daoFuncionario.recuperarTodos();
        
        assertNotNull(funcionarios);
    }
    */

    @Test
    public void testPersistir() {
    }

    @Test
    public void testRecuperar() {
    }

    @Test
    public void testRecuperarPorAtributo() {
    }

    @Test
    public void testAtualizar() {
    }

    @Test
    public void testDeletar() {
    }

    @Test
    public void testRecuperarTodos() {
    }

    /*@Test
    public void testPodeExcluirOuAlterar() {
    
        Funcionario funcionario = daoFuncionario.recuperar(1); //admin
        
        boolean retorno = daoFuncionario.podeExcluirOuAlterar(funcionario);
        
        //assertTrue(retorno);
        
        Funcionario admin2 = daoFuncionario.recuperar(6);
        
        retorno = daoFuncionario.podeExcluirOuAlterar(admin2);
        
        //assertTrue(retorno);
        
        Funcionario nadmin = daoFuncionario.recuperar(5);
        
        retorno = daoFuncionario.podeExcluirOuAlterar(nadmin);
        
        //assertFalse(retorno);
    }*/
    
    
}
