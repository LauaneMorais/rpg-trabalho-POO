package model;

import util.*;
import java.util.ArrayList;

public class Equipes {
    // definir atributos com dois lados opostos(Lado A e Lado B)
    private ArrayList<SemiDeus> ladoA;
    private ArrayList<SemiDeus> ladoB;

    // Construtor p/ preparar a arena de combate
    public Equipes() {
        this.ladoA = new ArrayList<SemiDeus>();
        this.ladoB = new ArrayList<SemiDeus>();
    }

    private SemiDeus sortearCombatente(String nome) {
        // definimos 4 tipos de combatentes, por isso são 4 faces p/ sortear
        int sorteado = Dado.rolar(4);
        if (sorteado == 1) {
            return new FilhoHefesto(nome); 
        } else if (sorteado == 2) {
            return new FilhoHecate(nome); 
        } else if (sorteado == 3) {
            return new FilhoApolo(nome); 
        } else {
            return new FilhoAres(nome); 
        }
    }

    // aqui preenchemos as listas p/ cada lado com os combatentes
    public int formarEquipes() {

        // uma batalha pode ser um duelo de 1 contra 1, ou uma guerra massiva de 100
        // contra 100
        int tam = Dado.rolar(2);//modifiquei pra 5 só para facilitar nos testes (larissa)
        System.out.println(">>>>>>>>> TORNEIO COMEÇA!!<<<<<<<<<<<\n");
        System.out.printf("batalha %d contra %d\n ",tam,tam);
        for (int i = 1; i <= tam; i++) {
            SemiDeus c1 = sortearCombatente("Combatente A" + i);
            this.ladoA.add(c1);

            SemiDeus c2 = sortearCombatente("Combatente B" + i);
            this.ladoB.add(c2);
        }

        return tam;
    }
    
    public ArrayList<SemiDeus> getLadoA() {
        return ladoA;
    }
    public ArrayList<SemiDeus> getLadoB() {
        return ladoB;
    }

    public boolean temVencedor() {
         return ladoA.isEmpty() || ladoB.isEmpty(); 
         // Lógica simplificada: se uma lista estiver vazia (todos mortos removidos), o outro venceu.
         // Ou você pode verificar se todos .estaVivo() == false.
    }
}