package br.edu.instituicao.service;

import br.edu.instituicao.model.Aluno;
import br.edu.instituicao.model.Pessoa;
import br.edu.instituicao.model.Professor;
import br.edu.instituicao.observer.Observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Secretaria {

    private static Secretaria instance;

    private ArrayList<Pessoa> membros;

    private final List<Observer> observadores = new ArrayList<>();

    private Secretaria() {
        this.membros = new ArrayList<>();
    }

    public static Secretaria getInstance() {
        if (instance == null) {
            instance = new Secretaria();
        }
        return instance;
    }

    public void registrarObservador(Observer observador) {
        observadores.add(observador);
    }

    public List<Observer> getObservadores() {
        return Collections.unmodifiableList(observadores);
    }

    private void notificarCadastro(Pessoa novaPessoa) {
        for (Observer observador : observadores) {
            observador.update(novaPessoa);
        }
    }

    public ArrayList<Pessoa> getMembros() {
        return membros;
    }

    public void setMembros(ArrayList<Pessoa> membros) {
        this.membros = membros;
    }

    public void cadastrarAluno(Aluno aluno) {
        membros.add(aluno);
        System.out.println("Aluno cadastrado com sucesso: " + aluno.getNome()
                + " (Matrícula: " + aluno.getMatricula() + ")");
        notificarCadastro(aluno);
    }

    public void cadastrarProfessor(Professor professor) {
        membros.add(professor);
        System.out.println("Professor cadastrado com sucesso: " + professor.getNome()
                + " (SIAPE: " + professor.getSiape() + ")");
        notificarCadastro(professor);
    }

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

    public Aluno localizarAluno(String criterio) {
        for (Pessoa pessoa : membros) {
            if (pessoa instanceof Aluno) {
                Aluno aluno = (Aluno) pessoa;
                if (aluno.getMatricula().equalsIgnoreCase(criterio)
                        || aluno.getNome().toLowerCase().contains(criterio.toLowerCase())) {
                    return aluno;
                }
            }
        }
        return null;
    }

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
