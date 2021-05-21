package view.preLoginAutenticado;

import controller.controlador.PrincipalController;
import controller.exceptions.CodigoAdmErradoException;
import controller.exceptions.UsuarioExistenteException;

import java.util.Scanner;

public class CadastrarAdmView {

    public void cadastrar(Scanner in, PrincipalController controllerPrincipal) {
        String login, senha, nome, codigoDeAdmin;

        in.nextLine();
        System.out.println("Insira os seguintes dados para criar sua conta de administrador:");
        System.out.println("Nome de login:");
        login = in.next();
        System.out.println("Senha do usuário:");
        senha = in.next();
        System.out.println("Nome de usuário (opcional. Seu nome será visto como " + "\"" + "convidado"
                + "\"" + " caso não preencha o espaço.");
        in.nextLine();
        nome = in.nextLine();
        if (nome.equals("")) {
            nome = "Convidado";
        }
        System.out.println("Digite o código de autenticação de criação do administrador:");
        codigoDeAdmin = in.next();
        try {
            controllerPrincipal.cadastro(login, senha, nome, codigoDeAdmin);
            System.out.println("Cadastro concluído com sucesso.");
        } catch (CodigoAdmErradoException | UsuarioExistenteException e) {
            System.out.println(e.getMessage());
        }
    }
}
