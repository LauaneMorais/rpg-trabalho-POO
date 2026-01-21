package br.com.ascensao.util;

import java.util.Random; //pacote para aleatoriedade

public class Dado {

    private static Random aleatorio = new Random();// craindo objeto random

    public static int rolar(int faces) { // recebe n faces e retorna um numero aleatorio de de 1 até n
        return aleatorio.nextInt(faces) + 1;
    }

}
