package br.com.ascensao.model;

import br.com.ascensao.util.*;

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
            return new FilhoHefesto(nome); // retorna nome do guardião/tanque
        } else if (sorteado == 2) {
            return new FilhoHecate(nome); // retorna nome do arcanista/mago
        } else if (sorteado == 3) {
            return new FilhoApolo(nome); // retorna nome do caçador/atirador
        } else {
            return new FilhoAres(nome); // retorna nome do guerreiro
        }

    }

    // aqui preenchemos as listas p/ cada lado com os combatentes
    public int formarEquipes() {
        // uma batalha pode ser um duelo de 1 contra 1, ou uma guerra massiva de 100
        // contra 100
        int tam = Dado.rolar(100);
        System.out.println(">>>>>>>>> TORNEIO COMEÇA!!");
        for (int i = 1; i <= tam; i++) {
            // escolhe combatente aleatório e adiciona no ladoA
            SemiDeus c1 = sortearCombatente(">>> Combatente A " + i);
            this.ladoA.add(c1);

            // escolhe combatente aleatório e adiciona no ladoB
            SemiDeus c2 = sortearCombatente(">>> Combatente B " + i);
            this.ladoB.add(c2);
        }

        return tam;
    }

    public boolean temSobreviventes(ArrayList<SemiDeus> lado) {
        for (SemiDeus guerreiro : lado) {
            if (guerreiro.estaVivo()) {
                return true; // tem combatentes
            }
        }
        return false; // sobrou ninguém
    }

    /*
     * Quando chega a vez de um combatente agir, ele escolhe um alvo aleatorio entre
     * os membros vivos
     * da equipe adversaria e desfere seu ataque caracterıstico.
     */

    public void turnoCombate(ArrayList<SemiDeus> atacantes, ArrayList<SemiDeus> defensores) {
        if (defensores.isEmpty()) { // verificação para ver a lista de defesa está vazia
            return;
        }

        for (SemiDeus a : atacantes) {
            if (!a.estaVivo()) { // para que mortos n ataquem
                continue;
            }

            int i = Dado.rolar(defensores.size()) - 1;
            SemiDeus alvo = defensores.get(i); // atribui um alvo com base em um indice aleatório da lista de defensores
            System.out.println(">>> " + a.getNome() + " ataca " + alvo.getNome());
            a.atacar(alvo); // atacante faz o ataque contra o alvo escolhido
            if (!alvo.estaVivo()) { // verifica se o alvo morreu para poder remove-lo da lista
                defensores.remove(alvo);
            }
        }
    }

    public void iniciarCombate() {
        int quantRodadas = 1;
        while (temSobreviventes(ladoA) && temSobreviventes(ladoB)) {
            System.out.println("<<<<<< RODADA " + quantRodadas);
            // lado A ataca
            turnoCombate(ladoA, ladoB);
            if (!temSobreviventes(ladoB)) {
                break;
            }
            // lado B contra-ataca
            turnoCombate(ladoB, ladoA);
            quantRodadas++;
        }
        if (temSobreviventes(ladoA)) {
            System.out.println("<<<<< VENCEDOR: LADO A");
        } else {
            System.out.println("<<<<< VENCEDOR LADO B!!");
        }
    }

}
