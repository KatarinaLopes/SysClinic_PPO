/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Katarina
 */
@ManagedBean
@ViewScoped
public class BeanPaginas implements Serializable{
    private String pendenteTabela = 
            "/restricted/acoes/include_pendentes_tabela.xhtml";

    private final String pendenteSchedule = 
            "/restricted/acoes/include_pendente_schedule.xhtml";
    
    private final String hojeTabela = 
            "/restricted/acoes/include_hoje_tabela.xhtml";
    
    private final String hojeSchedule = 
            "/restricted/acoes/include_hoje_schedule.xhtml";
    
    private final String concluidosTabela = 
            "/restricted/acoes/include_concluidos_tabela.xhtml";
    
    private final String concluidosSchedule = 
            "/restricted/acoes/include_concluidos_schedule.xhtml";
    
    private final String pacientesCadastrados = "/funcionarios/"
            + "pacientes_cadastrados.xhtml";
    
    private final String alterarPaciente = "/acoes/alterar_paciente.xhtml";
    
    private final String includeAlterarPaciente = "/restricted/pacientes/"
            + "include_alterar_pacientes.xhtml";
    
    private final String informacoesPessoais = "/pacientes/"
            + "informacoes_pessoais.xhtml";
    
    private String paginaAtual = "";
    
    
    public String getPendenteTabela() {
        return pendenteTabela;
    }

    public void setPendenteTabela(String pendenteTabela) {
        this.pendenteTabela = pendenteTabela;
    }

    public String getPaginaAtual() {
        return paginaAtual;
    }

    public void setPaginaAtual(String paginaAtual) {
        System.out.println(paginaAtual + "  f" + this.paginaAtual);
        this.paginaAtual = paginaAtual;
    }

    public String getPendenteSchedule() {
        return pendenteSchedule;
    }

    public String getHojeTabela() {
        return hojeTabela;
    }

    public String getHojeSchedule() {
        return hojeSchedule;
    }

    public String getConcluidosTabela() {
        return concluidosTabela;
    }

    public String getConcluidosSchedule() {
        return concluidosSchedule;
    }

    public String getPacientesCadastrados() {
        return pacientesCadastrados;
    }

    public String getAlterarPaciente() {
        return alterarPaciente;
    }

    public String getIncludeAlterarPaciente() {
        return includeAlterarPaciente;
    }

    public String getInformacoesPessoais() {
        return informacoesPessoais;
    }
    
}
