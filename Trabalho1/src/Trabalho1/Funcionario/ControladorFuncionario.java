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
     * Inicia a classe ControladorFuncionario
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

    /**
     * É utilizado pela TelaFuncionario para incluir um novo funcionário na lista de funcionários e gerar matrícula sequencial
     * para cada nova inclusão
     * @param conteudo - Conteúdo do tipo DadosFuncionario recebido na entrada do método cadastroFuncionario da TelaFuncionario
     * @return novo - Novo funcionário quando o conteudo não recebido não é vazio
     */
    public Funcionario incluirFuncionario(DadosFuncionario conteudo) {
        if (conteudo != null) {
                    Funcionario novo = new Funcionario(this.gerarMatriculaSequencial(), conteudo);
                    funcionarios.add(novo);
                    return novo;
        }
        return null;
    }

    /**
     * É utilizado pela classe TelaFuncionario, no método exclusaoFuncionario().
     * Se a matricula informada no parâmetro for existente, exclui o funcionário correspondente a mesma.
     * @param matricula
     * @return Verdadeiro se encontrar a matrícula e remover o funcionário. Falso se a matrícula não estiver cadastrada.
     */
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
    
    /**
     * Altera dados cadastrais dos funcionários de acordo com o solicitado. Se a entrada do campo for 0 (ou 00/00/00 para nascimento)
     * o dados se mantém, caso contrário, é alterado com a informação de entrada correspondente
     * @param matricula
     * @param conteudo
     * @return 
     */

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

    
    /**
     * É utilizado pela TelaFuncionario para exibir a lista de funcionários já cadastrados.
    */
    public void listarFuncionarios() {
        for(Funcionario funcionario: funcionarios){
            System.out.println("Matrícula: " + funcionario.getMatricula() + " | Nome: " + funcionario.getNome());
        }
    }

    /**
     *
     * @return O número da sequência de matrícula.
     */
    public int gerarMatriculaSequencial() {
        this.matriculaSequencial++;
        return this.matriculaSequencial;
    }

    /**
     * 
     * @param matricula 
     * @return O funcionário correspondente ao número de matrícula informado, caso exista.
     */
    public Funcionario findFuncionarioByMatricula(int matricula) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getMatricula() == matricula) {
                return funcionario;
            }
        }
        return null;
    }
    
    /**
     * É utilizado na TelaFuncionario e garante que nenhum funcionário tenha CPF igual a outro
     * @param cpf
     * @return Verdadeiro se o CPF já estiver em uso por algum funcionário já cadastrado
     * Falso caso contrário
     */
        public boolean findFuncionarioByCpf(long cpf) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getCpf() == cpf) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param matricula
     * @return Verdadeiro se o número de matrícula informado existir na lista de funcionários já cadastrados
     * Falso caso contrário
     */
    public boolean validaMatricula(int matricula) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getMatricula() == matricula) {
                return true;
            }
        }
        return false;
    }

}
