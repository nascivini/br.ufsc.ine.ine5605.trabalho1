package Trabalho1.Acesso;

import Trabalho1.Principal.ControladorPrincipal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public class ControladorAcesso {
    private ArrayList<Acesso> acessos;
    private ControladorPrincipal controladorPrincipal;
    private TelaAcesso telaAcesso;
    
    public ControladorAcesso(ControladorPrincipal controladorPrincipal){
        this.controladorPrincipal = controladorPrincipal;
    }

    public ArrayList<Acesso> getAcessos() {
        return acessos;
    }

    public ControladorPrincipal getControladorPrincipal() {
        return controladorPrincipal;
    }

    public TelaAcesso getTelaAcesso() {
        return telaAcesso;
    }
    
    public ArrayList<Acesso> findAcessosNegadosbyMatricula(int matricula){
    	ArrayList<Acesso> acessosNegadosMat = new ArrayList<Acesso>();
    	for(Acesso acesso : acessos) {
			if(acesso.getMotivo() != MotivoAcesso.OK && acesso.getMatricula() == matricula) {
				acessosNegadosMat.add(acesso);
			}
		}
    	return acessosNegadosMat;
    }
    
    public ArrayList<Acesso> findAcessosNegadosbyMotivo(MotivoAcesso motivo){
    	ArrayList<Acesso> acessosNegadosMot = new ArrayList<Acesso>();
    	for(Acesso acesso : acessos) {
			if(acesso.getMotivo() != MotivoAcesso.OK && acesso.getMotivo() == motivo ) {
				acessosNegadosMot.add(acesso);
			}
		}
    	return acessosNegadosMot;
    }
    
    public ArrayList<Acesso> findAcessosNegados(){
    	ArrayList<Acesso> acessosNegados = new ArrayList<Acesso>();
    	for(Acesso acesso : acessos) {
			if(acesso.getMotivo() != MotivoAcesso.OK) {
				acessosNegados.add(acesso);
			}
		}
    	return acessosNegados;
    }
    
    public Boolean verificaAcesso(int matricula) throws Exception{
		
		SimpleDateFormat formatarHora = new SimpleDateFormat("HH:mm");
		
		Calendar dataAgora = Calendar.getInstance();
		dataAgora.setTime(formatarHora.parse(formatarHora.format(dataAgora.getTime())));
		
		List<Calendar> listaHorariosCargo = getListaHorariosCargo(matricula);
		for (Calendar horarios : listaHorariosCargo) {
			
			Calendar horaEntrada = Calendar.getInstance();
			horaEntrada.setTime(formatarHora.parse(horarios.getHoraInicial()));
			
			Calendar horaSaida = Calendar.getInstance();
			horaSaida.setTime(formatarHora.parse(horarios.getHoraFinal()));

			if (horaEntrada.getTime().before(dataAgora.getTime()) && horaSaida.getTime().after(dataAgora.getTime())){
				return true;
			}
			
		}
		
		return false;
	}
    
    private List<Calendar> getListaHorariosCargo(int matricula) {
		
		List<Calendar> lista = new ArrayList<Calendar>();
		
		if (matricula == 1) {
			Calendar hora = Calendar.getInstance();
			hora.set(Calendar.HOUR_OF_DAY, 9);
			hora.set(Calendar.MINUTE, 30);
			lista.add(hora);
		}
		
		return lista;
	}
}
