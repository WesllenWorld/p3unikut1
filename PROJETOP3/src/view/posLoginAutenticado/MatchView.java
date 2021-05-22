package view.posLoginAutenticado;

import controller.controlador.PrincipalController;
import controller.exceptions.AutoRequisicaoException;
import controller.exceptions.LoginInvalidoException;
import model.exceptions.JaPossuemMatchException;
import model.exceptions.MatchJaFeitoException;

import java.util.Scanner;

public class MatchView {

    public void match(int op, String logado, Scanner in, PrincipalController controllerPrincipal){
        String exibirListas, login;
        boolean matchMutuo;


        switch (op) {
            case 0:
                break;
            case 1:
                exibirListas = controllerPrincipal.exibirMatches(logado);
                System.out.println("Lista de matches:");
                System.out.println(exibirListas);
                break;

            case 2:
                in.nextLine();
                System.out.println("Informe o login do usuário a ser solicitado o match:");
                login = in.next();
                try{
                    matchMutuo = controllerPrincipal.adicionarMatch(login, logado);
                    if(matchMutuo){
                        System.out.println("Você deu um match, mas o outro ainda não deu um match em você.");
                    }else{
                        System.out.println("MATCH! Agora vocês dois tem um match.");
                    }
                }catch(AutoRequisicaoException | LoginInvalidoException | JaPossuemMatchException | MatchJaFeitoException e){
                    System.out.println(e.getMessage());
                }
                break;
        }
    }
}
