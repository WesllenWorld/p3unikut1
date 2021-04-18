package interacoesDeContas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import usuarios.*; 
    
public class Contas {

	private List<Usuario> usuarios = new ArrayList();
	private List<Administrador> administrador = new ArrayList();

	public boolean cadastrarUsuarioAdm(Administrador ad) {// Método de inserção de um usuário
		if (administrador.contains(ad)) {
			return false;
		} else {
			administrador.add(ad);
			return true;
		}
	}

	public boolean cadastrarUsuario(Usuario u) {// Método de inserção de um usuário
		if (usuarios.contains(u)) {
			return false;
		} else {
			usuarios.add(u);
			return true;
		}
	}

	public boolean login(Usuario u) { // Login do usuário

		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getLogin().equals(u.getLogin())) {
				if (usuarios.get(i).getSenha().equals(u.getSenha())) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean loginAdmin(Administrador ad) { // Login do usuário

		for (int i = 0; i < administrador.size(); i++) {
			if (administrador.get(i).getLoginAdm().equals(ad.getLoginAdm())) {
				if (administrador.get(i).getSenhaAdm().equals(ad.getSenhaAdm())) {
					return true;
				}
			}
		}
		return false;
	}

	public void editarCadastro(Usuario u) {// Edição de perfil

		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getLogin().equals(u.getLogin())) {
				if (u.getSenha() == null) {// Editar apenas o nome
					usuarios.get(i).setNomeDeUsuario(u.getNomeDeUsuario());
					System.out.println(usuarios.get(i).toString());
					break;
				} else if (u.getNomeDeUsuario() == null) {// Editar apenas a senha
					usuarios.get(i).setSenha(u.getSenha());
					System.out.println(usuarios.get(i).toString());
					break;
				} else {// Editar nome e senha
					usuarios.set(i, u);
					System.out.println(usuarios.get(i).toString());
					break;
				}

			}
		}
	}

	public void editarCadastroAdmin(Administrador ad) {// Edição de perfil

		for (int i = 0; i < administrador.size(); i++) {
			if (administrador.get(i).getLoginAdm().equals(ad.getLoginAdm())) {
				if (ad.getSenhaAdm() == null) {// Editar apenas o nome
					administrador.get(i).setNomeDeAdm(ad.getNomeDeAdm());
					System.out.println(usuarios.get(i).toString());
					break;
				} else if (ad.getNomeDeAdm() == null) {// Editar apenas a senha
					administrador.get(i).setSenhaAdm(ad.getSenhaAdm());
					System.out.println(administrador.get(i).toString());
					break;
				} else {// Editar nome e senha
					administrador.set(i, ad);
					System.out.println(usuarios.get(i).toString());
					break;
				}
			}
		}
	}

	public void editarCadastroUsuarios(Usuario u) {

		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getLogin().equals(u.getLogin())) {
				if (u.getSenha() == null) {// Editar apenas o nome
					usuarios.get(i).setNomeDeUsuario(u.getNomeDeUsuario());
					System.out.println(usuarios.get(i).toString());
					break;
				} else if (u.getNomeDeUsuario() == null) {// Editar apenas a senha
					usuarios.get(i).setSenha(u.getSenha());
					System.out.println(usuarios.get(i).toString());
					break;
				} else {// Editar nome e senha
					usuarios.set(i, u);
					System.out.println(usuarios.get(i).toString());
					break;
				}

			}
		}

	}

	public int adicionarAmigo(Usuario usReceptor, Usuario usEmissor) {
		int retorno = -1;
		if (usEmissor.equals(usReceptor) == true) {// Se ambos são iguais
			return retorno;
		} else if (usuarios.contains(usReceptor) == false) {// Se o receptor está na lista
			return retorno;
		} else {
			for (int i = 0; i < usuarios.size(); i++) {// Buscar a posição do receptor
				if (usuarios.get(i).getLogin().equals(usReceptor.getLogin())) {
					usReceptor = usuarios.get(i);
					break;
				}
			}

			for (int i = 0; i < usuarios.size(); i++) {// Buscar a posição do emissor
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

	public boolean enviarRecado(Usuario usEmissor, Usuario usReceptor, String mensagem) {
		String recado = usEmissor.getLogin() + ":" + mensagem;
		if (usEmissor.equals(usReceptor) == true) {// Se ambos são iguais
			return false;
		} else if (usuarios.contains(usReceptor) == false) {// Se o receptor está na lista
			return false;
		} else {
			for (int i = 0; i < usuarios.size(); i++) {// Buscar a posição do receptor
				if (usuarios.get(i).getLogin().equals(usReceptor.getLogin())) {
					usReceptor = usuarios.get(i);
					break;
				}
			}
			usReceptor.adicionarRecado(recado);
		}
		return true;
	}

	public boolean excluirRecados(Usuario u) {
		for (int i = 0; i < usuarios.size(); i++) {// Buscar a posição do receptor
			if (usuarios.get(i).getLogin().equals(u.getLogin())) {
				u = usuarios.get(i);
				break;
			}
		}
		return u.limparRecados();
	}

	public boolean ExcluirContas(Usuario u) {

		if (usuarios.size() == 0) {
			System.out.println("A Lista está vazia.");
			return false;
		} else if (usuarios.contains(u)) {
			usuarios.remove(u);
			System.out.println("Usuário removido");
			return true;
		} else {

			System.out.println("Usuário não consta no sistema. ");
			return false;
		}
	}

}
