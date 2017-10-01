package Trabalho1.Principal;

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
        
        System.out.println("--- Bem vindo ao sistema! ---");
        System.out.println("--- Para acessar os módulos, escolha uma das opções abaixo e tecle enter. ---");
        System.out.println("--- 1 - Cargos ---");
        System.out.println("--- 2 - Funcionarios ---");
        System.out.println("--- 3 - Acessos ---");
        
        int opcao = teclado.nextInt();
        
        if(opcao == 1){
                this.controladorPrincipal.getControladorCargo().getTelaCargo().inicia();
        }
    }
}
