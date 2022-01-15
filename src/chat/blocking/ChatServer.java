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
           ClientSocket clientSocket = new ClientSocket(serverSockert.accept());
           
           new Thread(() -> clientMessageLoop(clientSocket)).start();
        
        }
    }
    
    public void clientMessageLoop(ClientSocket clientSocket){
        String msg;
        try{
            while((msg = clientSocket.getMessage()) != null){
                if("sair".equalsIgnoreCase(msg))
                    return;

                System.out.printf("Msg recebida do cliente %s: %s\n",clientSocket.getRemoteSocketAddress(), msg);
            }
        } finally{
            clientSocket.close();
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
