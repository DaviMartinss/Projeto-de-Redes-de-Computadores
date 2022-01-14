/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.blocking;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Davi
 */
public class ChatClient {
    private final String SERVER_ADDRESS = "127.0.0.1";
    private Socket clientSocket;
    
    public void start() throws IOException{
        clientSocket = new Socket(SERVER_ADDRESS, ChatServer.PORT);
    }
    
    public static void main(String[] args) {
        
        try {
            ChatClient cliet = new ChatClient();
            cliet.start();
        } catch (IOException ex) {
            System.out.println("Erro ao iniciar o clientt: "+ex.getMessage());
        }
    }
}
