package Trabalho1.Cargo;

import Trabalho1.Principal.ControladorPrincipal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public class ControladorCargo implements IControladorCargo {

    private final ControladorPrincipal controladorPrincipal;
    private final ArrayList<Cargo> cargos;
    private final TelaCargo telaCargo;
    private int sequencialCargo;

    public ControladorCargo(ControladorPrincipal controladorPrincipal) {
        this.cargos = new ArrayList<>();
        this.telaCargo = new TelaCargo(this);
        this.controladorPrincipal = controladorPrincipal;
        this.sequencialCargo = 0;
    }

    public ArrayList<Cargo> getCargos() {
        return cargos;
    }

    public TelaCargo getTelaCargo() {
        return telaCargo;
    }

    public ControladorPrincipal getControladorPrincipal() {
        return this.controladorPrincipal;
    }

    @Override
    public Cargo incluirCargo(DadosCargo conteudo, int codigo) {
        Cargo novo = new Cargo(conteudo, codigo);
        cargos.add(novo);
        return novo;
    }

    @Override
    public boolean excluirCargo(int codigo) {
        if (this.findCargoByCodigo(codigo) != null) {
            for (int i = 0; i < cargos.size(); i++) {
                if (cargos.get(i).getCodigo() == codigo) {
                    cargos.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Cargo alterarCargo(DadosCargo conteudo, int codigo) {
        Cargo alterado = findCargoByCodigo(codigo);
        if (conteudo != null) {
            if (conteudo.horarios == null) {
                alterado.setHorarios(new ArrayList<Calendar>());
            }
            if (conteudo.tipoCargo != null) {
                alterado.setTipoCargo(conteudo.tipoCargo);
                return alterado;
            } else if (conteudo.nome != null) {
                alterado.setNome(conteudo.nome);
                return alterado;
            } else if (conteudo.permiteAcesso == false) {
                alterado.setPermiteAcesso(false);
                return alterado;
            } else if (conteudo.permiteAcesso) {
                alterado.setPermiteAcesso(true);
                return alterado;
            } else {
                throw new IllegalArgumentException("Dado inválido! O cargo não foi alterado.");
            }
        }

        return null;
    }

    @Override
    public Cargo findCargoByCodigo(int codigo) throws IllegalArgumentException {
        if (codigo > 0) {
            for (Cargo cargoLista : this.cargos) {
                if (codigo == cargoLista.getCodigo()) {
                    return cargoLista;
                }
            }
            throw new IllegalArgumentException("Código Inválido!");
        }
        return null;
    }

    @Override
    public boolean findCargoByNome(String nome) {
        for (Cargo cargoAtual : cargos) {
            if (cargoAtual.getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gera um número sequencial para uso da TelaCargo no cadastro de um novo
     * cargo, permitindo a consistência dos códigos de cargos novos a serem
     * cadastrados no sistema.
     *
     * @return Sequencial gerado.
     */
    public int geraSequencialCargo() {
        this.sequencialCargo++;
        return this.sequencialCargo;
    }

    /**
     * É usado pela TelaCargo para listar os cargos já cadastrados.
     */
    //ficamos em dúvida entre colocar este método na tela ou no controlador
    public void listarCargos() {
        DateFormat formatador = new SimpleDateFormat("HH:mm");
        for (Cargo cargoLista : cargos) {
            System.out.println("Nome: " + cargoLista.getNome() + " | Código: " + cargoLista.getCodigo() + "| " + cargoLista.getTipoCargo().getDescricao());
            System.out.println("Horários deste cargo :");
            if (!cargoLista.getHorarios().isEmpty()) {
                for (int i = 0; i < cargoLista.getHorarios().size(); i = i + 2) {
                    Date horario1 = cargoLista.getHorarios().get(i).getTime();
                    Date horario2 = cargoLista.getHorarios().get(i + 1).getTime();
                    System.out.println("De " + formatador.format(horario1) + " à " + formatador.format(horario2) + ";");
                }
                System.out.println();
            }
        }
    }

    /**
     * Reduz o sequencial de cargos em uma unidade.
     */
    public void reduzSequencialCargo() {
        this.sequencialCargo = sequencialCargo - 1;
    }

    public boolean verificaHorarios(ArrayList<Calendar> horarios, Calendar horario1, Calendar horario2) {
        if (horarios.isEmpty()) {
            if (horario1.getTime() != horario2.getTime()) {
                return true;
            }
        } else {
            boolean horario1OK = false;
            boolean horario2OK = false;

            for (int i = 0; i < horarios.size(); i = i + 2) {
                if (horarios.get(i).after(horarios.get(i + 1))) {
                    if (horario1.before(horarios.get(i)) && horario1.after(horarios.get(i + 1))) {
                        horario1OK = true;
                    } else {
                        horario1OK = false;
                        throw new IllegalArgumentException("Horário inicial está dentro de uma faixa de horários já cadastrada ou é nulo. Verifique o mesmo e tente novamente. O cargo não foi cadastrado.");
                    }
                } else {
                    if (!(horario1.after(horarios.get(i)) && horario1.before(horarios.get(i + 1)))) {
                        horario1OK = true;
                    } else {
                        horario1OK = false;
                        throw new IllegalArgumentException("Horário inicial está dentro de uma faixa de horários já cadastrada ou é nulo. Verifique o mesmo e tente novamente. O cargo não foi cadastrado.");
                    }
                }
            }
            for (int i = 0; i < horarios.size(); i = i + 2) {
                if (horarios.get(i).after(horarios.get(i + 1))) {
                    if ((horario2.after(horario1)) && (horario2.before(horarios.get(i)))) {
                        horario2OK = true;
                    } else {
                        horario2OK = false;
                        throw new IllegalArgumentException("Horário final está dentro de uma faixa de horários já cadastrada ou é nulo. Verifique o mesmo e tente novamente. O cargo não foi cadastrado.");
                    }
                } else {
                    Calendar maior = horarios.get(0);
                    for (Calendar horario : horarios) {
                        if (horario.after(maior)) {
                            maior = horario;
                        }
                    }

                    Calendar menor = horarios.get(0);
                    for (Calendar horario : horarios) {
                        if (horario.before(menor)) {
                            menor = horario;
                        }
                    }

                    boolean validador = false;
                    if (horario1.equals(maior) && horario2.equals(menor)) {
                        validador = true;
                    }
                    if ((!(horario2.after(horarios.get(i)) && horario2.before(horarios.get(i + 1)))) || validador) {
                        horario2OK = true;
                    } else {
                        horario2OK = false;
                        throw new IllegalArgumentException("Horário final está dentro de uma faixa de horários já cadastrada ou é nulo. Verifique o mesmo e tente novamente. O cargo não foi cadastrado.");
                    }
                }
            }
            if (horario1OK && (!horario2OK)) {
                return false;
            } else if ((!horario1OK) && horario2OK) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
