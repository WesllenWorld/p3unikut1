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
                    System.out.println("Nome de usuário (opcional. Seu nome será visto como "+"\""+"convidado"+"\""+" caso não preencha o espaço.");
                    in.nextLine();
                    nome = in.nextLine();
                    if(nome.equals("")){
                        nome = "Convidado";
                    }
                    us = new Usuario(login, senha, nome);
                    boolean cadastroEfetivado = social.cadastrarUsuario(us);
                    if(cadastroEfetivado){
                        System.out.println("Cadastro concluído com sucesso.");
                    }else{
                        System.out.println("Não foi possível efetivar seu cadastro pois o nome de usuário já está em uso.");
                    }
                    break;

                case 2: //login + interações do usuário
                    in.nextLine();
                    System.out.println("Nome de login: ");
                    login = in.next();
                    System.out.println("Senha: ");
                    senha = in.next();
                    
                    do{
                        menuDeConta();
                        acaoDeConta = in.nextInt();



                    }while(acaoDeConta != 0);

                    break;
            }

        } while (op != 0);



    }
    public static void menuInicial() {//Menu inicial 
        System.out.println("BEM VINDO AO UNIKUT, A MAIS NOVA FORMA DE MANTER A UNICAP MAIS PERTINHO DE VOCÊ.");

        System.out.println("Tecle - 1 – Para fazer parte da comunidade do UNIKUT.");
        System.out.println("Tecle - 2 – Para logar agora mesmo, caso já faça parte da comunidade.");
        System.out.println("Tecle 0 – para sair do programa.");
    }

    public static void menuDeConta(){//Menu do usuário logado

    }


}
