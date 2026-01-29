package br.com.ascensao.model;

public class BuffPoseidon implements EfeitoDivino{
    @Override
    public void aplicarBuff(SemiDeus alvo){
        alvo.setTemReflexo(true);
        System.out.println("Poseidon te concedeu seu escudo e reflete o dano do inimigo");
    }

}