package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/ClienteDB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String SENHA = "laboratorio";

    public Connection getConexao() {
        try {
            Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
            return conn;
        } catch (SQLException e) {
            System.err.println("Erro ao conectar no banco de dados: " + e.getMessage());
            return null;
        }
    }
}