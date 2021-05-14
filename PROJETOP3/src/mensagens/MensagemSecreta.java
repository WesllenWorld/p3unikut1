package mensagens;

public class MensagemSecreta extends Mensagem {
    private String palavraChave;

    public MensagemSecreta(String emissor, String msg, String palavraChave) {
        super(emissor, msg);
        this.palavraChave = palavraChave;
    }

    @Override
    public String toString() {
        return "[" + getHorarioDeEnvio() + "] " + getEmissor() + ": " + "[MENSAGEM PROTEGIDA]";
    }

    public String verificarAcesso(String senha) {
        if (senha.equals(getPalavraChave())) {
            return "[" + getHorarioDeEnvio() + "] " + getEmissor() + ": " + getMsg();
        } else {
            return "Senha incorreta. Acesso negado.";
        }
    }

    String getPalavraChave() {
        return palavraChave;
    }

}
