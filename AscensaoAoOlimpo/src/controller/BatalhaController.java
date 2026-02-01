package controller;

import java.util.ArrayList;
import java.util.List;

import model.*;
import util.*;
import view.*;

public class BatalhaController {

    private Equipes arenaBatalha;
    private int indexA = 0;

    public BatalhaController(Equipes arenaBatalha) {
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
     * os membros vivos da equipe adversaria e desfere seu ataque caracterıstico.
     */
    public void turnoCombate(ArrayList<SemiDeus> atacantes, ArrayList<SemiDeus> defensores) {

        for (SemiDeus a : atacantes) {

            if (!a.estaVivo()) { // para que mortos n ataquem
                continue;
            }

            if (!temSobreviventes(defensores)) { // verificação para ver se a lista de defesa está vazia
                return;
            }

            SemiDeus alvo = escolherAlvo(defensores); // chamando metodo para escolher alvo aleatorio

            if (alvo != null) { // se existe um alvo, chama o metodo atacar
                System.out.println("\n" + a.getNome() + " ataca " + alvo.getNome() + "\n");
                a.atacar(alvo);
            }
        }
    }

    // Metodo auxiliar para escolher alvo aleatorio
    private SemiDeus escolherAlvo(ArrayList<SemiDeus> lista) {
        List<SemiDeus> guerreirosVivos = new ArrayList<>(); 

        for (SemiDeus vivos : lista) { // percorre a lista e filtra só os vivos.
            if (vivos.estaVivo()) {
                guerreirosVivos.add(vivos);
            }
        }

        if (guerreirosVivos.isEmpty()) { // retornar null caso a lista esteja vazia
            return null;
        }

        int sorteio = Dado.rolar(guerreirosVivos.size()) - 1; // sorteio de guerreio aleatorio
        return guerreirosVivos.get(sorteio); 
    }

    public void iniciarCombate() {
        arenaBatalha.formarEquipes(); // aciona a model equipes para formar as equipes

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

    // --- MÉTODOS ADICIONADOS PELA LARISSA (INTEGRADOS NA ESTRUTURA NOVA) ---

    // Metodo para formatar o nome do semi deus com o lado e numero
    private String formatarNome(SemiDeus s, String lado) {
        String deus = "";
        String classe = s.getClass().getSimpleName();
    
        // descobre o deus pai baseado na classe
        if (classe.contains("Hecate")) deus = "Hécate";
        else if (classe.contains("Ares")) deus = "Ares";
        else if (classe.contains("Apolo")) deus = "Apolo";
        else if (classe.contains("Hefesto")) deus = "Hefesto";

        // descobre a posição na lista para o ID (A1, B2, etc) 
        int posicao = (lado.equals("A") ? arenaBatalha.getLadoA().indexOf(s) : arenaBatalha.getLadoB().indexOf(s)) + 1;
        
        return "Filho de " + deus + " (" + lado + posicao + ")";
    }

    // Metodo para realizar o duelo de duplas (Lógica completa)
    public String dueloDeDuplas() { 
        ArrayList<SemiDeus> ladoA = arenaBatalha.getLadoA();
        ArrayList<SemiDeus> ladoB = arenaBatalha.getLadoB();

        // se um lado nao tem mais sobreviventes, fim de jogo
        if (!temSobreviventes(ladoA) || !temSobreviventes(ladoB)) {
            return "\nO DESTINO FOI SELADO. FIM DE JOGO!";
        }

        // se o indexA ultrapassar o tamanho da lista, reseta e inicia nova rodada
        if (indexA >= ladoA.size()) {
            indexA = 0;
            return "\n---NOVA RODADA INICIADA ---";
        }
        
        // pega o combatente atual do lado A
        SemiDeus atacante = ladoA.get(indexA);
        indexA++;

        // se o combatente estiver vivo, escolhe um alvo do lado B e ataca
        if (atacante.estaVivo()) {
            SemiDeus alvo = escolherAlvo(ladoB);
            
            // se encontrou um alvo vivo
            if (alvo != null) {
                // captura dados antes do ataque 
                double vidaAntes = alvo.getPontosvida();
                String nomeAtacante = formatarNome(atacante, "A");
                String nomeAlvo = formatarNome(alvo, "B");

                atacante.atacar(alvo);

                // calculo do dano real
                double dano = vidaAntes - alvo.getPontosvida();

                // montagem do Log detalhado
                StringBuilder sb = new StringBuilder();
                sb.append(nomeAtacante).append(" ataca ").append(nomeAlvo).append("\n");
                sb.append(nomeAlvo).append(" recebeu ").append(dano).append(" de dano.\n");
                sb.append("Vida restante: ").append(alvo.getPontosvida()).append("\n");
                
                // verifica se o alvo morreu
                if (!alvo.estaVivo()) {
                    sb.append(nomeAlvo).append(" tombou em combate!\n");
                }
                return sb.toString();
            }
        }

        return "O combatente atual está caído, passando para o próximo...";
    }
}