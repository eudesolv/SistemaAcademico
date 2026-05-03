package br.edu.instituicao.model;

import br.edu.instituicao.interfaces.Autenticavel;

public class Professor extends Pessoa implements Autenticavel {

    // Atributos privados do professor
    private String siape;
    private String senha;

    /**
     * Construtor do Professor.
     *
     * @param nome  o nome completo do professor
     * @param cpf   o CPF do professor
     * @param email o e-mail do professor
     * @param siape o número SIAPE do professor
     * @param senha a senha de acesso do professor
     */
    public Professor(String nome, String cpf, String email, String siape, String senha) {
        super(nome, cpf, email);
        this.siape = siape;
        this.senha = senha;
    }

    // ---- Getters e Setters ----

    /**
     * Retorna o número SIAPE do professor.
     *
     * @return o SIAPE
     */
    public String getSiape() {
        return siape;
    }

    /**
     * Define o número SIAPE do professor.
     *
     * @param siape o novo SIAPE
     */
    public void setSiape(String siape) {
        this.siape = siape;
    }

    /**
     * Retorna a senha do professor.
     *
     * @return a senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Define a senha do professor.
     *
     * @param senha a nova senha
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Verifica se a senha fornecida corresponde à senha cadastrada,
     * realizando a autenticação do professor no sistema.
     *
     * @param senha a senha a ser verificada
     * @return true se a senha estiver correta, false caso contrário
     */
    @Override
    public boolean login(String senha) {
        return this.senha.equals(senha);
    }

    /**
     * Retorna uma representação textual do professor.
     *
     * @return string com dados do professor
     */
    @Override
    public String toString() {
        return "[PROFESSOR] " + super.toString() + " | SIAPE: " + siape;
    }
}
