package br.com.ascensao.model;

public class BuffDionisio implements EfeitoDivino{
    @Override
    public void aplicarBuff(SemiDeus alvo){
        alvo.setEstaAtordoado(true);//dano 0
        System.out.println("Seu atacante ficou atordoado por beber demais e errou o golpe!");
    }

}