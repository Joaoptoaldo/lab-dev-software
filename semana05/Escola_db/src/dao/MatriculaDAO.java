package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDAO {
    private final Connection conn;

    public MatriculaDAO() {
        this.conn = new Conexao().getConexao();
    }

    public List<Object[]> listarPorNome(String nome) {
        List<Object[]> matriculas = new ArrayList<>();
        String sql = "SELECT m.id, a.nome AS aluno, p.nome AS professor, "
                + "m.data_matricula FROM matriculas m "
                + "JOIN alunos a ON a.id = m.id_aluno "
                + "JOIN professores p ON p.id = m.id_professor "
                + "WHERE a.nome LIKE ? OR p.nome LIKE ? ORDER BY m.id";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            String filtro = "%" + nome + "%";
            stmt.setString(1, filtro);
            stmt.setString(2, filtro);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                matriculas.add(new Object[]{
                    rs.getInt("id"),
                    rs.getString("aluno"),
                    rs.getString("professor"),
                    rs.getDate("data_matricula")
                });
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar matrículas: " + ex.getMessage());
        }

        return matriculas;
    }
}