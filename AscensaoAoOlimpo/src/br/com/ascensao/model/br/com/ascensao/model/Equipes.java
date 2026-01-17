package br.com.ascensao.model;

import java.util.ArrayList;

public class Equipes {
    // definir atributos com dois lados opostos(Lado A e Lado B)
    private ArrayList<SemiDeus> ladoA;
    private ArrayList<SemiDeus> ladoB;

    // Construtor p/ preparar a arena de combate
    public Equipes(){
        this.ladoA = new ArrayList<SemiDeus>();
        this.ladoB = new ArrayList<SemiDeus>();
    }

    private SemiDeus sortearCombatente(String nome) {
        // definimos 4 tipos de combatentes, por isso são 4 faces p/ sortear
        int sorteado = Dado.rolar(4);
        if(sorteado == 1){
            return new FilhoHefesto(nome); // retorna nome do guardião/tanque
        }else if(sorteado == 2){
            return new FilhoHecate(nome); // retorna nome do arcanista/mago
        }else if(sorteado == 3){
            return new FilhoApolo(nome); // retorna nome do caçador/atirador
        }else{
            return new FilhoAres(nome); // retorna nome do guerreiro
        }

    }
    // aqui preenchemos as listas p/ cada lado com os combatentes
    public void iniciarCombate(){
        // uma batalha pode ser um duelo de 1 contra 1, ou uma guerra massiva de 100 contra 100
        int tam = Dado.rolar(100);
        System.out.println(">>>>>>>>> TORNEIO COMEÇA!!");
        for(int i = 1; i <= tam; i++){
            // escolhe combatente aleatório e adiciona no ladoA
            SemiDeus c1 = sortearCombatente(">>> Combatente A " + i);
            this.ladoA.add(c1);
        
            // escolhe combatente aleatório e adiciona no ladoB
            SemiDeus c2 = sortearCombatente(">>> Combatente B" + i);
            this.ladoB.add(c2);
        }
    }


}
