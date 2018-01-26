/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.managers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Agendamento;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Feed;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Medico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Mensagem;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Paciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoPaciente;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.DaoGenerico;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.apache.tomcat.jni.Library;

/**
 *
 * @author Katarina
 */
public final class FeedManager {

    private Feed feed;

    public void inserirMensagemDeExclusao(HashMap<Paciente, Date> rolPacientes,
            Medico medico) {

        List<Paciente> pacientes = new ArrayList<>();
        pacientes.addAll(rolPacientes.keySet());

        Date[] datasPrevistas = (Date[]) rolPacientes.values().toArray();

        int i = 0;

        for (Paciente paciente : pacientes) {
            paciente.getFeed().incluirMensagensExclusaoDeAgendamento(medico,
                    datasPrevistas[i]);

            i++;

        }

    }

    public void inserirMensagemDeAtualizacaoDeHorario(HashMap<Paciente, Date> rolPacientes, Date horarioNovo, Medico medico) {

        List<Paciente> pacientes = new ArrayList<>();
        pacientes.addAll(rolPacientes.keySet());
        
        Date[] datasPrevistas = (Date[]) rolPacientes.values().
                toArray(new Date[rolPacientes.size()]);

        int i = 0;

        for (Paciente paciente : pacientes) {
            paciente.getFeed().incluirMensagensAlteracaoDeHorario(
                    datasPrevistas[i], horarioNovo, medico);

            i++;

        }
    }

    public void excluirMensagem(Mensagem m, Paciente p) {
        p.getFeed().excluirMensagem(m);

    }

    public static List<Mensagem> exibirMensagens(Paciente p) {
        return p.getFeed().getMensagens();
    }

}
