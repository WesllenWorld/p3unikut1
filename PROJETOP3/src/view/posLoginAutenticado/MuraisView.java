package view.posLoginAutenticado;

import controller.controlador.PrincipalController;
import controller.exceptions.LoginInvalidoException;
import model.exceptions.ListaVaziaException;

import java.util.Scanner;

public class MuraisView {

    public void murais(Scanner in, String logado, int op, PrincipalController controllerPrincipal) {
        String login;
        String mural;
        Thread t;
        switch (op) {
            case 0:// Sair
                break;
            case 1:// Exibir Murais
                System.out.println("Insira aqui o login de quem você deseja ver o mural: ");
                login = in.next();
                t = new Thread(new Runnable() {
                    public void run() {
                        try {
                            String exibirMural = controllerPrincipal.exibirMural(login);
                            System.out.println("Mural:");
                            System.out.println(exibirMural);
                        } catch (LoginInvalidoException | ListaVaziaException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                });
                t.start();
                break;

            case 2: // Criar Mural
                System.out.println("Escreva sua mensagem para o mundo: ");
                in.nextLine();
                mural = in.nextLine();
                t = new Thread(new Runnable() {
                    public void run() {
                        controllerPrincipal.enviarMural(logado, mural);
                        System.out.println("Mural criado com sucesso.");
                    }
                });
                t.start();
                break;

            case 3:// excluir mural
                t = new Thread(new Runnable() {
                    public void run() {
                        try {
                            controllerPrincipal.excluirMural(logado);
                            System.out.println("Seu Mural foi excluido com sucesso.");
                        } catch (ListaVaziaException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                });
                t.start();
                break;
        }
    }
}
