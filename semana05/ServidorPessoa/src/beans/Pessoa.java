package beans;

import java.io.Serializable;

public class Pessoa implements Serializable {
    private int id;
    private String nome;
    private String sexo;
    private String idioma;

    public Pessoa() {
    }

    public Pessoa(String nome, String sexo, String idioma) {
        this.nome = nome;
        this.sexo = sexo;
        this.idioma = idioma;
    }

    public Pessoa(int id, String nome, String sexo, String idioma) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.idioma = idioma;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    @Override
    public String toString() {
        return this.id + " - " + this.nome;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pessoa other = (Pessoa) obj;
        return this.id == other.id;
    }
}