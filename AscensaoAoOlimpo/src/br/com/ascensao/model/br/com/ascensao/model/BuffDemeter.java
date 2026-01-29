package br.com.ascensao.model;

public class BuffDemeter implements EfeitoDivino{
    @Override
    public void aplicarBuff(SemiDeus alvo){
        alvo.setFrutaSagrada(true);
        System.out.println("Demeter te concedeu a fruta sagrada que cura");
    }
}