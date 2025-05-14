package br.edu.ifsp.spo.java.cards.nucleo;

import br.edu.ifsp.spo.java.cards.itens.Baralho;
import br.edu.ifsp.spo.java.cards.regras.Pontuador;
import br.edu.ifsp.spo.java.cards.regras.PontuadorClassico;
import br.edu.ifsp.spo.java.cards.ui.JogoUI;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Jogo {

    private Baralho baralho;
    private Jogador jogador1;
    private Jogador jogador2;
    private int rodadas;
    private Pontuador pontuador;

    private JogoUI ui;

    public Jogo() {
            this.ui = new JogoUI();

            this.pontuador = this.ui.escolherPontuador();

            this.baralho = new Baralho();
            this.rodadas = ui.qtdRodadas();

            if (ui.escolherJogador()) {
                this.jogador1 = new Jogador(ui.solicitarNomeJogador(1));
                this.jogador2 = new Jogador(ui.solicitarNomeJogador(2));
            }
            else {
                this.jogador1 = new Jogador(ui.solicitarNomeJogador(1));
                this.jogador2 = new JogadorAI();
            }


            for (int i = 0; i < 2; i++) {
                this.jogador1.receberCarta(this.baralho.tirarCarta());
                this.jogador2.receberCarta(this.baralho.tirarCarta());
            }

    }

        public void Jogar() throws InterruptedException {
            int rodada_atual = 1;
            boolean player1_joga = true;
            boolean player2_joga = true;
            boolean verif_se_parou1 = false;
            boolean verif_se_parou2 = false;
            int cont = 1;


            while(rodada_atual <= this.rodadas) {
                if(cont > 1){
                    this.jogador1.limparMao();
                    this.jogador2.limparMao();
                    for (int i = 0; i < 2; i++) {
                        this.jogador1.receberCarta(this.baralho.tirarCarta());
                        this.jogador2.receberCarta(this.baralho.tirarCarta());
                    }
                }

                while (true) {
                    System.out.println(ui.exibir_mao(this.pontuador, this.jogador1));
                    if (this.pontuador.verificarPontuacao(this.jogador1.getMao()) > 21) {
                        break;
                    }

                    int para_continua = ui.parar_continuar();


                    if (para_continua == 1) {
                        break;
                    }
                    if (para_continua == 2) {
                        this.jogador1.receberCarta(this.baralho.tirarCarta());
                    }
                }


                while (true) {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(ui.exibir_mao(this.pontuador, this.jogador2));
                    if (this.pontuador.verificarPontuacao(this.jogador2.getMao()) > 21) {
                        break;
                    }
                    int para_continua;
                    if(this.jogador2 instanceof JogadorAI){
                        para_continua = ((JogadorAI) this.jogador2).pegar_carta(this.pontuador.verificarPontuacao(this.jogador2.getMao()));
                    }
                    else {
                        para_continua = ui.parar_continuar();
                    }


                    if (para_continua == 1) {
                        break;
                    }
                    if (para_continua == 2) {
                        this.jogador2.receberCarta(this.baralho.tirarCarta());
                    }
                }

                var pontuacao1 = this.pontuador.verificarPontuacao(this.jogador1.getMao());
                var pontuacao2 = this.pontuador.verificarPontuacao(this.jogador2.getMao());
                var vencedor = this.verifica_vencedor(pontuacao1, pontuacao2);
                ui.mostra_vencedor(vencedor);
                if(rodada_atual == this.rodadas){
                    System.out.println(ui.mostrarPontuacaoGeral(this.jogador1.getPontos(), this.jogador2.getPontos()));
                }
                rodada_atual++;
                cont += 1;

            }
        }

        private int verifica_vencedor(int pontuacao_jogador1, int pontuacao_jogador2){
            if(pontuacao_jogador1 > 21){
                if(pontuacao_jogador2 == 21){
                    this.jogador2.mudarPontos(30);
                    return 2;
                } else if (pontuacao_jogador2 < 21) {
                    //vencedor
                    this.jogador2.mudarPontos(pontuacao_jogador2);

                    //perdedor
                    this.jogador2.mudarPontos((-5));
                    return 2;
                }
                else //os dois estouraram
                {
                    this.jogador1.mudarPontos(pontuacao_jogador1-21);
                    this.jogador2.mudarPontos(pontuacao_jogador2-21);
                    return 0;
                }
            }
            else if (pontuacao_jogador2 > 21) {
                if(pontuacao_jogador1 == 21){
                    this.jogador1.mudarPontos(30);
                    return 1;
                } else if (pontuacao_jogador1 < 21) {
                    this.jogador1.mudarPontos(pontuacao_jogador1);
                    return 1;
                }
            }
            else if (pontuacao_jogador1 > pontuacao_jogador2) {
                if(pontuacao_jogador1 == 21){
                    this.jogador1.mudarPontos(30);
                    return 1;
                }
                else {
                    this.jogador1.mudarPontos(pontuacao_jogador1-pontuacao_jogador2);
                    return 1;
                }
            }
            else if (pontuacao_jogador2 > pontuacao_jogador1) {
                if(pontuacao_jogador2 == 21){
                    this.jogador2.mudarPontos(30);
                    return 2;
                }
                else {
                    this.jogador2.mudarPontos(pontuacao_jogador1-pontuacao_jogador2);
                    return 2;
                }
            }
            else {
                this.jogador1.mudarPontos(10);
                this.jogador2.mudarPontos(10);

            }
            return 0;
        }
}
