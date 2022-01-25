/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.blocking;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Davi
 */
public class ChatClient implements Runnable {
    private final String SERVER_ADDRESS = "127.0.0.1";
    public ClientSocket clientSocket;
    private final Scanner scanner;
    public Email emailRecebido = new Email();
    
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
        //System.out.println("Cliente Finalizado");
    }

    public void start() throws IOException, InterruptedException{
        final Socket socket = new Socket(SERVER_ADDRESS, ChatServer.PORT);
        
        clientSocket = new ClientSocket(socket);
        
        System.out.println("Cliente conectado ao servidor em "+ SERVER_ADDRESS + ":" +ChatServer.PORT);

        //messageLoop();
        
    }
    
    public void MessageLoopTela(String enderecoDestino, String titulo, String mensagem, boolean enviarEmail) throws IOException, InterruptedException{
        Email email = new Email();
        
        if(!enviarEmail){
            
            new Thread(this).start();
        }
        else
        {

            email.setEnderecoEmail(enderecoDestino);
            System.out.print("Assunto: ");
            email.setAssuntoEmail(titulo);
            System.out.print("Mensagem: ");
            email.setMessagemEmail(mensagem);

            if (clientSocket.sendMail(email)) {
                System.out.println("Mensagem enviada");
            }

            clientSocket.close();

            //scanner.close();
        }
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

                if (clientSocket.sendMail(email))
                    System.out.println("Mensagem enviada");
               
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
        
//        if (this.clientSocket.getMessage() != null) {
            //JOptionPane.showMessageDialog(null, "--- EMAIL:RECEBIDO ---", "RECEBEU EMAIL", JOptionPane.INFORMATION_MESSAGE);
            
            //System.out.println("--- EMAIL:RECEBIDO ---");
            while ((msg = this.clientSocket.getMessage()) != null) {
                
                if (msg.contains("Email")) {
                    this.emailRecebido.setEnderecoEmail(msg.split("Email:")[1]);
                    
                } else if (msg.contains("Assunto")) {
                    this.emailRecebido.setAssuntoEmail(msg.split("Assunto:")[1]);
                }
                else if (msg.contains("Mensagem")){
                    this.emailRecebido.setMessagemEmail(msg);
                }
            }
//        }
        
        //clientSocket.close();
        
    }
}