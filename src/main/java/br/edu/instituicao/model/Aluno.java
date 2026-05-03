package br.edu.instituicao.model;

import br.edu.instituicao.interfaces.Avaliavel;

import java.util.ArrayList;


public class Aluno extends Pessoa implements Avaliavel {

    // Atributos privados do aluno
    private String matricula;
    private ArrayList<Double> notas;

    /**
     * Construtor do Aluno.
     *
     * @param nome      o nome completo do aluno
     * @param cpf       o CPF do aluno
     * @param email     o e-mail do aluno
     * @param matricula o número de matrícula do aluno
     */
    public Aluno(String nome, String cpf, String email, String matricula) {
        super(nome, cpf, email);
        this.matricula = matricula;
        this.notas = new ArrayList<>();
    }

    // ---- Getters e Setters ----

    /**
     * Retorna a matrícula do aluno.
     *
     * @return a matrícula
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Define a matrícula do aluno.
     *
     * @param matricula a nova matrícula
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * Retorna a lista de notas do aluno.
     *
     * @return ArrayList de notas
     */
    public ArrayList<Double> getNotas() {
        return notas;
    }

    /**
     * Define a lista de notas do aluno.
     *
     * @param notas a nova lista de notas
     */
    public void setNotas(ArrayList<Double> notas) {
        this.notas = notas;
    }

    /**
     * Adiciona uma nota à lista de notas do aluno.
     *
     * @param nota a nota a ser adicionada (deve estar entre 0 e 10)
     */
    public void adicionarNota(double nota) {
        this.notas.add(nota);
    }

    /**
     * Calcula e retorna a média final do aluno com base em suas notas.
     * Retorna 0.0 caso o aluno não possua nenhuma nota registrada.
     *
     * @return a média aritmética de todas as notas, ou 0.0 se não houver notas
     */
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

    /**
     * Retorna uma representação textual do aluno.
     *
     * @return string com dados do aluno e sua média final
     */
    @Override
    public String toString() {
        return "[ALUNO] " + super.toString()
                + " | Matrícula: " + matricula
                + " | Notas: " + notas
                + " | Média: " + String.format("%.2f", getMediaFinal());
    }
}
