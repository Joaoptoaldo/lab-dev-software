package view;

import beans.Professor;
import dao.ProfessorDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Tela de visualização em tabela para Professores.
 * A View consome List<Professor> e extrai seus atributos para a JTable.
 */
public class RelatorioProfessores extends TabelaFrame {
    public RelatorioProfessores() {
        super("Relatório de Professores", new String[]{"ID", "Nome", "Idade", "Disciplina"}, filtro -> {
            ProfessorDAO dao = new ProfessorDAO();
            List<Professor> lista = dao.listarPorNome(filtro);
            List<Object[]> linhas = new ArrayList<>();

            for (Professor p : lista) {
                linhas.add(new Object[]{
                    p.getId(),
                    p.getNome(),
                    p.getIdade(),
                    p.getDisciplina()
                });
            }
            return linhas;
        });
    }
}
