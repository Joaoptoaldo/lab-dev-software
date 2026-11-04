package view;

import beans.Aluno;
import beans.Matricula;
import beans.Professor;
import dao.AlunoDAO;
import dao.MatriculaDAO;
import dao.ProfessorDAO;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Formulário Swing para Gerenciamento de Matrículas.
 * Demonstra na prática os seguintes conceitos de POO:
 * 1. Associação entre Objetos: JComboBox armazena objetos completos (Aluno e Professor), não apenas números.
 * 2. Polimorfismo e Sobrescrita: O JComboBox renderiza o texto a partir do método toString() das entidades.
 * 3. Encapsulamento e Separação de Responsabilidades: A tela apenas captura a interação do usuário,
 *    instancia o objeto Matricula associando Aluno e Professor, e delega ao MatriculaDAO a persistência.
 */
public class FormMatricula extends JFrame {

    private JTextField txtId;
    private JTextField txtData;
    private JComboBox<Aluno> cmbAluno;
    private JComboBox<Professor> cmbProfessor;
    private JButton btnCadastrar;
    private JButton btnConsultar;
    private JButton btnExcluir;
    private JButton btnLimpar;

    private final AlunoDAO alunoDAO = new AlunoDAO();
    private final ProfessorDAO professorDAO = new ProfessorDAO();
    private final MatriculaDAO matriculaDAO = new MatriculaDAO();

    public FormMatricula() {
        super("Cadastro de Matrículas");
        initComponents();
        carregarCombos();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(520, 380);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Cadastro de Matrícula (Associação)");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitulo.setBounds(30, 15, 450, 25);
        add(lblTitulo);

        // Campo ID
        JLabel lblId = new JLabel("ID da Matrícula:");
        lblId.setBounds(30, 60, 120, 25);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(150, 60, 100, 25);
        add(txtId);

        btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(260, 60, 100, 25);
        btnConsultar.addActionListener(this::btnConsultarActionPerformed);
        add(btnConsultar);

        // Campo Aluno (JComboBox armazenando objetos Aluno)
        JLabel lblAluno = new JLabel("Aluno:");
        lblAluno.setBounds(30, 105, 120, 25);
        add(lblAluno);

        cmbAluno = new JComboBox<>();
        cmbAluno.setBounds(150, 105, 320, 25);
        add(cmbAluno);

        // Campo Professor (JComboBox armazenando objetos Professor)
        JLabel lblProfessor = new JLabel("Professor:");
        lblProfessor.setBounds(30, 150, 120, 25);
        add(lblProfessor);

        cmbProfessor = new JComboBox<>();
        cmbProfessor.setBounds(150, 150, 320, 25);
        add(cmbProfessor);

        // Campo Data
        JLabel lblData = new JLabel("Data (AAAA-MM-DD):");
        lblData.setBounds(30, 195, 120, 25);
        add(lblData);

        txtData = new JTextField(LocalDate.now().toString());
        txtData.setBounds(150, 195, 140, 25);
        add(txtData);

        // Botões de ação
        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(50, 260, 110, 30);
        btnCadastrar.addActionListener(this::btnCadastrarActionPerformed);
        add(btnCadastrar);

        btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(175, 260, 110, 30);
        btnExcluir.addActionListener(this::btnExcluirActionPerformed);
        add(btnExcluir);

        btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(300, 260, 110, 30);
        btnLimpar.addActionListener(e -> limparCampos());
        add(btnLimpar);
    }

    /**
     * Preenche os JComboBox diretamente com os objetos retornados pelos DAOs.
     */
    public void carregarCombos() {
        cmbAluno.removeAllItems();
        List<Aluno> alunos = alunoDAO.listarTodos();
        for (Aluno a : alunos) {
            cmbAluno.addItem(a);
        }

        cmbProfessor.removeAllItems();
        List<Professor> professores = professorDAO.listarTodos();
        for (Professor p : professores) {
            cmbProfessor.addItem(p);
        }
    }

    /**
     * Ação do botão Cadastrar:
     * Recupera os objetos selecionados, cria a entidade Matricula e a persiste.
     */
    private void btnCadastrarActionPerformed(ActionEvent evt) {
        Aluno alunoSelecionado = (Aluno) cmbAluno.getSelectedItem();
        Professor professorSelecionado = (Professor) cmbProfessor.getSelectedItem();

        if (alunoSelecionado == null || professorSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um Aluno e um Professor para realizar a matrícula.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            LocalDate data = LocalDate.parse(txtData.getText().trim(), DateTimeFormatter.ISO_LOCAL_DATE);

            // Instanciação e Associação de Objetos
            Matricula matricula = new Matricula(alunoSelecionado, professorSelecionado, data);

            boolean sucesso = matriculaDAO.inserir(matricula);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Matrícula cadastrada com sucesso!");
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar matrícula no banco.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Data inválida! Utilize o formato AAAA-MM-DD (ex: 2026-11-03).", "Formato Inválido", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Ação do botão Consultar:
     * Busca a matrícula pelo ID e seleciona nos JComboBox os objetos correspondentes.
     */
    private void btnConsultarActionPerformed(ActionEvent evt) {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            Matricula matricula = matriculaDAO.getMatricula(id);

            if (matricula == null) {
                JOptionPane.showMessageDialog(this, "Matrícula não encontrada!", "Consulta", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Como Aluno e Professor implementam equals(), o setSelectedItem localiza corretamente
                cmbAluno.setSelectedItem(matricula.getAluno());
                cmbProfessor.setSelectedItem(matricula.getProfessor());
                txtData.setText(matricula.getDataMatricula().toString());
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Digite um ID numérico válido.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Ação do botão Excluir:
     */
    private void btnExcluirActionPerformed(ActionEvent evt) {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            int confirm = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir a matrícula #" + id + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                boolean sucesso = matriculaDAO.excluir(id);
                if (sucesso) {
                    JOptionPane.showMessageDialog(this, "Matrícula excluída com sucesso!");
                    limparCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "Não foi possível excluir a matrícula.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Informe um ID numérico para exclusão.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void limparCampos() {
        txtId.setText("");
        txtData.setText(LocalDate.now().toString());
        if (cmbAluno.getItemCount() > 0) cmbAluno.setSelectedIndex(0);
        if (cmbProfessor.getItemCount() > 0) cmbProfessor.setSelectedIndex(0);
        txtId.requestFocus();
    }
}

