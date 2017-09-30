/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trabalho1.Cargo;

import java.util.Date;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public class DadosCargo {
    
    private final int codigo;
    private final String nome;
    private final boolean  permiteAcesso;
    private final boolean ehGerencial;
    private final Date horarioPermitido;
    private final TipoCargo tipoCargo;

    public DadosCargo(int codigo, String nome, boolean permiteAcesso, boolean ehGerencial, Date horarioPermitido, TipoCargo tipo) {
        this.codigo = codigo;
        this.nome = nome;
        this.permiteAcesso = permiteAcesso;
        this.ehGerencial = ehGerencial;
        this.horarioPermitido = horarioPermitido;
        this.tipoCargo = tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public boolean isPermiteAcesso() {
        return permiteAcesso;
    }

    public boolean isEhGerencial() {
        return ehGerencial;
    }

    public Date getHorarioPermitido() {
        return horarioPermitido;
    }

    public TipoCargo getTipoCargo() {
        return tipoCargo;
    }
    
    
    
    
}
