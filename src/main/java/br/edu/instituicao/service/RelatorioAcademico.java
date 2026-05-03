package br.edu.instituicao.service;

import br.edu.instituicao.interfaces.Avaliavel;

import java.util.ArrayList;

/**
 * Serviço responsável por gerar relatórios acadêmicos.
 * Armazena objetos que implementam Avaliavel e calcula
 * estatísticas gerais da turma ou comunidade acadêmica.
 */
public class RelatorioAcademico {

    // Lista de entidades avaliáveis para o relatório
    private ArrayList<Avaliavel> dados;

    /**
     * Construtor padrão. Inicializa a lista de dados vazia.
     */
    public RelatorioAcademico() {
        this.dados = new ArrayList<>();
    }

    // ---- Getters e Setters ----

    /**
     * Retorna a lista de dados avaliáveis do relatório.
     *
     * @return ArrayList de Avaliavel
     */
    public ArrayList<Avaliavel> getDados() {
        return dados;
    }

    /**
     * Define a lista de dados avaliáveis do relatório.
     *
     * @param dados a nova lista de dados
     */
    public void setDados(ArrayList<Avaliavel> dados) {
        this.dados = dados;
    }

    /**
     * Adiciona um objeto Avaliavel à lista de dados do relatório.
     *
     * @param avaliavel o objeto a ser adicionado
     */
    public void adicionarDados(Avaliavel avaliavel) {
        this.dados.add(avaliavel);
    }

    /**
     * Calcula a média geral de todos os objetos Avaliavel registrados
     * e exibe o resultado no console.
     * Caso não haja dados registrados, exibe uma mensagem informativa.
     */
    public void exibirMediaGeral() {
        if (dados.isEmpty()) {
            System.out.println("Nenhum dado disponível para gerar o relatório.");
            return;
        }

        double somaTotal = 0.0;
        System.out.println("\n===== RELATÓRIO ACADÊMICO =====");
        System.out.printf("%-30s | %s%n", "Nome / Identificador", "Média Final");
        System.out.println("----------------------------------------------");

        for (Avaliavel avaliavel : dados) {
            double media = avaliavel.getMediaFinal();
            somaTotal += media;
            // Exibe o toString() do objeto ou a média caso não seja possível identificar
            System.out.printf("  Média individual: %.2f%n", media);
        }

        double mediaGeral = somaTotal / dados.size();
        System.out.println("----------------------------------------------");
        System.out.printf("Total de avaliados : %d%n", dados.size());
        System.out.printf("Média Geral        : %.2f%n", mediaGeral);
        System.out.println("===============================================\n");
    }

    /**
     * Calcula e retorna a média geral de todos os Avaliaveis registrados.
     *
     * @return a média geral, ou 0.0 se não houver dados
     */
    public double calcularMediaGeral() {
        if (dados.isEmpty()) {
            return 0.0;
        }
        double soma = 0.0;
        for (Avaliavel avaliavel : dados) {
            soma += avaliavel.getMediaFinal();
        }
        return soma / dados.size();
    }
}
