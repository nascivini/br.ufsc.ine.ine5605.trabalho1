package Trabalho1.Funcionario;

import Trabalho1.Cargo.Cargo;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public class DadosFuncionario {
    public final long cpf;
    public final String nome;
    public final Cargo cargo;
    public final Calendar nascimento;
    public final long telefone;
    public final float salario;

    public DadosFuncionario(long cpf, String nome, Cargo cargo, Calendar nascimento, long telefone, float salario) {
        this.cpf = cpf;
        this.nome = nome;
        this.cargo = cargo;
        this.nascimento = nascimento;
        this.telefone = telefone;
        this.salario = salario;
    }
    
}
