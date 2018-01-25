/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.views;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Feed;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Mensagem;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoPaciente;
import java.text.SimpleDateFormat;
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
    
    private Feed feed;
    
    
    private static final String CABECALHO_MENSAGEM = "O agendamento feito "
            + "para o médico %s ,nº de registro %s, especialidade %s, para a "
            + "data %s ";
            
    public static final String MENSAGEM_EXCLUSAO = CABECALHO_MENSAGEM + " foi "
            + "cancelado devido ao desligamento do médico em questão. Entre"
            + " em contato ou marque um novo agendamento.";
    
    public static final String MENSAGEM_ALTERACAO = CABECALHO_MENSAGEM + " teve"
            + " seu horário de início remarcado para %s devido a alteração nos "
            + "horários do médico em questão.";
    
    
    public void incluirMensagensExclusaoDeAgendamento(Medico m, Paciente p, 
            Agendamento agendamento){
        
                        
            String conteudoMensagem = String.format(MENSAGEM_EXCLUSAO, 
                    m.getNome(), m.getConselho(), m.getEspecialidade(), 
                    agendamento.getDataPrevista());
            
            p.getFeed().incluirMensagem(
                    new Mensagem(new Date(System.currentTimeMillis()), 
                        conteudoMensagem, "Alerta"));
            
        
    }
    
    public void incluirMensagensAlteracaoDeHorario(Medico m, Paciente p, 
            Date horarioAnterior, Agendamento agendamento){
        
        String conteudo = String.format(MENSAGEM_ALTERACAO, m.getNome(), 
                m.getConselho(), m.getEspecialidade(),
                new SimpleDateFormat("HH:mm").format(horarioAnterior), 
                agendamento.getPeriodo());
        
        p.getFeed().incluirMensagem(new Mensagem(new Date(System.
                currentTimeMillis()), conteudo, "Alerta"));
        
    }
    
    public void excluirMensagem(Mensagem m, Paciente p){
        p.getFeed().excluirMensagem(m);
    }
    
    public List<Mensagem> exibirMensagens(Paciente p){
        return p.getFeed().getMensagens();
    }
    
}
