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
        return controladorAcesso;
    }
    
    public void inicia(){
    
    }
}
