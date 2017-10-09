package Trabalho1.Cargo;

import Trabalho1.Cargo.Cargo;
import Trabalho1.Principal.ControladorPrincipal;
import java.util.ArrayList;

/**
 *
 * @author Vinicius Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public class ControladorCargo {
    
    private ControladorPrincipal controladorPrincipal;
    private ArrayList<Cargo> cargos;
    private TelaCargo telaCargo;
    

    public ControladorCargo(ControladorPrincipal controladorPrincipal) {
        this.cargos = new ArrayList<Cargo>();
        this.telaCargo = new TelaCargo(this);
        this.controladorPrincipal = controladorPrincipal;
    }
    
    /**
     * 
     * @return Retorna um ArrayList com a lista de cargos.
     */
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
    public Cargo incluirCargo(DadosCargo conteudo) {
        if (conteudo != null) {
            for (Cargo cargoLista : cargos) {
                if (cargoLista.getCodigo() == conteudo.getCodigo()) {
                    return null;
                } else {
                    Cargo novo = new Cargo(conteudo);
                    cargos.add(novo);
                }
            }
        }
        return null;
    }
    
    /**
     * Exclui o cargo da lista de cargos com base no código informado via parâmetro.
     * @param codigo 
     */
    public void excluirCargo(int codigo) {
        if (codigo > 0) {
            for (int i = 0; i < cargos.size(); i++) {
                if (cargos.get(i).getCodigo() == codigo) {
                    cargos.remove(i);
                }
            }
        }
    }
    
    /**
     * Altera os dados de um cargo para os novos dados informados via parâmetro, com exceção de seu código.
     * O código de um cargo não pode ser alterado.
     * @param conteudo
     * @return Cargo
     */
    public Cargo alterarCargo(DadosCargo conteudo) {
        if (conteudo != null) {
            for (Cargo cargoLista : cargos) {
                if (cargoLista.getCodigo() == conteudo.getCodigo()) {
                    cargoLista.setEhGerencial(conteudo.isEhGerencial());
                    cargoLista.setPermiteAcesso(conteudo.isPermiteAcesso());
                    cargoLista.setNome(conteudo.getNome());
                    cargoLista.setHorarioPermitido(conteudo.getHorarioPermitido());
                    
                    return cargoLista;
                } 
            }
        }
        return null;
    }
    /**
     * Recebe um número inteiro como parâmetro, e retorna uma lista de cargos com o tamanho informado por parâmetro.
     * @param tamanhoLista
     * @return cargosLista
     */
    public ArrayList listarCargos(int tamanhoLista){
        ArrayList<Cargo> cargosLista = new ArrayList<>();
        for(int i = 0; i < tamanhoLista; i++){
            cargosLista.add(cargos.get(i)); 
    }
        return cargosLista;
    }

}
