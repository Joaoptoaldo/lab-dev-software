/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto04;

//import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException; 


/**
 *
 * @author laboratorio
 */
public class Arquivo {
    private FileWriter arqW;
    private BufferedWriter escritor;
    
    private FileReader arqR;
    private BufferedReader leitor;
    
    private List<Pessoa> listaPessoas;
    
    public String nomeArquivo;
    
    /**
     * construtor da Classe Arquivo
     * @param nomeArquivo arquivo para ser lido
     */
    public Arquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        listaPessoas = new ArrayList<>();
    }
    
    public List<Pessoa> lerArquivo() {
        
        listaPessoas.clear();
        
        try {
            arqR = new FileReader(nomeArquivo + ".txt");
            leitor = new BufferedReader(arqR);
            
            String linha;
            
            while ((linha = leitor.readLine()) != null) {
                
                String[] campos = linha.split(";");
                
                String nome = campos[0];
                char sexo = campos[1].charAt(0);
                String idioma = campos[2];
                
                Pessoa p = new Pessoa (nome, sexo, idioma);
                
                listaPessoas.add(p);
                
            }
            
            leitor.close();
            arqR.close();
            
        } catch (FileNotFoundException e) {
            
            // começa com a lista vazia
            System.out.println("Arquivo ainda não existe.");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return listaPessoas;
    }
    
    public List<Pessoa> getListaPessoa() {
        return listaPessoas;
    }
    
    
    // grava toda a lista no arquivo
    public void gravarArquivo() {
        
        try {
            arqW = new FileWriter(nomeArquivo + ".txt", false);
            escritor = new BufferedWriter(arqW);
            
            for (Pessoa p : listaPessoas) {
                
                escritor.write(
                    p.nome + ";" +
                    p.sexo + ";" +
                    p.idioma 
                );
                
                escritor.newLine();
            }
            
            escritor.close();
            arqW.close();
            
            System.out.println("Lista salva no arquivo!");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
