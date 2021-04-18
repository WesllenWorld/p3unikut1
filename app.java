import java.util.Scanner;
import classes.*;
public class App {
    public static void main(String[] args) {
        Usuario us, usEmissor, usReceptor;
        Contas social = new Contas();
        Scanner in = new Scanner(System.in);
        String nome, login, senha, logado, exibirListas, recado, exibirMural, mural;
        int op, acaoDeConta;
        boolean EstadoDeLogado;

        do {
            menuInicial();
            op = in.nextInt();
            while (op > 2 || op < 0) {
                System.out.println("Opção inválida.");
                menuInicial();
                op = in.nextInt();
            }
            switch (op) {
            case 1...
            case 2...
            case 3...
            case 4:// Murais
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
                                System.out.println("Insira aqui o login de quem voce deseja ver o Recado: ");
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
                                if (excluirMural == true) {
                                    System.out.println("Seu Mural foi excluido com sucesso.");
                                } else {
                                    System.out.println("Você não possui nenhum mural para ser excluído.");
                                }
                                break;
                            }

                        }

public static void menuDeConta() {// Menu do usuário logado
        System.out.println("Funções disponíveis:");
        System.out.println("1 – Editar perfil");
        System.out.println("2 – Amigos");
        System.out.println("3 - Recados");
        System.out.println("4 - Murais");
        System.out.println("0 - Desconectar");
    }
          
public static void menuMural() {
        System.out.println("Menu de Murais:");
        System.out.println("1 - Ver Murais");
        System.out.println("2 - Criar Mural");
        System.out.println("3 - Excluir Seu Mural");
        System.out.println("0 - Voltar");
    }
}
