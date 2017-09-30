package Trabalho1.Funcionario;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
import Trabalho1.Funcionario.DadosFuncionario;
import Trabalho1.Funcionario.ControladorFuncionario;
import java.util.Scanner;

public class TelaFuncionario {
    private Scanner teclado;
    private ControladorFuncionario controladorFuncionario;

    public TelaFuncionario(ControladorFuncionario controladorFuncionario){
        this.controladorFuncionario = controladorFuncionario;
        this.teclado = new Scanner(System.in);
    }
    
    public void inicia(){
        int opcao = teclado.nextInt();
        System.out.println("Gerenciamento de Funcionarios:");
        System.out.println("1 - Incluir Funcionario");
        System.out.println("2 - Excluir Funcionario");
        System.out.println("3 - Alterar Dados de um Funcionario");
        System.out.println("4 - Listar Funcionários Cadastrados");
    }
}

