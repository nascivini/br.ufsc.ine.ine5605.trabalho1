package Trabalho1.Acesso;

import Trabalho1.Principal.ControladorPrincipal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public class ControladorAcesso implements IControladorAcesso {

    private final ArrayList<Acesso> acessos;
    private final ControladorPrincipal controladorPrincipal;
    private final TelaAcesso telaAcesso;

    /**
     * Recebe o controlador Principal como parametro para possibilitar a
     * comunicacao e cria um objeto da Classe ControladorAcesso
     * @param controladorPrincipal ControladorPrincipal em execução no programa.
     */
    public ControladorAcesso(ControladorPrincipal controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;
        this.acessos = new ArrayList<>();
        this.telaAcesso = new TelaAcesso(this);
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

    @Override
    public void findAcessosNegadosByMatricula(int matricula) {
        //ArrayList<Acesso> acessosNegadosMat = new ArrayList<Acesso>();
        SimpleDateFormat formatarHora = new SimpleDateFormat("HH:mm");
        for (Acesso acesso : acessos) {
            if (acesso.getMotivo() != MotivoAcesso.OK && acesso.getMatricula() == matricula) {
                //acessosNegadosMat.add(acesso);
                System.out.println("Matricula: " + acesso.getMatricula() + " | Horario: " + formatarHora.format(acesso.getHorario().getTime()) + " | Motivo: " + acesso.getMotivo().getDescricao());
            }
        }
        //return acessosNegadosMat;
    }

    @Override
    public void findAcessosNegadosByMotivo(MotivoAcesso motivo) {
        //ArrayList<Acesso> acessosNegadosMot = new ArrayList<Acesso>();
        SimpleDateFormat formatarHora = new SimpleDateFormat("HH:mm");
        for (Acesso acesso : acessos) {
            if (acesso.getMotivo() != MotivoAcesso.OK && acesso.getMotivo() == motivo) {
                //acessosNegadosMot.add(acesso);
                System.out.println("Matricula: " + acesso.getMatricula() + " | Horario: " + formatarHora.format(acesso.getHorario().getTime()) + " | Motivo: " + acesso.getMotivo().getDescricao());
            }
        }
        //return acessosNegadosMot;
    }

    @Override
    public void findAcessosNegados() {
        //ArrayList<Acesso> acessosNegados = new ArrayList<Acesso>();
        SimpleDateFormat formatarHora = new SimpleDateFormat("HH:mm");
        for (Acesso acesso : acessos) {
            if (acesso.getMotivo() != MotivoAcesso.OK) {
                //acessosNegados.add(acesso);
                System.out.println("Matricula: " + acesso.getMatricula() + " | Horario: " + formatarHora.format(acesso.getHorario().getTime()) + " | Motivo: " + acesso.getMotivo().getDescricao());
            }
        }
    }

    @SuppressWarnings("static-access")
	@Override
    public Acesso verificaAcesso(int matricula, int hora, int minuto) {
        Calendar dataAgora = Calendar.getInstance();
		dataAgora.set(0,0,0,hora,minuto);
        if (this.controladorPrincipal.getControladorFuncionario().validaMatricula(matricula)) { //validou a matricula, logo possui um funcionario com essa matricula
            if (this.controladorPrincipal.getControladorFuncionario().findFuncionarioByMatricula(matricula).getCargo().isEhGerencial()) {
                Acesso acesso = new Acesso(dataAgora, matricula, MotivoAcesso.OK);
                this.acessos.add(acesso);
                return acesso; //cargo gerencial possui acesso em qualquer hora
            } else if(this.controladorPrincipal.getControladorFuncionario().findFuncionarioByMatricula(matricula).getnAcessosNegados() >= 3) {
            	int valorNAcessoN = this.controladorPrincipal.getControladorFuncionario().findFuncionarioByMatricula(matricula).getnAcessosNegados();
            	this.controladorPrincipal.getControladorFuncionario().findFuncionarioByMatricula(matricula).setnAcessosNegados(valorNAcessoN + 1);
            	Acesso acesso = new Acesso(dataAgora, matricula, MotivoAcesso.BLOQUEADO);
                this.acessos.add(acesso);
                return acesso; //acesso bloqueado, mais de 3 tentativas de acesso invalidas
            } else if (!this.controladorPrincipal.getControladorFuncionario().findFuncionarioByMatricula(matricula).getCargo().isPermiteAcesso()) {
            	int valorNAcessoN = this.controladorPrincipal.getControladorFuncionario().findFuncionarioByMatricula(matricula).getnAcessosNegados();
            	this.controladorPrincipal.getControladorFuncionario().findFuncionarioByMatricula(matricula).setnAcessosNegados(valorNAcessoN + 1);
                Acesso acesso = new Acesso(dataAgora, matricula, MotivoAcesso.PERMISSAO);
                this.acessos.add(acesso);
                return acesso; //nao possui permissao em qualquer horario
            } else if (this.controladorPrincipal.getControladorFuncionario().findFuncionarioByMatricula(matricula).getCargo().isPermiteAcesso()) {
                ArrayList<Calendar> listaHorariosCargo = this.controladorPrincipal.getControladorFuncionario().findFuncionarioByMatricula(matricula).getCargo().getHorarios();
                for (int i = 0; i < listaHorariosCargo.size(); i = i + 2) {
                    Calendar horaEntrada = listaHorariosCargo.get(i);
                    Calendar horaSaida = listaHorariosCargo.get(i + 1);

                    if (horaEntrada.getTime().before(horaSaida.getTime()) && horaEntrada.getTime().before(dataAgora.getTime()) && horaSaida.getTime().after(dataAgora.getTime())) {
                        Acesso acesso = new Acesso(dataAgora, matricula, MotivoAcesso.OK);
                        this.acessos.add(acesso);
                        return acesso;
                    } else if (horaEntrada.getTime().after(horaSaida.getTime())) {
                        if (horaSaida.HOUR_OF_DAY == dataAgora.HOUR_OF_DAY && horaSaida.MINUTE >= dataAgora.MINUTE) {
                            Acesso acesso = new Acesso(dataAgora, matricula, MotivoAcesso.OK);
                            this.acessos.add(acesso);
                            return acesso; //acesso horario especial, hora atual = hora saida, verificar minutos
                        } else if (horaEntrada.HOUR_OF_DAY > dataAgora.HOUR_OF_DAY && horaSaida.HOUR_OF_DAY > dataAgora.HOUR_OF_DAY) {
                            Acesso acesso = new Acesso(dataAgora, matricula, MotivoAcesso.OK);
                            this.acessos.add(acesso);
                            return acesso; //acesso horario especial, ex: 22h as 5h com acesso a 1h
                        } else if (horaEntrada.HOUR_OF_DAY < dataAgora.HOUR_OF_DAY && horaSaida.HOUR_OF_DAY < dataAgora.HOUR_OF_DAY) {
                            Acesso acesso = new Acesso(dataAgora, matricula, MotivoAcesso.OK);
                            this.acessos.add(acesso);
                            return acesso; //acesso horario especial, ex: 22h as 5h com acesso a 23h 
                        }
                    }
                }
                int valorNAcessoN = this.controladorPrincipal.getControladorFuncionario().findFuncionarioByMatricula(matricula).getnAcessosNegados();
            	this.controladorPrincipal.getControladorFuncionario().findFuncionarioByMatricula(matricula).setnAcessosNegados(valorNAcessoN + 1);
                Acesso acesso = new Acesso(dataAgora, matricula, MotivoAcesso.ATRASADO);
                this.acessos.add(acesso);
                return acesso;
            }
        } else {
            Acesso acesso = new Acesso(dataAgora, matricula, MotivoAcesso.OUTRO);
            this.acessos.add(acesso);
            return acesso; //matricula nao encontrada, nao eh para acontecer nunca. 
        }
        return null; //nao eh para acontecer nunca.
    }


}
