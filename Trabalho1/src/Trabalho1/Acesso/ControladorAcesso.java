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
    
    public boolean verificaAcesso(int matricula) throws Exception{
		if(this.controladorPrincipal.getControladorFuncionario().validaMatricula(matricula)) { //validou a matricula, logo possui um funcionario com essa matricula
			if(this.controladorPrincipal.getControladorFuncionario().findFuncionarioByMatricula(matricula).getCargo().isEhGerencial()) {
				return true; //cargo gerencial possui acesso em qualquer hora
			}
			else if(!this.controladorPrincipal.getControladorFuncionario().findFuncionarioByMatricula(matricula).getCargo().isPermiteAcesso()) {
				return false; //nao possui permissao em qualquer horario
			}
			else if(this.controladorPrincipal.getControladorFuncionario().findFuncionarioByMatricula(matricula).getCargo().isPermiteAcesso()) {
				ArrayList<Calendar> listaHorariosCargo = this.controladorPrincipal.getControladorFuncionario().findFuncionarioByMatricula(matricula).getCargo().getHorarios();
				
				SimpleDateFormat formatarHora = new SimpleDateFormat("HH:mm");
				
				Calendar dataAgora = Calendar.getInstance();
				dataAgora.setTime(formatarHora.parse(formatarHora.format(dataAgora.getTime())));
				
				for (int i = 0; i < listaHorariosCargo.size(); i = i + 2) {
					Calendar horaEntrada = listaHorariosCargo.get(i);
					horaEntrada.setTime(formatarHora.parse(formatarHora.format(horaEntrada.getTime())));
					
					Calendar horaSaida = listaHorariosCargo.get(i + 1);
					horaSaida.setTime(formatarHora.parse(formatarHora.format(horaSaida.getTime())));

					if (horaEntrada.getTime().before(horaSaida.getTime()) && horaEntrada.getTime().before(dataAgora.getTime()) && horaSaida.getTime().after(dataAgora.getTime())){
						return true;
					}
					else if(horaEntrada.getTime().after(horaSaida.getTime())) {
						if(horaEntrada.HOUR_OF_DAY > dataAgora.HOUR_OF_DAY && horaSaida.HOUR_OF_DAY > dataAgora.HOUR_OF_DAY) {
							return true; //acesso horario especial, ex: 22h as 5h com acesso a 1h
						}
						else if(horaEntrada.HOUR_OF_DAY < dataAgora.HOUR_OF_DAY && horaSaida.HOUR_OF_DAY < dataAgora.HOUR_OF_DAY) {
							return true; //acesso horario especial, ex: 22h as 5h com acesso a 23h
						}
						else {
							return false;
						}
					}
				}
				return false;					
			}	
		} else {
			return false; //matricula nao encontrada
		}
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
