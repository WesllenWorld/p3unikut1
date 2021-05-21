package view.posLoginAutenticado;

import controller.controlador.PrincipalController;

import java.util.Scanner;

public class EditarUmCadastroView {

    public void editarOutroPerfil(PrincipalController controllerPrincipal, Scanner in, String login, int op) {
        String senha, nome;

        switch (op) {
            case 1:// Editar nome
                senha = null;
                in.nextLine();
                System.out.println("Digite o novo nome: ");
                nome = in.nextLine();
                if (nome.equals("")) {
                    nome = "convidado";
                }
                controllerPrincipal.editarCadastro(login, senha, nome);
                System.out.println("Perfil editado com sucesso.");
                break;
            case 2:// Editar senha
                nome = null;
                in.nextLine();
                System.out.println("Digite a nova senha: ");
                senha = in.next();
                controllerPrincipal.editarCadastro(login, senha, nome);
                System.out.println("Perfil editado com sucesso.");
                break;
            case 3:// Editar nome e senha
                in.nextLine();
                System.out.println("Digite o novo nome: ");
                nome = in.nextLine();
                if (nome.equals("")) {
                    nome = "convidado";
                }
                System.out.println("Digite a nova senha: ");
                senha = in.next();
                controllerPrincipal.editarCadastro(login, senha, nome);
                System.out.println("Perfil editado com sucesso.");
                break;
        }
    }


}
