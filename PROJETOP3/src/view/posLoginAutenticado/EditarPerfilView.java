package view.posLoginAutenticado;

import controller.controlador.PrincipalController;

import java.util.Scanner;

public class EditarPerfilView {

    public void editarPerfil(Scanner in, String logado, int op, PrincipalController controllerPrincipal) {
        String senha;
        String nome, nomeFinal;
        Thread t;

        switch (op) {
            case 1:// Editar nome
                senha = null;
                in.nextLine();
                System.out.println("Digite o novo nome: ");
                nome = in.nextLine();
                if (nome == null) {
                    nome = "convidado";
                }
                nomeFinal = nome;
                t = new Thread(new Runnable() {
                    public void run() {
                        controllerPrincipal.editarCadastro(logado, senha, nomeFinal);
                        System.out.println("Seu cadastro foi atualizado.");
                    }
                });
                t.start();
                break;
            case 2:// Editar senha
                nomeFinal = null;
                in.nextLine();
                System.out.println("Digite a nova senha: ");
                senha = in.next();
                t = new Thread(new Runnable() {
                    public void run() {
                        controllerPrincipal.editarCadastro(logado, senha, nomeFinal);
                        System.out.println("Seu cadastro foi atualizado.");
                    }
                });
                t.start();
                break;
            case 3:// Editar nome e senha
                in.nextLine();
                System.out.println("Digite o novo nome: ");
                nome = in.nextLine();
                if (nome == null) {
                    nome = "convidado";
                }
                nomeFinal = nome;
                System.out.println("Digite a nova senha: ");
                senha = in.next();
                t = new Thread(new Runnable() {
                    public void run() {
                        controllerPrincipal.editarCadastro(logado, senha, nomeFinal);
                        System.out.println("Seu cadastro foi atualizado.");
                    }
                });
                t.start();
                break;
        }
    }
}
