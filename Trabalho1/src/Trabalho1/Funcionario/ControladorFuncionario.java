package Trabalho1.Funcionario;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
import Trabalho1.Principal.ControladorPrincipal;
import java.util.ArrayList;

public class ControladorFuncionario {

    private ArrayList<Funcionario> funcionarios;
    private ControladorPrincipal controladorPrincipal;
    private TelaFuncionario telaFuncionario;
    private int matriculaSequencial = 0;
 
    /**
     * 
     * @param controladorPrincipal 
     */
    public ControladorFuncionario(ControladorPrincipal controladorPrincipal) {
        this.funcionarios = new ArrayList<Funcionario>();
        this.controladorPrincipal = controladorPrincipal;
        this.telaFuncionario = new TelaFuncionario(this);
    }

    public TelaFuncionario getTelaFuncionario() {
        return telaFuncionario; 
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }
    
        public ControladorPrincipal getControladorPrincipal() {
        return this.controladorPrincipal;
    }

    
    public Funcionario incluirFuncionario(DadosFuncionario conteudo) {
        if (conteudo != null) {
            for (Funcionario funcionario : funcionarios) {
                if (funcionario.getCpf() == conteudo.cpf) { //a classe transiente só é usada para passar parâmetros para a classe Funcionario, por isso ela pode ser pública, não precisa de get e set
                    return null;
                } else {
                    Funcionario novo = new Funcionario(this.gerarMatriculaSequencial(), conteudo);
                    funcionarios.add(novo);
                }
            }
        }
        return null;
    }

    public boolean excluirFuncionario(int matricula) {
        if (this.findFuncionarioByMatricula(matricula) != null) {
            for (int i = 0; i < this.funcionarios.size(); i++) {
                if (this.funcionarios.get(i).getMatricula() == matricula) {
                    funcionarios.remove(i);
                    return true;
                }
            }
        }
        return false;
    }
    

    public Funcionario alterarFuncionario(int matricula, DadosFuncionario conteudo) {
        if (validaMatricula(matricula)) {
            if (conteudo != null) {
                if (conteudo.cpf != 0) {
                    this.findFuncionarioByMatricula(matricula).setCpf(conteudo.cpf);
                }
                if (!conteudo.nome.equals("0")) {
                    this.findFuncionarioByMatricula(matricula).setNome(conteudo.nome);
                }
                if (!conteudo.cargo.equals(0)) {
                    this.findFuncionarioByMatricula(matricula).setCargo(conteudo.cargo);
                }
                if (!conteudo.nascimento.equals(0)) {
                    this.findFuncionarioByMatricula(matricula).setNascimento(conteudo.nascimento);
                }
                if (conteudo.telefone != 0) {
                    this.findFuncionarioByMatricula(matricula).setTelefone(conteudo.telefone);
                }
                if (conteudo.salario != 0) {
                    this.findFuncionarioByMatricula(matricula).setSalario(conteudo.salario);
                }              
            } else {
                this.findFuncionarioByMatricula(matricula);
            }
        } else {
            return null;
        }
        return null;
    }

    

    public void listarFuncionarios() {
        for(Funcionario funcionario: funcionarios){
            System.out.println("Matrícula: " + funcionario.getMatricula() + " | Nome: " + funcionario.getNome());
        }
    }

    /**
     *
     * @return O número da sequência de matrícula
     */
    public int gerarMatriculaSequencial() {
        this.matriculaSequencial++;
        return this.matriculaSequencial;
    }

    public Funcionario findFuncionarioByMatricula(int matricula) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getMatricula() == matricula) {
                return funcionario;
            }
        }
        return null;
    }

    public boolean validaMatricula(int matricula) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getMatricula() == matricula) {
                return true;
            }
        }
        return false;
    }

}
