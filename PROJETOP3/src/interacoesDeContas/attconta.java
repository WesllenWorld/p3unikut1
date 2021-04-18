package classes;

import java.util.ArrayList;
import java.util.List;

public class Contas {
    private List<Usuario> usuarios = new ArrayList();
  
  public String exibirMural(Usuario usReceptor) {
        String info;
        for (int i = 0; i < usuarios.size(); i++) {//Buscar a posição do receptor
            if (usuarios.get(i).getLogin().equals(usReceptor.getLogin())) {
                usReceptor = usuarios.get(i);
                break;
            }
        }
        info = usReceptor.listaMurais();
        return info;
    }

    public boolean enviarMural(Usuario u, String mensagem) {
        String recado = u.getLogin() + ":" + mensagem;
        for (int i = 0; i < usuarios.size(); i++) {//Buscar a posição do receptor
            if (usuarios.get(i).getLogin().equals(u.getLogin())) {
                u = usuarios.get(i);
                break;
            }
        }
            u.adicionarMural(recado);
            return true;
    }

    public boolean excluirMural(Usuario u) {
        for (int i = 0; i < usuarios.size(); i++) {//Buscar a posição do receptor
            if (usuarios.get(i).getLogin().equals(u.getLogin())) {
                u = usuarios.get(i);
                break;
            }
        }
        if(u.limparMural()==true){
            return true;
        } else{
            return false;
        }
    }


}
