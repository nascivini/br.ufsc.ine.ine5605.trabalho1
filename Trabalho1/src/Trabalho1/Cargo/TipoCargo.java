/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trabalho1.Cargo;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public enum TipoCargo {
    GERENCIAL("Cargo Gerencial", true),
    COMUM("Cargo Comum", true),
    CONVIDADO("Cargo Convidado", false);
    private String descricao;
    private boolean permiteAcesso;
    
    TipoCargo(String descricao, boolean permiteAcesso){
        this.descricao = descricao;
        this.permiteAcesso = permiteAcesso;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
    
    public boolean getPermiteAcesso(){
        return this.permiteAcesso;
    }
}
