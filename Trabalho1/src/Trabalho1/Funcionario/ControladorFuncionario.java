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
    
    public Funcionario incluirFuncionario(DadosFuncionario conteudo){
        if (conteudo != null) {
            for (Funcionario funcionarioLista: funcionarios) {
                if (funcionarioLista.getNome().equals(conteudo.nome)) {
                    return null;
                } else {
                    Funcionario novo = new Funcionario(conteudo);
                    funcionarios.add(novo);
                    
                    
                }
            }
        }
    }
    
    public int matriculaSequencial() {
        //método para gerar matrícula sequencial
    }
    
    public void excluirFuncionario(int matricula){
        if (matricula != null) {
            for (Funcionario funcionarioLista: funcionarios) {
                if (funcionarioLista.getNome().equals(conteud))
            }
        }
    }
    
    public void alterarFuncionario(DadosFuncionario conteudo){
    
    }
    
    public void listarFuncionarios(){
    
    }
}
