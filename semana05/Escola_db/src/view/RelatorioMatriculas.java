package view;

import dao.MatriculaDAO;

public class RelatorioMatriculas extends TabelaFrame {
    public RelatorioMatriculas() {
        super("Matrículas", new String[]{"ID", "Aluno", "Professor", "Data"},
                new MatriculaDAO()::listarPorNome);
    }
}
