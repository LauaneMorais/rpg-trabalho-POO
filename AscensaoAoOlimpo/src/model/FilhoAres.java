package model;

import util.*;

public class FilhoAres extends SemiDeus {

    private double taxaRouboVida;

    public FilhoAres() {

    }

    public FilhoAres(String nome) {
        super(nome, 110.0, 20.0, 2.0);//ATRIBUTOS OK
        this.taxaRouboVida = 0.25;//alterar taxa de roubo de vida após testes de balanceamento
    }

    @Override
    public void atacar(SemiDeus alvo) {
        double danoInicial = this.getAtaqueBase() * this.getStatus().getModificadorDano();// dano no inimigo a priori
        double vidaInimigoInicial = alvo.getPontosvida(); // vida do inimigo antes do ataque
        alvo.receberDano(danoInicial,this);

        double vidaInimigoApos = alvo.getPontosvida(); // vida do inimigo depois do ataque

        double danoCalculado = vidaInimigoInicial - vidaInimigoApos;// a diferença entre as duas vidas é o dano real.

        if (danoCalculado > 0) {
            double valorCura = danoCalculado * taxaRouboVida;// habilidade única, quando causa dano se cura.

            this.curar(valorCura);
        } else {
            this.curar(0.0);
        }

    }

    public double getTaxaRouboVida() {
        return taxaRouboVida;
    }

    public void setTaxaRouboVida(double taxaRouboVida) {
        this.taxaRouboVida = taxaRouboVida;
    }
}