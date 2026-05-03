package br.edu.instituicao.service;

import br.edu.instituicao.model.Aluno;
import br.edu.instituicao.model.Pessoa;
import br.edu.instituicao.model.Professor;

import java.util.ArrayList;


public class Secretaria {

    // Lista geral de membros (alunos e professores/coordenadores)
    private ArrayList<Pessoa> membros;

    /**
     * Construtor padrão. Inicializa a lista de membros vazia.
     */
    public Secretaria() {
        this.membros = new ArrayList<>();
    }

    // ---- Getters e Setters ----

    /**
     * Retorna a lista de membros cadastrados.
     *
     * @return ArrayList de Pessoa
     */
    public ArrayList<Pessoa> getMembros() {
        return membros;
    }

    /**
     * Define a lista de membros cadastrados.
     *
     * @param membros a nova lista de membros
     */
    public void setMembros(ArrayList<Pessoa> membros) {
        this.membros = membros;
    }

    /**
     * Cadastra um aluno na lista de membros da secretaria.
     *
     * @param aluno o aluno a ser cadastrado
     */
    public void cadastrarAluno(Aluno aluno) {
        membros.add(aluno);
        System.out.println("Aluno cadastrado com sucesso: " + aluno.getNome()
                + " (Matrícula: " + aluno.getMatricula() + ")");
    }

    /**
     * Cadastra um professor (ou coordenador) na lista de membros.
     *
     * @param professor o professor a ser cadastrado
     */
    public void cadastrarProfessor(Professor professor) {
        membros.add(professor);
        System.out.println("Professor cadastrado com sucesso: " + professor.getNome()
                + " (SIAPE: " + professor.getSiape() + ")");
    }

    /**
     * Lista todos os membros cadastrados na comunidade acadêmica.
     * Exibe uma mensagem caso não haja membros cadastrados.
     */
    public void listarMembros() {
        if (membros.isEmpty()) {
            System.out.println("Nenhum membro cadastrado.");
            return;
        }
        System.out.println("\n===== COMUNIDADE ACADÊMICA =====");
        for (int i = 0; i < membros.size(); i++) {
            System.out.println((i + 1) + ". " + membros.get(i));
        }
        System.out.println("================================\n");
    }

    /**
     * Localiza um aluno por matrícula ou por nome (busca parcial, ignora maiúsculas/minúsculas).
     * Retorna o primeiro aluno encontrado que corresponda ao critério.
     *
     * @param criterio a matrícula exata ou parte do nome do aluno
     * @return o Aluno encontrado, ou null se não encontrado
     */
    public Aluno localizarAluno(String criterio) {
        for (Pessoa pessoa : membros) {
            if (pessoa instanceof Aluno) {
                Aluno aluno = (Aluno) pessoa;
                // Busca por matrícula exata ou por nome (parcial, sem distinção de maiúsculas)
                if (aluno.getMatricula().equalsIgnoreCase(criterio)
                        || aluno.getNome().toLowerCase().contains(criterio.toLowerCase())) {
                    return aluno;
                }
            }
        }
        return null; // Aluno não encontrado
    }

    /**
     * Lança uma nota para um aluno localizado pelo critério informado.
     * Exibe mensagem de sucesso ou erro conforme o resultado.
     *
     * @param criterio a matrícula ou nome do aluno
     * @param nota     a nota a ser lançada (deve estar entre 0.0 e 10.0)
     */
    public void lancarNotas(String criterio, double nota) {
        Aluno aluno = localizarAluno(criterio);
        if (aluno == null) {
            System.out.println("Aluno não encontrado para o critério: \"" + criterio + "\"");
            return;
        }
        if (nota < 0.0 || nota > 10.0) {
            System.out.println("Nota inválida. A nota deve estar entre 0.0 e 10.0.");
            return;
        }
        aluno.adicionarNota(nota);
        System.out.printf("Nota %.2f lançada com sucesso para %s (Matrícula: %s).%n",
                nota, aluno.getNome(), aluno.getMatricula());
    }
}
