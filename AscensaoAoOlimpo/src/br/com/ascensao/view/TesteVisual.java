package br.com.ascensao.view;

import javax.swing.*;
import java.awt.*;


//TESTE GERADO POR IA



//Isso aqui é só para você testar o visual! 
// Não faz parte do jogo final.
public class TesteVisual {
    
    public static void main(String[] args) {
        // 1. Cria uma janela de teste
        JFrame janela = new JFrame("Teste do Painel");
        janela.setSize(400, 300);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(new FlowLayout());
        janela.getContentPane().setBackground(Color.BLACK);

        // 2. Cria um "SemiDeus Falso" para testar (MOCK)
        // Como o SemiDeus é abstrato, criamos uma classe anônima rápida
        br.com.ascensao.model.SemiDeus bonecoTeste = new br.com.ascensao.model.SemiDeus("Hércules Teste", 100, 20, 10) {
            @Override
            public void atacar(br.com.ascensao.model.SemiDeus alvo) {
                // Não faz nada, só teste visual
            }
        };
        
        // Simulando que ele perdeu vida
        bonecoTeste.setPontosvida(60); 

        // 3. Cria o SEU painel passando o boneco
        PainelSemiDeus meuPainel = new PainelSemiDeus(bonecoTeste);
        
        // 4. Adiciona na janela
        janela.add(meuPainel);
        
        // 5. Botão para testar dano
        JButton btnDano = new JButton("Levar Dano");
        btnDano.addActionListener(e -> {
            double vidaNova = bonecoTeste.getPontosvida() - 15;
            bonecoTeste.setPontosvida(vidaNova);
            meuPainel.atualizarVisual(); // <--- A MÁGICA ACONTECE AQUI
        });
        janela.add(btnDano);

        janela.setVisible(true);
    }
}