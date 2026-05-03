# Sistema de Gestão Acadêmica

Sistema acadêmico desenvolvido em Java para gerenciamento de alunos, professores, coordenadores, notas e relatórios.

---

## Estrutura de Pacotes

```
src/
└── br/
    └── edu/
        └── instituicao/
            ├── interfaces/
            │   ├── Avaliavel.java
            │   └── Autenticavel.java
            ├── model/
            │   ├── Pessoa.java
            │   ├── Aluno.java
            │   ├── Professor.java
            │   └── Coordenador.java
            ├── service/
            │   ├── RelatorioAcademico.java
            │   └── Secretaria.java
            └── main/
                └── Main.java
```

---

## Como Compilar e Executar

### Pré-requisito

- Java JDK 8 ou superior instalado e configurado no PATH.

### Compilação

A partir do diretório raiz do projeto (onde está este `README.md`), execute:

```bash
javac -d out -sourcepath src src/br/edu/instituicao/main/Main.java
```

> O parâmetro `-d out` instrui o compilador a colocar os `.class` na pasta `out/`.  
> O `-sourcepath src` permite que o compilador resolva automaticamente todas as dependências dentro de `src/`.

Caso prefira compilar todos os arquivos de uma vez:

```bash
# Linux / macOS
find src -name "*.java" | xargs javac -d out

# Windows (PowerShell)
Get-ChildItem -Recurse -Filter "*.java" src | ForEach-Object { $_.FullName } | ForEach-Object { javac -d out $_ }
```

### Execução

Após a compilação, execute a partir do diretório raiz:

```bash
java -cp out br.edu.instituicao.main.Main
```

---

## Por que `Pessoa` é uma Classe Abstrata?

A classe `Pessoa` é declarada como `abstract` porque, no contexto do sistema acadêmico, uma "pessoa genérica" não possui existência independente: toda pessoa que interage com a instituição assume obrigatoriamente um papel concreto — seja como `Aluno`, `Professor` ou `Coordenador`. Tornar `Pessoa` abstrata impede que o sistema instancie um objeto `Pessoa` sem papel definido, o que seria semanticamente incorreto e poderia introduzir dados inconsistentes. Além disso, a abstração força as subclasses a herdarem os atributos e comportamentos comuns (nome, CPF, e-mail) sem duplicação de código, ao mesmo tempo em que cada subclasse pode especializar ou implementar comportamentos próprios — como `Aluno` implementar `Avaliavel` e `Professor` implementar `Autenticavel`. Dessa forma, o design respeita o princípio de que a herança deve modelar uma relação "é um tipo de", e o modificador `abstract` reforça que `Pessoa` é apenas um molde conceitual, nunca um objeto completo por si só.

---

## Exemplo de Execução no Console

```
=========================================
  SISTEMA DE GESTÃO ACADÊMICA 
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
