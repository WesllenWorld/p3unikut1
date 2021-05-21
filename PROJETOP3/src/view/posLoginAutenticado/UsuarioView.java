package view.posLoginAutenticado;

import controller.controlador.PrincipalController;

import java.util.Scanner;

public class UsuarioView extends MenuView {

    public void usuario(String logado, PrincipalController controllerPrincipal, Scanner in) {
        int acaoDeConta;

        EditarPerfilView viewEditarPerfil = new EditarPerfilView();
        AmigosView viewAmigos = new AmigosView();
        RecadosView viewRecados = new RecadosView();
        MuraisView viewMurais = new MuraisView();

        do {
            menu();
            acaoDeConta = in.nextInt();// O que a conta pode fazer
            while (acaoDeConta < 0 || acaoDeConta > 4) {
                System.out.println("Opção inválida. Tente:");
                menu();
                acaoDeConta = in.nextInt();
            }
            switch (acaoDeConta) {
                case 1:
                    // Permita a um usuário cadastrado editar atributos de seu perfil
                    // Menu
                    menuEditar();
                    int op = in.nextInt();
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

    public void menu() {
        System.out.println("Funções disponíveis:");
        System.out.println("1 – Editar perfil");
        System.out.println("2 – Amigos");
        System.out.println("3 - Recados");
        System.out.println("4 - Murais");
        System.out.println("0 - Desconectar");
    }
}
