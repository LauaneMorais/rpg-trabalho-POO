package model;

public class BuffAthena implements EfeitoDivino {
    @Override
    // troquei o public void por public String e substitui o system out por return, pra aparecer no log de batalha (Larissa)
    public String aplicarBuff(SemiDeus alvo){
        alvo.getStatus().setModificadorDefesa(1.5);
        return "Athena te concedeu uma benção, sua defesa aumentou 50%";
    }
}