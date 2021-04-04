package classes;

import java.util.ArrayList;

public class Usuario {
    private String login, senha, nomeDeUsuario;
    private ArrayList<Usuario> amigos, pendentes;
    private ArrayList<String> recados;

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

    public boolean adicionar(Usuario u) {//Adicionar amigos
        if (this.pendentes.contains(u)) {//Se já existe um convite pendente de "u"
            this.amigos.add(u);
            u.amigos.add(this);
            pendentes.remove(u);
            u.pendentes.remove(this);

        } else if (u.pendentes.contains(this)) {//Se "u" possui um convite do usuário
            return false;
        } else if (u.amigos.contains(this) && this.amigos.contains(u)) {//Se ambos são amigos
            return false;
        } else {
            u.pendentes.add(this);
        }
        return true;
    }

    public void adicionarRecado(String recado) {//Adiciona String([Quem enviou]: + [recado]) na lista de recados
        recados.add(recado);
    }

    public boolean limparRecados() {//Excluir recados
        if (recados.size() == 0) {//Caso a lista esteja vazia
            return false;//Retorna falso, indicando que não excluiu nada (pois não havia nada)
        } else {//Caso a lista possua pelo menos um recado
            recados.clear();//Remove todos os recados
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

    public String listaRecados() {//Exibir recados
        String list = "";
        for (int i = 0; i < recados.size(); i++) {
            list += recados.get(i) + "\n";
        }
        return list;
    }


}
