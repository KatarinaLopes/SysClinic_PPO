/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Katarina
 */
@Embeddable
public class Mensagem implements Serializable{  
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInclusao;
    private String conteudo;

    @Deprecated
    public Mensagem(){
        
    }
    
    public Mensagem(Date data, String conteudo) {
        this.dataInclusao = data;
        this.conteudo = conteudo;
    }
    
    public Date getData() {
        return dataInclusao;
    }

    public void setData(Date data) {
        this.dataInclusao = data;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
 
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.dataInclusao);
        hash = 53 * hash + Objects.hashCode(this.conteudo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mensagem other = (Mensagem) obj;
        return Objects.equals(this.conteudo, other.conteudo);
    }
    
    
}
