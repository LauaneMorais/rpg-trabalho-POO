package br.com.ascensao.model;

public class BuffPoseidon implements EfeitoDivino{
    @Override
    public String aplicarBuff(SemiDeus alvo){
        alvo.getStatus().setTemReflexo(true);
        return "Poseidon te concedeu seu escudo e reflete o dano do inimigo";
    }

}