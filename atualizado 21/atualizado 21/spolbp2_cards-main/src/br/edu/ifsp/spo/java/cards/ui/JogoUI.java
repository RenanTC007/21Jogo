package br.edu.ifsp.spo.java.cards.ui;

import br.edu.ifsp.spo.java.cards.nucleo.Jogador;
import br.edu.ifsp.spo.java.cards.nucleo.JogadorAI;
import br.edu.ifsp.spo.java.cards.regras.Pontuador;
import br.edu.ifsp.spo.java.cards.regras.PontuadorAsValeOnze;
import br.edu.ifsp.spo.java.cards.regras.PontuadorClassico;

import java.util.Scanner;

public class JogoUI {

    public String solicitarNomeJogador(int numeroJogador) {
        System.out.println("Digite o nome do jogador " + numeroJogador);

        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public Pontuador escolherPontuador() {
        System.out.println("Escolha o mecanismo de pontuação:");
        System.out.println("(1) Para pontuação clássica (Padrão)");
        System.out.println("(2) Para pontuação \"Ás vale 11\"");

        Scanner sc = new Scanner(System.in);

        int selecao = sc.nextInt();

        Pontuador pontuador = new PontuadorClassico();

        switch (selecao) {
            case 1 -> pontuador = new PontuadorClassico();
            case 2 -> pontuador = new PontuadorAsValeOnze();
            default -> System.out.println("Utilizando o mecanismo de pontuação padrão.");
        }

        return pontuador;
    }

    public boolean escolherJogador(){
        System.out.println("Escolha o modo de jogo:");
        System.out.println("(1) Jogador vs AI");
        System.out.println("(2) Jogador vs Jogador");

        int ia_ou_n = new Scanner(System.in).nextInt();
        boolean player = true;



        switch (ia_ou_n){
            case 1 -> player = false;
            case 2 -> player = true;
            default -> System.out.println("O jogo será entre Player vs IA...");
        }
        return player;
    }

    public String exibir_mao(Pontuador pontuador, Jogador jogador) {
        String resultado = "\n==============";
        resultado += jogador.toString();
        resultado += "\n A pontuação do jogador é: " + pontuador.verificarPontuacao(jogador.getMao());
        resultado += "\n==============\n";
        return resultado;
    }

    public void mostra_vencedor(int vencedor) {
        if(vencedor == 1) {
            System.out.println("JOGADOR 1 VENCEU A RODADA");
        }
        if(vencedor == 2){
            System.out.println("JOGADOR 2 VENCEU A RODADA");
        }
        if(vencedor == 0){
            System.out.println("A RODADA EMPATOU");
        }
    }

    public void jogador_perdeu() {
        System.out.println("O JOGADOR PERDEU... fim de jogo");
    }

    public int parar_continuar(){
        System.out.println("O player deseja: \n -(1)PARAR\n -(2)CONTINUAR");
        int para_continua = new Scanner(System.in).nextInt();
        return para_continua;
    }

    public int qtdRodadas(){
        System.out.println("Escolha a quantidade de rodadas que irá durar uma partida (maximo: 10)");

        int qtd = new Scanner(System.in).nextInt();

        if (qtd > 10 || qtd<1){
            qtd = 5;
            System.out.println("A partida terá 5 rodadas, pois o valor digitado não é válido");

        }
        System.out.println("A partida terá "+ qtd+ " rodadas");
        return qtd;
    }

    public void mostrarPontuacaoGeral(int pontos1, int pontos2){
        String frase = "\n==============\n";
        frase += "A partida terminou...";

        frase+="\nPontuação geral dos jogadores: ";
        frase+="\n - Jogador 1: "+ pontos1;
        frase+="\n - Jogador 2: "+ pontos2;

        frase += "\n==============\n";

        if (pontos1 > pontos2){
            frase+="\n\n O JOGADOR 1 VENCEU A PARTIDA";
        }
        if (pontos1 < pontos2){
            frase+="\n\n O JOGADOR 2 VENCEU A PARTIDA";
        }
        if(pontos1 == pontos2){
            frase+="\n\n A PARTIDA TERMINOU EMPATADA... F";
        }


        System.out.println(frase);
    }

    public void mostrarRodadaAtual(int rodada){
        if (rodada == 1) {
            System.out.println("Começando nova partida...\n");
        }
       else{
            System.out.println("Começando nova rodada...\n");
        }
        System.out.println("RODADA: "+ rodada);
    }

    public void mostrarPontuacaoRodada(int pontos1, int pontos2){

        System.out.println("A rodada terminou...");

        System.out.println("Pontuação na rodada: ");
        System.out.println(" - Jogador 1: " + (pontos1 >= 0 ? "+" + pontos1 : pontos1));
        System.out.println(" - Jogador 2: "+ (pontos2 >= 0 ? "+" + pontos2 : pontos2)+"\n");

    }

}


