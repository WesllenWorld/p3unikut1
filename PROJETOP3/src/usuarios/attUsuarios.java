package classes;

import java.util.ArrayList;

public class Usuario {

    private String login, senha, nomeDeUsuario;
    private ArrayList<Usuario> amigos, pendentes;
    private ArrayList<String> recados, murais;

    public Usuario(String login, String senha, String nomeDeUsuario) {
        this.login = login;
        this.senha = senha;
        this.nomeDeUsuario = nomeDeUsuario;
        amigos = new ArrayList();
        pendentes = new ArrayList();
        recados = new ArrayList();
        murais = new ArrayList();
    }
  
  public String listaMurais() {//Exibir murais
        String list = "";
        for (int i = 0; i < murais.size(); i++) {
            list += murais.get(i) + "\n";
        }
        return list;
    }

    public void adicionarMural(String mural) {
        murais.add(mural);
    
    }

    public boolean limparMural() {
        if (murais.size() == 0) {
            return false;
        } else {
            murais.clear();
            return true;
        }
    }
}
  
    
