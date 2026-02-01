package view;

import controller.BatalhaController;
import model.Equipes;
import model.SemiDeus;
import util.ChanceBuff;
import util.Dado;
import util.SorteioBuff;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class ArenaFrame extends JFrame {
    
    private Equipes arena;
    private BatalhaController controller;
    private CardLayout cardLayout = new CardLayout();
    private JPanel painelCartoes;
    private String nomeSenhorDaGuerra;

    // Tela 1: Matriz 5 Colunas
    private JPanel matrizA, matrizB;
    private ArrayList<PainelSemiDeus> cardsA = new ArrayList<>();
    private ArrayList<PainelSemiDeus> cardsB = new ArrayList<>();
    
    // Tela 2: Duelo 1v1
    private JPanel painelBuff;
    private JLabel labelIconeBuff, labelTextoBuff;
    private JPanel container1v1;
    private JTextArea logTerminal;
    private JButton btnAtacar, btnVoltar;

    private int dueloAtual = 0; 

    public ArenaFrame() {
        this.arena = new Equipes();
        this.arena.formarEquipes();
        this.controller = new BatalhaController(this.arena);
        this.nomeSenhorDaGuerra = "Visitante";
        
        inicializarJanela();
    }

    public ArenaFrame(Equipes arenaSorteada, String nomeGuerreiro) {
        this.arena = arenaSorteada;
        this.controller = new BatalhaController(arenaSorteada);
        this.nomeSenhorDaGuerra = nomeGuerreiro;

        inicializarJanela();
    }

    private void inicializarJanela() {
        setTitle("Ascensão ao Olimpo - Torneio Oficial");
        setSize(1280, 920);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        painelCartoes = new JPanel(cardLayout);
        
        prepararCards(); 
        layoutMatriz(); 
        layoutDuelo(); 
        
        add(painelCartoes);
        cardLayout.show(painelCartoes, "ARENA");

        if (!arena.getLadoA().isEmpty() && !arena.getLadoB().isEmpty()) {
            atualizarDestaqueNaMatriz(arena.getLadoA().get(0), arena.getLadoB().get(0));
        }

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void layoutMatriz() {
        JPanel telaArena = new JPanel(new BorderLayout());
        telaArena.setBackground(new Color(20, 20, 20));

        JPanel centro = new JPanel(new GridLayout(1, 2, 20, 0));
        centro.setOpaque(false);

        matrizA = new JPanel(new GridLayout(0, 5, 5, 5));
        matrizA.setBackground(new Color(30, 30, 35));
        matrizA.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.YELLOW, 2), "OLIMPO (ZEUS)"));
        ((TitledBorder)matrizA.getBorder()).setTitleColor(Color.YELLOW);

        matrizB = new JPanel(new GridLayout(0, 5, 5, 5));
        matrizB.setBackground(new Color(30, 30, 35));
        matrizB.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.RED, 2), "SUBMUNDO (HADES)"));
        ((TitledBorder)matrizB.getBorder()).setTitleColor(Color.RED);

        for(PainelSemiDeus p : cardsA) matrizA.add(p);
        for(PainelSemiDeus p : cardsB) matrizB.add(p);

        centro.add(new JScrollPane(matrizA));
        centro.add(new JScrollPane(matrizB));
        
        JButton btnEntrarDuelo = new JButton("\u2694 EXECUTAR DUELO \u2694");
        btnEntrarDuelo.setFont(new Font("Segoe UI Symbol", Font.BOLD, 22));
        btnEntrarDuelo.setBackground(new Color(110, 0, 0));
        btnEntrarDuelo.setForeground(Color.WHITE);
        btnEntrarDuelo.setOpaque(true);
        btnEntrarDuelo.setContentAreaFilled(true);
        btnEntrarDuelo.addActionListener(e -> irParaDueloImediato());

        telaArena.add(centro, BorderLayout.CENTER);
        telaArena.add(btnEntrarDuelo, BorderLayout.SOUTH);
        painelCartoes.add(telaArena, "ARENA");
    }

    private void layoutDuelo() {
        JPanel telaDuelo = new JPanel(new BorderLayout());
        telaDuelo.setBackground(new Color(10, 10, 10));

        painelBuff = new JPanel(new BorderLayout());
        painelBuff.setBackground(new Color(255, 215, 0));
        painelBuff.setPreferredSize(new Dimension(1200, 160));
        painelBuff.setBorder(BorderFactory.createMatteBorder(0, 0, 8, 0, Color.ORANGE));
        painelBuff.setVisible(false);

        JLabel header = new JLabel("Um deus olhou para a arena!", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI Symbol", Font.BOLD, 30));
        labelIconeBuff = new JLabel();
        labelIconeBuff.setHorizontalAlignment(SwingConstants.CENTER);
        labelTextoBuff = new JLabel("", SwingConstants.CENTER);
        labelTextoBuff.setFont(new Font("Segoe UI Symbol", Font.ITALIC, 20));

        painelBuff.add(header, BorderLayout.NORTH);
        painelBuff.add(labelIconeBuff, BorderLayout.CENTER);
        painelBuff.add(labelTextoBuff, BorderLayout.SOUTH);

        container1v1 = new JPanel(new GridBagLayout());
        container1v1.setOpaque(false);

        logTerminal = new JTextArea(12, 50);
        logTerminal.setBackground(Color.BLACK);
        logTerminal.setForeground(new Color(0, 255, 0));
        logTerminal.setFont(new Font("Consolas", Font.PLAIN, 15));
        logTerminal.setEditable(false);

        JPanel sul = new JPanel(new BorderLayout());
        sul.add(new JScrollPane(logTerminal), BorderLayout.CENTER);

        JPanel areaBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 15));
        areaBotoes.setOpaque(false);

        btnAtacar = new JButton("\u2694 ATACAR! \u2694");
        btnAtacar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 22));
        btnAtacar.setBackground(new Color(130, 0, 0));
        btnAtacar.setForeground(Color.WHITE);
        btnAtacar.setOpaque(true);
        btnAtacar.setContentAreaFilled(true);

        btnVoltar = new JButton("\u21AA VOLTAR PARA ARENA");
        btnVoltar.setBackground(new Color(80, 80, 80));
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
        
        btnAtacar.addActionListener(e -> realizarAtaqueERevezar());
        btnVoltar.addActionListener(e -> {
            matrizA.removeAll();
            matrizB.removeAll();
            for(PainelSemiDeus p : cardsA) matrizA.add(p);
            for(PainelSemiDeus p : cardsB) matrizB.add(p);
            
            if (dueloAtual < arena.getLadoA().size() && dueloAtual < arena.getLadoB().size()) {
                 atualizarDestaqueNaMatriz(arena.getLadoA().get(dueloAtual), arena.getLadoB().get(dueloAtual));
            }
            
            cardLayout.show(painelCartoes, "ARENA");
            matrizA.revalidate();
            matrizB.revalidate();
            matrizA.repaint();
            matrizB.repaint();
        });

        areaBotoes.add(btnAtacar);
        areaBotoes.add(btnVoltar);
        sul.add(areaBotoes, BorderLayout.SOUTH);

        telaDuelo.add(painelBuff, BorderLayout.NORTH);
        telaDuelo.add(container1v1, BorderLayout.CENTER);
        telaDuelo.add(sul, BorderLayout.SOUTH);

        painelCartoes.add(telaDuelo, "DUELO");
    }

    private void irParaDueloImediato() {
        procurarProximoAtacanteVivo();
        
        if (arena.getLadoA().isEmpty() || arena.getLadoB().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não há combatentes vivos suficientes.");
            return;
        }

        SemiDeus heroiOlimpo = arena.getLadoA().get(dueloAtual);
        SemiDeus heroiSubmundo = escolherAlvoSeguro(arena.getLadoB());

        if(heroiOlimpo != null && heroiSubmundo != null){
            prepararDueloFocado(heroiOlimpo, heroiSubmundo);
            cardLayout.show(painelCartoes, "DUELO");
        } else {
            JOptionPane.showMessageDialog(this, "Não há combatentes vivos suficientes para iniciar o duelo.");
        }
    }

    // Método auxiliar para garantir escolha de alvo mesmo se o método do Controller for privado
    private SemiDeus escolherAlvoSeguro(ArrayList<SemiDeus> lista) {
        List<SemiDeus> vivos = new ArrayList<>();
        for (SemiDeus s : lista) {
            if (s.estaVivo()) vivos.add(s);
        }
        if (vivos.isEmpty()) return null;
        int sorteio = Dado.rolar(vivos.size()) - 1;
        return vivos.get(sorteio);
    }

    private void prepararDueloFocado(SemiDeus a, SemiDeus b) {
        container1v1.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 50, 0, 50);
      
        int indexA = arena.getLadoA().indexOf(a);
        int indexB = arena.getLadoB().indexOf(b);
        
        if (indexA != -1) container1v1.add(cardsA.get(indexA), gbc);

        JLabel vsLabel = new JLabel("VS");
        vsLabel.setFont(new Font("Segoe UI Symbol", Font.BOLD, 100)); 
        vsLabel.setForeground(new Color(220, 50, 50));
        container1v1.add(vsLabel, gbc);

        if (indexB != -1) container1v1.add(cardsB.get(indexB), gbc);

        painelBuff.setVisible(false);
        container1v1.revalidate();
        container1v1.repaint();
    }

    private void realizarAtaqueERevezar() {
        procurarProximoAtacanteVivo();
        
        if (arena.getLadoA().isEmpty() || arena.getLadoB().isEmpty()) return;

        SemiDeus a = arena.getLadoA().get(dueloAtual);
        
        SemiDeus b = null;
        for(SemiDeus s : arena.getLadoB()) {
            if(s.estaVivo()) { b = s; break; }
        }

        if(a.estaVivo() && b!=null  && b.estaVivo()) {

            prepararDueloFocado(a,b);
            atualizarDestaqueNaMatriz(a, b);

            logTerminal.append("--------------------------------------------------\n"); 

            processarIntervencaoDivina(a);

            double hpB_inicial = b.getPontosvida();
            a.atacar(b);
            double danoB = hpB_inicial - b.getPontosvida();

            logTerminal.append( a.getNome() + " ataca " + b.getNome() + "\n");
            logTerminal.append( b.getNome() + " recebeu " + String.format("%.1f", danoB) + " de dano. Vida: " + (int)b.getPontosvida() + "\n");

            if(b.estaVivo()) {
                double hpA_inicial = a.getPontosvida();
                b.atacar(a);
                double danoA = hpA_inicial - a.getPontosvida();
                logTerminal.append( b.getNome() + " contra-ataca!\n");
                logTerminal.append( a.getNome() + " recebeu " + String.format("%.1f", danoA) + " de dano. Vida: " + (int)a.getPontosvida() + "\n");
            } else {
                logTerminal.append( b.getNome() + " sucumbiu!\n");
            }
        
            int idxA = arena.getLadoA().indexOf(a);
            int idxB = arena.getLadoB().indexOf(b);
            if(idxA != -1) cardsA.get(idxA).atualizarPainel();
            if(idxB != -1) cardsB.get(idxB).atualizarPainel();
            
            if(!controller.temSobreviventes(arena.getLadoA()) || !controller.temSobreviventes(arena.getLadoB())) {
                boolean olimpoVenceu = controller.temSobreviventes(arena.getLadoA());
                String vencedor = olimpoVenceu ? "OLIMPO (ZEUS)" : "SUBMUNDO (HADES)";
                
                btnAtacar.setEnabled(false);
                btnAtacar.setText(" VITÓRIA DO: " + vencedor);
                String mensagemFinal;
                if(olimpoVenceu){
                    mensagemFinal = "A ordem do Olimpo segue sendo mantida! \n" +
                        "Zeus foi o vencedor do torneio, e Hades continua onde deve estar…\n" + 
                        "Parabens " + nomeSenhorDaGuerra + " por garantir que a paz fosse mantida de maneira justa!\n" +
                        "Nos vemos em breve…:";
                }else{
                    mensagemFinal = "A ordem do Olimpo pode ser desbalanceada… \n" +
                        "Hades venceu e o céu estremece!\n" +
                        "Parabéns " + nomeSenhorDaGuerra + " por garantir uma decisão justa!\n" +
                        "Nos vemos em breve…";
                }   
                JOptionPane.showMessageDialog(this, mensagemFinal);             
            }
        }
        dueloAtual++;
        if(dueloAtual >= cardsA.size()) {
            dueloAtual = 0;
            logTerminal.append("\nFIM DO CICLO. REINICIANDO RODADA DE ATAQUES!\n");
        }

        logTerminal.setCaretPosition(logTerminal.getDocument().getLength());
    }

    private void procurarProximoAtacanteVivo() {
        if (arena.getLadoA().isEmpty()) return;
        
        int limite = 0;
        while (!arena.getLadoA().get(dueloAtual).estaVivo() && limite < arena.getLadoA().size()) {
            dueloAtual++;
            if (dueloAtual >= arena.getLadoA().size()) dueloAtual = 0;
            limite++;
        }
    }

    private void atualizarDestaqueNaMatriz(SemiDeus atacanteAtual, SemiDeus defensorAtual) {
        for(PainelSemiDeus p : cardsA) p.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        for(PainelSemiDeus p : cardsB) p.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
      
        if(atacanteAtual != null) {
            int indexA = arena.getLadoA().indexOf(atacanteAtual);
            if(indexA != -1) cardsA.get(indexA).setBorder(BorderFactory.createLineBorder(Color.YELLOW, 4));
        }
        if(defensorAtual != null) {
            int indexB = arena.getLadoB().indexOf(defensorAtual);
            if(indexB != -1) cardsB.get(indexB).setBorder(BorderFactory.createLineBorder(Color.RED, 4));
        }
    }

    private void processarIntervencaoDivina(SemiDeus s) {
        if (ChanceBuff.Chance()) {
            
            String nomeDeus = SorteioBuff.aplicarBuffAleatorio(s); 
            String efeito = "Benção Divina";
            String deus = nomeDeus; 
            
            if (nomeDeus.contains(" (")) {
                 int indexParenteses = nomeDeus.indexOf(" (");
                 efeito = nomeDeus.substring(0, indexParenteses);
                 deus = nomeDeus.substring(nomeDeus.lastIndexOf("de ") + 3, nomeDeus.length() - 1);
            }

            painelBuff.setVisible(true); 
            labelTextoBuff.setText("A benção de " + deus.toUpperCase() + " foi concedida a " + s.getNome() + "!");
            logTerminal.append("✨ INTERVENÇÃO: " + efeito + " de " + deus + "\n");
            
            String file = (deus.toLowerCase().contains("dionisio") ? "maldicao_" : "bencao_") + deus.toLowerCase() + ".png";
            URL url = getClass().getResource("/assets/" + file);
            
            if(url != null) {
                ImageIcon icon = new ImageIcon(url);
                labelIconeBuff.setIcon(new ImageIcon(icon.getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH)));
            } 
        } else {
            painelBuff.setVisible(false);
        }
    }
    
    private void prepararCards() { 
        for (int i = 0; i < arena.getLadoA().size(); i++) {
            SemiDeus s = arena.getLadoA().get(i);
            s.setNome("Filho de " + s.getClass().getSimpleName().replace("Filho", "") + " (A" + (i + 1) + ")");
            cardsA.add(new PainelSemiDeus(s));
        }
        for (int i = 0; i < arena.getLadoB().size(); i++) {
            SemiDeus s = arena.getLadoB().get(i);
            s.setNome("Filho de " + s.getClass().getSimpleName().replace("Filho", "") + " (B" + (i + 1) + ")");
            cardsB.add(new PainelSemiDeus(s));
        }
    }
}