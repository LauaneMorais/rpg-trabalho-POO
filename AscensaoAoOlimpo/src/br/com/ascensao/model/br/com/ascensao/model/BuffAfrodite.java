package br.com.ascensao.model;

public class BuffAfrodite implements EfeitoDivino{
    @Override
    // troquei o public void por public String e substitui o system out por return, pra aparecer no log de batalha (Larissa)
    public String aplicarBuff(SemiDeus alvo){ 
        alvo.getStatus().setModificadorDano(0.5);
        return "Afodite te concedeu uma benção, seu dano foi reduzido pela metade";
    }
}