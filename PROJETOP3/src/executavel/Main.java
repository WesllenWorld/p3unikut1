package executavel;

import usuarios.*;
import interacoesDeContas.*;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Administrador ad;
		Usuario us, usEmissor, usReceptor;
		Contas social = new Contas();
		Scanner in = new Scanner(System.in);
		Scanner esc = new Scanner(System.in);
		String nome, nomeAdm, login, senha, logado, exibirListas, recado, loginadm, senhaadm, editUsers;
		int op, acaoDeConta, acaoDeContaAdm;
		boolean EstadoDeLogado, EstadoDeLogadoAdm;
		Usuario u;

		do {
			menuInicial();
			op = in.nextInt();
			while (op > 4 || op < 0) {
				System.out.println("Opção inválida.");
				menuInicial();
				op = in.nextInt();
			}
			switch (op) {
			case 1: // criação de conta
				in.nextLine();
				System.out.println("Insira os seguintes dados para criar sua conta:");
				System.out.println("Nome de login:");
				login = in.next();
				System.out.println("Senha do usuário:");
				senha = in.next();
				System.out.println("Nome de usuário (opcional. Seu nome será visto como " + "\"" + "convidado" + "\""
						+ " caso não preencha o espaço.");
				in.nextLine();
				nome = in.nextLine();
				if (nome.equals("")) {
					nome = "Convidado";
				}
				us = new Usuario(login, senha, nome);
				boolean cadastroEfetivado = social.cadastrarUsuario(us);
				if (cadastroEfetivado) {
					System.out.println("Cadastro concluído com sucesso.");
				} else {
					System.out.println("Não foi possível efetivar seu cadastro pois o nome de usuário já está em uso.");
				}

				break;

			case 3:
				in.nextLine();
				System.out.println("Insira os dados para criar sua conta ADMINISTRADORA :");
				System.out.println("Nome do login administrador :");
				loginadm = in.next();
				System.out.println("Senha de administrador");
				senhaadm = in.next();
				System.out.println("Nome de Administrador (opcional. Seu nome será visto como " + "\"" + "convidado"
						+ "\"" + " caso não preencha o espaço, para obter mais segurança.");
				in.nextLine();
				nomeAdm = in.nextLine();
				ad = new Administrador(loginadm, senhaadm, nomeAdm);
				boolean CadastroEfetivado = social.cadastrarUsuarioAdm(ad);

				if (nomeAdm.equals("")) {
					nomeAdm = "Convidado";
					if (CadastroEfetivado) {
						System.out.println("Cadastro concluído com sucesso.");
					} else {
						System.out.println(
								"Não foi possível efetivar seu cadastro pois o nome de usuário já está em uso.");
					}
				}
				break;

			case 2: // login + interações do usuário

				System.out.println("Escolha seu Método :");
				System.out.println(" 1 - Sou Usuário :");
				System.out.println(" 2 - Sou Administrador UNIKUT");
				System.out.println("Opção escolhida : ");
				int escolha = esc.nextInt();

				// Login como usuario
				// Login como usuario
				// Login como usuario

				if (escolha == 1) {

					System.out.println("Nome de login: ");
					login = in.next();
					System.out.println("Senha: ");
					senha = in.next();

					us = new Usuario(login, senha, "");
					EstadoDeLogado = social.login(us);
					if (EstadoDeLogado) {
						System.out.println("Bem-vindo!");
						logado = login;
						do {
							menuDeConta();
							acaoDeConta = in.nextInt();// O que a conta pode fazer
							while (acaoDeConta < 0 || acaoDeConta > 3) {
								System.out.println("Opção inválida. Tente:");
								menuDeConta();
								acaoDeConta = in.nextInt();
							}
							switch (acaoDeConta) {
							case 1:
								// Permita a um usuário cadastrado editar atributos de seu perfil
								// Menu
								System.out.println("O que deseja editar? ");
								System.out.println("1 - Apenas o Nome;");
								System.out.println("2 - Apenas a senha;");
								System.out.println("3 - Nome e senha.");
								op = in.nextInt();
								switch (op) {
								case 1:// Editar nome
									in.nextLine();
									System.out.println("Digite o novo nome: ");
									nome = in.nextLine();
									us = new Usuario(logado, null, nome);
									social.editarCadastro(us);
									System.out.println("Seu cadastro foi atualizado.");
									break;
								case 2:// Editar senha
									in.nextLine();
									System.out.println("Digite a nova senha: ");
									senha = in.next();
									us = new Usuario(logado, senha, null);
									social.editarCadastro(us);
									System.out.println("Seu cadastro foi atualizado.");
									break;
								case 3:// Editar nome e senha
									in.nextLine();
									System.out.println("Digite o novo nome: ");
									nome = in.nextLine();
									System.out.println("Digite a nova senha: ");
									senha = in.next();
									us = new Usuario(logado, senha, nome);
									social.editarCadastro(us);
									System.out.println("Seu cadastro foi atualizado.");
									break;
								}
								break;

							case 2:// Aba de amigos
								menuAmigos();
								op = in.nextInt();
								while (op < 0 || op > 2) {
									System.out.println("Opção inválida. Tente:");
									menuAmigos();
									op = in.nextInt();
								}
								switch (op) {
								case 1:// Adicionar amigo
									in.nextLine();
									System.out.println("Informe o login do usuário a ser adicionado:");
									login = in.next();
									usReceptor = new Usuario(login, "", "");
									usEmissor = new Usuario(logado, "", "");
									int statusDoPedido = social.adicionarAmigo(usReceptor, usEmissor);
									if (statusDoPedido == 0) {
										System.out.println("Pedido de amizade enviado. Aguardando aceitação.");
									} else if (statusDoPedido == 1) {
										System.out.println("Pedido de amizade aceito! Vocês agora são amigos.");
									} else {
										System.out.println(
												"Não foi possível enviar um pedido de amizade para o usuário solicitado. Verifique se o login está correto e tente novamente.");
									}

									break;
								case 2:
									System.out.println("1 - Listar amigos");
									System.out.println("2 - Pedidos pendentes");
									System.out.println("0 - Sair");// Listar amigos e pedidos de amizade
									op = in.nextInt();
									while (op < 0 || op > 2) {
										System.out.println("Opção inválida. Tente:");
										menuAmigos();
										op = in.nextInt();
									}

									switch (op) {
									case 0:// Sair
										break;
									case 1:// Exibir lista de amigos
										usEmissor = new Usuario(logado, "", "");
										exibirListas = social.exibirAmigos(usEmissor, op);
										System.out.println("Lista de amigos:");
										System.out.println(exibirListas);
										break;
									case 2: // Exibir pendentes
										usEmissor = new Usuario(logado, "", "");
										exibirListas = social.exibirAmigos(usEmissor, op);
										System.out.println(
												"Envie convites para remover a pendência e que vocês sejam amigos!");
										System.out.println("Há convites de amizade pendentes de:");
										System.out.println(exibirListas);
										break;
									}
									break;
								}
								break;

							case 3:// Recados
								menuRecados();
								op = in.nextInt();
								while (op < 0 || op > 3) {
									System.out.println("Opção inválida. Tente:");
									menuRecados();
									op = in.nextInt();
								}
								switch (op) {
								case 0:// Sair
									break;
								case 1:// Exibir recados
									usEmissor = new Usuario(logado, "", "");
									exibirListas = social.exibirRecados(usEmissor);
									System.out.println("Mensagens recentes serão sempre as que estão mais em baixo.");
									System.out.println("Recados:");
									System.out.println(exibirListas);
									break;
								case 2: // Enviar recado
									in.nextLine();
									System.out.println("Insira aqui o login do destinatário: ");
									login = in.next();
									System.out.println("Escreva seu recado: ");
									in.nextLine();
									recado = in.nextLine();
									usEmissor = new Usuario(logado, "", "");
									usReceptor = new Usuario(login, "", "");
									boolean recadoEnviado = social.enviarRecado(usEmissor, usReceptor, recado);
									if (recadoEnviado) {
										System.out.println(
												"Mensagem enviada. Envie mais mensagens ou aguarde uma resposta!");
									} else {
										System.out.println(
												"Não foi possível enviar o recado. Verifique os dados de entrada e tente novamente.");
									}
									break;
								case 3:// Limpar caixa de recados
									usEmissor = new Usuario(logado, "", "");
									boolean limparRecados = social.excluirRecados(usEmissor);
									if (limparRecados) {
										System.out.println("Seus recados foram excluídos com sucesso.");
									} else {
										System.out.println("Você não possui nenhum recado para ser excluído.");
									}
									break;
								}
								break;
							}

						} while (acaoDeConta != 0);
					} else {
						System.out.println("Login ou senha inválidos. Tente novamente.");
					}

//login administrador
//login administrador
//login administrador
//login administrador

					break;
				}

				if (escolha == 2) {
					System.out.println("Nome de login administrador: ");
					loginadm = in.next();
					System.out.println("Password: ");
					senhaadm = in.next();

					ad = new Administrador(loginadm, senhaadm, "");
					EstadoDeLogadoAdm = social.loginAdmin(ad);
					if (EstadoDeLogadoAdm) {
						System.out.println("Bem-vindo!");
						String logadoadm = loginadm;
						do {
							menuAdmin();
							acaoDeContaAdm = in.nextInt();// O que a conta pode fazer
							while (acaoDeContaAdm < 0 || acaoDeContaAdm > 5) {
								System.out.println("Opção inválida. Tente:");
								menuAdmin();
								acaoDeContaAdm = in.nextInt();
							}
							switch (acaoDeContaAdm) {
							case 1:
								// Permita a um usuário cadastrado editar atributos de seu perfil
								// Menu
								System.out.println("O que deseja editar? ");
								System.out.println("1 - Apenas o Nome;");
								System.out.println("2 - Apenas a senha;");
								System.out.println("3 - Nome e senha.");
								op = in.nextInt();
								switch (op) {
								case 1:// Editar nome
									in.nextLine();
									System.out.println("Digite o novo nome: ");
									nomeAdm = in.nextLine();
									ad = new Administrador(logadoadm, null, nomeAdm);
									social.editarCadastroAdmin(ad);
									System.out.println("Seu cadastro foi atualizado.");
									break;
								case 2:// Editar senha
									in.nextLine();
									System.out.println("Digite a nova senha: ");
									senha = in.next();
									ad = new Administrador(logadoadm, senha, null);
									social.editarCadastroAdmin(ad);
									System.out.println("Seu cadastro foi atualizado.");
									break;
								case 3:// Editar nome e senha
									in.nextLine();
									System.out.println("Digite o novo nome: ");
									nomeAdm = in.nextLine();
									System.out.println("Digite a nova senha: ");
									senhaadm = in.next();
									ad = new Administrador(logadoadm, senhaadm, nomeAdm);
									social.editarCadastroAdmin(ad);
									System.out.println("Seu cadastro foi atualizado.");
									break;

								}
								break;

							case 2:// Aba de amigos
								menuAmigos();
								op = in.nextInt();
								while (op < 0 || op > 2) {
									System.out.println("Opção inválida. Tente:");
									menuAmigos();
									op = in.nextInt();
								}
								switch (op) {
								case 1:// Adicionar amigo
									in.nextLine();
									System.out.println("Informe o login do usuário a ser adicionado:");
									login = in.next();
									usReceptor = new Usuario(login, "", "");
									usEmissor = new Usuario(logadoadm, "", "");
									int statusDoPedido = social.adicionarAmigo(usReceptor, usEmissor);
									if (statusDoPedido == 0) {
										System.out.println("Pedido de amizade enviado. Aguardando aceitação.");
									} else if (statusDoPedido == 1) {
										System.out.println("Pedido de amizade aceito! Vocês agora são amigos.");
									} else {
										System.out.println(
												"Não foi possível enviar um pedido de amizade para o usuário solicitado. Verifique se o login está correto e tente novamente.");
									}

									break;
								case 2:
									System.out.println("1 - Listar amigos");
									System.out.println("2 - Pedidos pendentes");
									System.out.println("0 - Sair");// Listar amigos e pedidos de amizade
									op = in.nextInt();
									while (op < 0 || op > 2) {
										System.out.println("Opção inválida. Tente:");
										menuAmigos();
										op = in.nextInt();
									}

									switch (op) {
									case 0:// Sair
										break;
									case 1:// Exibir lista de amigos
										usEmissor = new Usuario(logadoadm, "", "");
										exibirListas = social.exibirAmigos(usEmissor, op);
										System.out.println("Lista de amigos:");
										System.out.println(exibirListas);
										break;
									case 2: // Exibir pendentes
										usEmissor = new Usuario(logadoadm, "", "");
										exibirListas = social.exibirAmigos(usEmissor, op);
										System.out.println(
												"Envie convites para remover a pendência e que vocês sejam amigos!");
										System.out.println("Há convites de amizade pendentes de:");
										System.out.println(exibirListas);
										break;
									}
									break;
								}
								break;

							case 3:// Recados
								menuRecados();
								op = in.nextInt();
								while (op < 0 || op > 3) {
									System.out.println("Opção inválida. Tente:");
									menuRecados();
									op = in.nextInt();
								}
								switch (op) {
								case 0:// Sair
									break;
								case 1:// Exibir recados
									usEmissor = new Usuario(logadoadm, "", "");
									exibirListas = social.exibirRecados(usEmissor);
									System.out.println("Mensagens recentes serão sempre as que estão mais em baixo.");
									System.out.println("Recados:");
									System.out.println(exibirListas);
									break;
								case 2: // Enviar recado
									in.nextLine();
									System.out.println("Insira aqui o login do destinatário: ");
									login = in.next();
									System.out.println("Escreva seu recado: ");
									in.nextLine();
									recado = in.nextLine();
									usEmissor = new Usuario(logadoadm, "", "");
									usReceptor = new Usuario(login, "", "");
									boolean recadoEnviado = social.enviarRecado(usEmissor, usReceptor, recado);
									if (recadoEnviado) {
										System.out.println(
												"Mensagem enviada. Envie mais mensagens ou aguarde uma resposta!");
									} else {
										System.out.println(
												"Não foi possível enviar o recado. Verifique os dados de entrada e tente novamente.");
									}
									break;
								case 3:// Limpar caixa de recados
									usEmissor = new Usuario(logadoadm, "", "");
									boolean limparRecados = social.excluirRecados(usEmissor);
									if (limparRecados) {
										System.out.println("Seus recados foram excluídos com sucesso.");
									} else {
										System.out.println("Você não possui nenhum recado para ser excluído.");
									}
									break;
								}
								break;

							case 4:
								in.nextLine();
								System.out.println("Insira o Login do Usuário que deseja alterar : ");
								editUsers = in.nextLine();
								System.out.println("O que deseja editar do usuario? ");
								System.out.println("1 - Apenas o Nome;");
								System.out.println("2 - Apenas a senha;");
								System.out.println("3 - Nome e senha.");
								op = in.nextInt();
								switch (op) {
								case 1:// Editar nome
									in.nextLine();
									System.out.println("Digite o novo nome: ");
									nome = in.nextLine();
									u = new Usuario(editUsers, null, nome);
									social.editarCadastroUsuarios(u);
									System.out.println("Seu cadastro foi atualizado.");
									break;
								case 2:// Editar senha
									in.nextLine();
									System.out.println("Digite a nova senha: ");
									senha = in.next();
									u = new Usuario(editUsers, senha, null);
									social.editarCadastroUsuarios(u);
									System.out.println("Seu cadastro foi atualizado.");
									break;
								case 3:// Editar nome e senha
									in.nextLine();
									System.out.println("Digite o novo nome: ");
									nome = in.nextLine();
									System.out.println("Digite a nova senha: ");
									senha = in.next();
									u = new Usuario(editUsers, senha, nome);
									social.editarCadastroUsuarios(u);
									System.out.println("Seu cadastro foi atualizado.");
									break;

								}
								break;

							case 5:
								in.nextLine();
								System.out.println("Digite o Login do Usuário que deseja Excluir do SISTEMA");
								String exclusaoconta;
								exclusaoconta = in.nextLine();
								u = new Usuario(exclusaoconta, "", "");
								if (social.ExcluirContas(u) == true) {
									System.out.println("Conta removida com sucesso.");
								} else {
									System.out.println("Conta não removida / não existe.");
								}
								break;
							}

						} while (acaoDeContaAdm != 0);

					} else {
						System.out.println("Login ou senha inválidos. Tente novamente.");
					}

				}

				break;
			}

		} while (op != 0);

	}

	public static void menuInicial() {// Menu inicial
		System.out.println("BEM-VINDO AO UNIKUT, A MAIS NOVA FORMA DE MANTER A UNICAP MAIS PERTINHO DE VOCÊ.");

		System.out.println("Tecle - 1 – Para efetuar o Cadastro como Usuário.");
		System.out.println("Tecle - 2 – Para logar agora mesmo, caso já faça parte da comunidade.");
		System.out.println("Tecle - 3 - Para efetuar o Cadastro como Administrador");
		System.out.println("Tecle 0 – para sair do programa.");
	}

	public static void menuDeConta() {// Menu do usuário logado
		System.out.println("Funções disponíveis:");
		System.out.println("1 – Editar perfil");
		System.out.println("2 – Amigos");
		System.out.println("3 - Recados");
		System.out.println("0 - Desconectar");
	}

	public static void menuAmigos() {
		System.out.println("Menu de amigos:");
		System.out.println("1 - Adicionar amigos");
		System.out.println("2 - Listar amigos ou pedidos de amizade pendentes");
		System.out.println("0 - Voltar");
	}

	public static void menuRecados() {
		System.out.println("Menu de recados:");
		System.out.println("1 - Ver recados");
		System.out.println("2 - Mandar um recado");
		System.out.println("3 - Excluir recados");
		System.out.println("0 - Voltar");
	}

	public static void menuAdmin() {
		System.out.println("Funções disponíveis:");
		System.out.println("1 – Editar perfil");
		System.out.println("2 – Amigos");
		System.out.println("3 - Recados");
		System.out.println("4 - Editar Perfil de Usuários ");
		System.out.println("5 - Excluir Contas de Usuários ");
		System.out.println("0 - Desconectar");
	}

}
