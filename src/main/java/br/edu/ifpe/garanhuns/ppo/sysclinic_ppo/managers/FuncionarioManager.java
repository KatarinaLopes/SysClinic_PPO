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
import java.util.List;

/**
 *
 * @author Katarina
 */
public class FuncionarioManager {
    private DaoGenerico daoFuncionarios;
    
    public FuncionarioManager(DaoFuncionario daoFuncionarios){
        this.daoFuncionarios = daoFuncionarios;
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
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        validar(funcionario, confirmacaoSenha);
        daoFuncionarios.persistir(funcionario);
    }
    
    public Funcionario recuperar(int id){
        return (Funcionario) daoFuncionarios.recuperar(id);
    }
    
    public void atualizar(Funcionario f){
        daoFuncionarios.atualizar(f);
    }
    
    public void deletar(Funcionario f){
        daoFuncionarios.deletar(f);
    }
    
    public List<Funcionario> recuperarTodos(){
        return daoFuncionarios.recuperarTodos();
    }
    
    public boolean existeMaisDeUm(boolean administrador){
        int qtdeFuncionario = 0;
        int qtdeAdministrador = 0;

        List<Funcionario> funcionarios = daoFuncionarios.recuperarTodos();

        for (Funcionario funcionario : funcionarios) {
            if (funcionario.isAdministrador()) {
                qtdeAdministrador++;
            } else {
                qtdeFuncionario++;
            }
        }

        if (administrador) {
            return qtdeAdministrador > 1;
        } else {
            return qtdeFuncionario > 1;
        }
    }
    
    public String retornarMensagemDeAlerta(){
        return "Administradores podem cadastrar, alterar e excluir "
                        + "médicos e funcionários, mas não têm acesso "
                        + "ao sistema de agendamentos. Mude o "
                        + "privilégio apenas se necessário";
    }
}
