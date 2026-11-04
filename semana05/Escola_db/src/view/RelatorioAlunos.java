package view;

import beans.Aluno;
import dao.AlunoDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Tela de visualização em tabela para Alunos.
 * Demonstra a separação de responsabilidades:
 * - O DAO retorna objetos de domínio tipados (List<Aluno>).
 * - A View acessa os métodos getters dos objetos para renderizar as linhas na JTable.
 */
public class RelatorioAlunos extends TabelaFrame {
    public RelatorioAlunos() {
        super("Relatório de Alunos", new String[]{"ID", "Nome", "Idade", "Curso"}, filtro -> {
            AlunoDAO dao = new AlunoDAO();
            List<Aluno> lista = dao.listarPorNome(filtro);
            List<Object[]> linhas = new ArrayList<>();

            for (Aluno a : lista) {
                linhas.add(new Object[]{
                    a.getId(),
                    a.getNome(),
                    a.getIdade(),
                    a.getCurso()
                });
            }
            return linhas;
        });
    }
}
