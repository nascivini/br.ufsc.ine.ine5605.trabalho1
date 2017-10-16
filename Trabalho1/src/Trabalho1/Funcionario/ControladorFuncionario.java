package Trabalho1.Funcionario;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
import Trabalho1.Principal.ControladorPrincipal;
import java.util.ArrayList;

public class ControladorFuncionario implements IControladorFuncionario {

    private final ArrayList<Funcionario> funcionarios;
    private final ControladorPrincipal controladorPrincipal;
    private final TelaFuncionario telaFuncionario;
    private int matriculaSequencial;
 
    /**
     * Inicia a classe ControladorFuncionario
     * @param controladorPrincipal ControladorPrincipal criado pelo main
     */
    public ControladorFuncionario(ControladorPrincipal controladorPrincipal) {
        this.matriculaSequencial = 0;
        this.funcionarios = new ArrayList<>();
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
                    Funcionario novo = new Funcionario(this.gerarMatriculaSequencial(), conteudo);
                    funcionarios.add(novo);
                    return novo;
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
                if (!conteudo.nascimento.equals(00/00/00)) {
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
    
    @Override
    public void listarFuncionarios() {
        for(Funcionario funcionario: funcionarios){
            System.out.println("Matrícula: " + funcionario.getMatricula() + " | Nome: " + funcionario.getNome());
        }
    }
    
    @Override
    public int gerarMatriculaSequencial() {
        this.matriculaSequencial++;
        return this.matriculaSequencial;
    }
    
    @Override
    public Funcionario findFuncionarioByMatricula(int matricula) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getMatricula() == matricula) {
                return funcionario;
            }
        }
        return null;
    }
    
    @Override
    public boolean findFuncionarioByCpf(long cpf) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getCpf() == cpf) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean validaMatricula(int matricula) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getMatricula() == matricula) {
                return true;
            }
        }
        return false;
    }

}
