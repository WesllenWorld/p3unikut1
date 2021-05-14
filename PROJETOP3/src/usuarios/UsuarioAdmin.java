package usuarios;

import interacoesDeContas.Contas;

public class UsuarioAdmin extends Usuario {


    public UsuarioAdmin(String login, String senha, String nomeDeUsuario) {
        super(login, senha, nomeDeUsuario);
        adm = true;
    }

    public void removerUsuario(Contas contas, Usuario u) {
        Usuario encontrado = contas.buscar(u);
        contas.removerLogin(encontrado);
    }

    public void alterarUsuario(Contas contas, Usuario u) {
        contas.editarCadastro(u);
    }

}
