/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.validators;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import java.util.ArrayList;
import java.util.Date;
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
public class OperacoesTest {
    
    public OperacoesTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCriptografarSenha() throws Exception {
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveTestarValidarPacientePassandoPacienteNulo() {
        Paciente paciente = null;

        String msg = Operacoes.validarPaciente(paciente, "123");

    }

    @Test(expected = IllegalArgumentException.class)
    public void deveTestarValidarPacientecomDataDeAdmissaoIncorreta() {
        Paciente paciente = new Paciente(0, null, "Nome", "F",
                new Date(System.currentTimeMillis()), "(99)9999-9999",
                "(99)9-9999-9999", "email@email", "111.111.111-11", "123",
                new ArrayList<Agendamento>());

        String msg = Operacoes.validarPaciente(paciente, "123");

    }

    @Test
    public void deveTestarValidarPacienteComAtributosNulosTDD() {
        Paciente paciente = new Paciente(0,
                new Date(System.currentTimeMillis()), null, "F",
                new Date(System.currentTimeMillis()), "(99)9999-9999",
                "(99)9-9999-9999", "email@email.com", "111.111.111-11", "123",
                new ArrayList<Agendamento>());

        String msg = Operacoes.validarPaciente(paciente, "123");

        assertEquals("Nome inválido", msg);

        paciente.setNome("Nome");
        paciente.setSexo(null);

        msg = Operacoes.validarPaciente(paciente, "123");

        assertEquals("Sexo inválido", msg);

        paciente.setSexo("F");
        /*paciente.setDataNascimento(null);
        
        msg = Operacoes.validarPaciente(paciente);
        
        assertEquals("Data de nascimento inválida", msg);*/

        paciente.setTelefoneContato(null);

        msg = Operacoes.validarPaciente(paciente, "123");
        
        assertEquals("Telefone inválido", msg);
        
        paciente.setTelefoneContato("(99)9999-9999");
        paciente.setCelular(null);
        
        msg = Operacoes.validarPaciente(paciente, "123");
        
        assertEquals(null, msg);
        
        paciente.setCelular("(99)99999-9999");
        paciente.setEmail(null);
        
        msg = Operacoes.validarPaciente(paciente, "123");
        
        assertEquals(null, msg);
        
        paciente.setEmail("email@email.com");
        paciente.setCpf(null);
        
        msg = Operacoes.validarPaciente(paciente, "123");
        
        assertEquals("CPF inválido", msg);
        
        paciente.setCpf("111.111.111-11");
        paciente.setAgendamentos(null);
        
        msg = Operacoes.validarPaciente(paciente, "123");
        
        assertEquals("Lista de agendamentos inválida", msg);
        
        paciente.setAgendamentos(new ArrayList<Agendamento>());
        paciente.setSenha(null);
        
        msg = Operacoes.validarPaciente(paciente, "123");
        
        assertEquals("Senha inválida", msg);
        
        paciente.setSenha("123");
        
        msg = Operacoes.validarPaciente(paciente, null);
        
        assertEquals("Senha inválida", msg);
        
    }
    
    @Test
    public void deveTestarValidarPacienteComDadosIncorretos(){
        Paciente paciente = new Paciente(0, new Date(System.currentTimeMillis()), 
                "N0m3", "F", new Date(System.currentTimeMillis()), 
                "(99)9999-9999", "(99)99999-9999", "email@email.com", 
                "111.111.111-11", "123", new ArrayList<Agendamento>());
        
        String msg = Operacoes.validarPaciente(paciente, "123");
        
        assertEquals("Nome inválido", msg);
        
        paciente.setNome(" ");
        
        msg = Operacoes.validarPaciente(paciente, "123");
        
        assertEquals("Nome inválido", msg);
        
        paciente.setNome("");
        
        msg = Operacoes.validarPaciente(paciente, "123");
        
        assertEquals("Nome inválido", msg);
        
        paciente.setNome("Nome");
        paciente.setSexo("A");
        
        msg = Operacoes.validarPaciente(paciente, "123");
        
        assertEquals("Sexo inválido", msg);
        
        paciente.setSexo("1");
        
        msg = Operacoes.validarPaciente(paciente, "123");
        
        assertEquals("Sexo inválido", msg);
        
        paciente.setSexo("");
        
        msg = Operacoes.validarPaciente(paciente, "123");
        
        assertEquals("Sexo inválido", msg);
        
        paciente.setSexo(" ");
        
        msg = Operacoes.validarPaciente(paciente, "123");
        
        assertEquals("Sexo inválido", msg);
        
        paciente.setSexo("F");
        paciente.setTelefoneContato("(99)32222222");
        
        msg = Operacoes.validarPaciente(paciente, "123");
        
        assertEquals("Telefone inválido", msg);
        
        paciente.setTelefoneContato("999999999");
        
        msg = Operacoes.validarPaciente(paciente, "123");
        
        assertEquals("Telefone inválido", msg);
        
        paciente.setTelefoneContato("9a3fnvfbv");
        
        msg = Operacoes.validarPaciente(paciente, "123");
        
        assertEquals("Telefone inválido", msg);
        
        paciente.setTelefoneContato("");
        
        msg = Operacoes.validarPaciente(paciente, "123");
        
        assertEquals("Telefone inválido", msg);
        
        paciente.setTelefoneContato(" ");
        
        msg = Operacoes.validarPaciente(paciente, "123");
        
        assertEquals("Telefone inválido", msg);
        
        paciente.setTelefoneContato("(99)9999-9999");
        paciente.setCelular("(999999-9999");
        
        msg = Operacoes.validarPaciente(paciente, "123");
        
        assertEquals("Celular inválido", msg);
        
        paciente.setCelular("");
        
        msg = Operacoes.validarPaciente(paciente, "123");
        
        assertEquals("Celular inválido", msg);
        
        paciente.setCelular(" ");
        
        msg = Operacoes.validarPaciente(paciente, "123");
        
        assertEquals("Celular inválido", msg);
    }
    
    @Test
    public void deveTestarValidarPacientePassandoPacienteValidoRetornandoNull(){
        Paciente paciente = new Paciente(0, new Date(), "Nome Nome", "F",
                new Date(), "(99)9999-9999", "(99)99999-9999", 
                "email@email.com", "111.111.111-11", "123", 
                new ArrayList<Agendamento>());
        
        String mens = Operacoes.validarPaciente(paciente, "123");
        
        assertNull(mens);
        
    }

    @Test
    public void testMain() throws Exception {
    }

}
