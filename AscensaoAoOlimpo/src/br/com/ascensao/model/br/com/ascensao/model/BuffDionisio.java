package br.com.ascensao.model;

public class BuffDionisio implements EfeitoDivino{
    @Override
    public String aplicarBuff(SemiDeus alvo){
        alvo.getStatus().setEstaAtordoado(true);//dano 0
        return "Seu atacante ficou atordoado por beber demais e errou o golpe!";
    }

}