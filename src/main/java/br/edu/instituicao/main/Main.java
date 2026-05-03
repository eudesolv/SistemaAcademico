package br.edu.instituicao.main;

import br.edu.instituicao.interfaces.Autenticavel;
import br.edu.instituicao.model.Aluno;
import br.edu.instituicao.model.Coordenador;
import br.edu.instituicao.model.Pessoa;
import br.edu.instituicao.model.Professor;
import br.edu.instituicao.service.RelatorioAcademico;
import br.edu.instituicao.service.Secretaria;

import java.util.Scanner;

/**
 * Classe principal do Sistema de Gestão Acadêmica.
 * Fornece um menu interativo no console para gerenciamento
 * de alunos, professores, notas e relatórios.
 */
public class Main {

    // Instâncias dos serviços principais
    private static Secretaria secretaria = new Secretaria();
    private static RelatorioAcademico relatorio = new RelatorioAcademico();
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Ponto de entrada da aplicação.
     *
     * @param args argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("  SISTEMA DE GESTÃO ACADÊMICA v1.0");
        System.out.println("  Bem-vindo(a)!");
        System.out.println("=========================================");

        int opcao = -1;

        // Loop principal do menu
        while (opcao != 7) {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    cadastrarAluno();
                    break;
                case 2:
                    cadastrarProfessorOuCoordenador();
                    break;
                case 3:
                    lancarNotasAluno();
                    break;
                case 4:
                    secretaria.listarMembros();
                    break;
                case 5:
                    exibirEstatisticas();
                    break;
                case 6:
                    acessoAdministrativo();
                    break;
                case 7:
                    System.out.println("\nEncerrando o sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

    /**
     * Exibe o menu principal no console.
     */
    private static void exibirMenu() {
        System.out.println("\n----- MENU PRINCIPAL -----");
        System.out.println("1. Cadastrar Aluno");
        System.out.println("2. Cadastrar Professor / Coordenador");
        System.out.println("3. Lançar Notas de Aluno");
        System.out.println("4. Listar Comunidade Acadêmica");
        System.out.println("5. Exibir Estatísticas (Média Geral)");
        System.out.println("6. Acesso Administrativo (Login)");
        System.out.println("7. Sair");
        System.out.println("--------------------------");
    }

    /**
     * Fluxo interativo para cadastrar um novo aluno.
     */
    private static void cadastrarAluno() {
        System.out.println("\n--- Cadastrar Aluno ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine().trim();

        System.out.print("E-mail: ");
        String email = scanner.nextLine().trim();

        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine().trim();

        // Verifica se já existe aluno com a mesma matrícula
        if (secretaria.localizarAluno(matricula) != null
                && secretaria.localizarAluno(matricula).getMatricula().equalsIgnoreCase(matricula)) {
            System.out.println("Já existe um aluno cadastrado com a matrícula: " + matricula);
            return;
        }

        Aluno aluno = new Aluno(nome, cpf, email, matricula);
        secretaria.cadastrarAluno(aluno);
        // Adiciona automaticamente ao relatório acadêmico
        relatorio.adicionarDados(aluno);
    }

    /**
     * Fluxo interativo para cadastrar um Professor ou Coordenador.
     */
    private static void cadastrarProfessorOuCoordenador() {
        System.out.println("\n--- Cadastrar Professor / Coordenador ---");
        System.out.println("Tipo:");
        System.out.println("1. Professor");
        System.out.println("2. Coordenador");
        int tipo = lerInteiro("Escolha o tipo: ");

        if (tipo != 1 && tipo != 2) {
            System.out.println("Tipo inválido.");
            return;
        }

        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine().trim();

        System.out.print("E-mail: ");
        String email = scanner.nextLine().trim();

        System.out.print("SIAPE: ");
        String siape = scanner.nextLine().trim();

        System.out.print("Senha de acesso: ");
        String senha = scanner.nextLine().trim();

        if (tipo == 1) {
            Professor professor = new Professor(nome, cpf, email, siape, senha);
            secretaria.cadastrarProfessor(professor);
        } else {
            Coordenador coordenador = new Coordenador(nome, cpf, email, siape, senha);
            secretaria.cadastrarProfessor(coordenador);
        }
    }

    /**
     * Fluxo interativo para lançar uma nota para um aluno.
     * O aluno pode ser localizado por matrícula ou nome.
     */
    private static void lancarNotasAluno() {
        System.out.println("\n--- Lançar Notas ---");
        System.out.print("Informe a matrícula ou nome do aluno: ");
        String criterio = scanner.nextLine().trim();

        Aluno aluno = secretaria.localizarAluno(criterio);
        if (aluno == null) {
            System.out.println("Aluno não encontrado: \"" + criterio + "\"");
            return;
        }

        System.out.println("Aluno encontrado: " + aluno.getNome()
                + " (Matrícula: " + aluno.getMatricula() + ")");

        double nota = lerDouble("Digite a nota (0.0 a 10.0): ");
        secretaria.lancarNotas(aluno.getMatricula(), nota);
    }

    /**
     * Exibe as estatísticas da comunidade acadêmica via RelatorioAcademico.
     * Mostra a média geral de todos os alunos cadastrados.
     */
    private static void exibirEstatisticas() {
        System.out.println("\n--- Estatísticas Acadêmicas ---");
        relatorio.exibirMediaGeral();
    }

    /**
     * Fluxo de acesso administrativo: testa o login de um professor ou coordenador.
     * Busca pelo SIAPE informado e verifica a senha.
     */
    private static void acessoAdministrativo() {
        System.out.println("\n--- Acesso Administrativo ---");
        System.out.print("Informe o SIAPE: ");
        String siape = scanner.nextLine().trim();

        // Busca o professor ou coordenador pelo SIAPE na lista de membros
        Autenticavel autenticavel = null;
        String nomeEncontrado = "";
        for (Pessoa pessoa : secretaria.getMembros()) {
            if (pessoa instanceof Professor) {
                Professor prof = (Professor) pessoa;
                if (prof.getSiape().equalsIgnoreCase(siape)) {
                    autenticavel = prof;
                    nomeEncontrado = prof.getNome();
                    break;
                }
            }
        }

        if (autenticavel == null) {
            System.out.println("Nenhum professor/coordenador encontrado com SIAPE: " + siape);
            return;
        }

        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine().trim();

        if (autenticavel.login(senha)) {
            System.out.println("Autenticação bem-sucedida! Bem-vindo(a), " + nomeEncontrado + ".");
        } else {
            System.out.println("Senha incorreta. Acesso negado.");
        }
    }

    /**
     * Lê um número inteiro do console com tratamento de entrada inválida.
     *
     * @param mensagem a mensagem a ser exibida ao usuário
     * @return o inteiro lido
     */
    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String linha = scanner.nextLine().trim();
            try {
                return Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
            }
        }
    }

    /**
     * Lê um número decimal (double) do console com tratamento de entrada inválida.
     *
     * @param mensagem a mensagem a ser exibida ao usuário
     * @return o double lido
     */
    private static double lerDouble(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String linha = scanner.nextLine().trim().replace(",", ".");
            try {
                return Double.parseDouble(linha);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número (ex: 7.5).");
            }
        }
    }
}
