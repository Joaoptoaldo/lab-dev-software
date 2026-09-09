package estudo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arquivo {

    private FileReader arqR;
    private BufferedReader leitor;
    private FileWriter arqW;
    private BufferedWriter escritor;
    private List<Produto> listaProdutos;
    private String nomeArquivo;

    public Arquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        listaProdutos = new ArrayList<>();
    }

    public List<Produto> lerArquivo() {
        listaProdutos.clear();

        try {
            arqR = new FileReader(nomeArquivo + ".txt");
            leitor = new BufferedReader(arqR);

            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] campos = linha.split(";");

                if (campos.length >= 5) {
                    String nome = campos[0];
                    String descricao = campos[1];
                    double preco = Double.parseDouble(campos[2]);
                    int quantidade = Integer.parseInt(campos[3]);
                    String categoria = campos[4];

                    Produto p = new Produto(nome, descricao, preco, quantidade, categoria);
                    listaProdutos.add(p);
                }
            }

            leitor.close();
            arqR.close();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo ainda nao existe. Iniciando com lista vazia.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaProdutos;
    }

    public void gravarArquivo(List<Produto> listaParaGravar) {
        try {
            arqW = new FileWriter(nomeArquivo + ".txt", false);
            escritor = new BufferedWriter(arqW);

            for (Produto p : listaParaGravar) {
                escritor.write(p.toString());
                escritor.newLine();
            }

            escritor.close();
            arqW.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
