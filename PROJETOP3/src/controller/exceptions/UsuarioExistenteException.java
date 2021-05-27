package controller.exceptions;

public class UsuarioExistenteException extends Exception {

    public UsuarioExistenteException(String mensagem) {
        super(mensagem);
    }
}
