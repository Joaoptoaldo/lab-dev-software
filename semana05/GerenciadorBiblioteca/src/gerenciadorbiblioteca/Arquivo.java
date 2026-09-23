/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gerenciadorbiblioteca;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author laboratorio
 * Classe responsavel por ler e escrever no arquivo .txt
 */
public class Arquivo {
    private FileReader arqR;
    private BufferedReader leitor;
    private FileWriter arqW;
    private BufferedWriter escritor;
    private List<Livro> listaLivros;
    private String nomeArquivo;
    private int proximoId;

    public Arquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        this.listaLivros = new ArrayList<>();
    }

    /**
     * método para ler os livros do arquivo .txt
     * @return a lista de produtos lida do arquivo
     */
    public List<Livro> lerArquivo() {
        listaLivros.clear();

        try {
            arqR = new FileReader(nomeArquivo + ".txt");
            leitor = new BufferedReader(arqR);

            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] campos = linha.split(";");

                if (campos.length >= 6) {
                    String titulo = campos[0];
                    String autor = campos[1];
                    int anoPublicacao = Integer.parseInt(campos[2]);
                    String tipo = campos[3];
                    String categorias = campos[4];
                    String status = campos[5];

                    Livro l = new Livro(titulo, autor, anoPublicacao, tipo, categorias, status);
                    listaLivros.add(l);

                }
            }

            leitor.close();
            arqR.close();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo ainda nao existe. Iniciando com lista vazia.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter dados do arquivo.");
        }

        return listaLivros;
    }

    /**
     * método para gravar a lista de livros no arquivo .txt       
     * @param listaParaGravar a lista de livros a ser gravada
     */
    public void gravarArquivo(List<Livro> listaParaGravar) {
        try {
            arqW = new FileWriter(nomeArquivo + ".txt", false);
            escritor = new BufferedWriter(arqW);

            for (Livro l : listaParaGravar) {
                escritor.write(l.toString());
                escritor.newLine();
            }

            escritor.close();
            arqW.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Livro> getListaLivros() {
        return listaLivros;
    }
}
