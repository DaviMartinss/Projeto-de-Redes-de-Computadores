/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.blocking;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Davi
 */
public class ChatClient {
    private final String SERVER_ADDRESS = "127.0.0.1";
    private Socket clientSocket;
    private Scanner scanner;
    private PrintWriter out;
    
    public ChatClient(){
        scanner = new Scanner(System.in, "Cp1252");
    }
    
    public void start() throws IOException{
        clientSocket = new Socket(SERVER_ADDRESS, ChatServer.PORT);
        this.out = new PrintWriter(clientSocket.getOutputStream(), true); // envia mensagem para o servidor
        System.out.println("Cliente conectado ao servidor em "+ SERVER_ADDRESS + ":" +ChatServer.PORT);
        messageLoop();
    }
    
    private void messageLoop() throws IOException{
        String msg ;
        do{
            System.out.print("Digite uma mensagem (ou sair para finalizar): ");
            msg = scanner.nextLine();
            
            out.println(msg); // envia a mensagem
        }while (!msg.equalsIgnoreCase("sair"));
    }
    
    public static void main(String[] args) {
        
        try {
            ChatClient cliet = new ChatClient();
            cliet.start();
        } catch (IOException ex) {
            System.out.println("Erro ao iniciar o clientt: "+ex.getMessage());
        }
        System.out.println("Cliente Finalizado");
    }
}
