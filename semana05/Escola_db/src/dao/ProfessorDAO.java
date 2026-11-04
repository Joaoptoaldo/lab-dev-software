package dao;

import beans.Professor;
import conexao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) para a entidade Professor.
 * Responsável pelo acesso e persistência no banco de dados MySQL:
 * - Inserção (Create)
 * - Consulta individual e listagens (Read)
 * - Atualização (Update)
 * - Exclusão (Delete)
 */
public class ProfessorDAO {
    private final Conexao conexao;

    public ProfessorDAO() {
        this.conexao = new Conexao();
    }

    /**
     * Insere um novo professor no banco de dados.
     */
    public boolean inserir(Professor professor) {
        String sql = "INSERT INTO professores (nome, idade, disciplina) VALUES (?, ?, ?)";

        try (Connection conn = conexao.getConexao()) {
            if (conn == null) return false;

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, professor.getNome());
                stmt.setInt(2, professor.getIdade());
                stmt.setString(3, professor.getDisciplina());

                stmt.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao inserir professor: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Consulta um professor pelo ID.
     */
    public Professor getProfessor(int id) {
        String sql = "SELECT id, nome, idade, disciplina FROM professores WHERE id = ?";

        try (Connection conn = conexao.getConexao()) {
            if (conn == null) return null;

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new Professor(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getInt("idade"),
                            rs.getString("disciplina")
                        );
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao consultar professor por ID: " + ex.getMessage());
        }
        return null;
    }

    /**
     * Atualiza os dados de um professor existente.
     */
    public boolean editar(Professor professor) {
        String sql = "UPDATE professores SET nome = ?, idade = ?, disciplina = ? WHERE id = ?";

        try (Connection conn = conexao.getConexao()) {
            if (conn == null) return false;

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, professor.getNome());
                stmt.setInt(2, professor.getIdade());
                stmt.setString(3, professor.getDisciplina());
                stmt.setInt(4, professor.getId());

                stmt.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar professor: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Exclui um professor pelo ID.
     */
    public boolean excluir(int id) {
        String sql = "DELETE FROM professores WHERE id = ?";

        try (Connection conn = conexao.getConexao()) {
            if (conn == null) return false;

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao excluir professor: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Retorna a lista de todos os professores cadastrados.
     */
    public List<Professor> listarTodos() {
        return listarPorNome("");
    }

    /**
     * Retorna lista de professores filtrados por nome, ordenados alfabeticamente.
     */
    public List<Professor> listarPorNome(String nome) {
        List<Professor> professores = new ArrayList<>();
        String sql = "SELECT id, nome, idade, disciplina FROM professores WHERE nome LIKE ? ORDER BY nome";

        try (Connection conn = conexao.getConexao()) {
            if (conn == null) return professores;

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, "%" + (nome == null ? "" : nome) + "%");

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Professor prof = new Professor(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getInt("idade"),
                            rs.getString("disciplina")
                        );
                        professores.add(prof);
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao listar professores: " + ex.getMessage());
        }

        return professores;
    }
}
