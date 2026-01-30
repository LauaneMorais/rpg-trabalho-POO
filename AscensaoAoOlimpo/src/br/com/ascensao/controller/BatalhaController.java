package br.com.ascensao.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.ascensao.model.*;
import br.com.ascensao.util.*;
import br.com.ascensao.view.*;
import java.util.ArrayList;
///hlhiligl
public class BatalhaController {

    private Equipes arenaBatalha;
    private int indexA = 0;


    public BatalhaController(Equipes arenaBatalha) {//inicializando a arena como objeto de equipes
        this.arenaBatalha = arenaBatalha;
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

        for (SemiDeus a : atacantes) {

            if (!a.estaVivo()) { // para que mortos n ataquem
                continue;
            }

            if (!temSobreviventes(defensores)) { // verificação para ver se a lista de defesa está vazia e assim não dá erro p/ quando for passar p/ o próximo atacante
            return;
            }

            SemiDeus alvo = escolherAlvo(defensores);//chamando metodo para escolher alvo aleatorio

            if(alvo != null){//se existe um alvo,chama o metodo atacar
                System.out.println("\n" + a.getNome() + " ataca " + alvo.getNome()+"\n");
                a.atacar(alvo);

            }
            
            }
            
        }
    

    private SemiDeus escolherAlvo(ArrayList<SemiDeus> lista){//metodo auxiliar para escolher alvo aleatorio
        List<SemiDeus> guerreirosVivos = new ArrayList<>();//criando objeto guerreiros vivos

            for (SemiDeus vivos : lista){ //for each pra percorrer a lista e filtrar só os vivos.
                if(vivos.estaVivo()){
                    guerreirosVivos.add(vivos);
                }
            }

                if(guerreirosVivos.isEmpty()){//retornar null caso a lista esteja vazia
                    return null;
                }

                int sorteio = Dado.rolar(guerreirosVivos.size())-1;//para sorteio de guerreio aleatorio da lista de vivos
                return guerreirosVivos.get(sorteio);//guerreiro sorteado
            
    }
    

    public void iniciarCombate() {
        arenaBatalha.formarEquipes();//aciona a model equipes para formar as equipes
        
        int quantRodadas = 1;
        while (temSobreviventes(arenaBatalha.getLadoA()) && temSobreviventes(arenaBatalha.getLadoB())) {
            System.out.println("\n\nRODADA " + quantRodadas + "\n");
            // lado A ataca
            turnoCombate(arenaBatalha.getLadoA(), arenaBatalha.getLadoB());
            if (!temSobreviventes(arenaBatalha.getLadoB())) {
                break;
            }
            // lado B contra-ataca
            turnoCombate(arenaBatalha.getLadoB(), arenaBatalha.getLadoA());
            quantRodadas++;
        }
        if (temSobreviventes(arenaBatalha.getLadoA())) {
            System.out.println("\nVENCEDOR: LADO A!!");
        } else {
            System.out.println("\nVENCEDOR LADO B!!");
        }
    }
    // larissa: init teste
   public String dueloDeDuplas(){

    ArrayList<SemiDeus> ladoA = arenaBatalha.getLadoA();
    ArrayList<SemiDeus> ladoB = arenaBatalha.getLadoB();

    //verificar se a rodada acabou ou se alguem ganhou
    if(!temSobreviventes(ladoA) || !temSobreviventes(ladoB))
        return "Fim de jogo!";
    if(indexA >= ladoA.size()) {
        indexA = 0;
        return "Nova rodada!";
    }
    SemiDeus atacante = ladoA.get(indexA);
    indexA++;

    if(atacante.estaVivo()){
        SemiDeus alvo = escolherAlvo(ladoB);
        if (alvo != null) {
            atacante.atacar(alvo);
            return atacante.getNome() + "atacou" + alvo.getNome();
        }
    }

    return "O combatente foi tombado, próximo...";
   }
    
    //larissa: end teste
}
