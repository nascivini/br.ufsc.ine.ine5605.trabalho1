/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trabalho1.Cargo;

import Trabalho1.Cargo.ControladorCargo;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public class TelaCargo {
    private ControladorCargo controladorCargo;
    private Scanner teclado;
    
    public TelaCargo(ControladorCargo controladorCargo){
        this.controladorCargo = controladorCargo;
        teclado = new Scanner(System.in);
    }

    public ControladorCargo getControladorCargo() {
        return controladorCargo;
    }
    
    public void inicia(){
        
        System.out.println("--- Menu de Cadastro de Cargos: ---");
        System.out.println("Escolha a opção desejada, insira o número e tecle enter: ---");
        System.out.println("1 - Cadastrar um Cargo");
        System.out.println("2 - Excluir um Cargo");
        System.out.println("3 - Alterar os dados de um Cargo");
        System.out.println("4 - Voltar ao Menu Principal");
        
        int opcao = teclado.nextInt();
            
            if(opcao == 1){ 
                this.cadastroCargos();
            }    
            if(opcao == 2){
                this.exclusaoCargos();
            }
            if(opcao == 3){
                this.alterarCargos();
            }
            if(opcao == 4){
                controladorCargo.getControladorPrincipal().getTelaPrincipal().inicia();
            }
  
    }
    
    public void cadastroCargos(){
        System.out.println("Cadastro de Cargos");
                System.out.println("Insira os dados requisitados. Após a inserção de todos os dados, seu cargo será cadastrado no sistema.");
                
                System.out.println("Codigo: ");
                int codigo = teclado.nextInt();
                
                System.out.println("Nome: ");
                String nome = teclado.nextLine();
                
                System.out.println("É gerencial? Digite 'Y' caso sim, e 'N' caso não.");
                String gerencial = teclado.nextLine();
                if(gerencial.equals("Y") || gerencial.equals("y")){
                    System.out.println("Digite o horário em que o acesso é permitido.");
                    System.out.println("Atenção!!  O formato deve ser HH:MM");
                    
                    Date horario = new Date("HH:MM");
                    // Não lembrei a conversão de hora! 
                    SimpleDateFormat hora = new SimpleDateFormat(teclado.nextLine());
                }
                this.inicia();

        }

    private void exclusaoCargos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void alterarCargos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

