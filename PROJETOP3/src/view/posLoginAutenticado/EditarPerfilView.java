package view.posLoginAutenticado;

import controller.controlador.PrincipalController;

import java.util.Scanner;

public class EditarPerfilView {

    public void editarPerfil(Scanner in, String logado, int op, PrincipalController controller) {
        String senha;
        String nome;
        switch (op) {
            case 1:// Editar nome
                senha = null;
                in.nextLine();
                System.out.println("Digite o novo nome: ");
                nome = in.nextLine();
                if (nome == null) {
                    nome = "convidado";
                }
                controller.editarCadastro(logado, senha, nome);
                System.out.println("Seu cadastro foi atualizado.");

                break;
            case 2:// Editar senha
                nome = null;
                in.nextLine();
                System.out.println("Digite a nova senha: ");
                senha = in.next();
                controller.editarCadastro(logado, senha, nome);
                System.out.println("Seu cadastro foi atualizado.");
                break;
            case 3:// Editar nome e senha
                in.nextLine();
                System.out.println("Digite o novo nome: ");
                nome = in.nextLine();
                if (nome == null) {
                    nome = "convidado";
                }
                System.out.println("Digite a nova senha: ");
                senha = in.next();
                controller.editarCadastro(logado, senha, nome);
                System.out.println("Seu cadastro foi atualizado.");
                break;
        }
    }
}
