/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.managers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agenda;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Feed;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Mensagem;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoPaciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.DaoGenerico;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        
        pacienteManager.validarCadastrar(paciente, confirmacaoSenha);
        
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
            pacienteManager.validarCadastrar(paciente, confirmacaoSenha);
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
            pacienteManager.validarCadastrar(paciente, confirmacaoSenha);
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
    public void devePassarInserirMensagemDeExclusaoNoFeed() {
        Paciente paciente1 = new Paciente();
        paciente1.setFeed(new Feed(new ArrayList<Mensagem>()));
        Paciente paciente2 = new Paciente();
        paciente2.setFeed(new Feed(new ArrayList<Mensagem>()));
        
        Medico medico = mock(Medico.class);
        Agenda agenda = mock(Agenda.class);
        
        List<Agendamento> agendamentos = new ArrayList<>();
        Agendamento agendamento1NaoRealizado = new Agendamento(0, null, 
                paciente1, null, false);
        Agendamento agendamento2NaoRealizado = new Agendamento(0, null, 
                paciente2, null, false);
        
        agendamentos.add(agendamento1NaoRealizado);
        agendamentos.add(agendamento2NaoRealizado);
        
        when(medico.getAgenda()).thenReturn(agenda);
        when(medico.getAgenda().getAgendamentos()).thenReturn(agendamentos);
        
        doNothing().when(daoPacientes).atualizar(paciente1);
        doNothing().when(daoPacientes).atualizar(paciente2);
        
        pacienteManager.inserirMensagemDeExclusaoParaTodosPacientes(medico);
        
        int tamanho1 = paciente1.getFeed().getMensagens().size();
        int tamanho2 = paciente2.getFeed().getMensagens().size();
        
        assertEquals(tamanho1, tamanho2);
        assertEquals(tamanho1, 1);
        
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

    @Test
    public void testValidarCadastrar() {
    }

    @Test
    public void testRecuperar() {
    }

    @Test
    public void testInserirMensagemDeExclusaoParaTodosPacientes() {
    }

    @Test
    public void testInserirMensagemDeAtualizacaoDeHorario() {
    }

    @Test
    public void testExibirMensagens() {
    }

    @Test
    public void testExcluirMensagens() {
    }

    @Test
    public void testVerificarCorrespondenciaSenha() {
    }

    @Test
    public void devePassarAlterarSenha() throws Exception {
        Paciente paciente = new Paciente();
        paciente.setSenha("123");
        
        String senhaAntiga = "123";
        String senhaNova = "124";
        String confirmacao = "124";
    }
    
}
