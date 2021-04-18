package usuarios;

import mensagens.Mensagem;
import mensagens.MensagemSecreta;

import java.util.ArrayList;

public class Usuario {
    private String login, senha, nomeDeUsuario;
    private ArrayList<Usuario> amigos, pendentes;
    private ArrayList<Mensagem> recados;

    public Usuario(String login, String senha, String nomeDeUsuario) {
        this.login = login;
        this.senha = senha;
        this.nomeDeUsuario = nomeDeUsuario;
        amigos = new ArrayList();
        pendentes = new ArrayList();
        recados = new ArrayList();
    }

    public String getLogin() {

        return login;
    }

    public void setNomeDeUsuario(String nomeDeUsuario) {
        this.nomeDeUsuario = nomeDeUsuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNomeDeUsuario() {

        return nomeDeUsuario;
    }

    public String getSenha() {

        return senha;
    }

    public int adicionar(Usuario u) {//Adicionar amigos
        if (this.pendentes.contains(u)) {//Se já existe um convite pendente de "u"
            this.amigos.add(u);
            u.amigos.add(this);
            pendentes.remove(u);
            u.pendentes.remove(this);
            return 1;//1 corresponde à aceitação do pedido de amizade
        } else if (u.pendentes.contains(this)) {//Se "u" possui um convite do usuário
            return -1;//falha
        } else if (u.amigos.contains(this) && this.amigos.contains(u)) {//Se ambos são amigos
            return -1;//falha
        } else {
            u.pendentes.add(this);
        }
        return 0;//0 corresponde ao envio do pedido, permanecendo como 'pendente'
    }

    public void adicionarRecado(Mensagem recado) {/////
        recados.add(recado);
    }

    public boolean limparRecados() {
        if (recados.size() == 0) {
            return false;
        } else {
            recados.clear();
            return true;
        }
    }

    @Override //Comparador de usuários utilizando equals com o login
    public boolean equals(Object o) {
        Usuario u = (Usuario) o;
        if (this.login.equals(u.login)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return getLogin() + "\n";
    }

    public String listaDeAmigos() {//Exibir amigos
        String list = "";
        for (Usuario u : amigos) {
            list += u.toString();
        }
        return list;
    }

    public String listaDePendentes() {//Exibir pendentes
        String list = "";
        for (Usuario u : pendentes) {
            list += u.toString();
        }
        return list;
    }

    public String listaRecados() {//Exibir pendentes
        String list = "";
        for (int i = 0; i < recados.size(); i++) {
            list += (i + 1) + " - " + recados.get(i).toString() + "\n";/////
        }
        return list;
    }

    public String decodificar(int indice, String palavraChave) {/////
        if (recados.size() == 0) {
            return "Você não possui recados.";
        } else if (indice < 0 || indice >= recados.size()) {
            return "O número para a mensagem não está disponível.";
        } else if (!(recados.get(indice) instanceof MensagemSecreta)) {
            return "A mensagem selecionada não é uma mensagem secreta.";
        } else {
            String recado = ((MensagemSecreta) recados.get(indice)).verificarAcesso(palavraChave);
            return recado;
        }
    }

}
