package br.edu.instituicao.interfaces;

/**
 * Interface que define o contrato para entidades que podem ser avaliadas.
 * Qualquer classe que implemente esta interface deve fornecer
 * um método para calcular a média final.
 */
public interface Avaliavel {

    /**
     * Calcula e retorna a média final da entidade avaliável.
     *
     * @return a média final como valor double
     */
    double getMediaFinal();
}
