package br.com.ascensao.model;

import br.com.ascensao.util.*;

public class FilhoApolo extends SemiDeus {

    private static final int chanceCritico = 40;// variavel global imutavel
    private double multiplicador;// dobra a chance.

    public FilhoApolo() {

    }

    public FilhoApolo(String nome) {
        super(nome, 70.0, 15.0, 4.0);
        this.multiplicador = 2.0;
    }

    @Override
    public void atacar(SemiDeus alvo) {
        int chance = Dado.rolar(100);
        double danoInicial = this.getAtaqueBase() * this.getModificadorDano();// dano base
        double danoCritico = this.multiplicador * danoInicial;
        if (chance <= chanceCritico) { // se o numero sorteado no dado for <= 40 (40 numeros chance)
            alvo.receberDano(danoCritico,this);
            System.out.println("\nCRÍTICO! " + this.getNome() + " acertou uma flecha de luz em um ponto vital!");// efeito

        } else {
            alvo.receberDano(danoInicial,this);
        }
    }

    public static int getChancecritico() {
        return chanceCritico;
    }

    public double getMultiplicador() {
        return multiplicador;
    }

    public void setMultiplicador(double multiplicador) {
        this.multiplicador = multiplicador;
    }

}
