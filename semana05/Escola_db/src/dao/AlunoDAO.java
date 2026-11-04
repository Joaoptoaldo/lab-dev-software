package dao;

import beans.Aluno;
import conexao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) para a entidade Aluno.
 * Responsável exclusivamente pelo acesso e operações no banco de dados MySQL:
 * - Inserção (Create)
 * - Consulta individual e listagens (Read)
 * - Atualização (Update)
 * - Exclusão (Delete)
 */
public class AlunoDAO {
    private final Conexao conexao;

    public AlunoDAO() {
        this.conexao = new Conexao();
    }

    /**
     * Insere um novo aluno no banco de dados.
     */
    public boolean inserir(Aluno aluno) {
        String sql = "INSERT INTO alunos (nome, idade, curso) VALUES (?, ?, ?)";

        try (Connection conn = conexao.getConexao()) {
            if (conn == null) {
                System.err.println("Falha na conexão com o banco de dados.");
                return false;
            }
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, aluno.getNome());
                stmt.setInt(2, aluno.getIdade());
                stmt.setString(3, aluno.getCurso());

                stmt.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao inserir aluno: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Consulta um aluno pelo seu ID primário.
     */
    public Aluno getAluno(int id) {
        String sql = "SELECT id, nome, idade, curso FROM alunos WHERE id = ?";

        try (Connection conn = conexao.getConexao()) {
            if (conn == null) return null;

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new Aluno(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getInt("idade"),
                            rs.getString("curso")
                        );
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao consultar aluno por ID: " + ex.getMessage());
        }
        return null;
    }

    /**
     * Atualiza os dados de um aluno existente.
     */
    public boolean editar(Aluno aluno) {
        String sql = "UPDATE alunos SET nome = ?, idade = ?, curso = ? WHERE id = ?";

        try (Connection conn = conexao.getConexao()) {
            if (conn == null) return false;

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, aluno.getNome());
                stmt.setInt(2, aluno.getIdade());
                stmt.setString(3, aluno.getCurso());
                stmt.setInt(4, aluno.getId());

                stmt.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar aluno: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Exclui um aluno pelo ID.
     */
    public boolean excluir(int id) {
        String sql = "DELETE FROM alunos WHERE id = ?";

        try (Connection conn = conexao.getConexao()) {
            if (conn == null) return false;

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao excluir aluno: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Retorna a lista de todos os alunos cadastrados.
     */
    public List<Aluno> listarTodos() {
        return listarPorNome("");
    }

    /**
     * Retorna lista de alunos filtrados por parte do nome, ordenados alfabeticamente.
     */
    public List<Aluno> listarPorNome(String nome) {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT id, nome, idade, curso FROM alunos WHERE nome LIKE ? ORDER BY nome";

        try (Connection conn = conexao.getConexao()) {
            if (conn == null) return alunos;

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, "%" + (nome == null ? "" : nome) + "%");

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Aluno aluno = new Aluno(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getInt("idade"),
                            rs.getString("curso")
                        );
                        alunos.add(aluno);
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao listar alunos: " + ex.getMessage());
        }

        return alunos;
    }
}
