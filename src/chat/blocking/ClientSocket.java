/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.blocking;

import java.net.Socket;
import java.io.*;
import java.net.SocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Davi
 */
public class ClientSocket {
    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;
    
    public  ClientSocket(Socket socket) throws IOException{
        this.socket = socket;
        
        System.out.println("Client "+socket.getRemoteSocketAddress() +" conectou");
        this.in = new BufferedReader(
                 new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }
    
    public SocketAddress getRemoteSocketAddress(){
        return socket.getRemoteSocketAddress();
    }
    
    public void close(){
        try{
        in.close();
        out.close();
        socket.close();
        }catch(IOException ex){
            System.out.println("Erro ao fechar socket "+ex.getMessage());
        }
    }
    
    public String getMessage(){
        
        try {
            return  in.readLine();
        } catch (IOException ex) {
            return  null;
        }
    }
    
    public boolean sendMsg(String msg){
        out.println(msg);
        return !out.checkError(); // para saber se de fatoa msg foi enviada
    }
}
