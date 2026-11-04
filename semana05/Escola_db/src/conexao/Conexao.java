package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por gerenciar a conexão com o banco de dados MySQL.
 * Centraliza os dados de acesso (URL, usuário e senha) em um único local,
 * evitando duplicação nos DAOs.
 */
public class Conexao {
    // Parâmetros de conexão com o banco de dados 'escola'
    private static final String URL = "jdbc:mysql://localhost:3306/escola?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String SENHA = "laboratorio";

    /**
     * Abre e retorna uma nova conexão com o MySQL.
     * @return Objeto Connection aberto ou null em caso de falha.
     */
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
