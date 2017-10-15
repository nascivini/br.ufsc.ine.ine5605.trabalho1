package Trabalho1.Cargo;

import Trabalho1.Principal.ControladorPrincipal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    public Cargo incluirCargo(DadosCargo conteudo, int codigo){
        Cargo novo = new Cargo(conteudo, codigo);
        cargos.add(novo);
        return novo; 
    }
    
    @Override
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
    
    @Override
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
    
    @Override
    public boolean findCargoByCodigo(int codigo){
        for(Cargo cargoAtual : cargos){
            if(cargoAtual.getCodigo() == codigo){
                return true; 
            }
        }
        return false;
    }
    
    @Override
    public boolean findCargoByNome(String nome){
        for(Cargo cargoAtual : cargos){
            if(cargoAtual.getNome().equals(nome)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Gera um número sequencial para uso da TelaCargo no cadastro de um novo cargo, permitindo a consistência dos códigos de cargos novos a serem cadastrados no sistema.
     * @return Sequencial gerado.
     */
    public int geraSequencialCargo(){
        this.sequencialCargo++;
        return this.sequencialCargo;
    }
    
    /**
     * É usado pela TelaCargo para listar os cargos já cadastrados.
     */
    //ficamos em dúvida entre colocar este método na tela ou no controlador
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
    
    /**
     * Reduz o sequencial de cargos em uma unidade.
     */
    public void reduzSequencialCargo() {
       this.sequencialCargo = sequencialCargo - 1;
    }

}
