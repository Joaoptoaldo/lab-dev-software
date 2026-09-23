/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetodb;

import conexao.Conexao;
import beans.Pessoa;
import dao.PessoaDAO;

/**
 *
 * @author laboratorio
 */
public class Main {
    public static void main(String[] args) {
        Conexao c = new Conexao();
        c.getConexao();
        
        Pessoa p = new Pessoa();
        p.setNome("João Pedro");
        p.setIdioma("Portugues");
        p.setSexo("M");
        PessoaDAO pdao = new PessoaDAO();
        pdao.inserir(p);
    }
}
