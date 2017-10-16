package Trabalho1.Principal;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public class TelaPrincipal {
    private final ControladorPrincipal controladorPrincipal;
    private final Scanner teclado;
    
    /**
     * Recebe o controladorPrincipal como parametro para possibilitar a
     * comunicacao e cria um objeto da Classe TelaPrincipal
     * @param controladorPrincipal controladorPrincipal em execução no programa
     */
    public TelaPrincipal(ControladorPrincipal controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;
        this.teclado = new Scanner(System.in);
    }
    
    public ControladorPrincipal getControladorPrincipal(){
        return this.controladorPrincipal;
    }
    
    /**
     * Inicia a tela com o Menu das opções principais do sistema. Trata exceções do tipo 
     * IllegalArgumentException e InputMismatchException que possam vir dos ontros controladores, impedindo a finalização do programa.
     *
     */
    public void inicia(){
        int opcao = 0;
        try{
            System.out.println("--- Bem vindo ao sistema! ---");
            System.out.println("--- Para acessar os módulos, escolha uma das opções abaixo e tecle enter. ---");
            System.out.println("--- 1 - Cargos ---");
            System.out.println("--- 2 - Funcionarios ---");
            System.out.println("--- 3 - Acessar Setor Financeiro  ---");
            System.out.println("--- 4 - Sair  ---");
        
            try{
                opcao = teclado.nextInt();
            }
            catch(InputMismatchException e){
                throw new IllegalArgumentException("Opção inválida! Escolha uma opção dentre as opções na lista.");
            }
            switch(opcao){
                case(1):
                    this.getControladorPrincipal().getControladorCargo().getTelaCargo().inicia();
                    break;
                
                case(2):
                    this.getControladorPrincipal().getControladorFuncionario().getTelaFuncionario().inicia();
                    break;
                
                case(3):
                    this.getControladorPrincipal().getControladorAcesso().getTelaAcesso().inicia();
                    break;
                
                case (4):
                    System.exit(0);
            
                default:    
                    throw new IllegalArgumentException("Opção inválida! Escolha uma opção dentre as opções da lista.");
            }
        }
        catch(IllegalArgumentException e){
            e.getMessage();
            String [] args = {"1"};
            ClassePrincipal.main(args);
        }    
    }  
        
    }
