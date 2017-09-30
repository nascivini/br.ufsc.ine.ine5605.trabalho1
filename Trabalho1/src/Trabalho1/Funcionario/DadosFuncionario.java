package Trabalho1.Funcionario;

import Trabalho1.Cargo.Cargo;
import java.util.Date;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public abstract class DadosFuncionario {
    private final String nome;
    private final Cargo cargo;
    private final Date nascimento;
    private final long telefone;
    private final float salario;

    public DadosFuncionario(String nome, Cargo cargo, Date nascimento, long telefone, float salario) {
        this.nome = nome;
        this.cargo = cargo;
        this.nascimento = nascimento;
        this.telefone = telefone;
        this.salario = salario;
    }
    
}
