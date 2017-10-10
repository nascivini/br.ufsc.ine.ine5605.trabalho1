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
    }

    public void excluirFuncionario(int matricula) {
        if (validaMatricula(matricula)) {
            for (int i = 0; i < this.funcionarios.size(); i++) {
                if (this.funcionarios.get(i).getMatricula() == matricula) {
                    funcionarios.remove(i);
                }
            }
        }
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
                

            } else {
                this.findFuncionarioByMatricula(matricula);
            }
        } else {
            return null;
        }
    }

    

    public void listarFuncionarios() {

    }

    /**
     *
     * @return O número da sequência de matrícula
     */
    public int gerarMatriculaSequencial() {
        this.matriculaSequencial++;
        return this.matriculaSequencial;
    }

    private Funcionario findFuncionarioByMatricula(int matricula) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getMatricula() == matricula) {
                return funcionario;
            } else {
                return null;
            }
        }
    }

    public boolean validaMatricula(int matricula) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getMatricula() == matricula) {
                return true;
            } else {
                return false;
            }
        }
    }

}
