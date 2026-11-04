package dao;

import beans.Aluno;
import beans.Matricula;
import beans.Professor;
import conexao.Conexao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) para a entidade Matricula.
 * Demonstra a persistência e recuperação de Associações entre Objetos:
 * - Ao gravar: extrai os IDs dos objetos associados (aluno.getId() e professor.getId()).
 * - Ao ler: reconstrói as instâncias de Aluno e Professor associando-as ao objeto Matricula.
 */
public class MatriculaDAO {
    private final Conexao conexao;

    public MatriculaDAO() {
        this.conexao = new Conexao();
    }

    /**
     * Insere uma nova matrícula associando o Aluno e o Professor.
     */
    public boolean inserir(Matricula matricula) {
        String sql = "INSERT INTO matriculas (id_aluno, id_professor, data_matricula) VALUES (?, ?, ?)";

        try (Connection conn = conexao.getConexao()) {
            if (conn == null) return false;

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, matricula.getAluno().getId());
                stmt.setInt(2, matricula.getProfessor().getId());
                stmt.setDate(3, Date.valueOf(matricula.getDataMatricula() != null ? matricula.getDataMatricula() : LocalDate.now()));

                stmt.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao cadastrar matrícula: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Consulta uma matrícula pelo ID reconstruindo as instâncias associadas de Aluno e Professor.
     */
    public Matricula getMatricula(int id) {
        String sql = "SELECT m.id, m.data_matricula, "
                   + "a.id AS a_id, a.nome AS a_nome, a.idade AS a_idade, a.curso AS a_curso, "
                   + "p.id AS p_id, p.nome AS p_nome, p.idade AS p_idade, p.disciplina AS p_disciplina "
                   + "FROM matriculas m "
                   + "JOIN alunos a ON a.id = m.id_aluno "
                   + "JOIN professores p ON p.id = m.id_professor "
                   + "WHERE m.id = ?";

        try (Connection conn = conexao.getConexao()) {
            if (conn == null) return null;

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        Aluno aluno = new Aluno(
                            rs.getInt("a_id"),
                            rs.getString("a_nome"),
                            rs.getInt("a_idade"),
                            rs.getString("a_curso")
                        );

                        Professor prof = new Professor(
                            rs.getInt("p_id"),
                            rs.getString("p_nome"),
                            rs.getInt("p_idade"),
                            rs.getString("p_disciplina")
                        );

                        Date dataSql = rs.getDate("data_matricula");
                        LocalDate data = (dataSql != null) ? dataSql.toLocalDate() : LocalDate.now();

                        return new Matricula(rs.getInt("id"), aluno, prof, data);
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao consultar matrícula por ID: " + ex.getMessage());
        }
        return null;
    }

    /**
     * Atualiza o Aluno, o Professor e a Data de uma Matrícula existente.
     */
    public boolean editar(Matricula matricula) {
        String sql = "UPDATE matriculas SET id_aluno = ?, id_professor = ?, data_matricula = ? WHERE id = ?";

        try (Connection conn = conexao.getConexao()) {
            if (conn == null) return false;

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, matricula.getAluno().getId());
                stmt.setInt(2, matricula.getProfessor().getId());
                stmt.setDate(3, Date.valueOf(matricula.getDataMatricula()));
                stmt.setInt(4, matricula.getId());

                stmt.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar matrícula: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Exclui uma matrícula pelo seu ID primário.
     */
    public boolean excluir(int id) {
        String sql = "DELETE FROM matriculas WHERE id = ?";

        try (Connection conn = conexao.getConexao()) {
            if (conn == null) return false;

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao excluir matrícula: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Retorna todas as matrículas cadastradas.
     */
    public List<Matricula> listarTodos() {
        return listarPorNome("");
    }

    /**
     * Retorna a lista de matrículas filtrando pelo nome do aluno ou do professor.
     */
    public List<Matricula> listarPorNome(String filtro) {
        List<Matricula> matriculas = new ArrayList<>();
        String sql = "SELECT m.id, m.data_matricula, "
                   + "a.id AS a_id, a.nome AS a_nome, a.idade AS a_idade, a.curso AS a_curso, "
                   + "p.id AS p_id, p.nome AS p_nome, p.idade AS p_idade, p.disciplina AS p_disciplina "
                   + "FROM matriculas m "
                   + "JOIN alunos a ON a.id = m.id_aluno "
                   + "JOIN professores p ON p.id = m.id_professor "
                   + "WHERE a.nome LIKE ? OR p.nome LIKE ? "
                   + "ORDER BY m.id";

        try (Connection conn = conexao.getConexao()) {
            if (conn == null) return matriculas;

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                String busca = "%" + (filtro == null ? "" : filtro) + "%";
                stmt.setString(1, busca);
                stmt.setString(2, busca);

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Aluno aluno = new Aluno(
                            rs.getInt("a_id"),
                            rs.getString("a_nome"),
                            rs.getInt("a_idade"),
                            rs.getString("a_curso")
                        );

                        Professor prof = new Professor(
                            rs.getInt("p_id"),
                            rs.getString("p_nome"),
                            rs.getInt("p_idade"),
                            rs.getString("p_disciplina")
                        );

                        Date dataSql = rs.getDate("data_matricula");
                        LocalDate data = (dataSql != null) ? dataSql.toLocalDate() : LocalDate.now();

                        Matricula m = new Matricula(rs.getInt("id"), aluno, prof, data);
                        matriculas.add(m);
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao listar matrículas: " + ex.getMessage());
        }

        return matriculas;
    }
}
