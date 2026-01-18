package br.com.ascensao.util;

public class ChanceBuff {

    public static boolean Chance(int chance) {//modifcador static para nn precisar criar novo objeto.
        chance = Dado.rolar(100);
        if (chance <= 50) {
            return true;
        }
        return false;
    }

}
