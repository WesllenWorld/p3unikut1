package classes;

import java.util.ArrayList;
import java.util.List;

public class Contas {
    private List<Usuario> usuarios = new ArrayList();

    public boolean cadastrarUsuario(Usuario u) {//Método de inserção de um usuário
        if (usuarios.contains(u)) {
            return false;
        } else {
            usuarios.add(u);
            return true;
        }
    }

    public boolean login(Usuario u) { //Login do usuário

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getLogin().equals(u.getLogin())) {
                if (usuarios.get(i).getSenha().equals(u.getSenha())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void editarCadastro(Usuario u) {//Edição de perfil

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getLogin().equals(u.getLogin())) {
                if (u.getSenha() == null) {//Editar apenas o nome
                    usuarios.get(i).setNomeDeUsuario(u.getNomeDeUsuario());
                    System.out.println(usuarios.get(i).toString());
                    break;
                } else if (u.getNomeDeUsuario() == null) {//Editar apenas a senha
                    usuarios.get(i).setSenha(u.getSenha());
                    System.out.println(usuarios.get(i).toString());
                    break;
                } else {//Editar nome e senha
                    usuarios.set(i, u);
                    System.out.println(usuarios.get(i).toString());
                    break;
                }

            }
        }
    }


    //Demais métodos


}
