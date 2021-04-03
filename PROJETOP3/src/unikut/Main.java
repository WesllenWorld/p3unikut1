/*
    Componentes do grupo:
    Diogo dos Santos Ávila
    Flávio Antônio Medeiros de Farias
    Ryan Pereira Lima da Silva
    Wesllen Leonardo de Lira Santos
*/
package unikut;

import classes.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Usuario us;
        Contas social = new Contas();
        Scanner in = new Scanner(System.in);
        String nome, login, senha;
        int op, acaoDeConta;
        boolean logado;

        do {
            menuInicial();
            op = in.nextInt();
            while (op > 2 || op < 0) {
                System.out.println("Opção inválida.");
                menuInicial();
                op = in.nextInt();
            }
            switch (op) {
                case 1: //criação de conta
                    in.nextLine();
                    System.out.println("Insira os seguintes dados para criar sua conta:");
                    System.out.println("Nome de login:");
                    login = in.next();
                    System.out.println("Senha do usuário:");
                    senha = in.next();
                    System.out.println("Nome de usuário (opcional. Seu nome será visto como " + "\"" + "convidado" + "\"" + " caso não preencha o espaço.");
                    in.nextLine();
                    nome = in.nextLine();
                    if (nome.equals("")) {
                        nome = "Convidado";
                    }
                    us = new Usuario(login, senha, nome);
                    boolean cadastroEfetivado = social.cadastrarUsuario(us);
                    if (cadastroEfetivado) {
                        System.out.println("Cadastro concluído com sucesso.");
                    } else {
                        System.out.println("Não foi possível efetivar seu cadastro pois o nome de usuário já está em uso.");
                    }
                    break;

                case 2: //login + interações do usuário
                    in.nextLine();
                    System.out.println("Nome de login: ");
                    login = in.next();
                    System.out.println("Senha: ");
                    senha = in.next();

                    us = new Usuario(login, senha, "");
                    logado = social.login(us);
                    if (logado) {
                        System.out.println("Bem-vindo!");
                        do {
                            menuDeConta();
                            acaoDeConta = in.nextInt();
                            while (acaoDeConta < 0 || acaoDeConta > 3) {
                                System.out.println("Opção inválida. Tente:");
                                menuDeConta();
                                acaoDeConta = in.nextInt();
                            }
                            switch (acaoDeConta) {
                                case 1:
                                    // Permita a um usuário cadastrado editar atributos de seu perfil. Ele deve
                                    // poder modificar qualquer atributo do perfil.
                                    System.out.println("O que deseja editar? ");
                                    System.out.println("1 - Apenas o Nome;");
                                    System.out.println("2 - Apenas a senha;");
                                    System.out.println("3 - Nome e senha.");
                                    op = in.nextInt();
                                    switch (op) {
                                        case 1:
                                            in.nextLine();
                                            System.out.println("Digite o novo nome: ");
                                            nome = in.nextLine();
                                            us = new Usuario(login, null, nome);
                                            social.editarCadastro(us);
                                            break;
                                        case 2:
                                            in.nextLine();
                                            System.out.println("Digite a nova senha: ");
                                            senha = in.next();
                                            us = new Usuario(login, senha, null);
                                            social.editarCadastro(us);
                                            break;
                                        case 3:
                                            in.nextLine();
                                            System.out.println("Digite o novo nome: ");
                                            nome = in.nextLine();
                                            System.out.println("Digite a nova senha: ");
                                            senha = in.next();
                                            us = new Usuario(login, senha, nome);
                                            social.editarCadastro(us);
                                            break;
                                    }


                                    break;
                                case 2://Adicionar amigo

                                    break;
                                case 3:// Envio de recados

                                    break;
                            }


                        } while (acaoDeConta != 0);
                    } else {
                        System.out.println("Login ou senha inválidos. Tente novamente.");
                    }

                    break;
            }

        } while (op != 0);


    }

    public static void menuInicial() {//Menu inicial
        System.out.println("BEM-VINDO AO UNIKUT, A MAIS NOVA FORMA DE MANTER A UNICAP MAIS PERTINHO DE VOCÊ.");

        System.out.println("Tecle - 1 – Para fazer parte da comunidade do UNIKUT.");
        System.out.println("Tecle - 2 – Para logar agora mesmo, caso já faça parte da comunidade.");
        System.out.println("Tecle 0 – para sair do programa.");
    }

    public static void menuDeConta() {//Menu do usuário logado
        System.out.println("Funções disponíveis:");
        System.out.println("1 – Editar perfil.");
        System.out.println("2 – Adicionar um amigo.");
        System.out.println("3 - Enviar um recado.");
        System.out.println("0 - Desconectar");
    }


}
