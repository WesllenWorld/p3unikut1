package classes;

import java.util.ArrayList;
import java.util.List;

public class Contas {
    private List<Usuario> usuarios = new ArrayList();

    public boolean cadastrarUsuario(Usuario u){//Método de inserção de um usuário
        if(usuarios.contains(u)){
            return false;
        }else{
            usuarios.add(u);
            return true;
        }
    }

    public boolean login(Usuario u){//Login

        for(int i = 0; i<usuarios.size();i++){
            if(usuarios.get(i).getLogin().equals(u.getLogin())){
                if(usuarios.get(i).getSenha().equals(u.getSenha())){
                    return true;
                }
            }
        }
        return false;
    }

    //Demais métodos


}
