package view;

import dao.ProfessorDAO;

public class RelatorioProfessores extends TabelaFrame {
    public RelatorioProfessores() {
        super("Professores", new String[]{"ID", "Nome", "Idade", "Disciplina"},
                new ProfessorDAO()::listarPorNome);
    }
}
