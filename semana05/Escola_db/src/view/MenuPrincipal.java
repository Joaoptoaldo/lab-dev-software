package view;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuPrincipal extends JFrame {
    public MenuPrincipal() {
        super("Sistema Escola");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(500, 300));
        setLocationByPlatform(true);

        JMenuBar barra = new JMenuBar();

        JMenu arquivo = new JMenu("Arquivo");
        JMenuItem sair = new JMenuItem("Sair");
        sair.addActionListener(e -> System.exit(0));
        arquivo.add(sair);

        JMenu cadastro = new JMenu("Cadastros");
        JMenuItem aluno = new JMenuItem("Cadastrar aluno");
        JMenuItem professor = new JMenuItem("Cadastrar professor");
        aluno.addActionListener(e -> new FormAluno().setVisible(true));
        professor.addActionListener(e -> new FormProfessor().setVisible(true));
        cadastro.add(aluno);
        cadastro.add(professor);

        JMenu telas = new JMenu("Telas");
        JMenuItem alunos = new JMenuItem("Alunos");
        JMenuItem professores = new JMenuItem("Professores");
        JMenuItem matriculas = new JMenuItem("Matrículas");
        alunos.addActionListener(e -> new RelatorioAlunos().setVisible(true));
        professores.addActionListener(e -> new RelatorioProfessores().setVisible(true));
        matriculas.addActionListener(e -> new RelatorioMatriculas().setVisible(true));
        telas.add(alunos);
        telas.add(professores);
        telas.add(matriculas);

        barra.add(arquivo);
        barra.add(cadastro);
        barra.add(telas);
        setJMenuBar(barra);
    }
}
