/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.blocking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
           System.out.println("Client "+clientSocket.getRemoteSocketAddress() +" conectou");
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 
            String msg = in.readLine();
            System.out.println("Messagem recebida do cliente = " +clientSocket.getRemoteSocketAddress()
                + ":" +msg);
        }
    }

    public static void main(String[] args) {
        try{
            ChatServer server = new ChatServer();
            server.start();
        }catch(IOException ex){
            System.out.println("Erro ao iniciar o servidor: "+ex.getMessage());
        }
        System.out.println("Servidor Finalizado");
    }
}
