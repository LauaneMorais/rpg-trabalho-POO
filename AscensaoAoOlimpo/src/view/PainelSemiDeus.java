package view;

import model.FilhoApolo;
import model.FilhoAres;
import model.FilhoHecate;
import model.FilhoHefesto;
import model.SemiDeus;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.net.URL;

public class PainelSemiDeus extends JPanel {

    private SemiDeus semiDeus;

    private JLabel nomePersonagem;
    private JLabel imagemPersonagem;
    private JProgressBar barraVida;
    private JLabel statusPersonagem;
    private JLabel painelIndividualidade; 

    private final Color COR_FUNDO = new Color(40, 40, 45);

    public PainelSemiDeus (SemiDeus semiDeus) {
        this.semiDeus = semiDeus;

        this.setLayout(new BorderLayout(10, 10));
        this.setBackground(COR_FUNDO);
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.RAISED),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        this.setPreferredSize(new Dimension(210, 280));

        inicializarComponentes();
        atualizarPainel(); 
    }

    private void inicializarComponentes() {
        nomePersonagem = new JLabel(semiDeus.getNome(), SwingConstants.CENTER); 
        nomePersonagem.setForeground(Color.WHITE); 
        nomePersonagem.setFont(new Font("Segoe UI", Font.BOLD, 14));
        this.add(nomePersonagem, BorderLayout.NORTH); 

        imagemPersonagem = new JLabel();
        imagemPersonagem.setHorizontalAlignment(SwingConstants.CENTER);

        ImageIcon icone = carregarIconePersonagem(); 
        if (icone != null) {
            imagemPersonagem.setIcon(icone); 
        }
        this.add(imagemPersonagem, BorderLayout.CENTER); 

        JPanel painelInferior = new JPanel(new GridLayout(3,1));
        statusPersonagem = new JLabel("Atq: " + (int)semiDeus.getAtaqueBase() + "  Def: " + (int)semiDeus.getDefesaBase());
        statusPersonagem.setForeground(Color.LIGHT_GRAY);
        statusPersonagem.setHorizontalAlignment(SwingConstants.CENTER);
        painelInferior.add(statusPersonagem);

        painelIndividualidade = new JLabel(""); 
        painelIndividualidade.setForeground(new Color(200, 200, 200));
        painelIndividualidade.setHorizontalAlignment(SwingConstants.CENTER);
        painelInferior.add(painelIndividualidade);

        barraVida = new JProgressBar(0, (int) semiDeus.getPontosvidaMax()); 
        barraVida.setValue((int) semiDeus.getPontosvida());
        barraVida.setStringPainted(true);
        painelInferior.add(barraVida);
        
        painelInferior.setBackground(COR_FUNDO);
        this.add(painelInferior, BorderLayout.SOUTH); 
    }

    public void atualizarPainel() {
        int vidaAtual = (int) semiDeus.getPontosvida();
        int vidaMaxima = (int) semiDeus.getPontosvidaMax(); 

        barraVida.setValue(vidaAtual);
        barraVida.setString(vidaAtual+ "/" + vidaMaxima); 

        if (semiDeus instanceof FilhoHecate) {
            painelIndividualidade.setText("Mana: " + (int)((FilhoHecate)semiDeus).getMana());
        } else if (semiDeus instanceof FilhoHefesto) {
            painelIndividualidade.setText("Vigor: " + (int)((FilhoHefesto)semiDeus).getVigor());
        } else if (semiDeus instanceof FilhoApolo) {
            painelIndividualidade.setText("Crítico: " + FilhoApolo.getChancecritico() + "%");
        } else if (semiDeus instanceof FilhoAres) {
            int taxa = (int)(((FilhoAres)semiDeus).getTaxaRouboVida() * 100);
            painelIndividualidade.setText("Roubo Vida: " + taxa + "%");
        }

        if (vidaAtual > vidaMaxima * 0.6) {
            barraVida.setForeground(new Color(50, 205, 50)); 
        } else if (vidaAtual > vidaMaxima * 0.3) { 
            barraVida.setForeground(new Color(255, 165, 0)); 
        } else { 
            barraVida.setForeground(new Color(220, 20, 60)); 
        }

        if (!semiDeus.estaVivo()) {
            this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));
            nomePersonagem.setForeground(Color.GRAY);
            imagemPersonagem.setEnabled(false); 
        }
    }

    private ImageIcon carregarIconePersonagem() {
        String nomeArquivo = "default.png"; 
        String nomeClasse = semiDeus.getClass().getSimpleName();
        
        if (nomeClasse.contains("Teste") || nomeClasse.isEmpty()) {
            nomeArquivo = "filho_hefesto.png"; 
        } 
        else if (nomeClasse.contains("Hefesto")) nomeArquivo = "filho_hefesto.png";
        else if (nomeClasse.contains("Hecate")) nomeArquivo = "filho_hecate.png";
        else if (nomeClasse.contains("Apolo")) nomeArquivo = "filho_apolo.png";
        else if (nomeClasse.contains("Ares")) nomeArquivo = "filho_ares.png";

        try {
            URL url = getClass().getResource("/assets/" + nomeArquivo);
            
            if (url == null) {
                return null;
            }

            ImageIcon iconOriginal = new ImageIcon(url);
            Image img = iconOriginal.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
            
        } catch (Exception e) {
            return null;
        }
    }
}