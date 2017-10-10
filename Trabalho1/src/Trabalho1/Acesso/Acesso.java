/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trabalho1.Acesso;

import java.util.Calendar;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public class Acesso {
    private final Calendar horario;
    private final boolean ehPermitido;
    private final int matricula;
    private MotivoAcesso motivo;
    
    public Acesso(Calendar horario, boolean ehPermitido, int matricula, MotivoAcesso motivo){
        this.horario = horario;
        this.ehPermitido = ehPermitido;
        this.matricula = matricula;
        this.motivo = motivo;
    }

    public Calendar getHorario() {
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
