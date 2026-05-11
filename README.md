# Sistema de Gestão Acadêmica

Sistema acadêmico em Java para cadastro de alunos, professores e coordenadores, lançamento de notas, relatório de médias e acesso administrativo via CLI.

---

## Estrutura do Projeto

```
Sistema Academico/
├── pom.xml
├── README.md
└── src/
    └── main/
        └── java/
            └── br/
                └── edu/
                    └── instituicao/
                        ├── main/
                        │   └── Main.java
                        ├── model/
                        │   ├── Pessoa.java
                        │   ├── Aluno.java
                        │   ├── Professor.java
                        │   └── Coordenador.java
                        ├── interfaces/
                        │   ├── Avaliavel.java
                        │   └── Autenticavel.java
                        ├── service/
                        │   ├── Secretaria.java
                        │   └── RelatorioAcademico.java
                        ├── factory/
                        │   ├── PessoaFactory.java
                        │   ├── ProfessorFactory.java
                        │   ├── CoordenadorFactory.java
                        │   ├── AlunoFactory.java
                        │   └── FabricaDocentes.java
                        ├── strategy/
                        │   ├── EstrategiaCalculoHoras.java
                        │   ├── RegraPadrao.java
                        │   └── RegraGestor.java
                        └── observer/
                            ├── Observer.java
                            ├── NotificadorFinanceiro.java
                            └── NotificadorBiblioteca.java
```

### Resumo dos pacotes

| Pacote | Papel |
|--------|--------|
| `main` | Ponto de entrada (`Main`) e menu da CLI |
| `model` | Entidades de domínio (`Pessoa` e subclasses) |
| `interfaces` | Contratos `Avaliavel` e `Autenticavel` |
| `service` | `Secretaria` (cadastros e consultas) e `RelatorioAcademico` |
| `factory` | Criação de `Pessoa` / docentes (Factory Method) |
| `strategy` | Regras de cálculo de horas de atividade docente |
| `observer` | Observadores de eventos de cadastro na secretaria |

---

## Pré-requisitos

- [JDK 21](https://openjdk.org/) (conforme `pom.xml`)
- [Apache Maven](https://maven.apache.org/) (para compilar e executar com um comando)

---

## Como compilar e executar (Maven)

No diretório raiz do projeto (onde está o `pom.xml`):

```bash
mvn -q compile
java -cp target/classes br.edu.instituicao.main.Main
```

Os arquivos compilados ficam em `target/classes`, espelhando o pacote `br.edu.instituicao`.

---

## Compilação manual com `javac` (alternativa)

A partir da raiz do repositório:

```bash
javac -d out --release 21 -sourcepath src/main/java src/main/java/br/edu/instituicao/main/Main.java
```

Execução:

```bash
java -cp out br.edu.instituicao.main.Main
```

---

## Por que `Pessoa` é uma classe abstrata?

No modelo da instituição, uma pessoa sempre desempenha um papel concreto (aluno, professor ou coordenador). `Pessoa` abstrata centraliza nome, CPF e e-mail e impede instanciar um “cadastro genérico” sem papel definido. As subclasses especializam comportamento: `Aluno` implementa `Avaliavel`; `Professor` (e `Coordenador`) implementam `Autenticavel`.

---

## Exemplo de execução no console

```
=========================================
  SISTEMA DE GESTÃO ACADÊMICA AFYA
  Bem-vindo(a)!
=========================================

----- MENU PRINCIPAL -----
1. Cadastrar Aluno
2. Cadastrar Professor / Coordenador
3. Lançar Notas de Aluno
4. Listar Comunidade Acadêmica
5. Exibir Estatísticas (Média Geral)
6. Acesso Administrativo (Login)
7. Sair
--------------------------
Escolha uma opção: 1

--- Cadastrar Aluno ---
Nome: Eudes Oliveira
CPF: 123.456.789-00
E-mail: eudes.oliveira@email.com
Matrícula: 2024001
Aluno cadastrado com sucesso: Eudes Oliveira (Matrícula: 2024001)

----- MENU PRINCIPAL -----
...
Escolha uma opção: 3

--- Lançar Notas ---
Informe a matrícula ou nome do aluno: 2024001
Aluno encontrado: Eudes Oliveira (Matrícula: 2024001)
Digite a nota (0.0 a 10.0): 8.5
Nota 8,50 lançada com sucesso para Eudes Oliveira (Matrícula: 2024001).

----- MENU PRINCIPAL -----
...
Escolha uma opção: 5

--- Estatísticas Acadêmicas ---

===== RELATÓRIO ACADÊMICO =====
Nome / Identificador          | Média Final
----------------------------------------------
  Média individual: 8,50
----------------------------------------------
Total de avaliados : 1
Média Geral        : 8,50
===============================================

----- MENU PRINCIPAL -----
...
Escolha uma opção: 6

--- Acesso Administrativo ---
Informe o SIAPE: 1234567
Nenhum professor/coordenador encontrado com SIAPE: 1234567

----- MENU PRINCIPAL -----
...
Escolha uma opção: 7

Encerrando o sistema. Até logo!
```
