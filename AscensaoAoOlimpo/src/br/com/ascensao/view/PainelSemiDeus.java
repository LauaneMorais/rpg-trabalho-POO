package br.com.ascensao.view;

import br.com.ascensao.model.SemiDeus;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.net.URL;

//classe do cartão visual de um combatente
public class PainelSemiDeus extends JPanel {

    private SemiDeus semiDeus;

    private JLabel nomePersonagem;
    private JLabel imagemPersonagem;
    private JProgressBar barraVida;
    private JLabel statusPersonagem;

    private final Color COR_FUNDO = new Color(40, 40, 45);
    private final Color COR_TEXTO = new Color(240, 240, 240);
    private final Color COR_BORDA_VIVA = new Color(218, 165, 32); // dourado
    private final Color COR_BORDA_MORTA = new Color(100, 100, 100); // cinza


    public PainelSemiDeus (SemiDeus semiDeus) {
        this.semiDeus = semiDeus;

        //config do painel
        this.setLayout(new BorderLayout(10, 10));
        this.setBackground(COR_FUNDO);
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1),
                BorderFactory.createBevelBorder(BevelBorder.RAISED) // efeito 3D na borda
        ));

        this.setPreferredSize(new Dimension(200, 240));

        inicializarComponentes();
        atualizarPainel(); // chama uma vez para pintar a tela inicial
    }

    private void inicializarComponentes() {

        //nome do personagem que vai em cima no layout

        nomePersonagem = new JLabel(semiDeus.getNome(), SwingConstants.CENTER); //pega nome
        nomePersonagem.setForeground(Color.WHITE); //texto branquinho
        nomePersonagem.setFont(new Font("Segoe UI", Font.BOLD, 14));
        this.add(nomePersonagem, BorderLayout.NORTH); //nome em cima


        //imagem do personagem que vai no meio do layout

        imagemPersonagem = new JLabel();
        imagemPersonagem.setHorizontalAlignment(SwingConstants.CENTER);

        ImageIcon icone = carregarIconePersonagem(); 
        if (icone != null) {
            imagemPersonagem.setIcon(icone); // só cola a imagem se tiver achado 
        }
        this.add(imagemPersonagem, BorderLayout.CENTER); //imagem no meio da tela

        //vida e status que vai la embaixo

        //status
        JPanel painelInferior = new JPanel(new GridLayout(2,1));
        statusPersonagem = new JLabel("Ataque ⚔ : " + (int)semiDeus.getAtaqueBase() + "   Defesa ⛨ : " + (int)semiDeus.getDefesaBase());
        painelInferior.add(statusPersonagem);

        //vida
        // CORRIGIDO: Adicionei o ponto e vírgula no final da linha abaixo
        barraVida = new JProgressBar(0, (int) semiDeus.getPontosvidaMax()); //vai de 0 ate o valor max permitido do personagem
        barraVida.setValue((int) semiDeus.getPontosvida());
        barraVida.setStringPainted(true);
        painelInferior.add(barraVida);

        this.add(painelInferior, BorderLayout.SOUTH); 

        //tem que ter um grid layout poque o borderlayout so aceita um elemento por espaço, aí tem q criar um subelemento pra ele encaixar
    }

    public void atualizarPainel() {

        //atualiza atributos
        int vidaAtual = (int) semiDeus.getPontosvida();
        int vidaMaxima = (int) semiDeus.getPontosvidaMax(); 

        //atualiza barra de vida
        barraVida.setValue(vidaAtual);
        barraVida.setString(vidaAtual+ "/" + vidaMaxima); //ex: 76/120

        if (vidaAtual > vidaMaxima * 0.6) {
            barraVida.setForeground(new Color(50, 205, 50)); // barra de vida verde, muita vida
        } else if (vidaAtual > vidaMaxima * 0.3) { 
            barraVida.setForeground(new Color(255, 165, 0)); // barra laranja
        } else { 
            barraVida.setForeground(new Color(220, 20, 60)); // barra vermelha, pouca vida
        }
    }



private ImageIcon carregarIconePersonagem() {
        String nomeArquivo = "default.png"; 
        String nomeClasse = semiDeus.getClass().getSimpleName();
        
        // Lógica de seleção do arquivo
        if (nomeClasse.contains("Teste") || nomeClasse.isEmpty()) {
            nomeArquivo = "filho_hefesto.png"; // Força essa imagem no teste
        } 
        else if (nomeClasse.contains("Hefesto")) nomeArquivo = "filho_hefesto.png";
        else if (nomeClasse.contains("Hecate")) nomeArquivo = "filho_hecate.png";
        else if (nomeClasse.contains("Apolo")) nomeArquivo = "filho_apolo.png";
        else if (nomeClasse.contains("Ares")) nomeArquivo = "filho_ares.png";

        System.out.println("Procurando em: /br/com/ascensao/assets/" + nomeArquivo);

        try {
            // AQUI ESTÁ A CORREÇÃO MÁGICA DO CAMINHO:
            URL url = getClass().getResource("/br/com/ascensao/assets/" + nomeArquivo);
            
            if (url == null) {
                System.out.println("ERRO: Java não encontrou em /br/com/ascensao/assets/");
                return null;
            }

            ImageIcon iconOriginal = new ImageIcon(url);
            Image img = iconOriginal.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}