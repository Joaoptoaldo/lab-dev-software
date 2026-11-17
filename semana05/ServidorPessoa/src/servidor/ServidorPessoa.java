package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import beans.Pessoa;
import dao.PessoaDAO;

public class ServidorPessoa {
    private static final int PORTA = 12345;
    private final PessoaDAO pessoaDAO;
    private boolean rodando;

    public ServidorPessoa() {
        this.pessoaDAO = new PessoaDAO();
        this.rodando = true;
    }

    public void iniciar() {
        try (ServerSocket serverSocket = new ServerSocket(PORTA)) {
            System.out.println("Servidor iniciado na porta " + PORTA);
            System.out.println("Aguardando conexões de cliente...");

            while (rodando) {
                try {
                    Socket socketCliente = serverSocket.accept();

                    Thread threadCliente = new Thread(new ClienteHandler(socketCliente));
                    threadCliente.start();

                    System.out.println("Nova conexão aceita: " + socketCliente.getInetAddress().getHostAddress()
                        + " - Thread: " + threadCliente.getName());

                } catch (SocketException e) {
                    if (!rodando) break;
                    System.err.println("Erro no socket do servidor: " + e.getMessage());
                } catch (IOException e) {
                    System.err.println("Erro ao aceitar conexão: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro fatal ao iniciar servidor na porta " + PORTA + ": " + e.getMessage());
        }
    }

    public void parar() {
        this.rodando = false;
        System.out.println("\nServidor encerrado.");
    }

    private class ClienteHandler implements Runnable {
        private final Socket socket;

        public ClienteHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            String enderecoCliente = socket.getInetAddress().getHostAddress();

            try (
                ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream())
            ) {
                Object objetoRecebido = entrada.readObject();

                if (!(objetoRecebido instanceof Pessoa)) {
                    enviarResposta(saida, "ERRO: Objeto recebido não é do tipo Pessoa.");
                    return;
                }

                Pessoa pessoa = (Pessoa) objetoRecebido;

                System.out.println("[" + Thread.currentThread().getName() + "] Recebido: " + pessoa);

                if (pessoa.getNome() == null || pessoa.getNome().trim().isEmpty()) {
                    enviarResposta(saida, "ERRO: O campo 'nome' é obrigatório.");
                    return;
                }

                boolean sucesso = pessoaDAO.inserir(pessoa);

                if (sucesso) {
                    enviarResposta(saida, "SUCESSO: Pessoa cadastrada com sucesso!");
                    System.out.println("[" + Thread.currentThread().getName() + "] Cadastro realizado: " + pessoa.getNome());
                } else {
                    enviarResposta(saida, "ERRO: Falha ao inserir no banco de dados. Verifique se o banco está acessível.");
                    System.err.println("[" + Thread.currentThread().getName() + "] Falha no cadastro: " + pessoa.getNome());
                }

            } catch (ClassNotFoundException e) {
                System.err.println("[" + Thread.currentThread().getName() + "] Classe não encontrada: " + e.getMessage());
                try {
                    ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
                    enviarResposta(saida, "ERRO: Classe Pessoa não encontrada no servidor.");
                } catch (IOException ex) {
                }
            } catch (IOException e) {
                System.err.println("[" + Thread.currentThread().getName() + "] Erro de I/O com cliente " + enderecoCliente + ": " + e.getMessage());
            } catch (Exception e) {
                System.err.println("[" + Thread.currentThread().getName() + "] Erro inesperado: " + e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                }
                System.out.println("[" + Thread.currentThread().getName() + "] Conexão encerrada com " + enderecoCliente);
            }
        }

        private void enviarResposta(ObjectOutputStream saida, String mensagem) {
            try {
                saida.writeObject(mensagem);
                saida.flush();
            } catch (IOException e) {
                System.err.println("Erro ao enviar resposta: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        ServidorPessoa servidor = new ServidorPessoa();

        Runtime.getRuntime().addShutdownHook(new Thread(servidor::parar));

        servidor.iniciar();
    }
}