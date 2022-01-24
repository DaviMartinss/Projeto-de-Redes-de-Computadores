/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.blocking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Davi
 */
public class ChatServer {
    public static final int PORT = 4000;
    private final String SERVER_ADDRESS = "127.0.0.1";
    private ServerSocket serverSocket;
    private final List<ClientSocket> clientSocketList;
    Email emailServer = new Email();
    
    public ChatServer(){
        clientSocketList = new LinkedList<>();
    }
    
    public void start() throws IOException{
        serverSocket = new ServerSocket(PORT);
        System.out.println("Servidor iniciado na porta "+PORT);
        clientConnectionLoop();
    }
    
   private void clientConnectionLoop() throws IOException{
        try {
            while (true) {
                System.out.println("Aguardando conexão de novo cliente");
                
                final ClientSocket clientSocket;
                try {
                    clientSocket = new ClientSocket(serverSocket.accept());
                    System.out.println("Cliente " + clientSocket.getRemoteSocketAddress() + " conectado");
                }catch(SocketException e){
                    System.err.println("Erro ao aceitar conexão do cliente. O servidor possivelmente está sobrecarregado:");
                    System.err.println(e.getMessage());
                    continue;
                }
                
                /*
                Cria uma nova Thread para permitir que o servidor não fique bloqueado enquanto
                atende às requisições de um único cliente.
                */
                try {
                    new Thread(() -> clientMessageLoop(clientSocket)).start();
                    clientSocketList.add(clientSocket);
                }catch(OutOfMemoryError ex){
                    System.err.println(
                            "Não foi possível criar thread para novo cliente. O servidor possivelmente está sobrecarregdo. Conexão será fechada: ");
                    System.err.println(ex.getMessage());
                    clientSocket.close();
                }
            }
        } finally{
            /*Se sair do laço de repetição por algum erro, exibe uma mensagem
            indicando que o servidor finalizou e fecha o socket do servidor.*/
            stop();
        }
    }
    
    public void clientMessageLoop(ClientSocket clientSocket){
        String msg;
        emailServer.setRecebeuEmail(true);
        try{
            while((msg = clientSocket.getMessage()) != null){
                
                if(msg == null){
                    emailServer.setRecebeuEmail(false);
                }else{
                    // recebeu email 
                    if(msg.contains("Email")){
                        emailServer.setEnderecoEmail(msg.split("Email:")[1]);
                        
                    }else if(msg.contains("Assunto")){
                        emailServer.setAssuntoEmail(msg.split("Assunto:")[1]);
                    }else{
                        emailServer.setMessagemEmail(msg.split("Mensagem:")[1]);
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
            
            SendEmailToDestiny(clientSocket, emailServer, emailServer.getEnderecoEmail().split("Email:")[1]);
            
        } finally{
            clientSocket.close();
        }
    }
    
    private void SendEmailToDestiny(final ClientSocket sender, final Email email, final String enderecoDestino) {
        final Iterator<ClientSocket> iterator = clientSocketList.iterator();
        int count = 0;
        String enderecoClient;
        
        /*Percorre a lista usando o iterator enquanto existir um próxima elemento (hasNext)
        para processar, ou seja, enquanto não percorrer a lista inteira.*/
        while (iterator.hasNext()) {
            //Obtém o elemento atual da lista para ser processado.
            final ClientSocket client = iterator.next();
            /*Verifica se o elemento atual da lista (cliente) não é o cliente que enviou a mensagem.
            Se não for, encaminha a mensagem pra tal cliente.*/
            
             enderecoClient = client.getRemoteSocketAddress().toString();
             
            if (!client.equals(sender) && enderecoClient.equals(enderecoDestino)) {
                if(client.sendMail(email))
                    count++;
                else iterator.remove();
            }
        }
        System.out.println("Mensagem encaminhada para " + count + " clientes");
    }
    
    
    public static void main(String[] args) throws InterruptedException {
        try{
            ChatServer server = new ChatServer();
            server.start();
        }catch(IOException ex){
            System.out.println("Erro ao iniciar o servidor: "+ex.getMessage());
        }
        System.out.println("Servidor Finalizado");
    }
    
    private void stop()  {
        try {
            System.out.println("Finalizando servidor");
            serverSocket.close();
        } catch (IOException e) {
            System.err.println("Erro ao fechar socket do servidor: " + e.getMessage());
        }
    }
}
