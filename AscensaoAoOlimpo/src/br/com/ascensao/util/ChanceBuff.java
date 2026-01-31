package br.com.ascensao.util;

public class ChanceBuff {

    public static boolean Chance() {//modifcador static para nn precisar criar novo objeto.
        int chance = Dado.rolar(100);
        if (chance <= 60) {
            return true;
        }
        return false;
    }

}
