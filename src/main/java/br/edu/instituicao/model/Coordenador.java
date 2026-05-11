package br.edu.instituicao.model;

import br.edu.instituicao.strategy.RegraGestor;

public class Coordenador extends Professor {

    public Coordenador(String nome, String cpf, String email, String siape, String senha) {
        super(nome, cpf, email, siape, senha, new RegraGestor());
    }

    @Override
    public String toString() {
        return "[COORDENADOR] Nome: " + getNome()
                + " | CPF: " + getCpf()
                + " | E-mail: " + getEmail()
                + " | SIAPE: " + getSiape();
    }
}
