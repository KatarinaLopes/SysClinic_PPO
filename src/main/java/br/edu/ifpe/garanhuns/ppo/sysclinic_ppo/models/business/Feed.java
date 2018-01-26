/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 *
 * @author Katarina
 */
@Embeddable
public class Feed implements Serializable {

    private static final String CABECALHO_MENSAGEM = "O agendamento feito "
            + "para o médico %s ,nº de registro %s, especialidade %s, para a "
            + "data %s ";

    public static final String MENSAGEM_EXCLUSAO = CABECALHO_MENSAGEM + " foi "
            + "cancelado devido ao desligamento do médico em questão. Entre"
            + " em contato ou marque um novo agendamento.";

    public static final String MENSAGEM_ALTERACAO = CABECALHO_MENSAGEM + " teve"
            + " seu horário de início remarcado para %s devido a alteração nos "
            + "horários do médico em questão.";

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Mensagem> mensagens;

    public Feed() {
        mensagens = new ArrayList<>();
    }

    public List<Mensagem> getMensagens() {
        return mensagens;
    }

    public void setMensagens(List<Mensagem> mensagens) {
        this.mensagens = mensagens;
    }

    public void incluirMensagem(Mensagem m) {
        for (Mensagem mensagen : mensagens) {
            if (m.equals(mensagen)) {
                return;
            }
        }

        mensagens.add(m);
    }

    public void excluirMensagem(Mensagem m) {
        for (Mensagem mensagen : mensagens) {
            if (m.equals(mensagen)) {
                mensagens.remove(mensagen);
                break;
            }
        }
    }

    public void incluirMensagensExclusaoDeAgendamento(Medico medico, 
            Date dataPrevista) {

        String conteudoMensagem = String.format(MENSAGEM_EXCLUSAO,
                medico.getNome(), medico.getConselho(), 
                medico.getEspecialidade(), dataPrevista);

        incluirMensagem(new Mensagem(new Date(System.currentTimeMillis()),
                conteudoMensagem, "Alerta"));

    }

    public void incluirMensagensAlteracaoDeHorario(Date dataAnterior, 
            Date horarioNovo, Medico medico) {
     
        String conteudo = String.format(MENSAGEM_ALTERACAO, medico.getNome(), 
                medico.getConselho(), medico.getEspecialidade(),
                new SimpleDateFormat("dd/MM/yyyy").format(dataAnterior),
                new SimpleDateFormat("HH:mm").format(horarioNovo));

        incluirMensagem(new Mensagem(new Date(System.
                currentTimeMillis()), conteudo, "Alerta"));
    }

}
