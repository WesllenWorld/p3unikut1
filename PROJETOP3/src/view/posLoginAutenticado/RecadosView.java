package view.posLoginAutenticado;

import controller.controlador.PrincipalController;
import controller.exceptions.AutoRequisicaoException;
import controller.exceptions.LoginInvalidoException;
import controller.exceptions.SenhaInvalidaException;
import model.exceptions.ListaVaziaException;
import model.exceptions.MensagemNaoSecretaException;

import java.util.Scanner;

public class RecadosView {

    public void recados(Scanner in, String logado, int op, PrincipalController controllerPrincipal) {
        String login;
        String recado;
        String palavraChave;
        switch (op) {
            case 0:// Sair
                break;
            case 1:// Exibir recados
                Thread t3 = new Thread(new Runnable() {
                    public void run() {
                        try {
                            String exibirLista = controllerPrincipal.exibirRecados(logado);
                            System.out.println("Mensagens recentes serão sempre as que estão mais em baixo.");
                            System.out.println("Recados:");
                            System.out.println(exibirLista);
                        } catch (ListaVaziaException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                });
                t3.start();
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
                if (op == 1) {
                    try {
                        controllerPrincipal.enviarRecado(logado, login, recado);
                        System.out.println("Mensagem enviada.");
                    } catch (LoginInvalidoException | AutoRequisicaoException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    in.nextLine();
                    System.out.println("Digite a senha da mensagem a ser codificada:");
                    palavraChave = in.next();
                    try {
                        controllerPrincipal.enviarRecado(logado, login, recado, palavraChave);
                        System.out.println("Mensagem enviada.");
                    } catch (LoginInvalidoException | AutoRequisicaoException e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;
            case 3:// Limpar caixa de recados
                Thread t1 = new Thread(new Runnable() {
                    public void run() {
                        try {
                            controllerPrincipal.excluirRecados(logado);
                            System.out.println("Seus recados foram todos excluídos.");
                        } catch (ListaVaziaException e) {
                            System.out.println(e.getMessage());

                        }
                    }
                });
                t1.start();
                break;

            case 4: /////
                in.nextLine();
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        try {
                            String exibirLista = controllerPrincipal.exibirRecados(logado);
                            System.out.println("Mensagens recentes serão sempre as que estão mais em baixo.");
                            System.out.println("Recados:");
                            System.out.println(exibirLista);
                            System.out.println("Insira o número da mensagem a ser decodificada: ");
                            int indiceMensagem = in.nextInt();
                            indiceMensagem--;
                            System.out.println("Digite a palavra-chave da mensagem: ");
                            in.nextLine();
                            String palavraChave = in.next();
                            String result = controllerPrincipal.decodificarRecado(logado, indiceMensagem, palavraChave);
                            System.out.println(result + "\n");
                        } catch (IndexOutOfBoundsException | MensagemNaoSecretaException | SenhaInvalidaException
                                | ListaVaziaException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                });
                t.start();
                break;
        }
    }
}
