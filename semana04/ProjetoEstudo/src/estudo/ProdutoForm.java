package estudo;

import java.util.List;
import javax.swing.*;
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

    private Arquivo arquivo;
    private List<Produto> listaProdutos;
    private int linhaEdicao = -1;

    public ProdutoForm() {
        setTitle("Cadastro de Produtos");
        setSize(650, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        montarFormulario();

        arquivo = new Arquivo("produtos");
        listaProdutos = arquivo.lerArquivo();
        atualizarTabela();
    }

    private void montarFormulario() {
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 20, 80, 25);
        add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(110, 20, 200, 25);
        add(txtNome);

        JLabel lblDescricao = new JLabel("Descricao:");
        lblDescricao.setBounds(20, 55, 80, 25);
        add(lblDescricao);

        txtDescricao = new JTextField();
        txtDescricao.setBounds(110, 55, 200, 25);
        add(txtDescricao);

        JLabel lblPreco = new JLabel("Preco:");
        lblPreco.setBounds(20, 90, 80, 25);
        add(lblPreco);

        txtPreco = new JTextField();
        txtPreco.setBounds(110, 90, 200, 25);
        add(txtPreco);

        JLabel lblQuantidade = new JLabel("Quantidade:");
        lblQuantidade.setBounds(20, 125, 80, 25);
        add(lblQuantidade);

        txtQuantidade = new JTextField();
        txtQuantidade.setBounds(110, 125, 200, 25);
        add(txtQuantidade);

        JLabel lblCategoria = new JLabel("Categoria:");
        lblCategoria.setBounds(20, 160, 80, 25);
        add(lblCategoria);

        txtCategoria = new JTextField();
        txtCategoria.setBounds(110, 160, 200, 25);
        add(txtCategoria);

        tblProdutos = new JTable();
        tblProdutos.setModel(new DefaultTableModel(
            new Object [][] {},
            new String [] { "Nome", "Descricao", "Preco", "Quantidade", "Categoria" }
        ));

        JScrollPane scrollPane = new JScrollPane(tblProdutos);
        scrollPane.setBounds(20, 200, 590, 180);
        add(scrollPane);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(350, 20, 100, 30);
        btnSalvar.addActionListener(e -> btnSalvarClicado());
        add(btnSalvar);

        btnEditar = new JButton("Editar");
        btnEditar.setBounds(350, 55, 100, 30);
        btnEditar.addActionListener(e -> btnEditarClicado());
        add(btnEditar);

        btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(350, 90, 100, 30);
        btnExcluir.addActionListener(e -> btnExcluirClicado());
        add(btnExcluir);
    }

    private void atualizarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) tblProdutos.getModel();
        modelo.setRowCount(0);

        for (Produto p : listaProdutos) {
            modelo.addRow(new Object[]{
                p.getNome(),
                p.getDescricao(),
                p.getPreco(),
                p.getQuantidade(),
                p.getCategoria()
            });
        }
    }

    private void btnSalvarClicado() {
        String nome = txtNome.getText();
        String descricao = txtDescricao.getText();
        String precoTexto = txtPreco.getText().replace(",", ".");
        double preco = Double.parseDouble(precoTexto);
        int quantidade = Integer.parseInt(txtQuantidade.getText());
        String categoria = txtCategoria.getText();

        Produto p = new Produto(nome, descricao, preco, quantidade, categoria);

        if (linhaEdicao == -1) {
            listaProdutos.add(p);
        } else {
            listaProdutos.set(linhaEdicao, p);
            linhaEdicao = -1;
        }

        arquivo.gravarArquivo(listaProdutos);
        atualizarTabela();

        txtNome.setText("");
        txtDescricao.setText("");
        txtPreco.setText("");
        txtQuantidade.setText("");
        txtCategoria.setText("");
        txtNome.requestFocus();
    }

    private void btnEditarClicado() {
        int linha = tblProdutos.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um produto para editar.",
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
    }

    private void btnExcluirClicado() {
        int linha = tblProdutos.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(null, "Selecione um produto para excluir.",
                    "Atencao", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int resposta = JOptionPane.showConfirmDialog(null,
                "Deseja realmente excluir este produto?",
                "Confirmacao", JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            listaProdutos.remove(linha);
            arquivo.gravarArquivo(listaProdutos);

            DefaultTableModel modelo = (DefaultTableModel) tblProdutos.getModel();
            modelo.removeRow(linha);

            JOptionPane.showMessageDialog(null, "Produto excluido com sucesso!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ProdutoForm().setVisible(true);
        });
    }
}
