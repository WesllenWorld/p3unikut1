package model.usuarios;

import java.util.List;

public class UsuarioAdmin extends Usuario {


    public UsuarioAdmin(String login, String senha, String nomeDeUsuario) {
        super(login, senha, nomeDeUsuario);
        adm = true;
    }

    public void removerUsuario(List<Usuario> usuarios, Usuario u) {
        usuarios.remove(u);
    }


    
}
