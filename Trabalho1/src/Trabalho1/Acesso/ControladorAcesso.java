package Trabalho1.Acesso;

import Trabalho1.Principal.ControladorPrincipal;
import java.util.ArrayList;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public class ControladorAcesso {
    private ArrayList<Acesso> acessos;
    private ControladorPrincipal controladorPrincipal;
    private TelaAcesso telaAcesso;
    
    public ControladorAcesso(ControladorPrincipal controladorPrincipal){
        this.controladorPrincipal = controladorPrincipal;
    }

    public ArrayList<Acesso> getAcessos() {
        return acessos;
    }

    public ControladorPrincipal getControladorPrincipal() {
        return controladorPrincipal;
    }

    public TelaAcesso getTelaAcesso() {
        return telaAcesso;
    }
    
    public void listarAcessosNegados(){
    
    }
    
    public void listarAcessos(){
    
    }
    
    public boolean validarAcesso(){
        return true;//temporario(até implementação)
    } 
}
