package view;

import controller.BatalhaController;
import model.Equipes;
import model.SemiDeus;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class ArenaFrame extends JFrame {
    
    private Equipes arena;
    private BatalhaController controller;
    private JPanel painelLadoA;
    private JPanel painelLadoB;
    private JTextArea logBatalha;
    private JButton btnAcao;

    private ArrayList<PainelSemiDeus> cardsA = new ArrayList<>();
    private ArrayList<PainelSemiDeus> cardsB = new ArrayList<>();
    // Novo atributo para controlar os duelos 1v1 
    private int dueloAtual = 0; 

    public ArenaFrame() {
        // Deixando a janela maior e com título oficial
        setTitle("Ascensão ao Olimpo - Torneio da Ascensão");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // arrumando o aesthetic, pra seguir oq lau fez em "TelaBatalha"
        getContentPane().setBackground(new Color(20, 20, 20)); 
        setLayout(new BorderLayout(15, 15));

        arena = new Equipes();
        arena.formarEquipes(); // gera os exércitos aleotários no model

        // ligando a view com a controller (padrão mvc)
        controller = new BatalhaController(arena);

        montarLayout();
        povoarArena();

        setLocationRelativeTo(null); // centraliza pra não abrir no canto da tela
        setVisible(true);
    }

    private void montarLayout() {
        // dividindo a tela: lado A vs lado B
        JPanel centro = new JPanel(new GridLayout(1, 2, 30, 0));
        centro.setOpaque(false); // transparente pra aparecer o fundo escuro da janela

        // criando painéis com bordas coloridas para separar os times (olimpo vs submundo)
        painelLadoA = criarPainelEquipe("OLIMPO (ZEUS)", new Color(218, 165, 32));
        painelLadoB = criarPainelEquipe("SUBMUNDO (HADES)", new Color(139, 0, 0));

        // adicionando ScrollPane caso a guerra tenha muitos soldados (ex: 100 vs 100)
        centro.add(new JScrollPane(painelLadoA));
        centro.add(new JScrollPane(painelLadoB));
        add(centro, BorderLayout.CENTER);

        // painel de baixo para o log e o botão de ação
        JPanel inferior = new JPanel(new BorderLayout(10, 0));
        inferior.setOpaque(false);
        inferior.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        // estilizando o log: fundo preto e letras verdes tipo terminal/RPG (como na versão de lau)
        logBatalha = new JTextArea(8, 30);
        logBatalha.setEditable(false);
        logBatalha.setBackground(new Color(10, 10, 15));
        logBatalha.setForeground(new Color(50, 205, 50)); 
        logBatalha.setFont(new Font("Consolas", Font.PLAIN, 13));

        // Botão grande e chamativo para os turnos
        btnAcao = new JButton("⚔️ EXECUTAR DUELO ⚔️");
        btnAcao.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnAcao.setBackground(new Color(100, 0, 0)); // Vermelho Ares
        btnAcao.setForeground(Color.WHITE);
        btnAcao.addActionListener(e -> clicarNoBotao());

        inferior.add(new JScrollPane(logBatalha), BorderLayout.CENTER);
        inferior.add(btnAcao, BorderLayout.EAST);
        add(inferior, BorderLayout.SOUTH);
    }

    // método auxiliar para não repetir código de criação de painéis
    private JPanel criarPainelEquipe(String titulo, Color cor) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        p.setBackground(new Color(30, 30, 35));
        TitledBorder tb = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(cor, 2), titulo);
        tb.setTitleColor(cor);
        p.setBorder(tb);
        return p;
    }

    private void povoarArena() {
        // pegando os SemiDeuses do model e criando os cards visuais de lau
        for (SemiDeus s : arena.getLadoA()) {
            PainelSemiDeus p = new PainelSemiDeus(s);
            cardsA.add(p);
            painelLadoA.add(p);
        }
        for (SemiDeus s : arena.getLadoB()) {
            PainelSemiDeus p = new PainelSemiDeus(s);
            cardsB.add(p);
            painelLadoB.add(p);
        }
    }

    private void clicarNoBotao() {
        // lógica de duelo sequencial 
        // em vez de todo mundo bater de vez, cada clique mostra uma luta 1v1
        if (dueloAtual >= cardsA.size() || dueloAtual >= cardsB.size()) {
            logBatalha.append(">>> FIM DA RODADA! <<<\n");
            dueloAtual = 0; 
            resetarDestaques();
            return;
        }

        SemiDeus a = arena.getLadoA().get(dueloAtual);
        SemiDeus b = arena.getLadoB().get(dueloAtual);

        if (a.estaVivo() && b.estaVivo()) {
            resetarDestaques();
            // destaca visualmente quem está lutando no momento
            destacarDuelo(dueloAtual);
            
            logBatalha.append("--------------------------------\n");
            logBatalha.append("⚔️ " + a.getNome() + " desafiou " + b.getNome() + "!\n");
            
            // aqui a view pede para o model (via vontroller) aplicar o dano
            a.atacar(b);
            if (b.estaVivo()) b.atacar(a);
            
            // atualiza o visual dos cards (barrinha de vida)
            cardsA.get(dueloAtual).atualizarPainel();
            cardsB.get(dueloAtual).atualizarPainel();
        }

        dueloAtual++;
        // faz o log descer sozinho para o texto novo aparecer sempre
        logBatalha.setCaretPosition(logBatalha.getDocument().getLength());
    }

    private void destacarDuelo(int index) {
        cardsA.get(index).setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
        cardsB.get(index).setBorder(BorderFactory.createLineBorder(Color.RED, 3));
    }

    private void resetarDestaques() {
        for (PainelSemiDeus p : cardsA) p.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        for (PainelSemiDeus p : cardsB) p.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }
}