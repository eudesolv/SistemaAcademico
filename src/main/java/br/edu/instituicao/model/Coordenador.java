package br.edu.instituicao.model;


public class Coordenador extends Professor {

    /**
     * Construtor do Coordenador.
     *
     * @param nome  o nome completo do coordenador
     * @param cpf   o CPF do coordenador
     * @param email o e-mail do coordenador
     * @param siape o número SIAPE do coordenador
     * @param senha a senha de acesso do coordenador
     */
    public Coordenador(String nome, String cpf, String email, String siape, String senha) {
        super(nome, cpf, email, siape, senha);
    }

    /**
     * Retorna uma representação textual do coordenador.
     *
     * @return string com dados do coordenador
     */
    @Override
    public String toString() {
        return "[COORDENADOR] Nome: " + getNome()
                + " | CPF: " + getCpf()
                + " | E-mail: " + getEmail()
                + " | SIAPE: " + getSiape();
    }
}
