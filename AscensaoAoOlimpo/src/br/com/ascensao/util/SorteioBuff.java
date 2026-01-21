package br.com.ascensao.util;

import br.com.ascensao.model.*;

public class SorteioBuff {
    static int aux =0;

    public static void aplicarBuffAleatorio(SemiDeus alvo) {//modificador statico para nn precisar criar objeto.
        System.out.println("buff concedido para " + alvo.getNome() + "!"); // facilitar visualmente na fase de testes
        int sorteio = Dado.rolar(6);
        aux++;
        switch (sorteio) {
            case 1:// buff dionisio
                alvo.setEstaAtordoado(true);// atacante nn causa dano pois nn acerta o alvo.
                System.out.println("dionisio-foi atordoado");
                break;
            case 2:// buff hermes
                alvo.setModificadorDano(1.2); // o dano aumenta em 20%
                System.out.println("hermes-auemento de 20% no dano");
                break;
            case 3:// buff afrodite
                alvo.setModificadorDano(0.5);// dano reduz em 50%
                System.out.println("afrodite-dano reduzido em 50%");
                break;
            case 4:// buff athena
                alvo.setModificadorDefesa(1.5);// defesa aumenta em 50%
                System.out.println("athena-defesa aumentada em 50%");
                break;
            case 5:// buff poseidon
                alvo.setTemReflexo(true);// tem escudo de poseidon e dano é reduzido pela metade.(e metade volta ao
                System.out.println("poseidon-ativou escudo,recebe metade do dano");                      
                break;
            case 6:// buff deméter
                alvo.setFrutaSagrada(true);// aumenta vida em 50% estantaneamente
                System.out.println("demeter-cura aumenta 50%");
                break;
        }

    }

    public static int getAux() {
        return aux;
    }

    public void setAux(int aux) {
        SorteioBuff.aux = aux;
    }


}
