package view;

import dao.AlunoDAO;

public class RelatorioAlunos extends TabelaFrame {
    public RelatorioAlunos() {
        super("Alunos", new String[]{"ID", "Nome", "Idade", "Curso"},
                new AlunoDAO()::listarPorNome);
    }
}
