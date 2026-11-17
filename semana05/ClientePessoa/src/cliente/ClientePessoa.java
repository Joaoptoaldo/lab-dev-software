package cliente;

import beans.Pessoa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientePessoa extends JFrame {

    private static final String HOST_SERVIDOR = "localhost";
    private static final int PORTA_SERVIDOR = 12345;

    private JTextField txtNome;
    private JComboBox<String> cmbSexo;
    private JComboBox<String> cmbIdioma;
    private JButton btnCadastrar;
    private JLabel lblStatus;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ClientePessoa.class.getName());

    public ClientePessoa() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        JLabel lblTitulo = new JLabel("Cadastro de Pessoa");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel lblSexo = new JLabel("Sexo:");
        lblSexo.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel lblIdioma = new JLabel("Idioma:");
        lblIdioma.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        txtNome = new JTextField();
        txtNome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtNome.setPreferredSize(new Dimension(200, 30));

        cmbSexo = new JComboBox<>(new String[]{"Selecione", "M", "F"});
        cmbSexo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cmbSexo.setPreferredSize(new Dimension(200, 30));

        cmbIdioma = new JComboBox<>(new String[]{
            "Selecione", "portugues", "ingles", "espanhol", "frances", "alemao", "italiano", "japones", "chines", "outro"
        });
        cmbIdioma.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cmbIdioma.setPreferredSize(new Dimension(200, 30));

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCadastrar.setPreferredSize(new Dimension(150, 40));
        btnCadastrar.addActionListener(e -> btnCadastrarActionPerformed(e));

        lblStatus = new JLabel("Pronto para cadastrar");
        lblStatus.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
        lblStatus.setForeground(Color.GRAY);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblTitulo, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        add(lblNome, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        add(txtNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        add(lblSexo, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        add(cmbSexo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        add(lblIdioma, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        add(cmbIdioma, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnCadastrar, gbc);

        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        add(lblStatus, gbc);

        setTitle("Cliente - Cadastro de Pessoa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350);
        setResizable(false);
    }

    private void btnCadastrarActionPerformed(ActionEvent evt) {
        String nome = txtNome.getText().trim();
        String sexo = cmbSexo.getSelectedItem().toString();
        String idioma = cmbIdioma.getSelectedItem().toString();

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo 'Nome' é obrigatório.", "Validação", JOptionPane.WARNING_MESSAGE);
            txtNome.requestFocus();
            return;
        }

        if (cmbSexo.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Selecione o sexo.", "Validação", JOptionPane.WARNING_MESSAGE);
            cmbSexo.requestFocus();
            return;
        }

        if (cmbIdioma.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Selecione o idioma.", "Validação", JOptionPane.WARNING_MESSAGE);
            cmbIdioma.requestFocus();
            return;
        }

        btnCadastrar.setEnabled(false);
        btnCadastrar.setText("Enviando...");
        lblStatus.setText("Conectando ao servidor...");
        lblStatus.setForeground(Color.BLUE);

        SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() throws Exception {
                return enviarParaServidor(nome, sexo, idioma);
            }

            @Override
            protected void done() {
                try {
                    String resposta = get();
                    processarResposta(resposta);
                } catch (Exception ex) {
                    lblStatus.setText("Erro: " + ex.getMessage());
                    lblStatus.setForeground(Color.RED);
                    JOptionPane.showMessageDialog(ClientePessoa.this,
                        "Erro de comunicação: " + ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
                } finally {
                    btnCadastrar.setEnabled(true);
                    btnCadastrar.setText("Cadastrar");
                }
            }
        };

        worker.execute();
    }

    private String enviarParaServidor(String nome, String sexo, String idioma) {
        try (Socket socket = new Socket(HOST_SERVIDOR, PORTA_SERVIDOR);
             ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream())) {

            Pessoa pessoa = new Pessoa(nome, sexo, idioma);

            saida.writeObject(pessoa);
            saida.flush();

            Object respostaObj = entrada.readObject();

            if (respostaObj instanceof String) {
                return (String) respostaObj;
            } else {
                return "ERRO: Resposta inválida do servidor.";
            }

        } catch (UnknownHostException e) {
            return "ERRO: Servidor não encontrado em " + HOST_SERVIDOR + ":" + PORTA_SERVIDOR;
        } catch (IOException e) {
            return "ERRO: Não foi possível conectar ao servidor. Verifique se o servidor está rodando.";
        } catch (ClassNotFoundException e) {
            return "ERRO: Problema na serialização/deserialização.";
        }
    }

    private void processarResposta(String resposta) {
        if (resposta.startsWith("SUCESSO")) {
            lblStatus.setText(resposta);
            lblStatus.setForeground(new Color(0, 128, 0));
            JOptionPane.showMessageDialog(this, resposta, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
        } else if (resposta.startsWith("ERRO")) {
            lblStatus.setText(resposta);
            lblStatus.setForeground(Color.RED);
            JOptionPane.showMessageDialog(this, resposta, "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            lblStatus.setText("Resposta inesperada: " + resposta);
            lblStatus.setForeground(Color.ORANGE);
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        cmbSexo.setSelectedIndex(0);
        cmbIdioma.setSelectedIndex(0);
        txtNome.requestFocus();
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        SwingUtilities.invokeLater(() -> new ClientePessoa().setVisible(true));
    }
}