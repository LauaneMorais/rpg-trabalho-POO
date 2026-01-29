package br.com.ascensao.model;

public class BuffAfrodite implements EfeitoDivino{
    @Override
    public void aplicarBuff(SemiDeus alvo){
        alvo.getStatus().setModificadorDano(0.5);
        System.out.println("Afodite te concedeu uma benção, seu dano foi reduzido pela metade");
    }
}