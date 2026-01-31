package br.com.ascensao.model;

public class BuffAthena implements EfeitoDivino{
    @Override
    public String aplicarBuff(SemiDeus alvo){
        alvo.getStatus().setModificadorDefesa(1.5);
        return "Athena te concedeu uma benção, sua defesa aumentou 50%";
    }
    
}