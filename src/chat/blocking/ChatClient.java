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
public class ChatClient implements Runnable {
    private final String SERVER_ADDRESS = "127.0.0.1";
    private ClientSocket clientSocket;
    private final Scanner scanner;
    private boolean recebeEmail = true;
    
    public ChatClient(){
        scanner = new Scanner(System.in);
    }
    
    public static void main(String[] args) throws InterruptedException{
        
        try {
            ChatClient cliet = new ChatClient();
            cliet.start();
        } catch (IOException ex) {
            System.out.println("Erro ao iniciar o clientt: "+ex.getMessage());
        }
        System.out.println("Cliente Finalizado");
    }
    
    public void start() throws IOException, InterruptedException{
        final Socket socket = new Socket(SERVER_ADDRESS, ChatServer.PORT);
        
        clientSocket = new ClientSocket(socket);
        
        System.out.println("Cliente conectado ao servidor em "+ SERVER_ADDRESS + ":" +ChatServer.PORT);

        messageLoop();
        
        //if(this.recebeEmail)
            
        
    }
    
    private void messageLoop() throws IOException, InterruptedException{
        boolean sair = false;
        String desejaSair = "";
        Email email = new Email();
        
        System.out.print("Deseja enviar Email?(S/N): ");
        
        desejaSair = scanner.nextLine();
        
        if(desejaSair.equals("N")){
            
            new Thread(this).start();
        }
        else
        {
            do {
                String verificaSaida = "";

                System.out.print("Endereço email que vai enviar: ");
                email.setEnderecoEmail(scanner.nextLine());
                System.out.print("Assunto: ");
                email.setAssuntoEmail(scanner.nextLine());
                System.out.print("Mensagem: ");
                email.setMessagemEmail(scanner.nextLine());

                if (clientSocket.sendMail(email)) {
                    System.out.println("Mensagem enviada");
                    this.recebeEmail = false;
                }

                System.out.print("Digite 'sair' para encerrar): ");
                verificaSaida = scanner.nextLine();

                if (verificaSaida.equals("sair")) {
                    sair = true;
                }

            } while (!sair);

            clientSocket.close();

            scanner.close();
        }
          
    }
    
    @Override
    public void run() {
        
        String msg;
        if (clientSocket.getMessage() != null) {
            System.out.println();
            System.out.println("--- EMAIL:RECEBIDO ---");
            while ((msg = clientSocket.getMessage()) != null) {

                System.out.println(msg);
            }
        }
        clientSocket.close();
    }
}