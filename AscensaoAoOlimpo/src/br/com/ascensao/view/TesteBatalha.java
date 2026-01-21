package br.com.ascensao.view;

import br.com.ascensao.model.*;
import javax.swing.SwingUtilities;

public class TesteBatalha {
    

    //usando IA para fazer o testeeeeeeeeeeeeee
    public static void main(String[] args) {
        // SwingUtilities garante que a interface gráfica rode na thread certa
        SwingUtilities.invokeLater(() -> {
            
            // 1. ESCOLHA OS LUTADORES AQUI
            // Tente trocar as classes depois (Ex: FilhoApolo vs FilhoHecate)
            SemiDeus jogador = new FilhoApolo("fi de apolo (time zeus)");
            SemiDeus inimigo = new FilhoHefesto("fi de hecate (time hades)");

            // 2. Cria a tela passando os lutadores
            TelaBatalha tela = new TelaBatalha(jogador, inimigo);
            
            // 3. Centraliza e exibe
            tela.setLocationRelativeTo(null);
            tela.setVisible(true);
        });
    }
}

// primeiro teste: Filho de ares tem roubo de vida desbalanceado em comparação ao filho de hefesto
// segundo teste: filho de apolo foi MASSACRADO por filho de ares, tomou muito dano e nao tirou nem metade da vida dele
// terceiero teste: filho de ares esta perdendo as estribeirAs e matou filho de hecate

//fi de apolo morre muito rapido pra fi de hecate tambem
//apolo ganhou de hefesto e tomou pouco danoe