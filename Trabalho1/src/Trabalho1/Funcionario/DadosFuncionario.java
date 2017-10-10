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
    public final long cpf;
    public final String nome;
    public final Cargo cargo;
    public final Date nascimento;
    public final long telefone;
    public final float salario;

    public DadosFuncionario(long cpf, String nome, Cargo cargo, Date nascimento, long telefone, float salario) {
        this.cpf = cpf;
        this.nome = nome;
        this.cargo = cargo;
        this.nascimento = nascimento;
        this.telefone = telefone;
        this.salario = salario;
    }
    
}
