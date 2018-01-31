/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.managers;

import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.business.Funcionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.DaoFuncionario;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.persistence.dao.manager.DaoGenerico;
import br.edu.ifpe.garanhuns.ppo.sysclinic_ppo.models.validators.Operacoes;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Katarina
 */
public class FuncionarioManager {
    private DaoGenerico daoFuncionarios;
    
    public FuncionarioManager(){
        daoFuncionarios = new DaoFuncionario();
    }
    
    public void validar(Funcionario funcionario, String senha){
        String senhaFuncionario = funcionario.getSenha();

        if (!senhaFuncionario.equals(senha)) {
            throw new IllegalArgumentException("As senhas não correspondem!");
        }
       
        Funcionario existente = (Funcionario) daoFuncionarios.
                recuperarPorAtributo("matricula", funcionario.getMatricula());
        
        if(existente != null){
            throw new IllegalArgumentException("Este funcionário já está "
                    + "cadastrado, verifique se a matrícula está correto");
        }
    }
    
    public void cadastrar(Funcionario funcionario, String confirmacaoSenha) 
            throws NoSuchAlgorithmException, UnsupportedEncodingException{
        validar(funcionario, confirmacaoSenha);
        funcionario.setSenha(Operacoes.criptografarSenha(funcionario.
                getSenha()));
        daoFuncionarios.persistir(funcionario);
    }
}
