package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import java.util.function.Function;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TabelaFrame extends JFrame {
    private final Function<String, List<Object[]>> carregador;
    private final String[] colunas;
    private final JTextField txtFiltro = new JTextField(25);
    private final DefaultTableModel modelo;

    public TabelaFrame(String titulo, String[] colunas,
            Function<String, List<Object[]>> carregador) {
        super(titulo);
        this.colunas = colunas;
        this.carregador = carregador;
        this.modelo = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(650, 400);
        setLocationByPlatform(true);

        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.addActionListener(e -> carregar());
        txtFiltro.addActionListener(e -> carregar());

        JPanel painelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelFiltro.add(new JLabel("Nome:"));
        painelFiltro.add(txtFiltro);
        painelFiltro.add(btnFiltrar);

        add(painelFiltro, BorderLayout.NORTH);
        add(new JScrollPane(new JTable(modelo)), BorderLayout.CENTER);
        carregar();
    }

    private void carregar() {
        modelo.setRowCount(0);
        for (Object[] linha : carregador.apply(txtFiltro.getText().trim())) {
            modelo.addRow(linha);
        }
    }
}
