package br.edu.ifsp.spo.java.cards.nucleo;

import br.edu.ifsp.spo.java.cards.itens.Carta;

public class JogadorAI extends Jogador{
    public JogadorAI(){
        super("IA");
    }

    public int pegar_carta(int carta){
        if (carta >= 18){
            return 1;
        }
        else {
            return 2;
        }
    }
}
