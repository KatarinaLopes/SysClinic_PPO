/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Funcionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Horario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import java.util.ArrayList;
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
public class DaoMedicoTest {
    private DaoMedico daoMedico;
    
    public DaoMedicoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        daoMedico = new DaoMedico();
    }
    
    @After
    public void tearDown() {
    }

    /*@Test
    public void devePersistir() {
        int tamanhoAntigo = daoMedico.recuperarTodos().size();
        
        Medico medico = new Medico(0, 123456, 
                new Date(System.currentTimeMillis()), "MedicoTeste", "M", null,
                "(99)9999-9999", "CREMEPE 0000", "EspecialidadeTeste", 
        new ArrayList<Horario>());
        
        daoMedico.persistir(medico);
        
        int tamanhoNovo = daoMedico.recuperarTodos().size();
        
        assertEquals(++tamanhoAntigo, tamanhoNovo);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void deveTestarRecuperar() {
        Medico medicoExistente = daoMedico.recuperar(2);
        
        assertNotNull(medicoExistente);
        
        Medico MedicoNaoExistente = daoMedico.recuperar(100);
    }

    @Test
    public void testRecuperarPorAtributo() {
    }

    @Test
    public void testAtualizar() {
        Medico medicoAntigo = daoMedico.recuperar(2);
        
        medicoAntigo.setNome("MedicoTesteUpdate");
        
        daoMedico.atualizar(medicoAntigo);
        
        Medico medicoNovo = daoMedico.recuperar(2);
        
        assertEquals("MedicoTesteUpdate", medicoNovo.getNome());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDeletar() {
        Medico medicoAntigo = daoMedico.recuperar(2);
        
        daoMedico.deletar(medicoAntigo);
        
        Medico medicoNovo = daoMedico.recuperar(2);
    }*/

    @Test
    public void testRecuperarTodos() {
        List medicos = daoMedico.recuperarTodos();
        
        assertNotNull(medicos);
    }
    
}
