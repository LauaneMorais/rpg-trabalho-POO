package model;

import util.*;

public class FilhoApolo extends SemiDeus {

    private static final int chanceCritico = 40;// variavel global imutavel
    private double multiplicador;// dobra a chance.
    private int chance;

    public FilhoApolo() {

    }

    public FilhoApolo(String nome) {
        super(nome, 70.0, 15.0, 4.0);
        this.multiplicador = 2.0;
    }

    @Override
    public void atacar(SemiDeus alvo) {
        chance = Dado.rolar(100);
        double danoInicial = this.getAtaqueBase() * this.getStatus().getModificadorDano();// dano base
        double danoCritico = this.multiplicador * danoInicial;
        if (chance <= chanceCritico) { // se o numero sorteado no dado for <= 40 (40 numeros chance)
            System.out.println("CRÍTICO! " + this.getNome() + " acertou uma flecha de luz em um ponto vital!");// efeito
            alvo.receberDano(danoCritico,this);
            

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

    public int getChance() {
        return chance;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }
}