/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trabalho1.Cargo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

    public void inicia() throws IllegalArgumentException {

        System.out.println("--- Menu de Cadastro de Cargos: ---");
        System.out.println("Escolha a opção desejada, insira o número e tecle enter: ---");
        System.out.println("1 - Cadastrar um Cargo");
        System.out.println("2 - Excluir um Cargo");
        System.out.println("3 - Alterar os dados de um Cargo");
        System.out.println("4 - Listar os cargos já cadastrados");
        System.out.println("5 - Voltar ao Menu Principal");

        int opcao = teclado.nextInt();
        try {
            switch (opcao) {
                case (1):
                    this.cadastroCargos();

                case (2):
                    this.exclusaoCargos();

                case (3):
                    this.alterarCargos();

                case (4):
                    this.listarCargos();

                case (5):
                    controladorCargo.getControladorPrincipal().getTelaPrincipal().inicia();

                default:
                    throw new IllegalArgumentException("Opção Inválida! Escolha uma opção dentre das opções na lista.");
            }
        } catch (IllegalArgumentException e) {
            this.inicia();
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

        int opcaoCargo = 0;
        System.out.println("Escolha o tipo de cargo dentre os listados abaixo. Digite o respectivo número e tecle enter para selecionar. ");
        System.out.println("1 - Gerencial");
        System.out.println("2 - Comum");
        System.out.println("3 - Convidado");

        boolean ehGerencial = false;
        opcaoCargo = teclado.nextInt();
        String tipoCargo = "";
        boolean tipo = false;
        switch (opcaoCargo) {
            case (1):
                tipo = true;
                tipoCargo = "GERENCIAL";
                ehGerencial = true;
            case (2):
                tipoCargo = "COMUM";
                tipo = true;
            case (3):
                tipo = false;
                tipoCargo = "CONVIDADO";
        }
        ArrayList<Calendar> horarios = new ArrayList<>();
        DadosCargo cargoNovo = new DadosCargo();
        if (tipoCargo.equals("GERENCIAL")) {
            cargoNovo = new DadosCargo(codigo, nome, tipo, ehGerencial, horarios, tipoCargo);
        } 
        
        else {
            SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
            boolean continuaCadastro = true;
            while (continuaCadastro) {

                System.out.println("Digite o horário inicial em que o acesso é permitido.(com separador de :)");
                teclado.nextLine();
                String horarioInicial = teclado.nextLine();
                Calendar horario1 = Calendar.getInstance();
                if (horarioInicial != null) {
                    try {
                        horario1.setTime(formatador.parse(horarioInicial));
                    } catch (ParseException e) {
                        System.out.println("Digite um horário válido! Entre 00:00 e 23:59");
                        this.cadastroCargos();
                    }
                } else {
                    throw new IllegalArgumentException("Digite um horário válido! Entre 00:00 e 23:59");
                }

                System.out.println("Digite o horário final em que o acesso é permitido.(com separador de :)");
                String horarioFinal = teclado.nextLine();
                Calendar horario2 = Calendar.getInstance();
                if (horarioFinal != null) {
                    try {
                        horario2.setTime(formatador.parse(horarioFinal));
                    } catch (ParseException e) {
                        System.out.println("Digite um horário válido! Entre 00:00 e 23:59");
                        this.cadastroCargos();
                    }
                } else {
                    throw new IllegalArgumentException("Digite um horário válido! Entre 00:00 e 23:59");
                }

                System.out.println("Deseja cadastrar mais horários de acesso? Digite Y caso sim, e N caso não");
                String continuar = teclado.nextLine();

                if (continuar.equals("Y") || continuar.equals("y")) {
                    continuaCadastro = true;
                } else {
                    continuaCadastro = false;
                }
                horarios.add(horario1);
                horarios.add(horario2);
                cargoNovo = new DadosCargo(codigo, nome, tipo, ehGerencial, horarios, tipoCargo);
            }
            
            this.controladorCargo.incluirCargo(cargoNovo);
            this.inicia();

        }
    }

    

    /*private ArrayList<Calendar> cadastroHorarios() throws IllegalArgumentException {
        ArrayList<Calendar> horarios = new ArrayList<Calendar>();
        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
        boolean continuaCadastro = true;
        while (continuaCadastro) {

            System.out.println("Digite o horário inicial em que o acesso é permitido.(com separador de :)");
            teclado.nextLine();
            String horarioInicial = teclado.nextLine();
            Calendar horario1 = Calendar.getInstance();
            if (horarioInicial != null) {
                try {
                    horario1.setTime(formatador.parse(horarioInicial));
                } catch (ParseException e) {
                    System.out.println("Digite um horário válido! Entre 00:00 e 23:59");
                    this.cadastroCargos();
                }
            } else {
                throw new IllegalArgumentException("Digite um horário válido! Entre 00:00 e 23:59");
            }

            System.out.println("Digite o horário final em que o acesso é permitido.(com separador de :)");
            String horarioFinal = teclado.nextLine();
            Calendar horario2 = Calendar.getInstance();
            if (horarioFinal != null) {
                try {
                    horario2.setTime(formatador.parse(horarioFinal));
                } catch (ParseException e) {
                    System.out.println("Digite um horário válido! Entre 00:00 e 23:59");
                    this.cadastroCargos();
                }
            } else {
                throw new IllegalArgumentException("Digite um horário válido! Entre 00:00 e 23:59");
            }

            System.out.println("Deseja cadastrar mais horários de acesso? Digite Y caso sim, e N caso não");
            String continuar = teclado.nextLine();

            if (continuar.equals("Y") || continuar.equals("y")) {
                continuaCadastro = true;
            } else {
                continuaCadastro = false;
            }
            horarios.add(horario1);
            horarios.add(horario2);

        }
        return horarios;
    }
    */
    private void exclusaoCargos() throws IllegalArgumentException {
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

        }
    }

    private void alterarCargos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void listarCargos() {
        for (Cargo cargo : this.controladorCargo.getCargos()) {
            System.out.println("Cargos cadastrados: ");
            System.out.println("Código: " + cargo.getCodigo() + "Nome: " + cargo.getNome());
        }
        //this.inicia();
    }
}
