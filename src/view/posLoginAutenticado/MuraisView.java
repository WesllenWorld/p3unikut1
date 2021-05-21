package view.posLoginAutenticado;

import controller.controlador.PrincipalController;
import controller.exceptions.LoginInvalidoException;
import model.exceptions.ListaVaziaException;

import java.util.Scanner;

public class MuraisView {

    public void murais(Scanner in, String logado, int op, PrincipalController controllerPrincipal) {
        String login;
        String exibirMural;
        String mural;
        switch (op) {
            case 0:// Sair
                break;
            case 1:// Exibir Murais
                System.out.println("Insira aqui o login de quem você deseja ver o mural: ");
                login = in.next();
                try {
                    exibirMural = controllerPrincipal.exibirMural(login);
                    System.out.println("Mural:");
                    System.out.println(exibirMural);
                } catch (LoginInvalidoException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 2: // Criar Mural
                System.out.println("Escreva sua mensagem para o mundo: ");
                in.nextLine();
                mural = in.nextLine();
                controllerPrincipal.enviarMural(logado, mural);
                System.out.println("Mural criado com sucesso.");
                break;

            case 3:// excluir mural
                try {
                    controllerPrincipal.excluirMural(logado);
                    System.out.println("Seu Mural foi excluido com sucesso.");
                } catch (ListaVaziaException e) {
                    System.out.println(e.getMessage());
                }
                break;
        }
    }
}
