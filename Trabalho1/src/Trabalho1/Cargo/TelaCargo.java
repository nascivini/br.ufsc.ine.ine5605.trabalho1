/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trabalho1.Cargo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public class TelaCargo {
    private final ControladorCargo controladorCargo;
    private final Scanner teclado;
    
    public TelaCargo(ControladorCargo controladorCargo){
        this.controladorCargo = controladorCargo;
        teclado = new Scanner(System.in);
    }

    public ControladorCargo getControladorCargo() {
        return controladorCargo;
    }
    
    public void inicia() throws IllegalArgumentException{
        
        System.out.println("--- Menu de Cadastro de Cargos: ---");
        System.out.println("Escolha a opção desejada, insira o número e tecle enter: ---");
        System.out.println("1 - Cadastrar um Cargo");
        System.out.println("2 - Excluir um Cargo");
        System.out.println("3 - Alterar os dados de um Cargo");
        System.out.println("4 - Listar os cargos já cadastrados");
        System.out.println("5 - Voltar ao Menu Principal");
        
        int opcao = teclado.nextInt();
        try{
            switch (opcao){    
                case(1): 
                    this.cadastroCargos();
               
                case(2):
                    this.exclusaoCargos();
            
                case(3):
                    this.alterarCargos();
                
                case(4):
                    this.listarCargos(2);
                    
                case(5):
                    controladorCargo.getControladorPrincipal().getTelaPrincipal().inicia();
                    
                default:
                    throw new IllegalArgumentException("Opção Inválida! Escolha uma opção dentre das opções na lista.");
            }
        }
            catch(Exception InvallidArgumentException){
                this.inicia();
        }
    }
    public void cadastroCargos() throws ParseException{
        System.out.println("Cadastro de Cargos");
                System.out.println("Insira os dados requisitados. Após a inserção de todos os dados, seu cargo será cadastrado no sistema.");
                
                System.out.println("Codigo: ");
                int codigo = teclado.nextInt();
                
                System.out.println("Nome: ");
                String nome = teclado.nextLine();
                
                System.out.println("É gerencial? Digite 'Y' caso sim, e 'N' caso não.");
                String gerencial = teclado.nextLine();
                
                boolean ehGerencial = true;
                
                Date horarioInicio1 = new Date();
                try{
                    if(gerencial.equals("N") || gerencial.equals("n")){
                        ehGerencial = false;
                        System.out.println("Digite o horário inicial em que o acesso é permitido.(com separador de :)");
                        String horario = teclado.nextLine();
                    
                    if(horario.equals("09:00")){
                        SimpleDateFormat formatador = new SimpleDateFormat("hh:mm");
                        horarioInicio1 = formatador.parse(horario);
                        }
                    else{
                        throw new ParseException("Hora inválida! Digite um horário entre 00:00 e 23:59",1);
                    }
                    }
                  }
                catch(ParseException e){
                    this.inicia();
                }
                int opcaoCargo = 0;
                System.out.println("Escolha o tipo de cargo dentre os listados abaixo. Digite o respectivo número e tecle enter para selecionar. ");
                System.out.println("1 - Gerencial");
                System.out.println("2 - Comum");
                System.out.println("3 - Convidado");
                
                opcaoCargo = teclado.nextInt();
                //TipoCargo tipo = TipoCargo.COMUM;
                String tipoCargo =  "";
                boolean tipo = false;
                switch(opcaoCargo){
                    case (1):
                        //tipo = TipoCargo.GERENCIAL;
                        tipo = true;
                        tipoCargo = "GERENCIAL";
                    case (2):
                        //tipo = TipoCargo.COMUM;
                        tipoCargo = "COMUM";
                        tipo = true;
                    case (3):
                        //tipo = TipoCargo.CONVIDADO;
                        tipo = false;
                        tipoCargo = "CONVIDADO";
                }
                //DadosCargo cargoNovo = new DadosCargo (codigo,nome, tipo.getPermiteAcesso(), ehGerencial,horarioInicio1,tipo);
                DadosCargo cargoNovo = new DadosCargo (codigo, nome, tipo, ehGerencial, horarioInicio1, tipoCargo);
                this.controladorCargo.incluirCargo(cargoNovo);
                this.inicia();

        }

    private void exclusaoCargos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void alterarCargos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void listarCargos(int tamanhoLista){
        ArrayList<Cargo> cargos = controladorCargo.listarCargos(tamanhoLista);
        for (Cargo cargo : cargos){
            System.out.println("Cargos cadastrados: ");    
            System.out.println("Código: " +cargo.getCodigo() + "Nome: " + cargo.getNome());
        }
        this.inicia();
    }
}

