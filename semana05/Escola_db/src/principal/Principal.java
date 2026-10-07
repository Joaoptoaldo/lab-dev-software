/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import dao.AlunoDAO;
import dao.ProfessorDAO;
import beans.Aluno;
import beans.Professor;
import conexao.Conexao;

/**
 *
 * @author laboratorio
 */
public class Principal {
    public static void main(String[] args) {
        Conexao c = new Conexao();
        c.getConexao();
        
    }
    
}
