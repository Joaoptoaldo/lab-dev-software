/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import beans.Aluno;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private Conexao conexao;
    private Connection conn;

    public AlunoDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(Aluno aluno) {
        try {
            String sql = "INSERT INTO alunos(nome, idade, curso) VALUES (?,?,?)";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setString(3, aluno.getCurso());

            stmt.execute();

        } catch (SQLException ex) {
            System.out.println("Erro ao inserir aluno: " + ex.getMessage());
        }
    }

    public Aluno getAluno(int id) {
        String sql = "SELECT * FROM alunos WHERE id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Aluno aluno = new Aluno();

                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setIdade(rs.getInt("idade"));
                aluno.setCurso(rs.getString("curso"));

                return aluno;
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao consultar aluno: " + ex.getMessage());
        }

        return null;
    }

    public void editar(Aluno aluno) {
        try {
            String sql =
                    "UPDATE alunos SET nome = ?, idade = ?, curso = ? WHERE id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setString(3, aluno.getCurso());
            stmt.setInt(4, aluno.getId());

            stmt.execute();

        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar aluno: " + ex.getMessage());
        }
    }

    public List<Object[]> listarPorNome(String nome) {
        List<Object[]> alunos = new ArrayList<>();
        String sql = "SELECT id, nome, idade, curso FROM alunos WHERE nome LIKE ? ORDER BY nome";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                alunos.add(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getInt("idade"),
                    rs.getString("curso")
                });
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar alunos: " + ex.getMessage());
        }

        return alunos;
    }
}