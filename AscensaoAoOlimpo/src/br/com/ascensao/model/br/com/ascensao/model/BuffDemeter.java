package br.com.ascensao.model;

public class BuffDemeter implements EfeitoDivino{
    @Override
    // troquei o public void por public String e substitui o system out por return, pra aparecer no log de batalha (Larissa)
    public String aplicarBuff(SemiDeus alvo){
        alvo.getStatus().setFrutaSagrada(true);
        return "Demeter te concedeu a fruta sagrada que cura";
    }
}