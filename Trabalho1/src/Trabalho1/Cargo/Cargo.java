package Trabalho1.Cargo;

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
    private Date horarioPermitido;
    private TipoCargo tipoCargo;
    
    public Cargo(DadosCargo dados) {
        this.codigo = dados.getCodigo();
        this.nome = dados.getNome();
        this.permiteAcesso = dados.isPermiteAcesso();
        this.ehGerencial = dados.isEhGerencial();
        this.horarioPermitido = dados.getHorarioPermitido();
        this.tipoCargo = dados.getTipoCargo();
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

    public Date getHorarioPermitido() {
        return horarioPermitido;
    }

    public void setHorarioPermitido(Date horarioPermitido) {
        this.horarioPermitido = horarioPermitido;
    }
    
}
