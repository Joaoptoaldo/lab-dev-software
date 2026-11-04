package beans;

import java.util.Objects;

/**
 * Entidade que representa um Professor no sistema escolar.
 * Demonstra:
 * - Encapsulamento: atributos privados e métodos de acesso (getters/setters).
 * - Construtores e Sobrecarga: construtor padrão, para inserção e completo.
 * - Sobrescrita de métodos: toString() e equals/hashCode para uso em JComboBox.
 */
public class Professor {
    private int id;
    private String nome;
    private int idade;
    private String disciplina;

    // Construtor padrão
    public Professor() {
        
    }

    // Sobrecarga de construtor 1: Utilizado na tela para novos cadastros
    public Professor(String nome, int idade, String disciplina) {
        this.nome = nome;
        this.idade = idade;
        this.disciplina = disciplina;
    }

    // Sobrecarga de construtor 2: Utilizado pelo DAO na leitura do banco
    public Professor(int id, String nome, int idade, String disciplina) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.disciplina = disciplina;
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

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    // Sobrescrita do toString(): exibe o professor de forma clara em listas e JComboBox
    @Override
    public String toString() {
        return this.id > 0 ? this.id + " - " + this.nome + " (" + this.disciplina + ")" : this.nome;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Professor other = (Professor) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
