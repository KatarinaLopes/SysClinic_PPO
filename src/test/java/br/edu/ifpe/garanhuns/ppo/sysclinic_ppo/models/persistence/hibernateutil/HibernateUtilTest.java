/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.hibernateutil;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Katarina
 */
public class HibernateUtilTest {
    
    private static int tamanho = 6;
    
    public HibernateUtilTest() {
    }

    /*@Test
    public void testGetInstance() {
    }*/

 
    /*@Test
    public void deveTestarPersist() {
        int tamanhoAnterior = HibernateUtil.getInstance().recover("from Paciente").
                size();
        
        Paciente p = new Paciente(0, new Date(System.currentTimeMillis()), 
                "PacienteTeste", "M", new Date(System.currentTimeMillis()), 
                "(88)9-9999-9999", null, null, "222.222.22-22", "123", 
                new LinkedList<Agendamento>());
        
        HibernateUtil.getInstance().persist(p);
        
        int tamanhoNovo = HibernateUtil.getInstance().recover("from Paciente").
                size();
        
        assertEquals(++tamanhoAnterior, tamanhoNovo);
        
        tamanho++;
        
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void deveTestarPersistRetornandoConstraintViolationException(){
        HibernateUtil.getInstance().persist(new Paciente(0, 
                new Date(System.currentTimeMillis()), "NaoDevePersistir", "M", 
                new Date(System.currentTimeMillis()), "(88)9-9999-9999", null, 
                null, "111.111.111-11", "123", new LinkedList<Agendamento>()));
    }

    @Test
    public void deveTestarRecoverRetornandoListaDeRegistrosEVerificandoOTamanho() {
        
        List<Paciente> listaPacientes = HibernateUtil.getInstance().
                recover("from Paciente");
        
        assertEquals(7, listaPacientes.size());
        
    }
    
    @Test
    public void deveTestarRecoverRetornandoPacienteComId1(){
        Paciente p = (Paciente) HibernateUtil.getInstance().
                recover("from Paciente where id = 4").get(0);
        
        assertEquals(4, p.getId());
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void deveTestarRecoverPassandoIdInexistenteRetornando0(){
        Paciente p = (Paciente) HibernateUtil.getInstance().
                recover("from Paciente where id = 100").get(0);
        
        assertNull(p);
    }
    
    
    @Test
    public void deveTestarUpdate() {
        
        Paciente pacienteRecuperado = (Paciente) HibernateUtil.getInstance().
                recover("from Paciente where id = 12").get(0);
        
        pacienteRecuperado.setNome("NovoPacienteTeste");
        
        HibernateUtil.getInstance().update(pacienteRecuperado);
        
        Paciente novo = (Paciente) HibernateUtil.getInstance().
                recover("from Paciente where id = 12").get(0);
        
        assertEquals("NovoPacienteTeste", novo.getNome());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDelete() {
        
        String s = new String();
       
        Paciente p = 
                (Paciente) 
                HibernateUtil.
                        getInstance().
                        recover("from Paciente where id = 22").get(0);
        
        HibernateUtil.getInstance().delete(p);
        
        Paciente p1 = (Paciente) HibernateUtil.getInstance().
                recover("from Paciente where id = 22").get(0);
        
        //assertNotNull(p1);
        
    }*/

    @Test
    public void testMain() {
    }
}
