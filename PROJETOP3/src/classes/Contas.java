package classes;

import java.util.ArrayList;
import java.util.List;

public class Contas {
    private List<Usuario> usuarios = new ArrayList();

    public boolean cadastrarUsuario(Usuario u){
        if(usuarios.contains(u)){
            return false;
        }else{
            usuarios.add(u);
            return true;
        }
    }




}
