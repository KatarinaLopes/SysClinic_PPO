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
public class DaoPacienteTest {

    private DaoPaciente daoPaciente;
    private static int tamanho;

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

    @Test(expected = ConstraintViolationException.class)
    public void devePersistir() {
        tamanho = daoPaciente.recuperarTodos().size();

        Paciente paciente = new Paciente(0,
                new Date(), "Paciente", "F",
                new Date(System.currentTimeMillis()), "(99)9999-9999", null,
                null, "111.421.111-" + tamanho, "123", new ArrayList<Agendamento>());

        daoPaciente.persistir(paciente);

        int tamanhoNovo = daoPaciente.recuperarTodos().size();

        assertEquals(++tamanho, tamanhoNovo);

        daoPaciente.persistir(paciente);
    }

    @Test
    public void deveTestarRecuperar() {
        Paciente pacienteExistente = daoPaciente.recuperar(1);

        assertNotNull(pacienteExistente);

        Paciente pacienteNaoExistente = daoPaciente.recuperar(1000);
        
        assertNull(pacienteNaoExistente);
        
    }

    @Test
    public void testRecuperarPorAtributo() {
        int valor = tamanho;
        
        Paciente paciente = daoPaciente.
                recuperarPorAtributo("cpf", "111.421.111-"+(valor-1));
        
        assertNotNull(paciente);
        
        paciente = daoPaciente.recuperarPorAtributo("cpf", "1233");
        
        assertNull(paciente);
        
        
    }

   @Test
    public void testAtualizar() {
        Paciente pacienteAntigo = daoPaciente.recuperar(1);
        
        pacienteAntigo.setNome("PacienteTesteUpdate"+tamanho);
        
        daoPaciente.atualizar(pacienteAntigo);
        
        Paciente pacienteNovo = daoPaciente.recuperar(1);
        
        assertEquals("PacienteTesteUpdate"+tamanho, pacienteNovo.getNome());
    }

    //@Test(expected = IndexOutOfBoundsException.class)
    @Test
    public void testDeletar() {
        int valor = tamanho;
        
        Paciente pacienteAntigo = (Paciente) 
                daoPaciente.recuperarTodos().get(valor-1);
        
        int id = pacienteAntigo.getId();
        
        daoPaciente.deletar(pacienteAntigo);
        
        Paciente pacienteNovo = daoPaciente.recuperar(id);
        
        assertNull(pacienteNovo);
    }
    @Test
    public void testRecuperarTodos() {
        List pacientes = daoPaciente.recuperarTodos();

        assertNotNull(pacientes);
    }
}
