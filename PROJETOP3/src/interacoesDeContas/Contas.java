package interacoesDeContas;

import mensagens.Mensagem;
import mensagens.MensagemSecreta;
import usuarios.*;

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

    public boolean tipoUsuario(Usuario u) {

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getLogin().equals(u.getLogin())) {
                u = usuarios.get(i);
            }
        }
        return u.isAdm();
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

    public int adicionarAmigo(Usuario usReceptor, Usuario usEmissor) {
        int retorno = -1;
        if (usEmissor.equals(usReceptor) == true) {//Se ambos são iguais
            return retorno;
        } else if (usuarios.contains(usReceptor) == false) {//Se o receptor está na lista
            return retorno;
        } else {
            for (int i = 0; i < usuarios.size(); i++) {//Buscar a posição do receptor
                if (usuarios.get(i).getLogin().equals(usReceptor.getLogin())) {
                    usReceptor = usuarios.get(i);
                    break;
                }
            }

            for (int i = 0; i < usuarios.size(); i++) {//Buscar a posição do emissor
                if (usuarios.get(i).getLogin().equals(usEmissor.getLogin())) {
                    retorno = usuarios.get(i).adicionar(usReceptor);
                    if (retorno == -1) {
                        return -1;
                    } else {

                    }
                }
            }
            return retorno;
        }
    }

    public String exibirAmigos(Usuario u, int op) {
        int i;
        String info;
        for (i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getLogin().equals(u.getLogin())) {
                break;
            }
        }
        if (op == 1) {
            info = usuarios.get(i).listaDeAmigos();
            return info;
        } else {
            info = usuarios.get(i).listaDePendentes();
            return info;
        }
    }

    public String exibirRecados(Usuario u) {
        int i;
        String info;
        for (i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getLogin().equals(u.getLogin())) {
                break;
            }
        }
        info = usuarios.get(i).listaRecados();
        return info;
    }

    public boolean enviarRecado(Usuario usEmissor, Usuario usReceptor, String mensagem, String palavraChave) {/////PARAMETRO A MAIS
        if (usEmissor.equals(usReceptor) == true) {//Se ambos são iguais
            return false;
        } else if (usuarios.contains(usReceptor) == false) {//Se o receptor está na lista
            return false;
        } else {
            if (palavraChave.equals("")) {/////
                Mensagem recado = new Mensagem(usEmissor.getLogin(), mensagem);
                for (int i = 0; i < usuarios.size(); i++) {//Buscar a posição do receptor
                    if (usuarios.get(i).getLogin().equals(usReceptor.getLogin())) {
                        usReceptor = usuarios.get(i);
                        break;
                    }
                }
                usReceptor.adicionarRecado(recado);
            } else {/////
                Mensagem recado = new MensagemSecreta(usEmissor.getLogin(), mensagem, palavraChave);/////
                for (int i = 0; i < usuarios.size(); i++) {//Buscar a posição do receptor
                    if (usuarios.get(i).getLogin().equals(usReceptor.getLogin())) {
                        usReceptor = usuarios.get(i);
                        break;
                    }
                }
                usReceptor.adicionarRecado(recado);
            }
        }
        return true;
    }

    public boolean excluirRecados(Usuario u) {
        for (int i = 0; i < usuarios.size(); i++) {//Buscar a posição do receptor
            if (usuarios.get(i).getLogin().equals(u.getLogin())) {
                u = usuarios.get(i);
                break;
            }
        }
        return u.limparRecados();
    }

    public String decodificarRecado(Usuario usEmissor, int indice, String palavraChave) {/////
        for (int i = 0; i < usuarios.size(); i++) {//Buscar a posição do usuário
            if (usuarios.get(i).getLogin().equals(usEmissor.getLogin())) {
                usEmissor = usuarios.get(i);
                break;
            }
        }

        String mensagemDeRetorno = usEmissor.decodificar(indice, palavraChave);
        return mensagemDeRetorno;
    }

    public void removerLogin(Usuario u) {
        usuarios.remove(u);
    }

    public Usuario buscar(Usuario u) {/////ATUALIZAR O RESTO DO CÓDIGO

        for (int i = 0; i < usuarios.size(); i++) {//Buscar a posição do usuário
            if (usuarios.get(i).getLogin().equals(u.getLogin())) {
                if (usuarios.get(i).isAdm()) {
                    return null; //jogar a exceção de ser adm;
                } else {
                    return usuarios.get(i);
                }
            }
        }

        return null;
    }

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
        if (u.limparMural()) {
            return true;
        } else {
            return false;
        }
    }


}
