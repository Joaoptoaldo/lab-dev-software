/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projetodb;

import beans.Pessoa;
import dao.PessoaDAO;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import view.MenuPrincipal;

/**
 *
 * @author laboratorio
 */
public class ProjetoDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        int porta = 12345;
        
        try (ServerSocket servidorSocket = new ServerSocket(porta)) {
            System.out.println("Servidor aguardando conexões na porta" + porta);
            
            while (true) {
                try{
                    Socket clienteSocket = servidorSocket.accept();
                    System.out.println("Conexão aceita de " + clienteSocket.getInetAddress());
                    
                    // cria uma thread nova para lidar com o cliente
                    Thread threadCliente = new ThreadServer(clienteSocket);
                    threadCliente.start();
                    
                } catch (IOException ex) {
                    System.out.println("Erro ao aceitar conexão do cliente");
                }
            } 
        } catch (IOException ex) {
            System.out.println("Erro ao criar ServerSocket");
        }
        
        MenuPrincipal view = new MenuPrincipal();
        view.setVisible(true);
    }

}
