package estudo;

public class Produto {

    private String nome;
    private String descricao;
    private double preco;
    private int quantidade;
    private String categoria;

    public Produto(String nome, String descricao, double preco, int quantidade, String categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.categoria = categoria;
    }

    public String getNome() { 
        return nome; 
    }

    public String getDescricao() { 
        return descricao; 
    }

    public double getPreco() { 
        return preco; 
    }

    public int getQuantidade() { 
        return quantidade; 
    }

    public String getCategoria() { 
        return categoria; 
    }

    @Override
    public String toString() {
        return nome + ";" + descricao + ";" + preco + ";" + quantidade + ";" + categoria;
    }
}
