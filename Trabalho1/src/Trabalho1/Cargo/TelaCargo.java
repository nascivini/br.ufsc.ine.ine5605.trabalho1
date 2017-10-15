package Trabalho1.Cargo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
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

    public TelaCargo(ControladorCargo controladorCargo) {
        this.controladorCargo = controladorCargo;
        teclado = new Scanner(System.in);
    }

    public ControladorCargo getControladorCargo() {
        return controladorCargo;
    }

    public void inicia() throws IllegalArgumentException, InputMismatchException {

        System.out.println("--- Menu de Cadastro de Cargos: ---");
        System.out.println("Escolha a opção desejada, insira o número e tecle enter: ---");
        System.out.println("1 - Cadastrar um Cargo");
        System.out.println("2 - Excluir um Cargo");
        System.out.println("3 - Alterar os dados de um Cargo");
        System.out.println("4 - Listar os cargos já cadastrados");
        System.out.println("5 - Voltar ao Menu Principal");
        try {
        int opcao = teclado.nextInt();
        teclado.nextLine();
            switch (opcao) {
                case (1):
                    this.cadastroCargos();
                    break;
                case (2):
                    this.exclusaoCargos();
                    break;
                case (3):
                    this.alteracaoCargos();
                    break;
                case (4):
                    this.listarCargos();
                    break;
                case (5):
                    controladorCargo.getControladorPrincipal().getTelaPrincipal().inicia();
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        } 
        
        catch (IllegalArgumentException | InputMismatchException e) {
            System.out.println("Opção Inválida! Escolha uma opção dentre das opções na lista.");
            this.controladorCargo.getTelaCargo().inicia();
        }
    }

    public void cadastroCargos() {
        System.out.println("Cadastro de Cargos");
        System.out.println("Insira os dados requisitados. Após a inserção de todos os dados, seu cargo será cadastrado no sistema.");

        System.out.println("Codigo: ");
        int codigo = teclado.nextInt();

        teclado.nextLine();

        System.out.println("Nome: ");
        String nome = teclado.nextLine();
        teclado.nextLine();
        
        System.out.println("Escolha o tipo de cargo dentre os listados abaixo. Digite o respectivo número e tecle enter para selecionar. ");
        System.out.println("1 - Gerencial");
        System.out.println("2 - Comum");
        System.out.println("3 - Convidado");
        
        int opcaoCargo = teclado.nextInt();
        
        boolean ehGerencial = false;
        String tipoCargo = "";
        boolean tipo = false;
        try{
            switch (opcaoCargo) {
                case (1):
                    tipo = true;
                    tipoCargo = "GERENCIAL";
                    ehGerencial = true;
                    break;
                case (2):
                    tipoCargo = "COMUM";
                    tipo = true;
                    break;
                case (3):
                    tipo = false;
                    tipoCargo = "CONVIDADO";
                    break;
                default:
                    throw new IllegalArgumentException("Cargo não cadastrado. Você deverá digitar opções válidas.");
            }
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            this.inicia();
        }
        
        ArrayList<Calendar> horarios = new ArrayList<>();
        
        if (tipoCargo.equals("GERENCIAL")) {
            try{
                DadosCargo cargoNovo = new DadosCargo(codigo, nome, tipo, ehGerencial, horarios, tipoCargo);
                this.controladorCargo.incluirCargo(cargoNovo);
                System.out.println("Cargo cadastrado com sucesso!");
            }
            
            catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
            
            finally{
                this.inicia();
            }
        } 
        
        else {
            SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
            boolean continuaCadastro = true;
            
            Calendar horario1 = Calendar.getInstance();
            Calendar horario2 = Calendar.getInstance();
            
            while (continuaCadastro) {
                
                if(horarios.isEmpty()){
                    System.out.println("Digite o horário inicial em que o acesso é permitido.(com separador de :)");
                    teclado.nextLine();
                    String horarioInicial = teclado.nextLine();
                
                    if (horarioInicial != null) {
                        try {
                            horario1.setTime(formatador.parse(horarioInicial));
                        } 
                        catch (ParseException e) {
                            System.out.println("Digite um horário válido! Entre 00:00 e 23:59");
                            this.cadastroCargos();
                        }
                    }
                
                    else {
                        throw new IllegalArgumentException("Digite um horário válido! Entre 00:00 e 23:59");
                    }
                    
                    System.out.println("Digite o horário final em que o acesso é permitido.(com separador de :)");
                    String horarioFinal = teclado.nextLine();
                    if (horarioFinal != null) {
                        try {
                            horario2.setTime(formatador.parse(horarioFinal));
                            if(horario2.getTime().after(horario1.getTime())){
                                System.out.println("Horários Cadastrados com Sucesso!");
                            }
                            else{
                                throw new IllegalArgumentException("Horário Final é menor que o horário inicial. Faça o cadastro novamente.");
                            }
                        } 
                        catch (ParseException e) {
                            System.out.println("Digite um horário válido! Entre 00:00 e 23:59");
                            this.cadastroCargos();
                        }
                        catch(IllegalArgumentException e){
                            System.out.println(e.getMessage());
                            this.cadastroCargos();
                        }
                        System.out.println("Deseja cadastrar mais horários de acesso? Digite Y caso sim, ou digite qualquer caractere para finalizar o cadastro do cargo.");
                        String continuar = teclado.nextLine();

                        if (continuar.equals("Y") || continuar.equals("y")) {
                            continuaCadastro = true;
                        } 
                        else {
                            continuaCadastro = false;
                        }
                        teclado.nextLine();
                        horarios.add(horario1);
                        horarios.add(horario2);
                    }
                } 
                    
                
                else{
                    for(int i = 1; i < horarios.size(); i = i + 2){
                        System.out.println("Digite o horário inicial em que o acesso é permitido.(com separador de :)");
                        String horarioInicial = teclado.nextLine();
                        teclado.nextLine();
                        try{
                            if (horarioInicial != null && horarios.get(i).after(horario1)) {
                                try {
                                    horario1.setTime(formatador.parse(horarioInicial));
                                } 
                                catch (ParseException e) {
                                    System.out.println("Digite um horário válido! Entre 00:00 e 23:59");
                                }
                    
                                finally{
                                    this.cadastroCargos();
                                }   
                            }
                        
                            else{
                                throw new IllegalArgumentException ("Horário inicial está dentro de uma faixa de horários já cadastrada. Verifique o mesmo e tente novamente.");   
                            }
                        }
                        
                        catch(IllegalArgumentException e){
                            System.out.println(e.getMessage());
                            this.cadastroCargos();
                        }
                    }
                
                    for(int i = 0; i < horarios.size(); i = i + 2){
                        System.out.println("Digite o horário final em que o acesso é permitido.(com separador de :)");
                        teclado.nextLine();
                        String horarioInicial = teclado.nextLine();
                
                        try{
                            if (horarioInicial != null && horarios.get(i).after(horario1)) {
                                try {
                                    horario1.setTime(formatador.parse(horarioInicial));
                                } 
                                catch (ParseException e) {
                                    System.out.println("Digite um horário válido! Entre 00:00 e 23:59");
                                }
                    
                                finally{
                                    this.cadastroCargos();
                                }   
                            }
                        
                            else{
                                throw new IllegalArgumentException ("Horário final está dentro de uma faixa de horários já cadastrada. Verifique o mesmo e tente novamente.");   
                            }
                        }
                        
                        catch(IllegalArgumentException e){
                            System.out.println(e.getMessage());
                            this.cadastroCargos();
                        }

                        System.out.println("Deseja cadastrar mais horários de acesso? Digite Y caso sim, ou digite qualquer caractere para finalizar o cadastro do cargo.");
                        String continuar = teclado.nextLine();

                        if (continuar.equals("Y") || continuar.equals("y")) {
                            continuaCadastro = true;
                        } 
                        else {
                            continuaCadastro = false;
                        }
                        teclado.nextLine();
                        horarios.add(horario1);
                        horarios.add(horario2);
                    }
                
                }
            }
                try{
                    DadosCargo cargoNovo = new DadosCargo(codigo, nome, tipo, ehGerencial, horarios, tipoCargo);
                    this.controladorCargo.incluirCargo(cargoNovo);
                    System.out.println("Cargo cadastrado com sucesso!");
                }
                catch(IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
                finally{
                    this.inicia();
                }
            
        
        }
    }

    private void exclusaoCargos(){
        System.out.println("Para excluir um cargo do sistema, digite o código do mesmo.");
        int codigo = teclado.nextInt();

        if ((this.controladorCargo.excluirCargo(codigo)) == true) {
            System.out.println("Cargo excluído com sucesso!");
            this.inicia();
        } else {
            System.out.println("O código informado não pertence a nenhum cargo cadastrado.");
            System.out.println("Deseja tentar novamente? Digite Y ou N");
            String opcaoExclusao = teclado.nextLine();
            if (opcaoExclusao.equals("Y") || opcaoExclusao.equals("y")) {
                this.exclusaoCargos();
            }
            else{
                this.inicia();
            }

        }
    }

    private void alteracaoCargos() {
        System.out.println("Bem vindo à tela de alteração dos Cargos.");
        System.out.println("Só é possível alterar um dado por vez. Digite o código do cargo a ser alterado, e selecione qual dado deseja alterar. Horários não podem ser alterados, caso deseje realizar alterações, exclua o cargo e realize o cadastro novamente.");
        
        int codigo = teclado.nextInt();
        if(this.getControladorCargo().findCargoByCodigo(codigo) == null){
            System.out.println("Cargo não encontrado. Digite um código válido.");
            this.alteracaoCargos();
        }
        else{
            System.out.println("---------------------------------------------------------");
            System.out.println("1 - Alterar nome do cargo.");
            System.out.println("2 - Alterar tipo do cargo.");
            System.out.println("3 - Alterar nome do cargo");
            System.out.println("4 - Alterar permissão de acesso do cargo");
            System.out.println("4 - Alterar permissão de acesso do cargo");
            
            int opcao = teclado.nextInt();
            switch(opcao){
                
                case(1):
                    System.out.println("Digite o novo nome para o cargo. Não é possível cadastrar dois cargos com o mesmo nome no sistema.");
                    String novoNome = teclado.nextLine();
                    if(this.getControladorCargo().findCargoByNome(novoNome) != null){
                       DadosCargo novosDados = new DadosCargo();
                       novosDados.nome = novoNome;
                       this.controladorCargo.alterarCargo(novosDados);
                       this.inicia();
                       break;
                    }
                    else{
                        throw new IllegalArgumentException("Nome inválido! Já existe um cargo cadastrado com este nome no sistema. Verifique o nome e tente novamente.");
                    }
                case(2):
                    System.out.println("Selecione um dos tipos de cargo abaixo.");
                    System.out.println("1 - Gerencial");
                    System.out.println("2 - Comum");
                    System.out.println("3 - Convidado");                    
            }
        }
        }
    private void listarCargos() {
        this.controladorCargo.listarCargos();
        this.inicia();
    }
}
