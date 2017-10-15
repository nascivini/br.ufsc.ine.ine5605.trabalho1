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
    
    public TelaPrincipal(ControladorPrincipal controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;
        this.teclado = new Scanner(System.in);
    }
    
    public ControladorPrincipal getControladorPrincipal(){
        return this.controladorPrincipal;
    }
    
    public void inicia(){
        try{

            System.out.println("--- Bem vindo ao sistema! ---");
            System.out.println("--- Para acessar os módulos, escolha uma das opções abaixo e tecle enter. ---");
            System.out.println("--- 1 - Cadastro de Cargos ---");
            System.out.println("--- 2 - Cadastro de Funcionarios ---");
            System.out.println("--- 3 - Acessar Setor Financeiro  ---");
        
            int opcao = teclado.nextInt();
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
            
                default:    
                    throw new IllegalArgumentException("Opção inválida! Escolha uma opção dentre as opções da lista.");
            }
        }
        catch(IllegalArgumentException e){
            e.getMessage();
        }
        
        catch(InputMismatchException e){
            System.out.println("Opção inválida! Escolha uma opção dentre as opções da lista.");
            String [] args = null;
            ClassePrincipal.main(args);
        }
        
    }   

}

