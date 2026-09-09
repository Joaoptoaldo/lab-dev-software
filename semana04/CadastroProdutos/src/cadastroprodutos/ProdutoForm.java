package cadastroprodutos;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class ProdutoForm extends JFrame {

    private JTextField txtNome;
    private JTextField txtDescricao;
    private JTextField txtPreco;
    private JTextField txtQuantidade;
    private JTextField txtCategoria;
    private JTable tblProdutos;
    private JButton btnSalvar;
    private JButton btnEditar;
    private JButton btnExcluir;
    private JButton btnLimpar;

    private Arquivo arquivo;
    private List<Produto> listaProdutos;

    // controle
    private int linhaEdicao = -1;

    public ProdutoForm() {
        setTitle("Cadastro de Produtos");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        montarFormulario();

        arquivo = new Arquivo("produtos");
        listaProdutos = arquivo.lerArquivo();
        atualizarTabela();
    }

    private void montarFormulario() {

        JLabel lblTitulo = new JLabel("Cadastro de Produtos");
        lblTitulo.setBounds(20, 10, 300, 30);
        lblTitulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
        add(lblTitulo);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 55, 80, 25);
        add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(110, 55, 250, 25);
        add(txtNome);

        JLabel lblDescricao = new JLabel("Descricao:");
        lblDescricao.setBounds(380, 55, 80, 25);
        add(lblDescricao);

        txtDescricao = new JTextField();
        txtDescricao.setBounds(470, 55, 200, 25);
        add(txtDescricao);

        JLabel lblPreco = new JLabel("Preco:");
        lblPreco.setBounds(20, 90, 80, 25);
        add(lblPreco);

        txtPreco = new JTextField();
        txtPreco.setBounds(110, 90, 120, 25);
        add(txtPreco);

        JLabel lblQuantidade = new JLabel("Quantidade:");
        lblQuantidade.setBounds(250, 90, 80, 25);
        add(lblQuantidade);

        txtQuantidade = new JTextField();
        txtQuantidade.setBounds(340, 90, 80, 25);
        add(txtQuantidade);

        JLabel lblCategoria = new JLabel("Categoria:");
        lblCategoria.setBounds(440, 90, 80, 25);
        add(lblCategoria);

        txtCategoria = new JTextField();
        txtCategoria.setBounds(530, 90, 140, 25);
        add(txtCategoria);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(20, 130, 100, 30);
        btnSalvar.addActionListener(e -> btnSalvarClicado());
        add(btnSalvar);

        btnEditar = new JButton("Editar");
        btnEditar.setBounds(130, 130, 100, 30);
        btnEditar.addActionListener(e -> btnEditarClicado());
        add(btnEditar);

        btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(240, 130, 100, 30);
        btnExcluir.addActionListener(e -> btnExcluirClicado());
        add(btnExcluir);

        btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(350, 130, 100, 30);
        btnLimpar.addActionListener(e -> limparFormulario());
        add(btnLimpar);

        tblProdutos = new JTable();
        tblProdutos.setModel(new DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Nome", "Descricao", "Preco", "Quantidade", "Categoria"}
        ));
        tblProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tblProdutos);
        scrollPane.setBounds(20, 175, 650, 270);
        add(scrollPane);
    }

    private void atualizarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) tblProdutos.getModel();
        modelo.setRowCount(0);

        for (Produto p : listaProdutos) {
            modelo.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getDescricao(),
                p.getPreco(),
                p.getQuantidade(),
                p.getCategoria()
            });
        }
    }

    private void btnSalvarClicado() {
        if (txtNome.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo Nome!",
                    "Erro", JOptionPane.WARNING_MESSAGE);
            txtNome.requestFocus();
            return;
        }

        if (txtPreco.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o campo Preco!",
                    "Erro", JOptionPane.WARNING_MESSAGE);
            txtPreco.requestFocus();
            return;
        }

        try {
            String nome = txtNome.getText().trim();
            String descricao = txtDescricao.getText().trim();

            String precoTexto = txtPreco.getText().trim().replace(",", ".");
            double preco = Double.parseDouble(precoTexto);

            int quantidade = Integer.parseInt(txtQuantidade.getText().trim());

            String categoria = txtCategoria.getText().trim();

            Produto p;

            if (linhaEdicao == -1) {
                int novoId = arquivo.gerarProximoId();
                p = new Produto(novoId, nome, descricao, preco, quantidade, categoria);
                listaProdutos.add(p);
            } else {
                Produto existente = listaProdutos.get(linhaEdicao);
                p = new Produto(existente.getId(), nome, descricao, preco, quantidade, categoria);
                listaProdutos.set(linhaEdicao, p);
                linhaEdicao = -1;
            }

            // salva e atualiza
            arquivo.gravarArquivo(listaProdutos);
            atualizarTabela();
            limparFormulario();

            JOptionPane.showMessageDialog(null, "Produto salvo com sucesso!",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Verifique os campos numericos!\nPreco: use apenas numeros\nQuantidade: use apenas inteiros",
                    "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnEditarClicado() {
        int linha = tblProdutos.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um produto para editar!",
                    "Atencao", JOptionPane.WARNING_MESSAGE);
            return;
        }

        linhaEdicao = linha;

        Produto p = listaProdutos.get(linha);

        txtNome.setText(p.getNome());
        txtDescricao.setText(p.getDescricao());
        txtPreco.setText(String.valueOf(p.getPreco()));
        txtQuantidade.setText(String.valueOf(p.getQuantidade()));
        txtCategoria.setText(p.getCategoria());

        txtNome.requestFocus();
    }

    private void btnExcluirClicado() {
        int linha = tblProdutos.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um produto para excluir!",
                    "Atencao", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Produto p = listaProdutos.get(linha);

        int resposta = JOptionPane.showConfirmDialog(null,
                "Deseja realmente excluir o produto \"" + p.getNome() + "\"?",
                "Confirmar Exclusao",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (resposta == JOptionPane.YES_OPTION) {
            listaProdutos.remove(linha);
            arquivo.gravarArquivo(listaProdutos);
            atualizarTabela();
            limparFormulario();
            linhaEdicao = -1;

            JOptionPane.showMessageDialog(null, "Produto excluido com sucesso!",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void limparFormulario() {
        txtNome.setText("");
        txtDescricao.setText("");
        txtPreco.setText("");
        txtQuantidade.setText("");
        txtCategoria.setText("");
        linhaEdicao = -1;
        txtNome.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ProdutoForm().setVisible(true);
        });
    }
}
