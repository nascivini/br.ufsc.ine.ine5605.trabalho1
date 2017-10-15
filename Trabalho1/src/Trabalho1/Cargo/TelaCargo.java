package Trabalho1.Cargo;

import Trabalho1.Principal.ClassePrincipal;
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
    
    /**
     * Inicia a tela com o Menu das opções para o módulo Cargo. Pode jogar exceções do tipo IllegalArgumentException e InputMismatchException.
     * @throws IllegalArgumentException Caso seja digitada uma opção inválida.
     * @throws InputMismatchException Caso seja digitado um caractere inválido.
     */
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
                    this.getControladorCargo().getControladorPrincipal().getTelaPrincipal().inicia();
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        } 
        
        catch (IllegalArgumentException | InputMismatchException e) {
            System.out.println("Opção Inválida! Escolha uma opção dentre das opções na lista.");
            String[] args = null;
            ClassePrincipal.main(args);
        }
    }
    
    /**
     * Inicia o módulo de cadastro de Cargos no sistema. Faz o tratamento dos dados inseridos e efetiva o cadastro de um cargo, se utilizando do controladorCargo(atributo).
     * Utiliza os métodos incluirCargo, geraSequencialCargo, reduzSequencialCargo e findCargoByNome do controladorCargo.
     */
    private void cadastroCargos(){
        System.out.println("Cadastro de Cargos");
        System.out.println("Insira os dados requisitados. Após a inserção de todos os dados, seu cargo será cadastrado no sistema.");
        
        int codigo = this.getControladorCargo().geraSequencialCargo();
        System.out.println("Còdigo do novo cargo (gerado automaticamente) : " + codigo + ".");

        System.out.println("Nome: ");
        String nome = teclado.nextLine();
        
        try{
            if(this.getControladorCargo().findCargoByNome(nome)){
                this.getControladorCargo().reduzSequencialCargo();
                throw new IllegalArgumentException("Já existe um cargo com este nome no sistema. O cargo não foi cadastrado.");
            }
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            this.inicia();
        }   
        
        System.out.println("Escolha o tipo de cargo dentre os listados abaixo. Digite o respectivo número e tecle enter para selecionar. ");
        System.out.println("1 - Gerencial");
        System.out.println("2 - Comum");
        System.out.println("3 - Convidado");

        int opcaoCargo = teclado.nextInt();

        boolean ehGerencial = false;
        String tipoCargo = "";
        boolean tipo = false;
        try {
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
                    throw new IllegalArgumentException("Cargo não cadastrado. Você deve digitar opções válidas. Selecione um tipo dentre os tipos listados.");
            }
        } 
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            this.inicia();
        }

        ArrayList<Calendar> horarios = new ArrayList<>();

        switch (tipoCargo) {
            case "GERENCIAL":
                {
                    DadosCargo cargoNovo = new DadosCargo(nome, tipo, ehGerencial, horarios, tipoCargo);
                    this.getControladorCargo().incluirCargo(cargoNovo, codigo);
                    System.out.println("Cargo cadastrado com sucesso!");
                    break;
                }
            case "CONVIDADO":
                {
                    DadosCargo cargoNovo = new DadosCargo(nome, tipo, false, horarios, tipoCargo);
                    this.getControladorCargo().incluirCargo(cargoNovo, codigo);
                    System.out.println("Cargo cadastrado com sucesso!");
                    break;
                }
            default:
                SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
                boolean continuaCadastro = true;
                while (continuaCadastro) {
                    
                    if (horarios.isEmpty()) {
                        
                        teclado.nextLine();

                        System.out.println("Digite o horário inicial em que o acesso é permitido.(com separador de :)");
                        String horarioInicial = teclado.nextLine();
                        
                        
                        Calendar horario1 = Calendar.getInstance();
                        Calendar horario2 = Calendar.getInstance();
                        try{
                        if (horarioInicial != null) {
                            try {
                                horario1.setTime(formatador.parse(horarioInicial));
                            } catch (ParseException e) {
                                System.out.println("Digite um horário válido! Entre 00:00 e 23:59. O cargo não foi cadastrado.");
                                this.getControladorCargo().reduzSequencialCargo();
                                this.cadastroCargos();
                            }
                        } else {
                            this.getControladorCargo().reduzSequencialCargo();
                            throw new IllegalArgumentException("Digite um horário válido! Entre 00:00 e 23:59. O cargo não foi cadastrado.");
                        }
                        }
                        catch(IllegalArgumentException e){
                            System.out.println(e.getMessage());
                            this.inicia();
                        }
                        System.out.println("Digite o horário final em que o acesso é permitido.(com separador de :)");
                        String horarioFinal = teclado.nextLine();
                        if (horarioFinal != null) {
                            try {
                                horario2.setTime(formatador.parse(horarioFinal));
                                if (horario2.getTime().after(horario1.getTime())) {
                                    System.out.println("Horários Cadastrados com Sucesso!");
                                } else {
                                    throw new IllegalArgumentException("Horário Final é menor que o horário inicial. Faça o cadastro novamente.");
                                }
                            } catch (ParseException e) {
                                System.out.println("Digite um horário válido! Entre 00:00 e 23:59. Cargo não cadastrado.");
                                this.getControladorCargo().reduzSequencialCargo();
                                this.cadastroCargos();
                            } catch (IllegalArgumentException e) {
                                this.getControladorCargo().reduzSequencialCargo();
                                System.out.println(e.getMessage());
                                this.cadastroCargos();
                            }
                            
                            System.out.println("Deseja cadastrar mais horários de acesso? Digite Y caso sim, ou digite qualquer caractere para finalizar o cadastro do cargo.");
                            String continuar = teclado.nextLine();
                            
                            if (continuar.equals("Y") || continuar.equals("y")) {
                                continuaCadastro = true;
                            } else {
                                continuaCadastro = false;
                            }
                            teclado.nextLine();
                            horarios.add(horario1);
                            horarios.add(horario2);
                        }
                    }
                    
                    else {
                        Calendar horario3 = Calendar.getInstance();
                        Calendar horario4 = Calendar.getInstance();
                        boolean horario1OK = false;
                        boolean horario2OK = false;
                        
                        System.out.println("Digite o horário inicial em que o acesso é permitido.(Hora)");
                        int horaInicial = teclado.nextInt();
                        System.out.println("Digite os minutos do horário inicial.");
                        int minInicial = teclado.nextInt();
                        teclado.nextLine();
                        for (int i = 1; i < horarios.size(); i = i + 2) {
                            try {
                                if (horaInicial >= 0 && horaInicial <= 23 && minInicial >=0 && minInicial <=59) {
                                        horario3.set(0,0,0,horaInicial, minInicial);   
                                        if ((horario3.getTime().before(horarios.get(i).getTime()))) {
                                            horario1OK = true;
                                        }
                                        else {
                                            horario1OK = false;
                                            throw new IllegalArgumentException("Horário inicial está dentro de uma faixa de horários já cadastrada ou é nulo. Verifique o mesmo e tente novamente. O cargo não foi cadastrado.");
                                        }
                                }
                                else {
                                    throw new IllegalArgumentException("Hora ou minuto são inválidos e não estão no formato válido de hora. Verifique os mesmos e tente novamente. O cargo não foi cadastrado.");
                                }
                            } 
                            catch (IllegalArgumentException e) {
                                this.getControladorCargo().reduzSequencialCargo();
                                System.out.println(e.getMessage());
                                this.cadastroCargos();
                            }
                        }
                        
                        System.out.println("Digite o horário final em que o acesso é permitido.(Hora)");
                        int horaFinal = teclado.nextInt();
                        System.out.println("Digite os minutos do horário final.");
                        int minFinal = teclado.nextInt();
                        teclado.nextLine();
                        for (int i = 0; i < horarios.size(); i = i + 2) {
                            try{
                                if (horaFinal >= 0 && horaFinal <= 23 && minFinal >= 0 && minFinal <= 59) {
                                        horario4.set(0,0,0,horaFinal,minFinal);
                                        if (((horario4.getTime().before(horarios.get(i).getTime())))&& horario4.getTime().after(horario3.getTime())) {
                                            horario2OK = true;
                                        }
                                        else{
                                            horario2OK = false;
                                            throw new IllegalArgumentException("Horário final está dentro de uma faixa de horários já cadastrada ou é nulo. Verifique o mesmo e tente novamente. O cargo não foi cadastrado.");
                                        }
                                    }

                                    else {
                                        throw new IllegalArgumentException("Hora ou minuto são inválidos e não estão no formato válido de hora. Verifique os mesmos e tente novamente. O cargo não foi cadastrado.");
                                }
                            }
                            catch (IllegalArgumentException e) {
                                this.getControladorCargo().reduzSequencialCargo();
                                System.out.println(e.getMessage());
                                this.cadastroCargos();
                            }
                        }
                        
                        System.out.println("Deseja cadastrar mais horários de acesso? Digite Y caso sim, ou digite qualquer caractere para finalizar o cadastro do cargo.");
                        String continuar = teclado.nextLine();
                        
                        if (continuar.equals("Y") || continuar.equals("y")) {
                            continuaCadastro = true;
                        } else {
                            continuaCadastro = false;
                        }
                        
                        if(horario1OK && horario2OK){
                            horarios.add(horario3);
                            horarios.add(horario4);
                        }
                    }
                }
            DadosCargo cargoNovo = new DadosCargo(nome, tipo, ehGerencial, horarios, tipoCargo);
            this.getControladorCargo().incluirCargo(cargoNovo, codigo);
            System.out.println("Cargo cadastrado com sucesso!");
            break;
        }
        this.inicia();
    }
    
    /**
     * Inicia a tela de exclusão de cargos, faz o tratamento dos dados inseridos pelo usuário e, 
     * antes da exclusão, verifica a existência do cargo utilizando se do método findCargoByCodigo, do controladorCargo.
     * Utiliza o método excluirCargo, também do controladorCargo.
     */
    private void exclusaoCargos() {
        System.out.println("Para excluir um cargo do sistema, digite o código do mesmo.");
        int codigo = teclado.nextInt();

        if (this.getControladorCargo().findCargoByCodigo(codigo)) {
            this.getControladorCargo().excluirCargo(codigo);
            System.out.println("Cargo excluído com sucesso!");
            this.inicia();
        } 
        
        else {
            System.out.println("O código informado não pertence a nenhum cargo cadastrado.");
            System.out.println("Deseja tentar novamente? Digite Y ou N");
            String opcaoExclusao = teclado.nextLine();
            if (opcaoExclusao.equals("Y") || opcaoExclusao.equals("y")) {
                this.exclusaoCargos();
            } 
            else {
                this.inicia();
            }
        }
    }
    
    /**
     * Inicia a tela de alteração de cargos. Permite apenas a alteração de um dado do cargo por vez.
     * Utiliza se dos métodos findCargoByCodigo, findcargoByNome e alterarCargo do controladorCargo.
     */
    private void alteracaoCargos() {
        System.out.println("Bem vindo à tela de alteração dos Cargos.");
        System.out.println("Só é possível alterar um dado por vez. Digite o código do cargo a ser alterado, e selecione qual dado deseja alterar. Horários não podem ser alterados, caso deseje realizar alterações, exclua o cargo e realize o cadastro novamente.");

        int codigo = teclado.nextInt();
        if (!this.getControladorCargo().findCargoByCodigo(codigo)) {
            System.out.println("Cargo não encontrado. Digite um código válido.");
            this.alteracaoCargos();
        } else {
            System.out.println("---------------------------------------------------------");
            System.out.println("1 - Alterar nome do cargo.");
            System.out.println("2 - Alterar tipo do cargo.");
            System.out.println("4 - Alterar permissão de acesso do cargo");

            int opcao = teclado.nextInt();
            switch (opcao) {

                case (1):
                    System.out.println("Digite o novo nome para o cargo. Não é possível cadastrar dois cargos com o mesmo nome no sistema.");
                    String novoNome = teclado.nextLine();
                    if (!this.getControladorCargo().findCargoByNome(novoNome)) {
                        DadosCargo novosDados = new DadosCargo();
                        novosDados.nome = novoNome;
                        this.getControladorCargo().alterarCargo(novosDados, codigo);
                        this.inicia();
                        break;
                    } 
                    else {
                        throw new IllegalArgumentException("Nome inválido! Já existe um cargo cadastrado com este nome no sistema. Verifique o nome e tente novamente.");
                    }
                case (2):
                    System.out.println("Selecione um dos tipos de cargo abaixo.");
                    System.out.println("1 - Gerencial");
                    System.out.println("2 - Comum");
                    System.out.println("3 - Convidado");
            }
        }
    }
    
    /**
     * Chama o método listarCargos do controladorCargo para listar os cargos já cadastrados no sistema.
     */
    private void listarCargos() {
        this.getControladorCargo().listarCargos();
        this.inicia();
    }
}
