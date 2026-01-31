package br.com.ascensao.model;

public class BuffHermes implements EfeitoDivino{
    @Override
    public String aplicarBuff(SemiDeus alvo){
        alvo.getStatus().setModificadorDano(1.2);//dano aumenta 20%
        return "Hermes te concedeu aumento de velociade, dano ao oponente aumenta em 20%";
    }
}