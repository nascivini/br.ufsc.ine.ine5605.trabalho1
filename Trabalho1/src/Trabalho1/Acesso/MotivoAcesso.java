/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trabalho1.Acesso;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public enum MotivoAcesso {
    
    TRANSITO("Trânsito, engarrafamento."),
    CLIMA("Chuva"),
    OUTRO("NÂO ESPECIFICADO");
    
    private String descricao;
    
    MotivoAcesso(String descricao){
        this.descricao = descricao;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
}
