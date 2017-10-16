package Trabalho1.Funcionario;

import Trabalho1.Cargo.Cargo;
import java.util.Calendar;

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
    private Calendar nascimento;
    private long telefone;
    private float salario;
    private int nAcessosNegados;

    public Funcionario(int matricula, DadosFuncionario conteudo) {
        this.matricula = matricula;
        this.cpf = conteudo.cpf;
        this.nome = conteudo.nome;
        this.cargo = conteudo.cargo;
        this.nascimento = conteudo.nascimento;
        this.telefone = conteudo.telefone;
        this.salario = conteudo.salario;
        this.setnAcessosNegados(0);
    }
    
    /**
     * 
     * @return A matrícula do funcionário.
     */
    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
    
    /**
     * 
     * @return O nome do funcionário.
     */
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /**
     * 
     * @return O cargo do funcionário. 
     */
    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
    /**
     * 
     * @return A data de nascimento do funcionário.
     */
    public Calendar getNascimento() {
        return nascimento;
    }

    public void setNascimento(Calendar nascimento) {
        this.nascimento = nascimento;
    }

    /**
     * 
     * @return O número de telefone do funcionário.
     */
    public long getTelefone() {
        return telefone;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }

    /**
     * 
     * @return O salário do funcionário.
     */
    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    /**
     * 
     * @return O CPF do funcionário.
     */
    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }
    
    /**
     * 
     * @return O número de acessos negados do funcionário.
     */
	public int getnAcessosNegados() {
		return nAcessosNegados;
	}

	public void setnAcessosNegados(int nAcessosNegados) {
		this.nAcessosNegados = nAcessosNegados;
	}        
}
