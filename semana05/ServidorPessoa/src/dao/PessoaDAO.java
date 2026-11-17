package dao;

import beans.Pessoa;
import conexao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {
    private final Conexao conexao;

    public PessoaDAO() {
        this.conexao = new Conexao();
    }

    public boolean inserir(Pessoa pessoa) {
        String sql = "INSERT INTO pessoa (nome, sexo, idioma) VALUES (?, ?, ?)";

        try (Connection conn = conexao.getConexao()) {
            if (conn == null) {
                System.err.println("Falha na conexão com o banco de dados.");
                return false;
            }
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, pessoa.getNome());
                stmt.setString(2, pessoa.getSexo());
                stmt.setString(3, pessoa.getIdioma());

                stmt.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao inserir pessoa: " + ex.getMessage());
            return false;
        }
    }

    public Pessoa getPessoa(int id) {
        String sql = "SELECT id, nome, sexo, idioma FROM pessoa WHERE id = ?";

        try (Connection conn = conexao.getConexao()) {
            if (conn == null) return null;

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new Pessoa(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("sexo"),
                            rs.getString("idioma")
                        );
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao consultar pessoa por ID: " + ex.getMessage());
        }
        return null;
    }

    public boolean editar(Pessoa pessoa) {
        String sql = "UPDATE pessoa SET nome = ?, sexo = ?, idioma = ? WHERE id = ?";

        try (Connection conn = conexao.getConexao()) {
            if (conn == null) return false;

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, pessoa.getNome());
                stmt.setString(2, pessoa.getSexo());
                stmt.setString(3, pessoa.getIdioma());
                stmt.setInt(4, pessoa.getId());

                stmt.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar pessoa: " + ex.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM pessoa WHERE id = ?";

        try (Connection conn = conexao.getConexao()) {
            if (conn == null) return false;

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao excluir pessoa: " + ex.getMessage());
            return false;
        }
    }

    public List<Pessoa> listarTodos() {
        return listarPorNome("");
    }

    public List<Pessoa> listarPorNome(String nome) {
        List<Pessoa> pessoas = new ArrayList<>();
        String sql = "SELECT id, nome, sexo, idioma FROM pessoa WHERE nome LIKE ? ORDER BY nome";

        try (Connection conn = conexao.getConexao()) {
            if (conn == null) return pessoas;

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, "%" + (nome == null ? "" : nome) + "%");

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Pessoa pessoa = new Pessoa(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("sexo"),
                            rs.getString("idioma")
                        );
                        pessoas.add(pessoa);
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao listar pessoas: " + ex.getMessage());
        }

        return pessoas;
    }
}