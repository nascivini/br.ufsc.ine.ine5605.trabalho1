package Trabalho1.Principal;

import Trabalho1.Funcionario.ControladorFuncionario;
import Trabalho1.Cargo.ControladorCargo;
import Trabalho1.Acesso.ControladorAcesso;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public class ControladorPrincipal {
    private final TelaPrincipal telaPrincipal;
    private final ControladorFuncionario controladorFuncionario;
    private final ControladorCargo controladorCargo;
    private final ControladorAcesso controladorAcesso;
    
    
    public ControladorPrincipal() {
        this.telaPrincipal = new TelaPrincipal(this);
        this.controladorFuncionario = new ControladorFuncionario(this);
        this.controladorCargo = new ControladorCargo(this);
        this.controladorAcesso = new ControladorAcesso(this);
    }
    
    public TelaPrincipal getTelaPrincipal() {
        return telaPrincipal;
    }

    public ControladorFuncionario getControladorFuncionario() {
        return controladorFuncionario;
    }

    public ControladorCargo getControladorCargo() {
        return controladorCargo;
    }

    public ControladorAcesso getControladorAcesso() {
        return controladorAcesso;
    }    
    
}
