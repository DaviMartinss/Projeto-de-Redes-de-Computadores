/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.blocking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Davi
 */
public class ChatServer {
    public static final int PORT = 4000;
    private ServerSocket serverSockert;
    
    public void start() throws IOException{
        System.out.println("Servidor iniciado na porta "+PORT);
        serverSockert = new ServerSocket(PORT);
        clientConnectionLoop();
    }
    
    private void clientConnectionLoop() throws IOException{
        while (true) {            
           Socket clientSocket = serverSockert.accept();
            System.err.println("Client "+clientSocket.getReuseAddress());
        }
    }
    public static void main(String[] args) {
        try{
            ChatServer server = new ChatServer();
            server.start();
        }catch(IOException ex){
            System.out.println("Erro ao iniciar o servidor: "+ex.getMessage());
        }
        System.err.println("Servidor Finalizado");
    }
}
