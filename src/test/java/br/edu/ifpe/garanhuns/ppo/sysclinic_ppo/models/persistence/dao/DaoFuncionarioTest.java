/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Funcionario;
import java.util.Date;
import java.util.List;
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

   /* @Test
    public void devePersistir() {
        int tamanhoAntigo = daoFuncionario.recuperarTodos().size();
        
        Funcionario funcionario = new Funcionario(0, 12345, 
                new Date(System.currentTimeMillis()), "FuncionarioTest", "F", 
                null, "(99)9999-9999", "CargoTeste", false, "123");
        
        daoFuncionario.persistir(funcionario);
        
        int tamanhoNovo = daoFuncionario.recuperarTodos().size();
        
        assertEquals(++tamanhoAntigo, tamanhoNovo);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void deveTestarRecuperar() {
        Funcionario funcionarioExistente = daoFuncionario.recuperar(2);
        
        assertNotNull(funcionarioExistente);
        
        Funcionario funcionarioNaoExistente = daoFuncionario.recuperar(100);
    }

    @Test
    public void testRecuperarPorAtributo() {
    }

    @Test
    public void testAtualizar() {
        Funcionario funcionarioAntigo = daoFuncionario.recuperar(2);
        
        funcionarioAntigo.setNome("FuncionarioTesteUpdate");
        
        daoFuncionario.atualizar(funcionarioAntigo);
        
        Funcionario funcionarioNovo = daoFuncionario.recuperar(2);
        
        assertEquals("FuncionarioTesteUpdate", funcionarioNovo.getNome());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDeletar() {
        Funcionario funcionarioAntigo = daoFuncionario.recuperar(2);
        
        daoFuncionario.deletar(funcionarioAntigo);
        
        Funcionario funcionarioNovo = daoFuncionario.recuperar(2);
    }*/

    @Test
    public void testRecuperarTodos() {
        List funcionarios = daoFuncionario.recuperarTodos();
        
        assertNotNull(funcionarios);
    }
    
}
