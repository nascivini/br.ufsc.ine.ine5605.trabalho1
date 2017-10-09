package Trabalho1.Funcionario;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */

import Trabalho1.Principal.ControladorPrincipal;
import java.util.ArrayList;

public class ControladorFuncionario{
    private ArrayList<Funcionario> funcionarios;
    private ControladorPrincipal controladorPrincipal;
    private TelaFuncionario telaFuncionario;

    public ControladorFuncionario(ControladorPrincipal controladorPrincipal) {
        this.funcionarios = new ArrayList<Funcionario>();
        this.controladorPrincipal = controladorPrincipal;
        this.telaFuncionario = new TelaFuncionario(this);
    }   

    public TelaFuncionario getTelaFuncionario() {
        return telaFuncionario;
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }
    
    public void incluirFuncionario(DadosFuncionario conteudo){
        this.funcionarios.add(null);
    }
    
    public void excluirFuncionario(int matricula){
    
    }
    
    public void alterarFuncionario(DadosFuncionario conteudo){
    
    }
    
    public void listarFuncionarios(){
    
    }
}
