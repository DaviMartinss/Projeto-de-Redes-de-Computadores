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
import static java.lang.System.console;
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
        Email email = new Email();
        boolean cancela = false;
        
        do{
            System.out.print("Endereço email que vai enviar: ");
            email.setEnderecoEmail(scanner.nextLine());
            System.out.print("Assunto: ");
            email.setAssuntoEmail(scanner.nextLine());
            System.out.print("Mensagem: ");
            email.setMessagemEmail(scanner.nextLine());
            
            System.out.println("Você deseja cancelar o envio do Email? Sim/Não");
           
            email.setCancelaEmail(scanner.nextLine());
            
            
            if(email.getCancelaEmail().equalsIgnoreCase("SIM")){
                cancela = true;
                System.out.println("Envio cancelado");
            }else{
                System.out.println("email: "+email.getEnderecoEmail());
                out.println(email.getEnderecoEmail()); // envia a mensagem
                
                //System.out.println("assunto: "+email.getAssuntoEmail());
                out.println(email.getAssuntoEmail()); // envia a mensagem
                
                //System.out.println("corpo: "+email.getMessagemEmail());
                out.println(email.getMessagemEmail()); // envia a mensagem
                
                cancela = true;
                System.out.println("Mensagem enviada");
            }
            
        }while (!cancela);
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
