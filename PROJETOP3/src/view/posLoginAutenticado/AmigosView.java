package view.posLoginAutenticado;

import controller.exceptions.AutoRequisicaoException;
import controller.exceptions.LoginInvalidoException;
import controller.controlador.PrincipalController;
import model.exceptions.JaSaoAmigosException;
import model.exceptions.ListaVaziaException;
import model.exceptions.PedidoJaExistenteException;

import java.util.Scanner;

public class AmigosView {

    public void amigos(Scanner in, String logado, int op, PrincipalController controllerPrincipal) {
        String login;
        Thread t;

        switch (op) {
            case 1:// Adicionar amigo
                in.nextLine();
                System.out.println("Informe o login do usuário a ser adicionado:");
                login = in.next();
                t = new Thread(new Runnable() {
                    public void run() {
                        try {
                            boolean pedidoPendente = controllerPrincipal.adicionarAmigo(login, logado);
                            if (pedidoPendente) {
                                System.out.println("Pedido de amizade enviado. Aguardando aceitação.");
                            } else {
                                System.out.println("Pedido de amizade aceito! Vocês agora são amigos.");
                            }
                        } catch (AutoRequisicaoException | LoginInvalidoException | JaSaoAmigosException
                                | PedidoJaExistenteException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                });
                t.start();
                break;

            case 2:
                menuListar();// Listar amigos e pedidos de amizade
                op = in.nextInt();
                while (op < 0 || op > 2) {
                    System.out.println("Opção inválida. Tente:");
                    menuListar();
                    op = in.nextInt();
                }
                switch (op) {
                    case 0:// Sair
                        break;
                    case 1:// Exibir lista de amigos
                        t = new Thread(new Runnable() {
                            public void run() {
                                try {
                                    String exibirLista = controllerPrincipal.exibirAmigos(logado);
                                    System.out.println("Lista de amigos: ");
                                    System.out.println(exibirLista);
                                } catch (ListaVaziaException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                        });
                        t.start();

                        break;
                    case 2: // Exibir pendentes
                        t = new Thread(new Runnable() {
                            public void run() {
                                try {
                                    String exibirLista1 = controllerPrincipal.exibirPendentes(logado);
                                    System.out.println(
                                            "Envie convites para remover a pendência e que vocês sejam amigos!");
                                    System.out.println("Há convites de amizade pendentes de:");
                                    System.out.println(exibirLista1);
                                } catch (ListaVaziaException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                        });
                        t.start();
                        break;
                }
                break;
        }
    }

    public void menuListar() {
        System.out.println("1 - Listar amigos");
        System.out.println("2 - Pedidos pendentes");
        System.out.println("0 - Sair");
    }
}
