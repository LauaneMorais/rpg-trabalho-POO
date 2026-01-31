package br.com.ascensao.model;

public class BuffAfrodite implements EfeitoDivino{
    @Override
    public String aplicarBuff(SemiDeus alvo){ // tirando o public void dos buffs e o system out substituindo por return
        alvo.getStatus().setModificadorDano(0.5);
        return "Afodite te concedeu uma benção, seu dano foi reduzido pela metade";
    }
}