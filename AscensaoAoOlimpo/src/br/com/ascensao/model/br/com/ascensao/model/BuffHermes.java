package br.com.ascensao.model;

public class BuffHermes implements EfeitoDivino{
    @Override
    public void aplicarBuff(SemiDeus alvo){
        alvo.setModificadorDano(1.2);//dano aumenta 20%
        System.out.println("Hermes te concedeu aumento de velociade, dano ao oponente aumenta em 20%");
    }
}