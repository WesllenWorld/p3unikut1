package model.exceptions;

public class PedidoJaExistenteException extends Exception{
    public PedidoJaExistenteException(String mensagem){
        super(mensagem);
    }
}
