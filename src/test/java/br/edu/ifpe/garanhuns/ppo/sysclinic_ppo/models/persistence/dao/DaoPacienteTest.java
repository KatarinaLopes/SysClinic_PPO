/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Funcionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
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
public class DaoPacienteTest {
    
    private DaoPaciente daoPaciente;
    
    public DaoPacienteTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        daoPaciente = new DaoPaciente();
    }
    
    @After
    public void tearDown() {
    }

    /*@Test
    public void devePersistir() {
        int tamanhoAntigo = daoPaciente.recuperarTodos().size();
        
        Paciente paciente = new Paciente(0, 
                new Date(System.currentTimeMillis()), "Paciente", "F", 
                new Date(System.currentTimeMillis()), "(99)9999-9999", null, 
                null, "111.111.111-11", "123", new ArrayList<Agendamento>());
        
        daoPaciente.persistir(paciente);
        
        int tamanhoNovo = daoPaciente.recuperarTodos().size();
        
        assertEquals(++tamanhoAntigo, tamanhoNovo);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void deveTestarRecuperar() {
        Paciente pacienteExistente = daoPaciente.recuperar(11);
        
        assertNotNull(pacienteExistente);
        
        Paciente pacienteNaoExistente = daoPaciente.recuperar(1000);
    }

    @Test
    public void testRecuperarPorAtributo() {
    }

    @Test
    public void testAtualizar() {
        Paciente pacienteAntigo = daoPaciente.recuperar(11);
        
        pacienteAntigo.setNome("PacienteTesteUpdate");
        
        daoPaciente.atualizar(pacienteAntigo);
        
        Paciente pacienteNovo = daoPaciente.recuperar(11);
        
        assertEquals("PacienteTesteUpdate", pacienteNovo.getNome());
    }

    //@Test(expected = IndexOutOfBoundsException.class)
    @Test
    public void testDeletar() {
        Paciente pacienteAntigo = daoPaciente.recuperar(11);
        
        daoPaciente.deletar(pacienteAntigo);
        
        //Paciente pacienteNovo = daoPaciente.recuperar(11);
    }*/

    @Test
    public void testRecuperarTodos() {
        List pacientes = daoPaciente.recuperarTodos();
        
        assertNotNull(pacientes);
    }
}
