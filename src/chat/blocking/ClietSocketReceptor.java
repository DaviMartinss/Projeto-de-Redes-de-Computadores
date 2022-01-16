/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.blocking;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Davi
 */
public class ClietSocketReceptor {
    
    private static void clearBuffer(Scanner scanner) {
       
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
    
    private final String SERVER_ADDRESS = "127.0.0.1";
    private Socket clientSocket;
    private Scanner scanner;
    private PrintWriter out;
    
    public ClietSocketReceptor(){
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
        
        System.out.print("informe o seu email(Que vai chegar a msg): ");
        email.setEnderecoEmailRemetente(scanner.nextLine());
        clearBuffer(scanner);
             
        out.println(email.getEnderecoEmailRemetente()); // envia a mensagem
        System.out.println("Mensagem enviada");    
    }
    
    public static void main(String[] args) {
        try{
            ClietSocketReceptor clietSocketReceptor = new ClietSocketReceptor();
            clietSocketReceptor.start();
        
        }catch(IOException ex){
            System.out.println("Erro ao iniciar o Cliente Receptor: "+ex.getMessage());
        }
        
        System.out.println("Cliente Remetente Finalizado");
    }
}
