package br.edu.instituicao.service;

import br.edu.instituicao.interfaces.Avaliavel;

import java.util.ArrayList;

public class RelatorioAcademico {

    private ArrayList<Avaliavel> dados;

    public RelatorioAcademico() {
        this.dados = new ArrayList<>();
    }

    public ArrayList<Avaliavel> getDados() {
        return dados;
    }

    public void setDados(ArrayList<Avaliavel> dados) {
        this.dados = dados;
    }

    public void adicionarDados(Avaliavel avaliavel) {
        this.dados.add(avaliavel);
    }

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
            System.out.printf("  Média individual: %.2f%n", media);
        }

        double mediaGeral = somaTotal / dados.size();
        System.out.println("----------------------------------------------");
        System.out.printf("Total de avaliados : %d%n", dados.size());
        System.out.printf("Média Geral        : %.2f%n", mediaGeral);
        System.out.println("===============================================\n");
    }

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
