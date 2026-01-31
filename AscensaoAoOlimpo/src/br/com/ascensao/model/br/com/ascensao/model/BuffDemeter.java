package br.com.ascensao.model;

public class BuffDemeter implements EfeitoDivino{
    @Override
    public String aplicarBuff(SemiDeus alvo){
        alvo.getStatus().setFrutaSagrada(true);
        return "Demeter te concedeu a fruta sagrada que cura";
    }
}