package Trabalho1.Cargo;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public class DadosCargo {
    
    public String nome;
    public boolean  permiteAcesso;
    public final boolean ehGerencial;
    public ArrayList<Calendar> horarios;
    public TipoCargo tipoCargo;
    
    /**
     * Recebe os dados de um cargo, vindos da TelaCargo, e instancia um objeto transiente para posterior uso para o cadastro de um cargo pelo ControladorCargo.
     * @param nome Nome do cargo.
     * @param permiteAcesso Permite acesso ou não à porta do financeiro. 
     * @param ehGerencial É gerencial ou não.
     * @param horarios ArrayList com os horários do cargo.
     * @param tipo Tipo do cargo.
     */
    public DadosCargo(String nome, boolean permiteAcesso, boolean ehGerencial, ArrayList<Calendar> horarios, String tipo) {
        this.nome = nome;
        this.permiteAcesso = permiteAcesso;
        this.ehGerencial = ehGerencial;
        this.horarios = horarios;
        switch(tipo) {
            case "GERENCIAL":
                this.tipoCargo = TipoCargo.GERENCIAL;
                break;
            case "COMUM":
                this.tipoCargo = TipoCargo.COMUM;
                break;
            case "CONVIDADO":
                this.tipoCargo = TipoCargo.CONVIDADO;
                break;
            default:
                this.tipoCargo = TipoCargo.COMUM;
        }       
    }

    public DadosCargo() {
        this.ehGerencial = false;
        this.horarios = null;
        this.nome = null;
        this.permiteAcesso = false;
        this.tipoCargo = null;
    }
}
