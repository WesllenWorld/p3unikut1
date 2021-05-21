package model.mensagens;

import java.util.Date;

public class Mensagem {
    protected Date horario;
    protected String msg;
    protected String emissor;
    protected boolean secreta;

    public Mensagem(String emissor, String msg){
        this.horario = new Date();
        this.msg = msg;
        this.emissor = emissor;
        this.secreta = false;
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

    public boolean isSecreta(){
        return secreta;
    }
}
