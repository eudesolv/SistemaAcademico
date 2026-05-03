package br.edu.instituicao.interfaces;

/**
 * Interface que define o contrato para entidades que podem se autenticar.
 * Qualquer classe que implemente esta interface deve fornecer
 * um método de login com validação de senha.
 */
public interface Autenticavel {

    /**
     * Realiza a autenticação da entidade comparando a senha fornecida
     * com a senha cadastrada.
     *
     * @param senha a senha a ser verificada
     * @return true se a autenticação for bem-sucedida, false caso contrário
     */
    boolean login(String senha);
}
