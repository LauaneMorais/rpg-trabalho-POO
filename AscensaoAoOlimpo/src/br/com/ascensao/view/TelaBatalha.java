package br.com.ascensao.view;

import br.com.ascensao.model.SemiDeus;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class TelaBatalha extends JFrame {
    
    private PainelSemiDeus painelTimeAmigo;
    private PainelSemiDeus painelTimeInimigo;

    private JTextArea logBatalha; //pra ir dando uma conferida
    private JButton botaoAtacar;
    
    private SemiDeus amigo;
    private SemiDeus inimigo;

    private JPanel painelBuff;
    private JLabel mensagemBuff;
    private JLabel imagemBuff;
    private JLabel nomeDeusBuff;


    public TelaBatalha(SemiDeus amigo, SemiDeus inimigo) {
        this.amigo = amigo;
        this.inimigo = inimigo;

        setTitle("Ascensão ao Olimpo - FIGHT!");
        setSize(1100, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); //pra poder dividir entre norte, sul, centro...


        getContentPane().setBackground(new Color(20, 20, 20)); // coloca um fundo escuro

        inicializarInterface();
    }


    //tinha feito com flowlayout e ficou meio desorganizado, ai pedi pra a ia reorganizar meu codigo pra ficar certinho e ela fez isso que funcionou :D
    
    private void inicializarInterface() {

        painelBuff = new JPanel(new BorderLayout());
        painelBuff.setBackground(new Color(255, 215, 0)); // douradinho
        painelBuff.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Color.ORANGE));
        painelBuff.setVisible(false); // pra ficar invisível enquanto nao for ativado
        painelBuff.setPreferredSize(new Dimension(1100, 120));

        mensagemBuff = new JLabel("Um deus olhou para a arena!", SwingConstants.CENTER);
        mensagemBuff.setFont( new Font("Segoe UI", Font.BOLD, 15));
        mensagemBuff.setForeground(Color.BLACK);

        imagemBuff = new JLabel();
        imagemBuff.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel centroDivino = new JPanel(new BorderLayout());
        centroDivino.setOpaque(false);
        centroDivino.add(mensagemBuff, BorderLayout.NORTH);
        centroDivino.add(imagemBuff, BorderLayout.CENTER);
        centroDivino.add(nomeDeusBuff, BorderLayout.SOUTH);
        
        painelBuff.add(centroDivino, BorderLayout.CENTER);
        
        // cola la em cima
        add(painelBuff, BorderLayout.NORTH);
        
        // Usamos GridBagLayout para um controle de posicionamento mais preciso que o FlowLayout
        JPanel arena = new JPanel(new GridBagLayout());
        arena.setOpaque(false); // Transparente

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 20, 0, 20); // Margem lateral de 20px entre os elementos

        // --- LADO ESQUERDO: JOGADOR ---
        painelTimeAmigo = new PainelSemiDeus(amigo);
        gbc.gridx = 0; // Coluna 0
        gbc.gridy = 0; // Linha 0
        arena.add(painelTimeAmigo, gbc);

        // --- CENTRO: VS ---
        JLabel labelVs = new JLabel("VS");
        labelVs.setFont(new Font("Segoe UI", Font.BOLD, 48));
        labelVs.setForeground(new Color(200, 50, 50)); // Vermelho Sangue
        
        gbc.gridx = 1; // Coluna 1 (Meio)
        arena.add(labelVs, gbc);

        // --- LADO DIREITO: INIMIGO ---
        painelTimeInimigo = new PainelSemiDeus(inimigo);
        gbc.gridx = 2; // Coluna 2
        arena.add(painelTimeInimigo, gbc);

        add(arena, BorderLayout.CENTER);

        // --- 2. CONTROLES E LOG (BAIXO) ---
        JPanel painelControles = new JPanel(new BorderLayout());
        painelControles.setBackground(new Color(30, 30, 30));
        painelControles.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Área de Texto (Log)
        logBatalha = new JTextArea(6, 40);
        logBatalha.setEditable(false);
        logBatalha.setBackground(new Color(10, 10, 10));
        logBatalha.setForeground(Color.GREEN);
        logBatalha.setFont(new Font("Consolas", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(logBatalha);
        painelControles.add(scroll, BorderLayout.CENTER);

        // Botão de Ação
        botaoAtacar = new JButton("⚔️ REALIZAR TURNO ⚔️");
        botaoAtacar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        botaoAtacar.setBackground(new Color(100, 0, 0));
        botaoAtacar.setForeground(Color.WHITE);
        botaoAtacar.setFocusPainted(false);
        botaoAtacar.setPreferredSize(new Dimension(250, 50));
        
        // O que acontece quando clica no botão:
        botaoAtacar.addActionListener((ActionEvent e) -> {
            executarTurno();
        });

        painelControles.add(botaoAtacar, BorderLayout.EAST);
        add(painelControles, BorderLayout.SOUTH);
    }

    private void executarTurno() {
        if (!amigo.estaVivo() || !inimigo.estaVivo()) return;

        logBatalha.append("--------------------------------------------------\n");
        
        // 1. amigo Ataca
        logBatalha.append("🔵 " + amigo.getNome() + " preparou um ataque...\n");
        amigo.atacar(inimigo);
        painelTimeInimigo.atualizarPainel(); // Atualiza a barra de vida visual
        
        // Verifica se inimigo morreu
        if (!inimigo.estaVivo()) {
            logBatalha.append("💀 O INIMIGO CAIU! VITÓRIA!\n");
            finalizarBatalha(true);
            return;
        }

        // 2. Inimigo Contra-Ataca (se sobreviveu)
        logBatalha.append("🔴 " + inimigo.getNome() + " contra-ataca!\n");
        inimigo.atacar(amigo);
        painelTimeAmigo.atualizarPainel(); // Atualiza a barra de vida visual

        // Verifica se amigo morreu
        if (!amigo.estaVivo()) {
            logBatalha.append("💀 VOCÊ FOI DERROTADO...\n");
            finalizarBatalha(false);
        }
        
        // Rola o log para baixo automaticamente
        logBatalha.setCaretPosition(logBatalha.getDocument().getLength());
    }


    private void finalizarBatalha(boolean vitoria) {
        botaoAtacar.setEnabled(false); // Desativa o botão para não clicar mais
        
        if(vitoria) {
            botaoAtacar.setText("VITÓRIA!");
            botaoAtacar.setBackground(Color.GREEN);
        } else {
            botaoAtacar.setText("DERROTA...");
            botaoAtacar.setBackground(Color.GRAY);
        }
    }

    }

