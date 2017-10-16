package Trabalho1.Acesso;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurélio Geremias
 */
public interface IControladorAcesso {

    /**
     * Recebe uma matricula e lista os acessos negados da mesma
     * @param matricula Numero da Matricula para geração do relatório.
     *
     */    
    public void findAcessosNegadosByMatricula(int matricula);
    
    /**
     * Recebe um motivo e lista os acessos negados do mesmo
     * @param motivo Motivo de acesso para a geração do relatório.
     */    
    public void findAcessosNegadosByMotivo(MotivoAcesso motivo);

    /**
     * Lista todos os acessos negados
     */    
    public void findAcessosNegados();
    
    /**
     * Recebe uma matricula e realiza a verificacao se a mesma esta apta a
     * acessar a porta
     * @param matricula Matrícula para validação de acesso
     * @param hora Hora para verificação
     * @param minuto Minuto para verificação
     * @return Verdadeiro ou falso, dependendo se a matricula possui ou nao
     * acesso a porta
     */    
    public Acesso verificaAcesso(int matricula, int hora, int minuto);
}
