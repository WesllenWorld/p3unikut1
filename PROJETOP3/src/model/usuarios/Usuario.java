package model.usuarios;

import controller.exceptions.SenhaInvalidaException;
import model.exceptions.JaSaoAmigosException;
import model.exceptions.ListaVaziaException;
import model.exceptions.MensagemNaoSecretaException;
import model.exceptions.PedidoJaExistenteException;
import model.mensagens.Mensagem;
import model.mensagens.MensagemSecreta;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String login, senha, nomeDeUsuario;
    private ArrayList<Usuario> amigos, pendentes;
    private ArrayList<Mensagem> recados;
    private ArrayList<String> murais;

    boolean adm;

    public Usuario(String login, String senha, String nomeDeUsuario) {
        this.login = login;
        this.senha = senha;
        this.nomeDeUsuario = nomeDeUsuario;
        amigos = new ArrayList();
        pendentes = new ArrayList();
        recados = new ArrayList();
        murais = new ArrayList();
        adm = false;
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

    public void adicionarConta(Usuario u, List<Usuario> lista) {
        lista.add(u);
    }

    public boolean verificaSenha(String senha) {
        return (this.getSenha().equals(senha));
    }

    public boolean adicionar(Usuario u) throws JaSaoAmigosException, PedidoJaExistenteException {//Adicionar amigos
        if (this.contem(this.pendentes, u)) {//Se já existe um convite pendente de "u"
            this.adicionarAmigo(u);
            u.adicionarAmigo(this);
            this.removerPendente(u);
            u.removerPendente(this);
            return false;//corresponde à aceitação do pedido de amizade
        } else if (u.contem(u.pendentes, this)) {//Se "u" possui um convite do usuário
            throw new PedidoJaExistenteException("Já existe um pedido pendente. Aguarde a aceitação do usuário solicitado.");//falha
        } else if (u.contem(u.amigos, this) && this.contem(amigos, u)) {//Se ambos são amigos
            throw new JaSaoAmigosException("Você e o usuário solicitado já são amigos.");//falha
        } else {
            u.adicionarPendente(this);
            return true;//Pedido enviado e pendente
        }
    }

    public void adicionarAmigo(Usuario u) {
        amigos.add(u);
    }

    public void adicionarPendente(Usuario u) {
        pendentes.add(u);
    }

    public void removerPendente(Usuario u) {
        pendentes.remove(u);
    }

    public void adicionarRecado(Mensagem recado) {/////
        recados.add(recado);
    }

    public void limparRecados() {
        recados.clear();
    }

    @Override //Comparador de usuários utilizando equals com o login
    public boolean equals(Object o) {
        Usuario u = (Usuario) o;
        return this.login.equals(u.login);
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

    public String listaRecados() {//Exibir pendentes
        String list = "";
        for (int i = 0; i < recados.size(); i++) {
            list += (i + 1) + " - " + recados.get(i).toString() + "\n";/////
        }
        return list;
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

    public void limparMural() throws ListaVaziaException {
        if (isEmpty(murais)) {
            throw new ListaVaziaException("Lista vazia.");
        } else {
            murais.clear();
        }
    }

    public String decodificar(int indice, String palavraChave) throws ListaVaziaException, MensagemNaoSecretaException, SenhaInvalidaException {/////
        if (isEmpty(recados)) {
            throw new ListaVaziaException("Lista vazia");
        } else if (!indiceValido(indice, recados.size())) {
            throw new IndexOutOfBoundsException("Indice indisponível.");
        } else if (!recados.get(indice).isSecreta()) {
            throw new MensagemNaoSecretaException("Mensagem é comum.");
        } else {
            try {
                return ((MensagemSecreta) recados.get(indice)).verificarAcesso(palavraChave);
            } catch (SenhaInvalidaException e) {
                throw e;
            }

        }
    }

    public boolean indiceValido(int i, int tamanho) {

        return i >= 0 && i < tamanho + 1;
    }

    public boolean contem(ArrayList lista, Usuario u) {
        return lista.contains(u);
    }

    public boolean isEmpty(ArrayList lista) {
        return lista.size() == 0;
    }

    public boolean isAdm() {
        return adm;
    }

}
