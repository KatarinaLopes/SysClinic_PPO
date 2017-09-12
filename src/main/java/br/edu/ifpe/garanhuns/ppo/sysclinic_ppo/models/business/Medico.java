/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Katarina
 */
@Entity
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Deprecated
    public Medico() {
    }

    public int getId() {
        return id;
    }
    
    
    
}
