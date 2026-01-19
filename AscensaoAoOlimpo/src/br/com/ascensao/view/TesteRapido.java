package br.com.ascensao.view;

import javax.swing.*;
import java.awt.*;
import br.com.ascensao.model.SemiDeus;

public class TesteRapido {
    
    public static void main(String[] args) {
        // 1. Configura a Janela de Teste
        JFrame janela = new JFrame("Teste Visual - Painel do Personagem");
        janela.setSize(400, 500);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(new FlowLayout());
        // Fundo escuro para contrastar com o painel
        janela.getContentPane().setBackground(Color.DARK_GRAY);

        // 2. CRIANDO UM BONECO DE MENTIRA (MOCK)
        // Precisamos criar uma classe com "Hefesto" no nome para sua lógica de imagem funcionar!
        class FilhoHefestoTeste extends SemiDeus {
            public FilhoHefestoTeste() {
                // Nome, Vida, Ataque, Defesa
                super("Leo Valdez", 100, 45, 80); 
            }
            
            @Override
            public void atacar(SemiDeus alvo) {
                // Não faz nada no teste visual
            }
        }

        // Instancia o boneco
        SemiDeus meuBoneco = new FilhoHefestoTeste();

        // 3. CRIA O SEU PAINEL
        System.out.println("Criando painel para: " + meuBoneco.getClass().getSimpleName());
        PainelSemiDeus painel = new PainelSemiDeus(meuBoneco);
        janela.add(painel);

        // 4. BOTÕES DE CONTROLE
        JPanel controles = new JPanel();
        
        JButton btnDano = new JButton("Levar Dano (-15)");
        btnDano.addActionListener(e -> {
            double novaVida = meuBoneco.getPontosvida() - 15;
            meuBoneco.setPontosvida(novaVida);
            painel.atualizarPainel(); // Testa a barra descendo e mudando de cor
        });

        JButton btnCurar = new JButton("Curar (+20)");
        btnCurar.addActionListener(e -> {
            double novaVida = meuBoneco.getPontosvida() + 20;
            // Impede de passar do máximo (lógica simples de teste)
            if(novaVida > meuBoneco.getPontosvidaMax()) novaVida = meuBoneco.getPontosvidaMax();
            
            meuBoneco.setPontosvida(novaVida);
            painel.atualizarPainel();
        });

        controles.add(btnDano);
        controles.add(btnCurar);
        
        janela.add(controles);

        // Mostra a janela
        janela.setVisible(true);
    }
}