package br.edu.ifsp.spo.java.cards.nucleo;

import br.edu.ifsp.spo.java.cards.itens.Carta;

import java.util.ArrayList;
import java.util.List;

public class Jogador  {

    private final String nome;
    private List<Carta> mao;
    private int pontos_geral;

    public Jogador(String nome){
        this.nome = nome;
        this.mao = new ArrayList<>();
        this.pontos_geral = 0;

    }

    public void receberCarta(Carta carta){
        this.mao.add(carta);
    }

    public void mudarPontos(int pontos){ this.pontos_geral+=pontos;}

    public int getPontos(){return this.pontos_geral;}

    @Override
    public String toString() {
        String resultado = "\nJogador: " + this.nome;

        resultado += "\n A mão do jogador é:";

        for(Carta carta : this.mao){
            resultado += "\n- " + carta.toString();
        }

        return resultado;
    }

    public List<Carta> getMao() {
        return this.mao;
    }
    public void limparMao(){this.mao.clear();}



}
