package executavel;

import usuarios.*;
import interacoesDeContas.Contas;

import java.util.Scanner;

public class Main {

    // Nota: O sinal "/////" refere-se a trechos que foram alterados (visível no
    // vídeo da atividade)
    public static void main(String[] args) {
        Usuario us, usEmissor, usReceptor;
        Contas social = new Contas();
        Scanner in = new Scanner(System.in);
        String nome, login, senha, logado, exibirListas, recado, palavraChave, codigoDeAdmin, codigoCorreto = "Un1ku7@dm1n", mural, exibirMural;
        int op, acaoDeConta, indiceMensagem;
        boolean EstadoDeLogado, recadoEnviado, cadastroEfetivado, administrador;

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
                    in.nextLine();
                    System.out.println("Insira os seguintes dados para criar sua conta:");
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
                    us = new Usuario(login, senha, nome);
                    cadastroEfetivado = social.cadastrarUsuario(us);
                    if (cadastroEfetivado) {
                        System.out.println("Cadastro concluído com sucesso.");
                    } else {
                        System.out.println(
                                "Não foi possível efetivar seu cadastro pois o nome de usuário já está em uso.");
                    }
                    break;

                case 2: // login + interações do usuário
                    in.nextLine();
                    System.out.println("Nome de login: ");
                    login = in.next();
                    System.out.println("Senha: ");
                    senha = in.next();

                    Usuario usLogado = new Usuario(login, senha, "");
                    EstadoDeLogado = social.login(usLogado);
                    if (EstadoDeLogado) {
                        administrador = social.tipoUsuario(usLogado);
                        System.out.println("Bem-vindo!");
                        logado = login;
                        if (!administrador) {
                            do {
                                menuDeConta();
                                acaoDeConta = in.nextInt();// O que a conta pode fazer
                                while (acaoDeConta < 0 || acaoDeConta > 4) {
                                    System.out.println("Opção inválida. Tente:");
                                    menuDeConta();
                                    acaoDeConta = in.nextInt();
                                }
                                switch (acaoDeConta) {
                                    case 1:
                                        // Permita a um usuário cadastrado editar atributos de seu perfil
                                        // Menu
                                        menuEditar();
                                        op = in.nextInt();
                                        switch (op) {
                                            case 1:// Editar nome
                                                in.nextLine();
                                                System.out.println("Digite o novo nome: ");
                                                nome = in.nextLine();
                                                us = new Usuario(logado, null, nome);
                                                social.editarCadastro(us);
                                                System.out.println("Seu cadastro foi atualizado.");
                                                break;
                                            case 2:// Editar senha
                                                in.nextLine();
                                                System.out.println("Digite a nova senha: ");
                                                senha = in.next();
                                                us = new Usuario(logado, senha, null);
                                                social.editarCadastro(us);
                                                System.out.println("Seu cadastro foi atualizado.");
                                                break;
                                            case 3:// Editar nome e senha
                                                in.nextLine();
                                                System.out.println("Digite o novo nome: ");
                                                nome = in.nextLine();
                                                System.out.println("Digite a nova senha: ");
                                                senha = in.next();
                                                us = new Usuario(logado, senha, nome);
                                                social.editarCadastro(us);
                                                System.out.println("Seu cadastro foi atualizado.");
                                                break;
                                        }
                                        break;

                                    case 2:// Aba de amigos
                                        menuAmigos();
                                        op = in.nextInt();
                                        while (op < 0 || op > 2) {
                                            System.out.println("Opção inválida. Tente:");
                                            menuAmigos();
                                            op = in.nextInt();
                                        }
                                        switch (op) {
                                            case 1:// Adicionar amigo
                                                in.nextLine();
                                                System.out.println("Informe o login do usuário a ser adicionado:");
                                                login = in.next();
                                                usReceptor = new Usuario(login, "", "");
                                                usEmissor = new Usuario(logado, "", "");
                                                int statusDoPedido = social.adicionarAmigo(usReceptor, usEmissor);
                                                if (statusDoPedido == 0) {
                                                    System.out.println(
                                                            "Pedido de amizade enviado. Aguardando aceitação.");
                                                } else if (statusDoPedido == 1) {
                                                    System.out.println(
                                                            "Pedido de amizade aceito! Vocês agora são amigos.");
                                                } else {
                                                    System.out.println(
                                                            "Não foi possível enviar um pedido de amizade para o usuário solicitado. Verifique se o login está correto e tente novamente.");
                                                }

                                                break;
                                            case 2:
                                                System.out.println("1 - Listar amigos");
                                                System.out.println("2 - Pedidos pendentes");
                                                System.out.println("0 - Sair");// Listar amigos e pedidos de amizade
                                                op = in.nextInt();
                                                while (op < 0 || op > 2) {
                                                    System.out.println("Opção inválida. Tente:");
                                                    menuAmigos();
                                                    op = in.nextInt();
                                                }

                                                switch (op) {
                                                    case 0:// Sair
                                                        break;
                                                    case 1:// Exibir lista de amigos
                                                        usEmissor = new Usuario(logado, "", "");
                                                        exibirListas = social.exibirAmigos(usEmissor, op);
                                                        System.out.println("Lista de amigos:");
                                                        System.out.println(exibirListas);
                                                        break;
                                                    case 2: // Exibir pendentes
                                                        usEmissor = new Usuario(logado, "", "");
                                                        exibirListas = social.exibirAmigos(usEmissor, op);
                                                        System.out.println(
                                                                "Envie convites para remover a pendência e que vocês sejam amigos!");
                                                        System.out.println("Há convites de amizade pendentes de:");
                                                        System.out.println(exibirListas);
                                                        break;
                                                }
                                                break;
                                        }
                                        break;

                                    case 3:// Recados
                                        menuRecados();
                                        op = in.nextInt();
                                        while (op < 0 || op > 4) {
                                            System.out.println("Opção inválida. Tente:");
                                            menuRecados();
                                            op = in.nextInt();
                                        }
                                        switch (op) {
                                            case 0:// Sair
                                                break;
                                            case 1:// Exibir recados
                                                usEmissor = new Usuario(logado, "", "");
                                                exibirListas = social.exibirRecados(usEmissor);
                                                System.out.println(
                                                        "Mensagens recentes serão sempre as que estão mais em baixo.");
                                                System.out.println("Recados:");
                                                System.out.println(exibirListas);
                                                break;
                                            case 2: // Enviar recado /////
                                                in.nextLine();
                                                System.out.println("Insira aqui o login do destinatário: ");
                                                login = in.next();
                                                System.out.println("Escreva seu recado: ");
                                                in.nextLine();
                                                recado = in.nextLine();
                                                System.out.println("O recado será enviado como:");///// {
                                                System.out.println("1 - Mensagem comum");
                                                System.out.println("2 - Mensagem secreta");
                                                op = in.nextInt();
                                                while (op < 1 || op > 2) {
                                                    System.out.println("Opção inválida. Tente:");
                                                    System.out.println("1 - Mensagem comum");
                                                    System.out.println("2 - Mensagem secreta");
                                                    op = in.nextInt();
                                                } ///// }
                                                usEmissor = new Usuario(logado, "", "");
                                                usReceptor = new Usuario(login, "", "");
                                                if (op == 1) {///// {
                                                    recadoEnviado = social.enviarRecado(usEmissor, usReceptor, recado,
                                                            "");
                                                } else {
                                                    System.out.println("Digite a palavra-chave para a mensagem:");
                                                    palavraChave = in.next();
                                                    recadoEnviado = social.enviarRecado(usEmissor, usReceptor, recado,
                                                            palavraChave);
                                                } ///// }
                                                if (recadoEnviado) {
                                                    System.out.println(
                                                            "Mensagem enviada. Envie mais mensagens ou aguarde uma resposta!");
                                                } else {
                                                    System.out.println(
                                                            "Não foi possível enviar o recado. Verifique os dados de entrada e tente novamente.");
                                                }
                                                break;
                                            case 3:// Limpar caixa de recados
                                                usEmissor = new Usuario(logado, "", "");
                                                boolean limparRecados = social.excluirRecados(usEmissor);
                                                if (limparRecados) {
                                                    System.out.println("Seus recados foram excluídos com sucesso.");
                                                } else {
                                                    System.out.println(
                                                            "Você não possui nenhum recado para ser excluído.");
                                                }
                                                break;
                                            case 4: /////
                                                in.nextLine();
                                                usEmissor = new Usuario(logado, "", "");
                                                exibirListas = social.exibirRecados(usEmissor);
                                                System.out.println(exibirListas);

                                                System.out.println("Insira o número da mensagem a ser decodificada: ");
                                                indiceMensagem = in.nextInt();
                                                indiceMensagem--;
                                                System.out.println("Digite a palavra-chave da mensagem: ");
                                                in.nextLine();
                                                palavraChave = in.nextLine();
                                                String result = social.decodificarRecado(usEmissor, indiceMensagem,
                                                        palavraChave);
                                                System.out.println(result + "\n");

                                                break;
                                        }
                                        break;

                                    case 4: // Murais
                                        menuMural();
                                        op = in.nextInt();
                                        while (op < 0 || op > 3) {
                                            System.out.println("Opção inválida. Tente:");
                                            menuMural();
                                            op = in.nextInt();
                                        }
                                        switch (op) {
                                            case 0:// Sair

                                                break;
                                            case 1:// Exibir Murais
                                                System.out.println(
                                                        "Insira aqui o login de quem voce deseja ver o Recado: ");
                                                login = in.next();
                                                usEmissor = new Usuario(login, "", "");
                                                exibirMural = social.exibirMural(usEmissor);
                                                System.out.println("Mural:");
                                                System.out.println(exibirMural);

                                                break;
                                            case 2: // Criar Mural
                                                System.out.println("Escreva sua mensagem para o mundo: ");
                                                in.nextLine();
                                                mural = in.nextLine();
                                                usEmissor = new Usuario(logado, "", "");
                                                boolean muralCriado = social.enviarMural(usEmissor, mural);
                                                if (muralCriado) {
                                                    System.out.println("Mural Criado!");
                                                } else {
                                                    System.out.println(
                                                            "Não foi possível criar o mural. Verifique os dados de entrada e tente novamente.");
                                                }

                                                break;
                                            case 3:// excluir mural
                                                usEmissor = new Usuario(logado, "", "");
                                                boolean excluirMural = social.excluirMural(usEmissor);
                                                if (excluirMural) {
                                                    System.out.println("Seu Mural foi excluido com sucesso.");
                                                } else {
                                                    System.out
                                                            .println("Você não possui nenhum mural para ser excluído.");
                                                }
                                                break;
                                        }

                                        break;
                                }

                            } while (acaoDeConta != 0);
                        } else {
                            UsuarioAdmin adminLogado = new UsuarioAdmin(logado, null, null);
                            do {
                                menuDeAdmin();
                                acaoDeConta = in.nextInt();// O que a conta pode fazer
                                while (acaoDeConta < 0 || acaoDeConta > 5) {
                                    System.out.println("Opção inválida. Tente:");
                                    menuDeAdmin();
                                    acaoDeConta = in.nextInt();
                                }
                                switch (acaoDeConta) {
                                    case 1:
                                        // Permita a um usuário cadastrado editar atributos de seu perfil
                                        // Menu
                                        menuEditar();
                                        op = in.nextInt();
                                        switch (op) {
                                            case 1:// Editar nome
                                                in.nextLine();
                                                System.out.println("Digite o novo nome: ");
                                                nome = in.nextLine();
                                                us = new Usuario(logado, null, nome);
                                                social.editarCadastro(us);
                                                System.out.println("Seu cadastro foi atualizado.");
                                                break;
                                            case 2:// Editar senha
                                                in.nextLine();
                                                System.out.println("Digite a nova senha: ");
                                                senha = in.next();
                                                us = new Usuario(logado, senha, null);
                                                social.editarCadastro(us);
                                                System.out.println("Seu cadastro foi atualizado.");
                                                break;
                                            case 3:// Editar nome e senha
                                                in.nextLine();
                                                System.out.println("Digite o novo nome: ");
                                                nome = in.nextLine();
                                                System.out.println("Digite a nova senha: ");
                                                senha = in.next();
                                                us = new Usuario(logado, senha, nome);
                                                social.editarCadastro(us);
                                                System.out.println("Seu cadastro foi atualizado.");
                                                break;
                                        }
                                        break;

                                    case 2:// Aba de amigos
                                        menuAmigos();
                                        op = in.nextInt();
                                        while (op < 0 || op > 2) {
                                            System.out.println("Opção inválida. Tente:");
                                            menuAmigos();
                                            op = in.nextInt();
                                        }
                                        switch (op) {
                                            case 1:// Adicionar amigo
                                                in.nextLine();
                                                System.out.println("Informe o login do usuário a ser adicionado:");
                                                login = in.next();
                                                usReceptor = new Usuario(login, "", "");
                                                usEmissor = new Usuario(logado, "", "");
                                                int statusDoPedido = social.adicionarAmigo(usReceptor, usEmissor);
                                                if (statusDoPedido == 0) {
                                                    System.out.println(
                                                            "Pedido de amizade enviado. Aguardando aceitação.");
                                                } else if (statusDoPedido == 1) {
                                                    System.out.println(
                                                            "Pedido de amizade aceito! Vocês agora são amigos.");
                                                } else {
                                                    System.out.println(
                                                            "Não foi possível enviar um pedido de amizade para o usuário solicitado. Verifique se o login está correto e tente novamente.");
                                                }

                                                break;
                                            case 2:
                                                System.out.println("1 - Listar amigos");
                                                System.out.println("2 - Pedidos pendentes");
                                                System.out.println("0 - Sair");// Listar amigos e pedidos de amizade
                                                op = in.nextInt();
                                                while (op < 0 || op > 2) {
                                                    System.out.println("Opção inválida. Tente:");
                                                    menuAmigos();
                                                    op = in.nextInt();
                                                }

                                                switch (op) {
                                                    case 0:// Sair
                                                        break;
                                                    case 1:// Exibir lista de amigos
                                                        usEmissor = new Usuario(logado, "", "");
                                                        exibirListas = social.exibirAmigos(usEmissor, op);
                                                        System.out.println("Lista de amigos:");
                                                        System.out.println(exibirListas);
                                                        break;
                                                    case 2: // Exibir pendentes
                                                        usEmissor = new Usuario(logado, "", "");
                                                        exibirListas = social.exibirAmigos(usEmissor, op);
                                                        System.out.println(
                                                                "Envie convites para remover a pendência e que vocês sejam amigos!");
                                                        System.out.println("Há convites de amizade pendentes de:");
                                                        System.out.println(exibirListas);
                                                        break;
                                                }
                                                break;
                                        }
                                        break;

                                    case 3:// Recados
                                        menuRecados();
                                        op = in.nextInt();
                                        while (op < 0 || op > 4) {
                                            System.out.println("Opção inválida. Tente:");
                                            menuRecados();
                                            op = in.nextInt();
                                        }
                                        switch (op) {
                                            case 0:// Sair
                                                break;
                                            case 1:// Exibir recados
                                                usEmissor = new Usuario(logado, "", "");
                                                exibirListas = social.exibirRecados(usEmissor);
                                                System.out.println(
                                                        "Mensagens recentes serão sempre as que estão mais em baixo.");
                                                System.out.println("Recados:");
                                                System.out.println(exibirListas);
                                                break;
                                            case 2: // Enviar recado /////
                                                in.nextLine();
                                                System.out.println("Insira aqui o login do destinatário: ");
                                                login = in.next();
                                                System.out.println("Escreva seu recado: ");
                                                in.nextLine();
                                                recado = in.nextLine();
                                                System.out.println("O recado será enviado como:");///// {
                                                System.out.println("1 - Mensagem comum");
                                                System.out.println("2 - Mensagem secreta");
                                                op = in.nextInt();
                                                while (op < 1 || op > 2) {
                                                    System.out.println("Opção inválida. Tente:");
                                                    System.out.println("1 - Mensagem comum");
                                                    System.out.println("2 - Mensagem secreta");
                                                    op = in.nextInt();
                                                } ///// }
                                                usEmissor = new Usuario(logado, "", "");
                                                usReceptor = new Usuario(login, "", "");
                                                if (op == 1) {///// {
                                                    recadoEnviado = social.enviarRecado(usEmissor, usReceptor, recado,
                                                            "");
                                                } else {
                                                    System.out.println("Digite a palavra-chave para a mensagem:");
                                                    palavraChave = in.next();
                                                    recadoEnviado = social.enviarRecado(usEmissor, usReceptor, recado,
                                                            palavraChave);
                                                } ///// }
                                                if (recadoEnviado) {
                                                    System.out.println(
                                                            "Mensagem enviada. Envie mais mensagens ou aguarde uma resposta!");
                                                } else {
                                                    System.out.println(
                                                            "Não foi possível enviar o recado. Verifique os dados de entrada e tente novamente.");
                                                }
                                                break;
                                            case 3:// Limpar caixa de recados
                                                usEmissor = new Usuario(logado, "", "");
                                                boolean limparRecados = social.excluirRecados(usEmissor);
                                                if (limparRecados) {
                                                    System.out.println("Seus recados foram excluídos com sucesso.");
                                                } else {
                                                    System.out.println(
                                                            "Você não possui nenhum recado para ser excluído.");
                                                }
                                                break;
                                            case 4: /////
                                                in.nextLine();
                                                usEmissor = new Usuario(logado, "", "");
                                                exibirListas = social.exibirRecados(usEmissor);
                                                System.out.println(exibirListas);

                                                System.out.println("Insira o número da mensagem a ser decodificada: ");
                                                indiceMensagem = in.nextInt();
                                                indiceMensagem--;
                                                System.out.println("Digite a palavra-chave da mensagem: ");
                                                in.nextLine();
                                                palavraChave = in.nextLine();
                                                String result = social.decodificarRecado(usEmissor, indiceMensagem,
                                                        palavraChave);
                                                System.out.println(result + "\n");

                                                break;
                                        }
                                        break;
                                    case 4:
                                        menuMural();
                                        op = in.nextInt();
                                        while (op < 0 || op > 3) {
                                            System.out.println("Opção inválida. Tente:");
                                            menuMural();
                                            op = in.nextInt();
                                        }
                                        switch (op) {
                                            case 0:// Sair

                                                break;
                                            case 1:// Exibir Murais
                                                System.out.println(
                                                        "Insira aqui o login de quem voce deseja ver o Recado: ");
                                                login = in.next();
                                                usEmissor = new Usuario(login, "", "");
                                                exibirMural = social.exibirMural(usEmissor);
                                                System.out.println("Mural:");
                                                System.out.println(exibirMural);

                                                break;
                                            case 2: // Criar Mural
                                                System.out.println("Escreva sua mensagem para o mundo: ");
                                                in.nextLine();
                                                mural = in.nextLine();
                                                usEmissor = new Usuario(logado, "", "");
                                                boolean muralCriado = social.enviarMural(usEmissor, mural);
                                                if (muralCriado) {
                                                    System.out.println("Mural Criado!");
                                                } else {
                                                    System.out.println(
                                                            "Não foi possível criar o mural. Verifique os dados de entrada e tente novamente.");
                                                }

                                                break;
                                            case 3:// excluir mural
                                                usEmissor = new Usuario(logado, "", "");
                                                boolean excluirMural = social.excluirMural(usEmissor);
                                                if (excluirMural) {
                                                    System.out.println("Seu Mural foi excluido com sucesso.");
                                                } else {
                                                    System.out
                                                            .println("Você não possui nenhum mural para ser excluído.");
                                                }
                                                break;
                                        }

                                        break;

                                    case 5: // Login de ADM
                                        menuAdministrador();
                                        op = in.nextInt();
                                        while (op < 0 || op > 2) {
                                            menuAdministrador();
                                            op = in.nextInt();
                                        }
                                        switch (op) {
                                            case 1:
                                                System.out.println("Qual o login do usuário que será editado?");
                                                login = in.next();
                                                us = new Usuario(login, null, null);
                                                administrador = social.tipoUsuario(us);
                                                if (administrador) {
                                                    System.out.println("Não é possível editar outros administradores.");
                                                    break;
                                                } else {
                                                    menuEditar();
                                                    op = in.nextInt();
                                                    switch (op) {
                                                        case 1:// Editar nome
                                                            in.nextLine();
                                                            System.out.println("Digite o novo nome: ");
                                                            nome = in.nextLine();
                                                            us = new Usuario(login, null, nome);
                                                            break;
                                                        case 2:// Editar senha
                                                            in.nextLine();
                                                            System.out.println("Digite a nova senha: ");
                                                            senha = in.next();
                                                            us = new Usuario(login, senha, null);
                                                            break;
                                                        case 3:// Editar nome e senha
                                                            in.nextLine();
                                                            System.out.println("Digite o novo nome: ");
                                                            nome = in.nextLine();
                                                            System.out.println("Digite a nova senha: ");
                                                            senha = in.next();
                                                            us = new Usuario(login, senha, nome);
                                                            break;
                                                    }
                                                    adminLogado.alterarUsuario(social, us);
                                                    System.out.println("O cadastro foi atualizado.");
                                                }
                                                break;

                                            case 2:
                                                System.out.println("Informe o login do usuário a ser excluído");
                                                login = in.next();
                                                us = new Usuario(login, null, null);

                                                if (social.buscar(us) == null) {
                                                    System.out.println("Não foi encontrado um usuário com esse login.");
                                                } else {
                                                    adminLogado.removerUsuario(social, us);
                                                    System.out.println("Usuário removido com sucesso.");
                                                }
                                                break;
                                        }
                                }

                            } while (acaoDeConta != 0);
                        }
                    } else {
                        System.out.println("Login ou senha inválidos. Tente novamente.");
                    }
                    break;

                case 3: // Criação de conta ADM
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
                    if (!codigoCorreto.equals(codigoDeAdmin)) {
                        System.out.println("Código de administrador incorreto. O usuário não foi cadastrado.");
                    } else {
                        us = new UsuarioAdmin(login, senha, nome);
                        cadastroEfetivado = social.cadastrarUsuario(us);
                        if (cadastroEfetivado) {
                            System.out.println("Cadastro concluído com sucesso.");
                        } else {
                            System.out.println(
                                    "Não foi possível efetivar seu cadastro pois o nome de usuário já está em uso.");
                        }
                        break;
                    }

            }

        } while (op != 0);
        in.close();

    }

    public static void menuInicial() {// Menu inicial
        System.out.println("BEM-VINDO AO UNIKUT, A MAIS NOVA FORMA DE MANTER A UNICAP MAIS PERTINHO DE VOCÊ.");

        System.out.println("Tecle - 1 – Para fazer parte da comunidade do UNIKUT.");
        System.out.println("Tecle - 2 – Para logar agora mesmo, caso já faça parte da comunidade.");
        System.out.println("Tecle - 3 - Para criar um novo administrador.");
        System.out.println("Tecle 0 – para sair do programa.");
    }

    public static void menuDeConta() {// Menu do usuário logado
        System.out.println("Funções disponíveis:");
        System.out.println("1 – Editar perfil");
        System.out.println("2 – Amigos");
        System.out.println("3 - Recados");
        System.out.println("4 - Murais");
        System.out.println("0 - Desconectar");
    }

    public static void menuDeAdmin() {// Menu do usuário logado
        System.out.println("Funções disponíveis:");
        System.out.println("1 – Editar perfil");
        System.out.println("2 – Amigos");
        System.out.println("3 - Recados");
        System.out.println("4 - Murais");
        System.out.println("5 - Administrador");
        System.out.println("0 - Desconectar");
    }

    public static void menuAmigos() {
        System.out.println("Menu de amigos:");
        System.out.println("1 - Adicionar amigos");
        System.out.println("2 - Listar amigos ou pedidos de amizade pendentes");
        System.out.println("0 - Voltar");
    }

    public static void menuRecados() {
        System.out.println("Menu de recados:");
        System.out.println("1 - Ver recados");
        System.out.println("2 - Mandar um recado");
        System.out.println("3 - Excluir recados");
        System.out.println("4 - Decodificar mensagem secreta");/////
        System.out.println("0 - Voltar");
    }

    public static void menuAdministrador() {
        System.out.println("Menu administrador:");
        System.out.println("1 - Editar um usuário");
        System.out.println("2 - Excluir um perfil");
        System.out.println("0 - Voltar");
    }

    public static void menuEditar() {
        System.out.println("O que deseja editar? ");
        System.out.println("1 - Apenas o Nome;");
        System.out.println("2 - Apenas a senha;");
        System.out.println("3 - Nome e senha.");
    }

    public static void menuMural() {
        System.out.println("Menu de Murais:");
        System.out.println("1 - Ver Murais");
        System.out.println("2 - Criar Mural");
        System.out.println("3 - Excluir Seu Mural");
        System.out.println("0 - Voltar");
    }
}
