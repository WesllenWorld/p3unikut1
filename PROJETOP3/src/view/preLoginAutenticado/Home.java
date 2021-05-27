package view.preLoginAutenticado;

import controller.controlador.PrincipalController;

import java.util.Scanner;

public class Home {

    // Nota: O sinal "/////" refere-se a trechos que foram alterados (visível no
    // vídeo da atividade)
    public void home() {
        Scanner in = new Scanner(System.in);
        int op;

        PrincipalController controllerPrincipal = new PrincipalController();
        view.preLoginAutenticado.LoginView viewLogin = new LoginView();
        view.preLoginAutenticado.CadastrarView viewCadastrar = new CadastrarView();
        view.preLoginAutenticado.CadastrarAdmView viewCadastrarAdm = new CadastrarAdmView();

        do {
            menuInicial();
            op = in.nextInt();
            while (op > 3 || op < 0) {
                System.out.println("Opção inválida.");
                menuInicial();
                op = in.nextInt();
            }
            switch (op) {
                case 1: // criação de conta
                    viewCadastrar.cadastrar(in, controllerPrincipal);
                    break;

                case 2: // login + interações do usuário
                    viewLogin.login(in, controllerPrincipal);
                    break;

                case 3: // Criação de conta ADM
                    viewCadastrarAdm.cadastrar(in, controllerPrincipal);
                    break;
            }

        } while (op != 0);
        in.close();
    }

    public void menuInicial() {
        System.out.println("BEM-VINDO AO UNIKUT, A MAIS NOVA FORMA DE MANTER A UNICAP MAIS PERTINHO DE VOCÊ.");

        System.out.println("Tecle - 1 – Para fazer parte da comunidade do UNIKUT.");
        System.out.println("Tecle - 2 – Para logar agora mesmo, caso já faça parte da comunidade.");
        System.out.println("Tecle - 3 - Para criar um novo administrador.");
        System.out.println("Tecle 0 – para sair do programa.");
    }

}
