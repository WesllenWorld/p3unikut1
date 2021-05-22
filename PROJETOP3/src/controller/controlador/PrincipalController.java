package controller.controlador;

import controller.exceptions.*;
import model.exceptions.*;
import model.mensagens.Mensagem;
import model.mensagens.MensagemSecreta;
import model.usuarios.*;

import java.util.ArrayList;
import java.util.List;

public class PrincipalController {
    private List<Usuario> usuarios;

    public PrincipalController() {
        usuarios = new ArrayList();
    }

    public Usuario busca(Usuario u) {
        for (int i = 0; i < usuarios.size(); i++) {//Buscar a posição do usuário
            if (usuarios.get(i).getLogin().equals(u.getLogin())) {
                u = usuarios.get(i);
                return u;
            }
        }
        return null;
    }

    public boolean contem(Usuario u) {
        return usuarios.contains(u);
    }

    public void cadastro(String login, String senha, String nome) throws UsuarioExistenteException {//Método de inserção de um usuário
        Usuario u = new Usuario(login, senha, nome);
        if (!contem(u)) {
            u.adicionarConta(u, usuarios);
        } else {
            throw new UsuarioExistenteException("Login já utilizado.");
        }
    }

    public void cadastro(String login, String senha, String nome, String codigo) throws UsuarioExistenteException, CodigoAdmErradoException {//Método de inserção de um usuário
        String codigoCorreto = "Un1ku7@dm1n";
        if (!codigo.equals(codigoCorreto)) {
            throw new CodigoAdmErradoException("Código de administrador incorreto.");
        } else {
            UsuarioAdmin u = new UsuarioAdmin(login, senha, nome);
            if (!contem(u)) {
                u.adicionarConta(u, usuarios);
            } else {
                throw new UsuarioExistenteException("Login já utilizado.");
            }
        }
    }

    public void login(String login, String senha) throws LoginInvalidoException, SenhaInvalidaException { //Login do usuário
        Usuario u = new Usuario(login, "", "");
        u = busca(u);
        if (u == null) {
            throw new LoginInvalidoException("Login não encontrado.");
        } else {
            if (!u.verificaSenha(senha)) {
                throw new SenhaInvalidaException("Senha incorreta.");
            }
        }
    }

    public boolean tipoUsuario(String login) throws LoginInvalidoException {
        Usuario u = new Usuario(login, "", "");
        u = busca(u);
        if (u == null) {
            throw new LoginInvalidoException("Login inválido.");
        } else {
            return u.isAdm();
        }
    }

    public void editarCadastro(String login, String senha, String nome) {//Edição de perfil
        Usuario u = new Usuario(login, "", "");
        u = busca(u);
        if (senha == null && nome != null) {
            u.setNomeDeUsuario(nome);
        } else if (senha != null && nome == null) {
            u.setSenha(senha);
        } else {
            u.setSenha(senha);
            u.setNomeDeUsuario(nome);
        }
    }

    public boolean adicionarAmigo(String login, String logado) throws AutoRequisicaoException, LoginInvalidoException, JaSaoAmigosException, PedidoJaExistenteException {
        Usuario usReceptor = new Usuario(login, "", "");
        Usuario usEmissor = new Usuario(logado, "", "");
        if (usEmissor.equals(usReceptor)) {//Se ambos são iguais
            throw new AutoRequisicaoException("Pedido enviado ao mesmo usuário.");
        } else if (!contem(usReceptor)) {//Se o receptor não está na lista
            throw new LoginInvalidoException("Login não encontrado.");
        } else {
            try {
                usReceptor = busca(usReceptor);
                usEmissor = busca(usEmissor);
                return usEmissor.adicionar(usReceptor);
            } catch (JaSaoAmigosException | PedidoJaExistenteException e) {
                throw e;
            }
        }
    }

    public String exibirAmigos(String login) {
        Usuario u = new Usuario(login, "", "");
        String info;
        u = busca(u);
        info = u.listaDeAmigos();
        return info;
    }

    public String exibirPendentes(String login) {
        Usuario u = new Usuario(login, "", "");
        String info;
        u = busca(u);
        info = u.listaDePendentes();
        return info;
    }

    public String exibirRecados(String logado) {
        Usuario u = new Usuario(logado, "", "");
        String info;
        u = busca(u);
        info = u.listaRecados();
        return info;
    }

