/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gerenciadorbiblioteca;

/**
 *
 * @author laboratorio
 * Classe que representa um livro da biblioteca
 */
public class Livro {
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private String tipo;
    private String categorias;
    private String status;

    /**
     * construtor da classe Livro
     * @param titulo
     * @param autor
     * @param anoPublicacao
     * @param tipo
     * @param categorias
     * @param status 
     */
    public Livro(String titulo, String autor, int anoPublicacao, String tipo, String categorias, String status) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.tipo = tipo;
        this.categorias = categorias;
        this.status = status;
    }

    // ----
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }
    
    // -----------

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCategorias() {
        return categorias;
    }

    public void setCategorias(String categorias) {
        this.categorias = categorias;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return titulo + ";" + autor + ";" + anoPublicacao + ";" + tipo + ";" + categorias + ";" + status;
    }
    
}
