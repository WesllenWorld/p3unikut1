package mensagens;
import java.util.Date;

public class Mensagem {
    protected Date horario;
    protected String msg;
    protected String emissor;

    public Mensagem(String emissor, String msg){
        this.horario = new Date();
        this.msg = msg;
        this.emissor = emissor;
    }

    public String toString(){
        return "[" + getHorarioDeEnvio() + "] " + getEmissor() + ": "+getMsg();
    }

    String getHorarioDeEnvio(){
        return this.horario.toString();
    }

    String getMsg(){
        return this.msg;
    }

    String getEmissor(){
        return this.emissor;
    }
}
