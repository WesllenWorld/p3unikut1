package model.mensagens;

import controller.exceptions.SenhaInvalidaException;

public class MensagemSecreta extends Mensagem {
    private String palavraChave;

    public MensagemSecreta(String emissor, String msg, String palavraChave) {
        super(emissor, msg);
        this.palavraChave = palavraChave;
        this.secreta = true;
    }

    @Override
    public String toString() {
        return "[" + getHorarioDeEnvio() + "] " + getEmissor() + ": " + "[MENSAGEM PROTEGIDA]";
    }

    public String verificarAcesso(String senha) throws SenhaInvalidaException{
        if (senha.equals(getPalavraChave())) {
            return "[" + getHorarioDeEnvio() + "] " + getEmissor() + ": " + getMsg();
        } else {
            throw new SenhaInvalidaException("Senha incorreta");
        }
    }

    String getPalavraChave() {
        return palavraChave;
    }

}
