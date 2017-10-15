package Trabalho1.Cargo;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurélio Geremias
 */
public interface IControladorCargo {

    /**
     * Recebe os dados do cargo a ser incluído via parâmetro, inclui o cargo na
     * lista de cargos, e retorna o cargo incluído.
     *
     * @param conteudo Conteúdo do Cargo (dados principais).
     * @param codigo Código do cargo (gerado automaticamente).
     * @return Cargo Cargo incluído.
     */
    public Cargo incluirCargo(DadosCargo conteudo, int codigo);

    /**
     * Exclui o cargo da lista de cargos com base no código informado via
     * parâmetro.
     *
     * @param codigo Codigo do cargo a ser excluído.
     * @return True or false indicando se o cargo foi exclu[ido ou não.
     */
    public boolean excluirCargo(int codigo);

    /**
     * Altera os dados de um cargo para os novos dados informados via parâmetro,
     * com exceção de seu código. O código de um cargo não pode ser alterado.
     *
     * @param conteudo Conteúdo a ser alterado no cargo.
     * @param codigo Código do cargo que sofrerá alterações.
     * @return Cargo Retorna o cargo que sofreu alterações.
     */
    public Cargo alterarCargo(DadosCargo conteudo, int codigo);

    /**
     * "Varre" a lista de cargos cadastrados, buscando por um cargo que contenha
     * o código passado como parâmetro. Retorna um Cargo nulo, caso não o
     * encontre, e o Cargo encontrado, caso o encontre.
     *
     * @param codigo Código do cargo a ser buscado.
     * @return boolean Falso, caso não encontre, e verdadeiro, caso encontre.
     */
    public boolean findCargoByCodigo(int codigo);
    
    /**
     * "Varre" a lista de cargos cadastrados, buscando por um cargo que contenha
     * o nome passado como parâmetro. Retorna um Cargo nulo, caso não o
     * encontre, e o Cargo encontrado, caso o encontre.
     * @param nome Nome do cargo a ser buscado.
     * @return boolean Falso, caso não encontre, e verdadeiro, caso encontre.
     */
    public boolean findCargoByNome(String nome);
}
