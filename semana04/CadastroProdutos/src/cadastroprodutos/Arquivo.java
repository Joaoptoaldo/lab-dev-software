package cadastroprodutos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** Responsável por ler e escrever produtos no arquivo .txt */
public class Arquivo {

    private FileReader arqR;
    private BufferedReader leitor;
    private FileWriter arqW;
    private BufferedWriter escritor;
    private List<Produto> listaProdutos;
    private String nomeArquivo;
    private int proximoId;

    public Arquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        this.listaProdutos = new ArrayList<>();
        this.proximoId = 1;
    }

    /**
     * método para ler os produtos do arquivo .txt
     * @return a lista de produtos lida do arquivo
     */
    public List<Produto> lerArquivo() {
        listaProdutos.clear();
        proximoId = 1;

        try {
            arqR = new FileReader(nomeArquivo + ".txt");
            leitor = new BufferedReader(arqR);

            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] campos = linha.split(";");

                if (campos.length >= 6) {
                    int id = Integer.parseInt(campos[0]);
                    String nome = campos[1];
                    String descricao = campos[2];
                    double preco = Double.parseDouble(campos[3]);
                    int quantidade = Integer.parseInt(campos[4]);
                    String categoria = campos[5];

                    Produto p = new Produto(id, nome, descricao, preco, quantidade, categoria);
                    listaProdutos.add(p);

                    if (id >= proximoId) {
                        proximoId = id + 1;
                    }
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

        return listaProdutos;
    }

    /**
     * método para gravar a lista de produtos no arquivo .txt       
     * @param listaParaGravar a lista de produtos a ser gravada
     */
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

    /**
     * gera o próximo ID para um novo produto
     * @return o próximo ID disponível
     */
    public int gerarProximoId() {
        return proximoId++;
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }
}
