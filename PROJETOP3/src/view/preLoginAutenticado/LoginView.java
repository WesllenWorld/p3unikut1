package view.preLoginAutenticado;

import controller.controlador.PrincipalController;
import controller.exceptions.LoginInvalidoException;
import controller.exceptions.SenhaInvalidaException;
import view.posLoginAutenticado.UsuarioAdmView;
import view.posLoginAutenticado.UsuarioView;

import java.util.Scanner;

public class LoginView {

    public void login(Scanner in, PrincipalController controllerPrincipal) {

        String login, senha, logado;
        boolean estadoDeLogado = false, administrador = false;
        UsuarioView viewUsuario = new UsuarioView();
        UsuarioAdmView viewUsuarioAdm = new UsuarioAdmView();

        in.nextLine();
        System.out.println("Nome de login: ");
        login = in.next();
        System.out.println("Senha: ");
        senha = in.next();
        try {
            controllerPrincipal.login(login, senha);
            estadoDeLogado = true;
            administrador = controllerPrincipal.tipoUsuario(login);
        } catch (LoginInvalidoException | SenhaInvalidaException e) {
            System.out.println(e.getMessage());
        }
        if (estadoDeLogado) {
            System.out.println("Bem-vindo!");
            logado = login;
            if (!administrador) {
                viewUsuario.usuario(logado, controllerPrincipal, in);
            } else {
                logado = login;
                viewUsuarioAdm.usuario(logado, controllerPrincipal, in);
            }
        } else {
            System.out.println("Login ou senha inválidos. Tente novamente.");
        }
    }
}

