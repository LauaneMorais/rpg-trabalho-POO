package br.com.ascensao.util;

public class ChanceBuff {

    public boolean Chance (int chance){//colocar na classe util.
        chance = Dado.rolar(100);
        if(chance<=50){
            return true;
        }
        return false;
    }

}
