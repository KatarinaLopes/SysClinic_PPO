/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.views;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Katarina
 */
@ManagedBean
@ApplicationScoped
public class ViewFeed {
    
    private ViewManagerPacientes viewManagerPaciente;

    public ViewManagerPacientes getViewManagerPaciente() {
        return viewManagerPaciente;
    }

    public void setViewManagerPaciente(ViewManagerPacientes viewManagerPaciente) {
        this.viewManagerPaciente = viewManagerPaciente;
    }
    
    private int i = 1;
    
    public int getI(){
        return i;
    }
    
    public void increment(){
        i++;
    }
}
