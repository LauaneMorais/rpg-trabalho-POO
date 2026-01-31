package model;

public class BuffHermes implements EfeitoDivino {
    @Override
    // troquei o public void por public String e substitui o system out por return, pra aparecer no log de batalha (Larissa)
    public String aplicarBuff(SemiDeus alvo){
        alvo.getStatus().setModificadorDano(1.2);//dano aumenta 20%
        return "Hermes te concedeu aumento de velociade, dano ao oponente aumenta em 20%";
    }
}