package br.com.ascensao.model;

public class BuffAthena implements EfeitoDivino{
    @Override
    public void aplicarBuff(SemiDeus alvo){
        alvo.getStatus().setModificadorDefesa(1.5);
        System.out.println("Athena te concedeu uma benção, sua defesa aumentou 50%");
    }
    
}