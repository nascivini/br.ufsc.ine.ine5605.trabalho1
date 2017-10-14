package Trabalho1.Acesso;

import java.util.Scanner;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public class TelaAcesso {

    private Scanner teclado;
    private ControladorAcesso controladorAcesso;

    /**
     * Recebe o controlador Acesso como parametro para possibilitar a
     * comunicacao e cria um objeto da Classe TelaAcesso
     *
     * @param controladorAcesso
     */
    public TelaAcesso(ControladorAcesso controladorAcesso) {
        this.controladorAcesso = controladorAcesso;
        this.teclado = new Scanner(System.in);
    }

    public ControladorAcesso getControladorAcesso() {
        return this.controladorAcesso;
    }

    public void inicia() throws IllegalArgumentException {
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
                    this.controladorAcesso.getControladorPrincipal().getTelaPrincipal().inicia();
                    break;

                default:
                    throw new IllegalArgumentException("Opcaio Invalida! Escolha uma opcao dentre das opcoes na lista.");
            }
        } catch (Exception InvallidArgumentException) {
            this.inicia();
        }
    }

    public void menuAcessosNegados() throws IllegalArgumentException {
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
                    this.controladorAcesso.findAcessosNegados();
                    this.menuAcessosNegados();
                    break;
                case (2):
                    System.out.println("Digite a matricula e tecle enter: ---");
                    int matricula = teclado.nextInt();
                    this.controladorAcesso.findAcessosNegadosByMatricula(matricula);
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
                    MotivoAcesso motivo;
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
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Motivo não cadastrado. Você deveria digitar opções válidas.");
                        this.menuAcessosNegados();
                    }
                    this.controladorAcesso.findAcessosNegadosByMotivo(motivo);
                    this.menuAcessosNegados();
                    break;
                case (4):
                    this.inicia();
                    break;
                default:
                    throw new IllegalArgumentException("Opcao Invalida! Escolha uma opcao dentre das opcoes na lista.");
            }
        } catch (Exception InvallidArgumentException) {
            this.inicia();
        }
    }

    public void realizarAcesso() {
        System.out.println("Digite a matricula na qual deseja efetuar o acesso");
        int matricula = teclado.nextInt();
        if (this.controladorAcesso.getControladorPrincipal().getControladorFuncionario().validaMatricula(matricula)) {
            Acesso acesso = this.controladorAcesso.verificaAcesso(matricula);
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
        } else {
            System.out.println("Matricula nao encontrada.");
            this.realizarAcesso();
        }
    }

}
