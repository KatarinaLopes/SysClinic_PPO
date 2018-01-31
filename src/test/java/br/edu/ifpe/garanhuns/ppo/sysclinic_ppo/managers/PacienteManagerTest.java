/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.managers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoPaciente;
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
public class PacienteManagerTest {
    
    private PacienteManager pacienteManager;
    private DaoGenerico daoPacientes;
    
    public PacienteManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        daoPacientes = mock(DaoPaciente.class);
        pacienteManager = new PacienteManager((DaoPaciente) daoPacientes);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void deveTestarValidarPassandoNoTeste() {
        String senha = "123";
        String confirmacaoSenha = "123";
        String cpf = "111.111.111-11";
        
        Paciente paciente = new Paciente();
        
        paciente.setCpf(cpf);
        paciente.setSenha(senha);
        
        when(daoPacientes.recuperarPorAtributo("cpf", cpf)).thenReturn(null);
        
        pacienteManager.validar(paciente, confirmacaoSenha);
        
    }
    
    @Test
    public void deveTestarValidarSenhasDiferentesFalhando(){
        String senha = "123";
        String confirmacaoSenha = "1234";
        String cpf = "111.111.111-11";
        String mensagem = "";
        
        Paciente paciente = new Paciente();
        
        paciente.setCpf(cpf);
        paciente.setSenha(senha);
        
        when(daoPacientes.recuperarPorAtributo("cpf", cpf)).thenReturn(null);
        
        try{
            pacienteManager.validar(paciente, confirmacaoSenha);
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
        String cpf = "111.111.111-11";
        String mensagem = "";
        
        Paciente paciente = new Paciente();
        
        paciente.setCpf(cpf);
        paciente.setSenha(senha);
        
        when(daoPacientes.recuperarPorAtributo("cpf", cpf)).
                thenReturn(paciente);
        
        try{
            pacienteManager.validar(paciente, confirmacaoSenha);
            fail();
        }catch(IllegalArgumentException ex){
            mensagem = ex.getMessage();
        }
        
        assertEquals("Este paciente já está cadastrado, verifique se o CPF "
                + "está correto", mensagem);
    }

    @Test
    public void testCadastrar() throws Exception {
    }

    @Test
    public void testRecuperarTodos() {
    }

    @Test
    public void testInserirMensagemDeExclusaoNoFeed() {
    }

    @Test
    public void testInserirMensagemDeAlteracaoDeHorarioNoFeed() {
    }

    @Test
    public void testExcluirMensagem() {
    }

    @Test
    public void testRetornarTodasAsMensagens() {
    }

    @Test
    public void testAtualizar() {
    }

    @Test
    public void testAtualizarListaDePacientes() {
    }
    
}
