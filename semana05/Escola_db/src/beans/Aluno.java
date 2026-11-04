package beans;

import java.util.Objects;

/**
 * Entidade que representa um Aluno no sistema escolar.
 * Demonstra:
 * - Encapsulamento: atributos privados e métodos de acesso (getters/setters).
 * - Construtores e Sobrecarga: construtor padrão, para inserção (sem id) e completo.
 * - Sobrescrita de métodos: toString() e equals/hashCode para uso em JComboBox.
 */
public class Aluno {
    private int id;
    private String nome;
    private int idade;
    private String curso;

    // Construtor padrão (sem parâmetros)
    public Aluno() {
    }

    // Sobrecarga de construtor 1: Utilizado na tela para novos cadastros (antes de ter o ID gerado pelo banco)
    public Aluno(String nome, int idade, String curso) {
        this.nome = nome;
        this.idade = idade;
        this.curso = curso;
    }

    // Sobrecarga de construtor 2: Utilizado pelo DAO na leitura do banco de dados (com ID)
    public Aluno(int id, String nome, int idade, String curso) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.curso = curso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    // Sobrescrita do toString(): permite que o JComboBox renderize o aluno de forma legível
    @Override
    public String toString() {
        return this.id > 0 ? this.id + " - " + this.nome + " (" + this.curso + ")" : this.nome;
    }

    // equals e hashCode baseados no ID: permite que o JComboBox encontre o item selecionado
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Aluno other = (Aluno) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
