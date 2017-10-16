/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trabalho1.Principal;
/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public class ClassePrincipal {
    
    ControladorPrincipal ctrl;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ControladorPrincipal ctrl = new ControladorPrincipal();
        ctrl.getTelaPrincipal().inicia();
        
        if(args[0].equals("1")){
            ctrl.getTelaPrincipal().inicia();
        }
    
    }
}
