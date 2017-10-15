package Trabalho1.Acesso;

import Trabalho1.Principal.ClassePrincipal;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public class TelaAcesso {

    private final Scanner teclado;
    private final ControladorAcesso controladorAcesso;

    /**
     * Recebe o controlador Acesso como parametro para possibilitar a
     * comunicacao e cria um objeto da Classe TelaAcesso
     * @param controladorAcesso ControladorAcesso em execução no programa.
     */
    public TelaAcesso(ControladorAcesso controladorAcesso) {
        this.controladorAcesso = controladorAcesso;
        this.teclado = new Scanner(System.in);
    }

    public ControladorAcesso getControladorAcesso() {
        return this.controladorAcesso;
    }
    /**
     * Inicia a tela com o Menu das opções para o módulo de Acesso. Pode jogar exceções do tipo IllegalArgumentException e InputMismatchException.
     * @throws IllegalArgumentException Caso seja digitada uma opção inválida.
     * @throws InputMismatchException Caso seja digitado um caractere inválido.
     */
    public void inicia() throws IllegalArgumentException, InputMismatchException {
        System.out.println("--- Menu para Acesso / Controle de Acesso: ---");
        System.out.println("Escolha a opcao desejada, insira o numero e tecle enter: ---");
        System.out.println("1 - Realizar um Acesso");
        System.out.println("2 - Listar Acessos Negados");
        System.out.println("3 - Voltar ao Menu Principal");

        int opcao = teclado.nextInt();
        try {
            switch (opcao) {
                case (1):
                    this.realizarAcesso();
                    break;
                case (2):
                    this.menuAcessosNegados();
                    break;
                case (3):
                    this.getControladorAcesso().getControladorPrincipal().getTelaPrincipal().inicia();
                    break;

                default:
                    throw new IllegalArgumentException();
            }
        } 
        catch (IllegalArgumentException | InputMismatchException e) {
            System.out.println("Opção Inválida! Escolha uma opção dentre das opções na lista.");
            String[] args = null;
            ClassePrincipal.main(args);
        }
    }
    /**
     * Inicia a tela de listagem de acessos negados. Faz o tratamento dos dados inseridos pelo usuário e, 
     * antes da exclusão, verifica a existência do cargo utilizando se dos métodos de controladorAcesso.
     */
    public void menuAcessosNegados(){
        System.out.println("--- Menu de Listagem de Acessos Negados: ---");
        System.out.println("Escolha a opcao desejada, insira o numero e tecle enter: ---");
        System.out.println("1 - Listar todos acessos negados");
        System.out.println("2 - Listar acessos negados por matricula");
        System.out.println("3 - Listar acessos negados por motivo");
        System.out.println("4 - Voltar ao menu geral Acesso.");

        int opcao = teclado.nextInt();
        try {
            switch (opcao) {
                case (1):
                    this.getControladorAcesso().findAcessosNegados();
                    this.menuAcessosNegados();
                    break;
                case (2):
                    System.out.println("Digite a matricula e tecle enter: ---");
                    int matricula = teclado.nextInt();
                    this.getControladorAcesso().findAcessosNegadosByMatricula(matricula);
                    this.menuAcessosNegados();
                    break;
                case (3):
                    System.out.println("Escolha o motivo, digite o respectivo numero e tecle enter: ---");
                    System.out.println("1 - OK");
                    System.out.println("2 - ATRASADO");
                    System.out.println("3 - PERMISSAO");
                    System.out.println("4 - BLOQUEADO");
                    System.out.println("5 - OUTRO");

                    int opcaoMotivo = teclado.nextInt();
                    MotivoAcesso motivo = MotivoAcesso.OK;
                    try {
                        switch (opcaoMotivo) {
                            case (1):
                                motivo = MotivoAcesso.OK;
                                break;
                            case (2):
                                motivo = MotivoAcesso.ATRASADO;
                                break;
                            case (3):
                                motivo = MotivoAcesso.PERMISSAO;
                                break;
                            case (4):
                                motivo = MotivoAcesso.BLOQUEADO;
                                break;
                            case (5):
                                motivo = MotivoAcesso.OUTRO;
                                break;
                            default:
                                throw new IllegalArgumentException("Motivo não cadastrado. Você deve digitar opções válidas. Selecione um motivo dentre os motivos listados.");
                        }
                    } 
                    catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        this.inicia();
                    }
                    this.getControladorAcesso().findAcessosNegadosByMotivo(motivo);
                    this.menuAcessosNegados();
                    break;
                case (4):
                    this.inicia();
                    break;
                default:
                    throw new IllegalArgumentException("Opcao Invalida! Escolha uma opcao dentre das opcoes na lista.");
            }
        } 
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            this.inicia();
        }
    }

     /**
     * Inicia a tela de acesso ao sistema. Faz o tratamento dos dados inseridos pelo usuário e, 
     * realiza o acesso utilizando-se dos métodos de controladorAcesso.
     */
    public void realizarAcesso() {
        System.out.println("Digite a matricula na qual deseja efetuar o acesso");
        int matricula = teclado.nextInt();
        if (this.getControladorAcesso().getControladorPrincipal().getControladorFuncionario().validaMatricula(matricula)) {
            Acesso acesso = this.getControladorAcesso().verificaAcesso(matricula);
            switch (acesso.getMotivo()) {
                case OK:
                    System.out.println("Acesso realizado com sucesso.");
                    this.inicia();
                    break;
                case ATRASADO:
                    System.out.println("Acesso negado, atrasado.");
                    this.inicia();
                    break;
                case PERMISSAO:
                    System.out.println("Acesso negado, sem permissao.");
                    this.inicia();
                    break;
                case BLOQUEADO:
                    System.out.println("Acesso negado, bloqueado.");
                    this.inicia();
                    break;
                case OUTRO:
                    System.out.println("Acesso negado, motivo nao cadastrado. Consulte o administrador do sistema.");
                    this.inicia();
                    break;
            }
        } 
        else {
            System.out.println("Matricula nao encontrada.");
            this.realizarAcesso();
        }
    }

}
