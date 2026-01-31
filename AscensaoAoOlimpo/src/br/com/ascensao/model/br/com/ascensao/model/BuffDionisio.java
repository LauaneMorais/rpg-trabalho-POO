package br.com.ascensao.model;

public class BuffDionisio implements EfeitoDivino{
    @Override
    // troquei o public void por public String e substitui o system out por return, pra aparecer no log de batalha (Larissa)
    public String aplicarBuff(SemiDeus alvo){
        alvo.getStatus().setEstaAtordoado(true);//dano 0
        return "Seu atacante ficou atordoado por beber demais e errou o golpe!";
    }

}