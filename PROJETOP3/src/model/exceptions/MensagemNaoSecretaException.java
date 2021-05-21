package model.exceptions;

public class MensagemNaoSecretaException extends Exception{
    public MensagemNaoSecretaException(String mensagem){
        super(mensagem);
    }
}
