package br.edu.instituicao.model;

import br.edu.instituicao.interfaces.Autenticavel;
import br.edu.instituicao.strategy.EstrategiaCalculoHoras;
import br.edu.instituicao.strategy.RegraPadrao;

public class Professor extends Pessoa implements Autenticavel {

    private String siape;
    private String senha;

    private EstrategiaCalculoHoras estrategiaCalculoHoras;

    public Professor(String nome, String cpf, String email, String siape, String senha,
            EstrategiaCalculoHoras estrategiaCalculoHoras) {
        super(nome, cpf, email);
        this.siape = siape;
        this.senha = senha;
        this.estrategiaCalculoHoras = estrategiaCalculoHoras;
    }

    public Professor(String nome, String cpf, String email, String siape, String senha) {
        this(nome, cpf, email, siape, senha, new RegraPadrao());
    }

    public String getSiape() {
        return siape;
    }

    public void setSiape(String siape) {
        this.siape = siape;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEstrategiaCalculoHoras(EstrategiaCalculoHoras estrategiaCalculoHoras) {
        this.estrategiaCalculoHoras = estrategiaCalculoHoras;
    }

    public EstrategiaCalculoHoras getEstrategiaCalculoHoras() {
        return estrategiaCalculoHoras;
    }

    public double calcularHorasAtividade() {
        return estrategiaCalculoHoras.calcularHorasAtividade(this);
    }

    @Override
    public boolean login(String senha) {
        return this.senha.equals(senha);
    }

    @Override
    public String toString() {
        return "[PROFESSOR] " + super.toString() + " | SIAPE: " + siape;
    }
}
