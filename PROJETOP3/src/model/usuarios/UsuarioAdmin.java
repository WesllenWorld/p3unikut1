package model.usuarios;

import java.util.List;

public class UsuarioAdmin extends model.usuarios.Usuario {


    public UsuarioAdmin(String login, String senha, String nomeDeUsuario) {
        super(login, senha, nomeDeUsuario);
        adm = true;
    }

    public void removerUsuario(List<model.usuarios.Usuario> usuarios, Usuario u) {
        usuarios.remove(u);
    }


    
}
