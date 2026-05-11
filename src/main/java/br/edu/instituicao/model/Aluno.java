package br.edu.instituicao.model;

import br.edu.instituicao.interfaces.Avaliavel;

import java.util.ArrayList;

public class Aluno extends Pessoa implements Avaliavel {

    private String matricula;
    private ArrayList<Double> notas;

    public Aluno(String nome, String cpf, String email, String matricula) {
        super(nome, cpf, email);
        this.matricula = matricula;
        this.notas = new ArrayList<>();
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public ArrayList<Double> getNotas() {
        return notas;
    }

    public void setNotas(ArrayList<Double> notas) {
        this.notas = notas;
    }

    public void adicionarNota(double nota) {
        this.notas.add(nota);
    }

    @Override
    public double getMediaFinal() {
        if (notas.isEmpty()) {
            return 0.0;
        }
        double soma = 0.0;
        for (Double nota : notas) {
            soma += nota;
        }
        return soma / notas.size();
    }

    @Override
    public String toString() {
        return "[ALUNO] " + super.toString()
                + " | Matrícula: " + matricula
                + " | Notas: " + notas
                + " | Média: " + String.format("%.2f", getMediaFinal());
    }
}
