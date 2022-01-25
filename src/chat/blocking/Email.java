/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.blocking;

/**
 *
 * @author Davi
 */
public class Email {
    private String enderecoEmail;
    private String enderecoRemetente;
    private String assuntoEmail;
    private String messagemEmail;
    private String cancelaEmail;
    private boolean RecebeuEmail;

    public String getEnderecoRemetente() {
        if(enderecoRemetente != null && !(enderecoRemetente.equals("")) ){
            return "Remetente:" +enderecoRemetente;
        }else{
            return null;
        }
    }

    public void setEnderecoRemetente(String enderecoRemetente) {
        
        this.enderecoRemetente = enderecoRemetente;
    }
    
    public String getEnderecoEmail() {
        if(enderecoEmail != null && !(enderecoEmail.equals("")) ){
            return "Email:" +enderecoEmail;
        }else{
            return null;
        }
        
    }

    public void setEnderecoEmail(String enderecoEmail) {
        this.enderecoEmail = enderecoEmail;
    }

    public String getAssuntoEmail() {
        if(assuntoEmail != null && !(assuntoEmail.equals(""))){
            return "Assunto:" +assuntoEmail;
        }else{
            return null;
        }
    }

    public void setAssuntoEmail(String assuntoEmail) {
        this.assuntoEmail = assuntoEmail;
    }

    public String getMessagemEmail() {
        if(messagemEmail != null && !(messagemEmail.equals(""))){
            return "Mensagem:" +messagemEmail;
        }else{
            return null;
        }
    }

    public void setMessagemEmail(String messagemEmail) {
        this.messagemEmail = messagemEmail;
    }
    
     public String getCancelaEmail() {
        return cancelaEmail;
    }

    public void setCancelaEmail(String cancelaEmail) {
        this.cancelaEmail = cancelaEmail;
    }
    
    public boolean isRecebeuEmail() {
        return RecebeuEmail;
    }

    public void setRecebeuEmail(boolean RecebeuEmail) {
        this.RecebeuEmail = RecebeuEmail;
    }
    
}
