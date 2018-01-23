/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.views;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Mensagem;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoPaciente;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.apache.tomcat.jni.Library;

/**
 *
 * @author Katarina
 */
public final class ViewManagerPacientes {
    
    public void incluirMensagensExclusaoDeAgendamento(Medico m){
        List<Agendamento> agendamentos = m.getAgenda().getAgendamentos();
        
        for (Agendamento agendamento : agendamentos) {
            Paciente p = agendamento.getPaciente();
            
            String conteudoMensagem = "O agendamento feito para o médico "
            + m.getNome() + ", " + m.getConselho() + ", especialidade " 
                    + m.getEspecialidade() + ", para a data " + 
                    agendamento.getDataPrevista() + " foi cancelado e excluído "
                            + "devido ao desligamento do médico em questão. "
                            + "Mais informações, entrar em contato com a "
                            + "clínica";
            
            p.getFeed().incluirMensagem(
                    new Mensagem(new Date(System.currentTimeMillis()), 
                        conteudoMensagem, "Alerta"));
            
            new DaoPaciente().atualizar(p);
        }
    }
    
    public void excluirMensagem(Mensagem m, Paciente p){
        p.getFeed().excluirMensagem(m);
        
        new DaoPaciente().atualizar(p);
    }
}
