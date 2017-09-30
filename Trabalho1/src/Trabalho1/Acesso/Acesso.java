/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trabalho1.Acesso;

import java.util.Date;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public class Acesso {
    private final Date horario;
    private final boolean ehPermitido;
    private final int matricula;
    private MotivoAcesso motivo;
    
    public Acesso(Date horario, boolean ehPermitido, int matricula, MotivoAcesso motivo){
        this.horario = horario;
        this.ehPermitido = ehPermitido;
        this.matricula = matricula;
        this.motivo = motivo;
    }

    public Date getHorario() {
        return horario;
    }

    public boolean isEhPermitido() {
        return ehPermitido;
    }

    public int getMatricula() {
        return matricula;
    }

    public MotivoAcesso getMotivo() {
        return motivo;
    }
    
    
}
