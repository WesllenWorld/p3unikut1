package view.posLoginAutenticado;

import controller.controlador.PrincipalController;
import controller.exceptions.AutoRequisicaoException;
import controller.exceptions.LoginInvalidoException;
import model.exceptions.JaPossuemMatchException;
import model.exceptions.ListaVaziaException;

import model.exceptions.MatchJaFeitoException;

import java.util.Scanner;

public class MatchView {

    public void match(int op, String logado, Scanner in, PrincipalController controllerPrincipal) {
        String login;

        switch (op) {
            case 0:
                break;
            case 1:// matches mútuos
                Thread t2 = new Thread(new Runnable() {
                    public void run() {
                        try {
                            String exibirListas1 = controllerPrincipal.exibirMatches(logado);
                            System.out.println("Lista de matches:");
                            System.out.println(exibirListas1);
                        } catch (ListaVaziaException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                });
                t2.start();
                break;

            case 2:
                in.nextLine();// adicionar
                System.out.println("Informe o login do usuário a ser solicitado o match:");
                login = in.next();
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        try {
                            boolean matchMutuo = controllerPrincipal.adicionarMatch(login, logado);
                            if (matchMutuo) {
                                System.out.println("Você deu um match, mas o outro ainda não deu um match em você.");
                            } else {
                                System.out.println("MATCH! Agora vocês dois tem um match.");
                            }
                        } catch (AutoRequisicaoException | LoginInvalidoException | JaPossuemMatchException
                                | MatchJaFeitoException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                });
                t.start();
                break;

            case 3:// matches não-mútuos
                Thread t1 = new Thread(new Runnable() {
                    public void run() {
                        try {
                            String ExibirMatchs = controllerPrincipal.exibirMeusMatches(logado);
                            System.out.println("Meus matches: ");
                            System.out.println(ExibirMatchs);
                        } catch (ListaVaziaException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                });
                t1.start();
                break;

        }
    }
}