    public void enviarRecado(String logado, String login, String recado) throws AutoRequisicaoException, LoginInvalidoException {
        Usuario usEmissor = new Usuario(logado, "", "");
        Usuario usReceptor = new Usuario(login, "", "");
        if (usEmissor.equals(usReceptor)) {
            throw new AutoRequisicaoException("Mensagem para si mesmo.");
        } else if (!contem(usReceptor)) {
            throw new LoginInvalidoException("Login não encontrado.");
        } else {
            usReceptor = busca(usReceptor);
            Mensagem mensagem = new Mensagem(usEmissor.getLogin(), recado);
            usReceptor.adicionarRecado(mensagem);
        }
    }

    public void enviarRecado(String logado, String login, String recado, String palavraChave) throws AutoRequisicaoException, LoginInvalidoException {
        Usuario usEmissor = new Usuario(logado, "", "");
        Usuario usReceptor = new Usuario(login, "", "");
        if (usEmissor.equals(usReceptor)) {
            throw new AutoRequisicaoException("Mensagem para si mesmo.");
        } else if (!contem(usReceptor)) {
            throw new LoginInvalidoException("Login não encontrado.");
        } else {
            usReceptor = busca(usReceptor);
            MensagemSecreta mensagem = new MensagemSecreta(usEmissor.getLogin(), recado, palavraChave);
            usReceptor.adicionarRecado(mensagem);
        }
    }

    public void excluirRecados(String logado) {
        Usuario u = new Usuario(logado, "", "");
        u = busca(u);
        u.limparRecados();
    }

    public String decodificarRecado(String logado, int indice, String palavraChave) throws SenhaInvalidaException, ListaVaziaException, MensagemNaoSecretaException {/////
        Usuario u = new Usuario(logado, "", "");
        u = busca(u);
        try {
            return u.decodificar(indice, palavraChave);
        } catch (SenhaInvalidaException | ListaVaziaException | MensagemNaoSecretaException e) {
            throw e;
        }
    }

    public void removerLogin(String logado, String login) throws LoginInvalidoException, AutoRequisicaoException {
        UsuarioAdmin u = new UsuarioAdmin(logado, "", "");
        Usuario usuarioDeletado = new Usuario(login, "", "");

        if (!contem(usuarioDeletado)) {
            throw new LoginInvalidoException("Usuário não encontrado.");
        } else {
            if (usuarioDeletado.equals(u)) {
                throw new AutoRequisicaoException("Auto-Exclusão.");
            } else {
                u.removerUsuario(usuarios, usuarioDeletado);
            }
        }
    }

    public String exibirMural(String login) throws LoginInvalidoException {
        Usuario u = new Usuario(login, "", "");
        String info;
        u = busca(u);
        if (u == null) {
            throw new LoginInvalidoException("Login inválido.");
        } else {
            info = u.listaMurais();
            return info;
        }
    }

    public void enviarMural(String logado, String mensagem) {
        Usuario u = new Usuario(logado, "", "");
        u = busca(u);
        String recado = u.getLogin() + ":" + mensagem;
        u.adicionarMural(recado);
    }

    public void excluirMural(String logado) throws ListaVaziaException {
        Usuario u = new Usuario(logado, "", "");
        u = busca(u);
        try {
            u.limparMural();
        } catch (ListaVaziaException e) {
            throw e;
        }
    }

    public String exibirMatches(String logado) {
        Usuario u = new Usuario(logado, "","");
        String info;
        u = busca(u);
        info = u.listaDeMatches();
        return info;
    }

    public boolean adicionarMatch(String login, String logado) throws AutoRequisicaoException, LoginInvalidoException, JaPossuemMatchException, MatchJaFeitoException {
        Usuario usReceptor = new Usuario(login, "","");
        Usuario usEmissor = new Usuario(logado, "","");
        if (usEmissor.equals(usReceptor)) {//Se ambos são iguais
            throw new AutoRequisicaoException("Você não pode fazer um match com você mesmo.");
        } else if (!contem(usReceptor)) {//Se o receptor nao está na lista
            throw new LoginInvalidoException("Login não encontrado.");
        } else {
            try{
                usReceptor = busca(usReceptor);
                usEmissor = busca(usEmissor);
                boolean statusMatch = usEmissor.adicionarMatch(usReceptor);
                return statusMatch;
            }catch(JaPossuemMatchException | MatchJaFeitoException e){
                throw e;
            }

        }
    }

}
