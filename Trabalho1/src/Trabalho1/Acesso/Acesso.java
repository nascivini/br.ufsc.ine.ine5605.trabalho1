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
    private final int matricula;
    private final MotivoAcesso motivo;

    /**
     * Recebe os parametros e cria um objeto da Classe acesso
     * @param horario Horario do Acesso
     * @param matricula Matrícula do Funcionário que realizou o Acesso
     * @param motivo Motivo do Acesso
     */
    public Acesso(Calendar horario, int matricula, MotivoAcesso motivo) {
        this.horario = horario;
        this.matricula = matricula;
        this.motivo = motivo;
    }

    public Calendar getHorario() {
        return horario;
    }

    public int getMatricula() {
        return matricula;
    }

    public MotivoAcesso getMotivo() {
        return motivo;
    }
}
