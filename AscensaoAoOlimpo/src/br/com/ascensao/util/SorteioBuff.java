package br.com.ascensao.util;

import br.com.ascensao.model.*;

public class SorteioBuff {

    public static void aplicarBuffAleatorio(SemiDeus alvo) {//modificador statico para nn precisar criar objeto.
        int sorteio = Dado.rolar(6);
        switch (sorteio) {
            case 1:// buff dionisio
                alvo.setEstaAtordoado(true);// atacante nn causa dano pois nn acerta o alvo.
                break;
            case 2:// buff hermes
                alvo.setModificadorDano(1.2); // o dano aumenta em 20%
                break;
            case 3:// buff afrodite
                alvo.setModificadorDano(0.5);// dano reduz em 50%
                break;
            case 4:// buff athena
                alvo.setModificadorDefesa(1.5);// defesa aumenta em 50%
                break;
            case 5:// buff poseidon
                alvo.setTemReflexo(true);// tem escudo de poseidon e dano é reduzido pela metade.(e metade volta ao
                                         // oponente)
                break;
            case 6:// buff deméter
                alvo.setFrutaSagrada(true);// aumenta vida em 50% estantaneamente
                break;
        }

    }

}
