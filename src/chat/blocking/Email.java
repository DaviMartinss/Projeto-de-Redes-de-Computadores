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
    private String assuntoEmail;
    private String messagemEmail;
    private String cancelaEmail;
    //private boolean RecebeuEmailEnvio;
    //private boolean RecebeuEmailChegada;
    
    private boolean RecebeuEmail;
    
    private String enderecoEmailRemetente;
    private boolean emailEnvio;
    private boolean emailChegada;
    
    public Email() {}
    
    public Email(String enderecoEmail, String assuntoEmail, String messagemEmail) {
        this.enderecoEmail = enderecoEmail;
        this.assuntoEmail = assuntoEmail;
        this.messagemEmail = messagemEmail;
    }
    
    public boolean isRecebeuEmail() {
        return RecebeuEmail;
    }

    public void setRecebeuEmail(boolean RecebeuEmail) {
        this.RecebeuEmail = RecebeuEmail;
    }

    /*
    public boolean isRecebeuEmailChegada() {
        return RecebeuEmailChegada;
    }

    public void setRecebeuEmailChegada(boolean RecebeuEmailChegada) {
        this.RecebeuEmailChegada = RecebeuEmailChegada;
    }
*/
    
    public String getEnderecoEmail() {
        if(enderecoEmail != null && !(enderecoEmail.equals("")) ){
            return "Email Envio:" +enderecoEmail;
        }else{
            return null;
        }
        
    }

    public void setEnderecoEmail(String enderecoEmail) {
        this.enderecoEmail = enderecoEmail;
    }

    public String getAssuntoEmail() {
        if(assuntoEmail != null && !(assuntoEmail.equals(""))){
            return "Assunto: " +assuntoEmail;
        }else{
            return null;
        }
    }

    public void setAssuntoEmail(String assuntoEmail) {
        this.assuntoEmail = assuntoEmail;
    }

    public String getMessagemEmail() {
        if(messagemEmail != null && !(messagemEmail.equals(""))){
            return "Mensagem: " +messagemEmail;
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
    /*
    public boolean isRecebeuEmailEnvio() {
        return RecebeuEmailEnvio;
    }

    public void setRecebeuEmailEnvio(boolean RecebeuEmail) {
        this.RecebeuEmailEnvio = RecebeuEmail;
    }
    */
    public String getEnderecoEmailRemetente() {
        if(enderecoEmailRemetente != null && !(enderecoEmailRemetente.equals(""))){
            return "Email Remetente: " +enderecoEmailRemetente;
        }else{
            return null;
        }
    }

    public void setEnderecoEmailRemetente(String enderecoEmailRemetente) {
        this.enderecoEmailRemetente = enderecoEmailRemetente;
    }
    
    public boolean isEmailEnvio() {
        return emailEnvio;
    }

    public void setEmailEnvio(boolean emailEnvio) {
        this.emailEnvio = emailEnvio;
    }

    public boolean isEmailChegada() {
        return emailChegada;
    }

    public void setEmailChegada(boolean emailChegada) {
        this.emailChegada = emailChegada;
    }
}
