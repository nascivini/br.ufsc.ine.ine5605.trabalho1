/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    public TelaAcesso(ControladorAcesso controladorAcesso){
        this.controladorAcesso = controladorAcesso;
    }

    public ControladorAcesso getControladorAcesso() {
        return this.controladorAcesso;
    }
    
    public void inicia() throws IllegalArgumentException{
    	System.out.println("--- Menu para Acesso / Controle de Acesso: ---");
        System.out.println("Escolha a opcao desejada, insira o numero e tecle enter: ---");
        System.out.println("1 - Realizar um Acesso");
        System.out.println("2 - Listar Acessos Negados");
        System.out.println("5 - Voltar ao Menu Principal");
        
        int opcao = teclado.nextInt();
        try{
            switch (opcao){    
                case(1): 
                    break;
                case(5):
                    this.controladorAcesso.getControladorPrincipal().getTelaPrincipal().inicia();
                	break;
                    
                default:
                    throw new IllegalArgumentException("Opcaio Invalida! Escolha uma opcao dentre das opcoes na lista.");
            }
        }
            catch(Exception InvallidArgumentException){
                this.inicia();
        }
    }
}
