/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business;

import java.io.Serializable;
import java.util.ArrayList;
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
public class Feed implements Serializable{
   
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
    
    
   public void incluirMensagem(Mensagem m){
       for (Mensagem mensagen : mensagens) {
           if(m.equals(mensagen)){
               return;
           }
       }
       
       mensagens.add(m);
   }
   
   public void excluirMensagem(Mensagem m){
       for (Mensagem mensagen : mensagens) {
           if(m.equals(mensagen)){
               mensagens.remove(mensagen);
               break;
           }
       }
   }  
  
}
