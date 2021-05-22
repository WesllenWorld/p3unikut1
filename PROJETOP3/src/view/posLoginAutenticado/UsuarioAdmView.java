package view.posLoginAutenticado;

import controller.controlador.PrincipalController;
import controller.exceptions.AutoRequisicaoException;
import controller.exceptions.LoginInvalidoException;

import java.util.Scanner;

public class UsuarioAdmView extends MenuView{


    public void usuario(String logado, PrincipalController controllerPrincipal, Scanner in){
        int acaoDeConta, op;
        String login;
        boolean buscadoEAdmin;

        EditarPerfilView viewEditarPerfil = new EditarPerfilView();
        AmigosView viewAmigos = new AmigosView();
        RecadosView viewRecados = new RecadosView();
        MuraisView viewMurais = new MuraisView();
        EditarUmCadastroView viewEditarUmCadastro = new EditarUmCadastroView();
        MatchView viewMatch = new MatchView();

        do {
            menu();
            acaoDeConta = in.nextInt();// O que a conta pode fazer
            while (acaoDeConta < 0 || acaoDeConta > 6) {
                System.out.println("Opção inválida. Tente:");
                menu();
                acaoDeConta = in.nextInt();
            }
            switch (acaoDeConta) {
                case 1:
                    // Permita a um usuário cadastrado editar atributos de seu perfil
                    // Menu
                    menuEditar();
                    op = in.nextInt();
                    viewEditarPerfil.editarPerfil(in, logado, op, controllerPrincipal);
                    break;

                case 2:// Aba de amigos
                    menuAmigos();
                    op = in.nextInt();
                    while (op < 0 || op > 2) {
                        System.out.println("Opção inválida. Tente:");
                        menuAmigos();
                        op = in.nextInt();
                    }
                    viewAmigos.amigos(in, logado, op, controllerPrincipal);
                    break;

                case 3:// Recados
                    menuRecados();
                    op = in.nextInt();
                    while (op < 0 || op > 4) {
                        System.out.println("Opção inválida. Tente:");
                        menuRecados();
                        op = in.nextInt();
                    }
                    viewRecados.recados(in, logado, op, controllerPrincipal);
                    break;
                case 4: // Murais
                    menuMural();
                    op = in.nextInt();
                    while (op < 0 || op > 3) {
                        System.out.println("Opção inválida. Tente:");
                        menuMural();
                        op = in.nextInt();
                    }
                    viewMurais.murais(in, logado, op, controllerPrincipal);
                    break;

                case 5:
                    menuMatches();
                    op = in.nextInt();
                    while (op < 0 || op > 2) {
                        System.out.println("Opção inválida. Tente:");
                        menuMatches();
                        op = in.nextInt();
                    }
                    viewMatch.match(op, logado, in, controllerPrincipal);
                    break;

                case 6: // opções do administrador
                    menuAdministrador();
                    op = in.nextInt();
                    while (op < 0 || op > 2) {
                        menuAdministrador();
                        op = in.nextInt();
                    }
                    switch (op) {
                        case 1:
                            System.out.println("Qual o login do usuário que será editado?");
                            login = in.next();
                            try {
                                buscadoEAdmin = controllerPrincipal.tipoUsuario(login);
                                if (buscadoEAdmin) {
                                    System.out.println("Não é possível editar outros administradores.");
                                    break;
                                } else {
                                    menuEditar();
                                    op = in.nextInt();
                                    while (op < 1 || op > 3) {
                                        menuEditar();
                                        op = in.nextInt();
                                    }
                                    viewEditarUmCadastro.editarOutroPerfil(controllerPrincipal, in, login, op);
                                    System.out.println("O cadastro foi atualizado.");
                                }
                            } catch (LoginInvalidoException e) {
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 2:
                            System.out.println("Informe o login do usuário a ser excluído");
                            login = in.next();
                            try {
                                controllerPrincipal.removerLogin(logado, login);
                                System.out.println("Usuário removido com sucesso.");
                            } catch (LoginInvalidoException | AutoRequisicaoException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                    }
            }

        } while (acaoDeConta != 0);
    }
    public void menuAmigos() {
        System.out.println("Menu de amigos:");
        System.out.println("1 - Adicionar amigos");
        System.out.println("2 - Listar amigos ou pedidos de amizade pendentes");
        System.out.println("0 - Voltar");
    }
    public void menuRecados() {
        System.out.println("Menu de recados:");
        System.out.println("1 - Ver recados");
        System.out.println("2 - Mandar um recado");
        System.out.println("3 - Excluir recados");
        System.out.println("4 - Decodificar mensagem secreta");/////
        System.out.println("0 - Voltar");
    }

    public void menuAdministrador() {
        System.out.println("Menu administrador:");
        System.out.println("1 - Editar um usuário");
        System.out.println("2 - Excluir um perfil");
        System.out.println("0 - Voltar");
    }

    public void menuEditar() {
        System.out.println("O que deseja editar? ");
        System.out.println("1 - Apenas o Nome;");
        System.out.println("2 - Apenas a senha;");
        System.out.println("3 - Nome e senha.");
    }

    public void menuMural() {
        System.out.println("Menu de Murais:");
        System.out.println("1 - Ver Murais");
        System.out.println("2 - Criar Mural");
        System.out.println("3 - Excluir Seu Mural");
        System.out.println("0 - Voltar");
    }

    public void menuMatches() {
        System.out.println("Menu de Matches:");
        System.out.println("1 - Ver Matches");
        System.out.println("2 - Adicionar um Match");
        System.out.println("0 - Voltar");
    }

    public void menu(){
        System.out.println("Funções disponíveis:");
        System.out.println("1 – Editar perfil");
        System.out.println("2 – Amigos");
        System.out.println("3 - Recados");
        System.out.println("4 - Murais");
        System.out.println("5 - Match");
        System.out.println("6 - Administrador");
        System.out.println("0 - Desconectar");
    }
}
