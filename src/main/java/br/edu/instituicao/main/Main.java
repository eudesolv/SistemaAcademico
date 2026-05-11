package br.edu.instituicao.main;

import br.edu.instituicao.factory.AlunoFactory;
import br.edu.instituicao.factory.FabricaDocentes;
import br.edu.instituicao.factory.PessoaFactory;
import br.edu.instituicao.interfaces.Autenticavel;
import br.edu.instituicao.model.Aluno;
import br.edu.instituicao.model.Pessoa;
import br.edu.instituicao.model.Professor;
import br.edu.instituicao.observer.NotificadorBiblioteca;
import br.edu.instituicao.observer.NotificadorFinanceiro;
import br.edu.instituicao.service.RelatorioAcademico;
import br.edu.instituicao.service.Secretaria;

import java.util.Scanner;

public class Main {

    private static Secretaria secretaria = Secretaria.getInstance();
    private static final AlunoFactory FABRICA_ALUNO = new AlunoFactory();
    private static RelatorioAcademico relatorio = new RelatorioAcademico();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("  SISTEMA DE GESTÃO ACADÊMICA AFYA ");
        System.out.println("  Bem-vindo(a)!");
        System.out.println("=========================================");

        secretaria.registrarObservador(new NotificadorFinanceiro());
        secretaria.registrarObservador(new NotificadorBiblioteca());

        int opcao = -1;

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

        if (secretaria.localizarAluno(matricula) != null
                && secretaria.localizarAluno(matricula).getMatricula().equalsIgnoreCase(matricula)) {
            System.out.println("Já existe um aluno cadastrado com a matrícula: " + matricula);
            return;
        }

        Pessoa criado = FABRICA_ALUNO.criarAluno(nome, cpf, email, matricula);
        Aluno aluno = (Aluno) criado;
        secretaria.cadastrarAluno(aluno);
        relatorio.adicionarDados(aluno);
    }

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

        PessoaFactory fabrica = FabricaDocentes.obter(tipo);
        Pessoa docente = fabrica.criarDocente(nome, cpf, email, siape, senha);
        secretaria.cadastrarProfessor((Professor) docente);
    }

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

    private static void exibirEstatisticas() {
        System.out.println("\n--- Estatísticas Acadêmicas ---");
        relatorio.exibirMediaGeral();
    }

    private static void acessoAdministrativo() {
        System.out.println("\n--- Acesso Administrativo ---");
        System.out.print("Informe o SIAPE: ");
        String siape = scanner.nextLine().trim();

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
