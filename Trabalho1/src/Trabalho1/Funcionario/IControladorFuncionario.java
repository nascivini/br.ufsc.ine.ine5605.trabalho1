package Trabalho1.Funcionario;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurélio Geremias
 */
public interface IControladorFuncionario {
    
    /**
     * É utilizado pela TelaFuncionario para incluir um novo funcionário na lista de funcionários e gerar matrícula sequencial
     * para cada nova inclusão
     * @param conteudo - Conteúdo do tipo DadosFuncionario recebido na entrada do método cadastroFuncionario da TelaFuncionario
     * @return novo - Novo funcionário quando o conteudo não recebido não é vazio
     */    
    public Funcionario incluirFuncionario (DadosFuncionario conteudo);
    
    /**
     * É utilizado pela classe TelaFuncionario, no método exclusaoFuncionario().
     * Se a matricula informada no parâmetro for existente, exclui o funcionário correspondente a mesma.
     * @param matricula Matrícula do cargo a ser excluído
     * @return Verdadeiro se encontrar a matrícula e remover o funcionário. Falso se a matrícula não estiver cadastrada.
     */    
    public boolean excluirFuncionario(int matricula);
    
    /**
     * Altera dados cadastrais dos funcionários de acordo com o solicitado. Se a entrada do campo for 0 (ou 00/00/00 para nascimento)
     * o dados se mantém, caso contrário, é alterado com a informação de entrada correspondente
     * @param matricula Matrícula do funcionário a ser alterado
     * @param dados Conteúdo a ser alterado
     * @return Funcionario que sofreu alterações
     */    
    public Funcionario alterarFuncionario(int matricula, DadosFuncionario dados);
    
    /**
     * É utilizado pela TelaFuncionario para exibir a lista de funcionários já cadastrados.
    */    
    public void listarFuncionarios();
    
    /**
     *
     * @return O número da sequência de matrícula.
     */    
    public int gerarMatriculaSequencial();
    
    /**
     * @param matricula Matrícula a ser buscada
     * @return O funcionário correspondente ao número de matrícula informado, caso exista.
     */    
    public Funcionario findFuncionarioByMatricula(int matricula);
    
    /**
     * É utilizado na TelaFuncionario e garante que nenhum funcionário tenha CPF igual a outro
     * @param cpf CPF do Funcionário a ser buscado
     * @return Verdadeiro se o CPF já estiver em uso por algum funcionário já cadastrado
     * Falso caso contrário
     */    
    public boolean findFuncionarioByCpf(long cpf);    
    
     /** 
     * @param matricula Matrícula a ser validada
     * @return Verdadeiro se o número de matrícula informado existir na lista de funcionários já cadastrados
     * Falso caso contrário
     */
    public boolean validaMatricula(int matricula);
}
