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
  private String formatarNome(SemiDeus s, String lado) {
        String deus = "";
        String classe = s.getClass().getSimpleName();
        
        // Identifica o Panteão
        if (classe.contains("Hecate")) deus = "Hécate";
        else if (classe.contains("Ares")) deus = "Ares";
        else if (classe.contains("Apolo")) deus = "Apolo";
        else if (classe.contains("Hefesto")) deus = "Hefesto";

        // Descobre a posição na lista para o ID (A1, B2, etc)
        int posicao = (lado.equals("A") ? arenaBatalha.getLadoA().indexOf(s) : arenaBatalha.getLadoB().indexOf(s)) + 1;
        
        return "Filho de " + deus + " (" + lado + posicao + ")";
    }

    public String dueloDeDuplas() {
        ArrayList<SemiDeus> ladoA = arenaBatalha.getLadoA();
        ArrayList<SemiDeus> ladoB = arenaBatalha.getLadoB();

        if (!temSobreviventes(ladoA) || !temSobreviventes(ladoB)) {
            return "\n🏛️ O DESTINO FOI SELADO. FIM DE JOGO!";
        }

        if (indexA >= ladoA.size()) {
            indexA = 0;
            return "\n--- 🔄 NOVA RODADA INICIADA ---";
        }

        SemiDeus atacante = ladoA.get(indexA);
        indexA++;

        if (atacante.estaVivo()) {
            SemiDeus alvo = escolherAlvo(ladoB);
            
            if (alvo != null) {
                // Captura dados antes do ataque para o log detalhado
                double vidaAntes = alvo.getPontosvida();
                String nomeAtacante = formatarNome(atacante, "A");
                String nomeAlvo = formatarNome(alvo, "B");

                atacante.atacar(alvo);

                // Cálculo do dano real (Igual ao seu terminal)
                double dano = vidaAntes - alvo.getPontosvida();

                // Montagem do Log detalhado
                StringBuilder sb = new StringBuilder();
                sb.append("\n⚔️ ").append(nomeAtacante).append(" ataca ").append(nomeAlvo).append("\n");
                sb.append("💥 ").append(nomeAlvo).append(" recebeu ").append(dano).append(" de dano.\n");
                sb.append("❤️ Vida restante: ").append(alvo.getPontosvida()).append("\n");

                if (!alvo.estaVivo()) {
                    sb.append("💀 ").append(nomeAlvo).append(" tombou em combate!\n");
                }
                return sb.toString();
            }
        }

        return "⏭️ O combatente atual está caído, passando para o próximo...";
    }
    //larissa: end teste
}
