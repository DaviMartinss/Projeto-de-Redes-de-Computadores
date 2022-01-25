/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.blocking;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


/**
 *
 * @author Davi
 */
public class ChatClient{
    private final String SERVER_ADDRESS = "127.0.0.1";
    public ClientSocket clientSocket;
    private final Scanner scanner;
    public Email emailRecebido = null;
    
    public ChatClient(){
        scanner = new Scanner(System.in);
    }
    

    public void start() throws IOException, InterruptedException{
        final Socket socket = new Socket(SERVER_ADDRESS, ChatServer.PORT);
        
        clientSocket = new ClientSocket(socket);
        
        System.out.println("Cliente conectado ao servidor em "+ SERVER_ADDRESS + ":" +ChatServer.PORT);

    }
    
    public void MessageLoopTela(String enderecoDestino, String titulo, String mensagem, boolean enviarEmail) throws IOException, InterruptedException{
        Email email = new Email();
        
        email.setEnderecoRemetente(clientSocket.getLocalSocketAddress().toString());

        email.setEnderecoEmail(enderecoDestino);

        System.out.print("Assunto: ");
        email.setAssuntoEmail(titulo);
        System.out.print("Mensagem: ");
        email.setMessagemEmail(mensagem);

        if (clientSocket.sendMail(email)) {
            System.out.println("Mensagem enviada");
        }

        clientSocket.close();

    }
    
}      