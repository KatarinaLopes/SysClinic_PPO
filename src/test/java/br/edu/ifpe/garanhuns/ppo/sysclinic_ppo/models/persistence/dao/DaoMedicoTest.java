/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Funcionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Horario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
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
public class DaoMedicoTest {

    private DaoMedico daoMedico;
    private static int tamanho;

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

    @Test(expected = ConstraintViolationException.class)
    public void devePersistir() {
        tamanho = daoMedico.recuperarTodos().size();

        Medico medico = new Medico(0, 123 + tamanho,
                new Date(System.currentTimeMillis()), "MedicoTeste", "M", null,
                "(99)9999-9999", "CREMEPE 000" + tamanho, "EspecialidadeTeste",
                new ArrayList<Horario>(), new ArrayList<Agendamento>());

        daoMedico.persistir(medico);

        int tamanhoNovo = daoMedico.recuperarTodos().size();

        assertEquals(++tamanho, tamanhoNovo);
        
        daoMedico.persistir(medico);
    }

    @Test
    public void deveTestarRecuperar() {
        Medico medicoExistente = daoMedico.recuperar(1);

        assertNotNull(medicoExistente);

        Medico MedicoNaoExistente = daoMedico.recuperar(1000);
        
        assertNull(MedicoNaoExistente);
    }

    
    @Test()
    public void testRecuperarPorAtributo() {
        int contador = tamanho;
        
        String valor = "CREMEPE 000"+(contador-1);
        
        Medico medico = daoMedico.recuperarPorAtributo("nome", "MedicoTeste");
        
        System.out.println(valor);
        
        assertNotNull(medico);
        
        medico = daoMedico.recuperarPorAtributo("conselho", valor);
        
        assertNotNull(medico);
        
        int matricula = 123+(contador-1);
        
        medico = daoMedico.recuperarPorAtributo("matricula", matricula);
        
        assertNotNull(medico);
        
        medico = daoMedico.recuperarPorAtributo("matricula", 000);
        
        assertNull(medico);
        
        medico = daoMedico.recuperarPorAtributo("conselho", "dnbf");
        
        assertNull(medico);
    }
    
    @Test
    public void testAtualizar() {
        Medico medicoAntigo = daoMedico.recuperar(1);

        medicoAntigo.setNome("MedicoTesteUpdate" + tamanho);

        daoMedico.atualizar(medicoAntigo);

        Medico medicoNovo = daoMedico.recuperar(1);

        assertEquals("MedicoTesteUpdate" + tamanho, medicoNovo.getNome());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDeletar() {
        Medico medicoAntigo = (Medico) daoMedico.recuperarTodos().get(tamanho);

        int id = medicoAntigo.getId();

        daoMedico.deletar(medicoAntigo);

        Medico medicoNovo = daoMedico.recuperar(id);
    }
    

    @Test
    public void testRecuperarTodos() {
        List medicos = daoMedico.recuperarTodos();

        assertNotNull(medicos);
    }

}
