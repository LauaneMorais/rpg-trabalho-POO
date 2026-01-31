package br.com.ascensao.model;

public class BuffPoseidon implements EfeitoDivino{
    @Override
    // troquei o public void por public String e substitui o system out por return, pra aparecer no log de batalha (Larissa)
    public String aplicarBuff(SemiDeus alvo){
        alvo.getStatus().setTemReflexo(true);
        return "Poseidon te concedeu seu escudo e reflete o dano do inimigo";
    }

}