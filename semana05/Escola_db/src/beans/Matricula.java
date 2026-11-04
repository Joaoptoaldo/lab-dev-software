package beans;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Entidade que representa a Matrícula de um Aluno com um Professor em uma determinada data.
 * Demonstra:
 * - Associação entre Objetos: Matricula mantém referências diretas para instâncias de Aluno e Professor.
 * - Agregação: Aluno e Professor possuem ciclo de vida independente da Matrícula.
 * - Encapsulamento: atributos privados, construtores sobrecarregados e métodos de acesso.
 */
public class Matricula {
    private int id;
    private Aluno aluno;           // Associação: referência a um objeto Aluno
    private Professor professor;   // Associação: referência a um objeto Professor
    private LocalDate dataMatricula;

    // Construtor padrão
    public Matricula() {
    }

    // Sobrecarga de construtor 1: Utilizado para novo cadastro (sem id)
    public Matricula(Aluno aluno, Professor professor, LocalDate dataMatricula) {
        this.aluno = aluno;
        this.professor = professor;
        this.dataMatricula = dataMatricula;
    }

    // Sobrecarga de construtor 2: Utilizado na leitura do banco de dados (com id)
    public Matricula(int id, Aluno aluno, Professor professor, LocalDate dataMatricula) {
        this.id = id;
        this.aluno = aluno;
        this.professor = professor;
        this.dataMatricula = dataMatricula;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public LocalDate getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDate dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    @Override
    public String toString() {
        String alunoNome = (aluno != null) ? aluno.getNome() : "Aluno indefinido";
        String profNome = (professor != null) ? professor.getNome() : "Professor indefinido";
        return "Matrícula #" + id + ": " + alunoNome + " com Prof. " + profNome + " (" + dataMatricula + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Matricula other = (Matricula) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

