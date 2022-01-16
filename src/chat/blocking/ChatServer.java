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
import java.util.Scanner;

/**
 *
 * @author Davi
 */
public class ChatServer {
    
    private static void clearBuffer(Scanner scanner) {
       
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
    
    public static final int PORT = 4000;
    private ServerSocket serverSockert;
    Email emailServer = new Email();
    
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
        emailServer.setRecebeuEmail(true);
        
        try{
            while((msg = clientSocket.getMessage()) != null){
                System.out.println("msg = "+msg);
                if(msg.equals("null")){
                    emailServer.setRecebeuEmail(false);
                }else{
                    // recebeu email
                    if(msg.contains("Email")){
                        emailServer.setEnderecoEmail(msg);
                    }else if(msg.contains("Assunto")){
                        emailServer.setAssuntoEmail(msg);
                    }else{
                        emailServer.setMessagemEmail(msg);
                    }
                }
            }

            if(emailServer.isRecebeuEmail()){
                System.out.println(emailServer.getEnderecoEmail());
                System.out.println(emailServer.getAssuntoEmail());
                System.out.println(emailServer.getMessagemEmail());
            }else{
                System.out.println("Email não chegou ao Servidor");
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
