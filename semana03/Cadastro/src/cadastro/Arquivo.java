/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastro;

import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author laboratorio
 */
public class Arquivo {
    private FileWriter arqW;
    private BufferedWriter escritor;
    
    private FileReader arqR;
    private BufferedReader leitor;
    
    private List<Aluno> listaAlunos;
    public String nomeArquivo;
    
    public Arquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        listaAlunos = new ArrayList<>();
    }
    
    public List<Aluno> lerArquivo() {
        listaAlunos.clear();
        
        try {
            arqR = new FileReader(nomeArquivo + ".txt");
            leitor = new BufferedReader(arqR);
            
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] campos = linha.split(";");
                
                if(campos.length >= 9) {
                    Aluno a = new Aluno(
                        campos[0], campos[1], campos[2], 
                        campos[3], campos[4], campos[5], 
                        campos[6], campos[7], campos[8]
                    );
                    listaAlunos.add(a);
                }
            }
            
            leitor.close();
            arqR.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo ainda não existe. Iniciando com lista vazia.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return listaAlunos;
    }
    
    public List<Aluno> getListaAlunos() {
        return listaAlunos;
    }
    
    // Grava toda a lista no arquivo
    public void gravarArquivo(List<Aluno> listaParaGravar) {
        try {
            arqW = new FileWriter(nomeArquivo + ".txt", false);
            escritor = new BufferedWriter(arqW);
            
            for (Aluno a : listaParaGravar) {
                escritor.write(a.toString());
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
