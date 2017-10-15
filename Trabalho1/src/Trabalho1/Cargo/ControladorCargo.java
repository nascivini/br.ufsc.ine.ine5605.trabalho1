package Trabalho1.Cargo;

import Trabalho1.Principal.ControladorPrincipal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Vinicius Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public class ControladorCargo {
    
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
    /**
     * Recebe os dados do cargo a ser incluído via parâmetro, inclui o cargo na lista de cargos, e retorna o cargo incluído.
     * @param conteudo
     * @return Cargo
     */
    public Cargo incluirCargo(DadosCargo conteudo, int codigo){
        Cargo novo = new Cargo(conteudo, codigo);
        cargos.add(novo);
        return novo; 
    }
    
    /**
     * Exclui o cargo da lista de cargos com base no código informado via parâmetro.
     * @param codigo Codigo do cargo a ser excluído. 
     * @return True or false indicando se o cargo foi exclu[ido ou não.
     */
    public boolean excluirCargo(int codigo) {
        if (this.findCargoByCodigo(codigo)) {
            for (int i = 0; i < cargos.size(); i++) {
                if (cargos.get(i).getCodigo() == codigo) {
                    cargos.remove(i);
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Altera os dados de um cargo para os novos dados informados via parâmetro, com exceção de seu código.
     * O código de um cargo não pode ser alterado.
     * @param conteudo Conteúdo a ser alterado no cargo.
     * @return Cargo Retorna o cargo que sofreu alterações.
     */
    public Cargo alterarCargo(DadosCargo conteudo, int codigo) {
        if (conteudo != null) {
            for (Cargo cargoLista : cargos) {
                if (cargoLista.getCodigo() == codigo) {
                    cargoLista.setEhGerencial(conteudo.ehGerencial);
                    cargoLista.setPermiteAcesso(conteudo.permiteAcesso);
                    cargoLista.setNome(conteudo.nome);
                    //cargoLista.setHorario(conteudo.horarios);
                    
                    return cargoLista;
                } 
            }
        }
        return null;
    }
    
    /**
     * "Varre" a lista de cargos cadastrados, buscando por um cargo que contenha o código passado como parâmetro. Retorna um Cargo nulo, caso não o encontre, e o Cargo encontrado, caso o encontre.
     * @param codigo
     * @return Cargo
     */
    public boolean findCargoByCodigo(int codigo){
        for(Cargo cargoAtual : cargos){
            if(cargoAtual.getCodigo() == codigo){
                return true; 
            }
        }
        return false;
    }
    
    public boolean findCargoByNome(String nome){
        for(Cargo cargoAtual : cargos){
            if(cargoAtual.getNome().equals(nome)){
                return true;
            }
        }
        return false;
    }
    
    public int geraSequencialCargo(){
        this.sequencialCargo++;
        return this.sequencialCargo;
    }
    public void listarCargos(){
        DateFormat formatador = new SimpleDateFormat("HH:mm");
        for(Cargo cargoLista : cargos){
            System.out.println("Nome: " + cargoLista.getNome() + " | Código: " + cargoLista.getCodigo());
            System.out.println("Horários deste cargo :");
            for(int i = 0; i < cargoLista.getHorarios().size(); i = i+2){
                Date horario1 = cargoLista.getHorarios().get(i).getTime();
                Date horario2 = cargoLista.getHorarios().get(i+1).getTime();
                System.out.println("De : " + formatador.format(horario1) + " à " + formatador.format(horario2) + ";");
            }
            System.out.println();
        }
    }

    public void reduzSequencialCargo() {
       this.sequencialCargo = sequencialCargo - 1;
    }

}
