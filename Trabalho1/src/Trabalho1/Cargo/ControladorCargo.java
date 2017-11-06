package Trabalho1.Cargo;

import Trabalho1.Principal.ControladorPrincipal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public class ControladorCargo implements IControladorCargo {

    private final ControladorPrincipal controladorPrincipal;
    private CargoDAO cargoDAO;
    private final TelaCargo telaCargo;
    private int sequencialCargo;

    public ControladorCargo(ControladorPrincipal controladorPrincipal) {
        this.cargoDAO = new CargoDAO();
        this.telaCargo = new TelaCargo(this);
        this.controladorPrincipal = controladorPrincipal;
        this.sequencialCargo = 0;
    }

    public ArrayList<Cargo> getCargos() {
        return new ArrayList<Cargo>(cargoDAO.getList());
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
        cargoDAO.put(novo);
        return novo;
    }

    @Override
    public boolean excluirCargo(int codigo) {
        if(this.findCargoByCodigo(codigo) != null){
            this.cargoDAO.remove(this.cargoDAO.get(codigo));
            return true;
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
        Cargo cargo = cargoDAO.get(codigo);
        if (cargo != null) {
            return cargo;
        }
        else{
            throw new IllegalArgumentException("Código Inválido!");
        }
    }

    @Override
    public boolean findCargoByNome(String nome) {
        for (Cargo cargoAtual : this.cargoDAO.getList()) {
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

    @Override
    public void listarCargos() {
        DateFormat formatador = new SimpleDateFormat("HH:mm");
        for (Cargo cargoLista : this.cargoDAO.getList()) {
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

    @Override
    public void reduzSequencialCargo() {
        this.sequencialCargo = sequencialCargo - 1;
    }

    @Override
    public boolean verificaHorarios(ArrayList<Calendar> horarios, Calendar horario1, Calendar horario2) {

        if (horarios.isEmpty()) {
            if (horario1.compareTo(horario2) != 0) {
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
                        throw new IllegalArgumentException("HorÃ¡rio inicial estÃ¡ dentro de uma faixa de horÃ¡rios jÃ¡ cadastrada ou Ã© nulo. Verifique o mesmo e tente novamente. O cargo nÃ£o foi cadastrado.");
                    }
                } else {
                    if ((!(horario1.after(horarios.get(i)) && horario1.before(horarios.get(i + 1))))) {
                        horario1OK = true;
                    } else {
                        horario1OK = false;
                        throw new IllegalArgumentException("HorÃ¡rio inicial estÃ¡ dentro de uma faixa de horÃ¡rios jÃ¡ cadastrada ou Ã© nulo. Verifique o mesmo e tente novamente. O cargo nÃ£o foi cadastrado.");
                    }
                }
            }
            for (int i = 0; i < horarios.size(); i = i + 2) {
                if (horarios.get(i).after(horarios.get(i + 1))) {
                    if ((horario2.after(horario1)) && (horario2.before(horarios.get(i)))) {
                        horario2OK = true;
                    } else {
                        horario2OK = false;
                        throw new IllegalArgumentException("HorÃ¡rio inicial estÃ¡ dentro de uma faixa de horÃ¡rios jÃ¡ cadastrada ou Ã© nulo. Verifique o mesmo e tente novamente. O cargo nÃ£o foi cadastrado.");
                    }
                } else {
                    if (horario1.after(horario2) && horario1.after(horarios.get(i + 1)) && horario2.before(horarios.get(i))) {
                        horario2OK = true;
                    } else if (!(horario2.after(horarios.get(i)) && ((horario2.before(horarios.get(i + 1))))) && (horario2.before(horarios.get(i)) || (horario1.after(horarios.get(i + 1)) && horario2.after(horarios.get(i + 1))))) {
                        if (horario2.after(horarios.get(i))) {
                            horario2OK = false;
                        } else {
                            horario2OK = true;
                        }
                    } else {
                        horario2OK = false;
                        throw new IllegalArgumentException("HorÃ¡rio inicial estÃ¡ dentro de uma faixa de horÃ¡rios jÃ¡ cadastrada ou Ã© nulo. Verifique o mesmo e tente novamente. O cargo nÃ£o foi cadastrado.");
                    }
                }
            }
            if (horario1OK && horario2OK) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
