package Trabalho1.Cargo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public class Cargo {
    
    private int codigo;
    private String nome;
    private boolean permiteAcesso;
    private boolean ehGerencial;
    private ArrayList<Calendar> horarios;
    private TipoCargo tipoCargo;
    
    public Cargo(DadosCargo dados) {
        this.codigo = dados.codigo;
        this.nome = dados.nome;
        this.permiteAcesso = dados.permiteAcesso;
        this.ehGerencial = dados.ehGerencial;
        this.horarios = dados.horarios;
        this.tipoCargo = dados.tipoCargo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isPermiteAcesso() {
        return permiteAcesso;
    }

    public void setPermiteAcesso(boolean permiteAcesso) {
        this.permiteAcesso = permiteAcesso;
    }

    public boolean isEhGerencial() {
        return ehGerencial;
    }

    public void setEhGerencial(boolean ehGerencial) {
        this.ehGerencial = ehGerencial;
    }

    public ArrayList<Calendar> getHorarios() {
        return horarios;
    }


    
}
