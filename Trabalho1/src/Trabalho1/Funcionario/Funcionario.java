package Trabalho1.Funcionario;

import Trabalho1.Cargo.Cargo;
import java.util.Date;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public class Funcionario {
    private int matricula;
    private String nome;
    private Cargo cargo;
    private Date nascimento;
    private long telefone;
    private float salario;

    public Funcionario(int matricula, String nome, Cargo cargo, Date nascimento, long telefone, float salario) {
        this.matricula = matricula;
        this.nome = nome;
        this.cargo = cargo;
        this.nascimento = nascimento;
        this.telefone = telefone;
        this.salario = salario;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public long getTelefone() {
        return telefone;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }
    
}
