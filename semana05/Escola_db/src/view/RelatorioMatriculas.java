package view;

import beans.Matricula;
import dao.MatriculaDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Tela de visualização em tabela para Matrículas.
 * Demonstra a navegação pela Associação entre Objetos:
 * acessa propriedades dos objetos Aluno e Professor a partir da instância de Matricula
 * (ex: m.getAluno().getNome(), m.getProfessor().getDisciplina()).
 */
public class RelatorioMatriculas extends TabelaFrame {
    public RelatorioMatriculas() {
        super("Relatório de Matrículas", new String[]{"ID", "Aluno", "Curso", "Professor", "Disciplina", "Data"}, filtro -> {
            MatriculaDAO dao = new MatriculaDAO();
            List<Matricula> lista = dao.listarPorNome(filtro);
            List<Object[]> linhas = new ArrayList<>();

            for (Matricula m : lista) {
                linhas.add(new Object[]{
                    m.getId(),
                    m.getAluno() != null ? m.getAluno().getNome() : "-",
                    m.getAluno() != null ? m.getAluno().getCurso() : "-",
                    m.getProfessor() != null ? m.getProfessor().getNome() : "-",
                    m.getProfessor() != null ? m.getProfessor().getDisciplina() : "-",
                    m.getDataMatricula()
                });
            }
            return linhas;
        });
    }
}
