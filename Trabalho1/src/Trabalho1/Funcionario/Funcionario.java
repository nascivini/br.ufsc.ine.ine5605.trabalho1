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
    private long cpf;
    private String nome;
    private Cargo cargo;
    private Date nascimento;
    private long telefone;
    private float salario;
    private int nAcessosNegados;

    public Funcionario(int matricula, DadosFuncionario conteudo, int nAcessosNegados) {
        this.matricula = matricula;
        this.cpf = conteudo.cpf;
        this.nome = conteudo.nome;
        this.cargo = conteudo.cargo;
        this.nascimento = conteudo.nascimento;
        this.telefone = conteudo.telefone;
        this.salario = conteudo.salario;
        this.nAcessosNegados = nAcessosNegados;
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

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }
    
    
}
