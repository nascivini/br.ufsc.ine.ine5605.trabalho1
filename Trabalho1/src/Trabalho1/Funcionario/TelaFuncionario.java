package Trabalho1.Funcionario;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
import Trabalho1.Cargo.Cargo;
import Trabalho1.Funcionario.DadosFuncionario;
import Trabalho1.Funcionario.ControladorFuncionario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TelaFuncionario {
    private Scanner teclado;
    private ControladorFuncionario controladorFuncionario;

    public TelaFuncionario(ControladorFuncionario controladorFuncionario){
        this.controladorFuncionario = controladorFuncionario;
        this.teclado = new Scanner(System.in);
    }
    
    public void inicia() throws IllegalArgumentException, InputMismatchException, ParseException {
        System.out.println("---Menu de Cadastro de Funcionários---");
        System.out.println("Escolha a opção desejada, insira o número correspondente e tecle enter:");
        System.out.println("1 - Incluir Funcionario");
        System.out.println("2 - Excluir Funcionario");
        System.out.println("3 - Alterar Dados de um Funcionario");
        System.out.println("4 - Listar Funcionários Cadastrados");
        System.out.println("5 - Voltar ao Menu Principal");
    
    try {
        int opcao = teclado.nextInt();
        teclado.nextLine();
            switch (opcao) {
                case (1):
                    this.cadastroFuncionario();
                    break;
                case (2):
                    this.exclusaoFuncionario();
                    break;
                case (3):
                    this.alteracaoFuncionario();
                    break;
                case (4):
                    this.listarFuncionario();
                    break;
                case (5):
                    controladorFuncionario.getControladorPrincipal().getTelaPrincipal().inicia();
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        } 
    
    catch (IllegalArgumentException | InputMismatchException e) {
            System.out.println("Opção Inválida! Escolha uma opção dentre as opções na lista.");
            this.controladorFuncionario.getTelaFuncionario().inicia();
        }
    }
    
        public void cadastroFuncionario() throws ParseException {
        System.out.println("Cadastro de Funcionário");
        System.out.println("Insira os dados requisitados. Após a inserção de todos os dados, o funcionário será cadastrado no sistema.");

        System.out.println("CPF: ");
        int cpf = teclado.nextInt();

        System.out.println("Nome: ");
        String nome = teclado.nextLine();

        System.out.println("Código do cargo: ");
        int codigo = teclado.nextInt();        
        Cargo cargo = this.controladorFuncionario.getControladorPrincipal().getControladorCargo().findCargoByCodigo(codigo);
        
        System.out.println("Nascimento: ");
        System.out.println("O formato dia/mês/ano deve ser respeitado. Exemplo: 15/02/1994");
        try {
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            String data = teclado.nextLine();
            Calendar nascimento = Calendar.getInstance();
            nascimento.setTime(formatador.parse(data));
        }
        
        catch (ParseException erro) {
            System.out.println("Essa não é uma data válida.");
            this.controladorFuncionario.getTelaFuncionario().inicia();
        }
        
        System.out.println("Telefone: ");
        long telefone = teclado.nextLong();
        
        System.out.println("Salário: ");
        float salario = teclado.nextFloat();
        
    }

    public void exclusaoFuncionario() throws ParseException {
        System.out.println("Para excluir um funcionário do sistema, digite a matrícula do mesmo.");
        int matricula = teclado.nextInt();

        if (this.controladorFuncionario.excluirFuncionario(matricula)) {
            System.out.println("Funcionário excluído com sucesso!");
            this.inicia();
        } else {
            System.out.println("A matrícula informada não pertence a nenhum funcionário cadastrado.");
            System.out.println("Deseja tentar novamente? Digite Y ou N");
            String opcaoExclusao = teclado.nextLine();
            if (opcaoExclusao.equals("Y") || opcaoExclusao.equals("y")) {
                this.exclusaoFuncionario();
            }
            else{
                this.inicia();
            }
        }
    }

    private void alteracaoFuncionario() throws ParseException {
        System.out.println("Bem-vindo à tela de alteração de dados dos funcionários.");
        System.out.println("Só é possível alterar um dado por vez. Digite a matrícula a ser alterada, e selecione qual dado deseja alterar.");
        
        int matricula = teclado.nextInt();
        if(this.controladorFuncionario.findFuncionarioByMatricula(matricula) == null){
            System.out.println("Funcionário não encontrado. Digite uma matrícula válida.");
            this.alteracaoFuncionario();
        }
        else {
            System.out.println("--------------------------------------");
            System.out.println("ATENÇÃO: Digite 0 nos campos que você não quiser alterar.");
           
            System.out.println("CPF: ");
            int cpf = teclado.nextInt();
            
            System.out.println("Nome: ");
            String nome = teclado.nextLine();
            
            System.out.println("Codigo do cargo: ");
            int codigo = teclado.nextInt();
            Cargo cargo = this.controladorFuncionario.getControladorPrincipal().getControladorCargo().findCargoByCodigo(codigo);
           
            System.out.println("Nascimento:");
                    System.out.println("O formato dia/mês/ano deve ser respeitado. Exemplo: 15/02/1994");
                try {
                SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
                String data = teclado.nextLine();
                Calendar nascimento = Calendar.getInstance();
                nascimento.setTime(formatador.parse(data));
                }
        
                catch (ParseException erro) {
                System.out.println("Essa não é uma data válida.");
                this.controladorFuncionario.getTelaFuncionario().inicia();
                }
            
            System.out.println("Telefone ");
            long telefone = teclado.nextLong();
            
            System.out.println("Salário: ");
            float salario = teclado.nextFloat();
            
            DadosFuncionario novosDados = new DadosFuncionario(long cpf, String nome, Cargo cargo, Calendar nascimento, long telefone, float salario);
            this.controladorFuncionario.alterarFuncionario(matricula, novosDados);
            
        }
        }
    private void listarFuncionarios() throws ParseException {
        this.controladorFuncionario.listarFuncionarios();
        this.inicia();
    }
}

