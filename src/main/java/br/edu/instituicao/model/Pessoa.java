package br.edu.instituicao.model;

public abstract class Pessoa {

    // Atributos privados encapsulados
    private String nome;
    private String cpf;
    private String email;

    /**
     * Construtor da classe Pessoa.
     *
     * @param nome  o nome completo da pessoa
     * @param cpf   o CPF da pessoa (somente números)
     * @param email o endereço de e-mail da pessoa
     */
    public Pessoa(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    // ---- Getters ----

    /**
     * Retorna o nome da pessoa.
     *
     * @return o nome completo
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o CPF da pessoa.
     *
     * @return o CPF
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Retorna o e-mail da pessoa.
     *
     * @return o endereço de e-mail
     */
    public String getEmail() {
        return email;
    }

    // ---- Setters ----

    /**
     * Define o nome da pessoa.
     *
     * @param nome o novo nome completo
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Define o CPF da pessoa.
     *
     * @param cpf o novo CPF
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Define o e-mail da pessoa.
     *
     * @param email o novo endereço de e-mail
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retorna uma representação textual da pessoa.
     *
     * @return string com nome, CPF e e-mail
     */
    @Override
    public String toString() {
        return "Nome: " + nome + " | CPF: " + cpf + " | E-mail: " + email;
    }
}
