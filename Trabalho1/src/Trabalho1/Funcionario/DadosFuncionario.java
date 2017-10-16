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

    /**
     * Recebe os dados de um funcionário, vindo da TelaFuncionario
     * @param cpf CPF do funcionário
     * @param nome Nome do funcionário
     * @param cargo Cargo do funcionário
     * @param nascimento Data de nascimento do funcionário
     * @param telefone Telefone do funcionário
     * @param salario Salário do funcionário
     */
    
    public DadosFuncionario(long cpf, String nome, Cargo cargo, Calendar nascimento, long telefone, float salario) {
        this.cpf = cpf;
        this.nome = nome;
        this.cargo = cargo;
        this.nascimento = nascimento;
        this.telefone = telefone;
        this.salario = salario;
    }
    
}
