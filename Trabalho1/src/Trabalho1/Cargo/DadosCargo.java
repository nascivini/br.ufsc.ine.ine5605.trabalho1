/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class DadosCargo {
    
    public final int codigo;
    public final String nome;
    public final boolean  permiteAcesso;
    public final boolean ehGerencial;
    public final ArrayList<Calendar> horarios;
    public final TipoCargo tipoCargo;
    
    /**
     * Recebe os dados de um cargo, vindos da TelaCargo, e instancia um objeto transiente para posterior uso para o cadastro de um cargo pelo ControladorCargo.
     * @param codigo
     * @param nome
     * @param permiteAcesso
     * @param ehGerencial
     * @param horarios
     * @param tipo 
     */
    public DadosCargo(int codigo, String nome, boolean permiteAcesso, boolean ehGerencial, ArrayList<Calendar> horarios, String tipo) {
        this.codigo = codigo;
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
}
