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

    private String pendenteSchedule = 
            "/restricted/acoes/include_pendente_schedule.xhtml";
    
    private String hojeTabela = "/restricted/acoes/include_hoje_tabela.xhtml";
    
    private String hojeSchedule = 
            "/restricted/acoes/include_hoje_schedule.xhtml";
    
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
    
    
}
