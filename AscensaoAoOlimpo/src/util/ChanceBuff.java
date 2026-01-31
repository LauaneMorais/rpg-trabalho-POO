package util;

public class ChanceBuff {

    public static boolean Chance() {//modifcador static para nn precisar criar novo objeto.
        int chance = Dado.rolar(100);
        if (chance <= 80) {
            return true;
        }
        return false;
    }

}