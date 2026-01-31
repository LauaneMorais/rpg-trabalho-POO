package br.com.ascensao.view;

import br.com.ascensao.controller.BatalhaController;
import br.com.ascensao.model.Equipes;
import br.com.ascensao.model.SemiDeus;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class ArenaFrame extends JFrame {
    
    private Equipes arena;
    private BatalhaController controller;
    private CardLayout cardLayout = new CardLayout();
    private JPanel painelCartoes;
    private String nomeSenhorDaGuerra;

    // tela 1: matriz 5 Colunas
    private JPanel matrizA, matrizB;
    private ArrayList<PainelSemiDeus> cardsA = new ArrayList<>();
    private ArrayList<PainelSemiDeus> cardsB = new ArrayList<>();
    
    // tela 2: duelo 1v1
    private JPanel painelBuff;
    private JLabel labelIconeBuff, labelTextoBuff;
    private JPanel container1v1;
    private JTextArea logTerminal;
    private JButton btnAtacar, btnVoltar;

    private int dueloAtual = 0; // índice que vai rodar a fila (0, 1, 2...)

    public ArenaFrame(Equipes arenaSorteada, String nomeGuerreiro) {

        this.arena = arenaSorteada;
        this.controller = new BatalhaController(arenaSorteada);
        this.nomeSenhorDaGuerra = nomeGuerreiro;

        setTitle("Ascensão ao Olimpo - Torneio Oficial");
        setSize(1280, 920);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        painelCartoes = new JPanel(cardLayout);
        
        prepararCards(); 
        layoutMatriz(); 
        layoutDuelo(); 
        
        add(painelCartoes);
        cardLayout.show(painelCartoes, "ARENA");

        atualizarDestaqueNaMatriz(arena.getLadoA().get(0), arena.getLadoB().get(0)); // começa destaancndo a primeira dupla
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void layoutMatriz() {
        JPanel telaArena = new JPanel(new BorderLayout());
        telaArena.setBackground(new Color(20, 20, 20));

        JPanel centro = new JPanel(new GridLayout(1, 2, 20, 0));
        centro.setOpaque(false);

        // matriz de 5 colunas
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

        // banner de Buff
        painelBuff = new JPanel(new BorderLayout());
        painelBuff.setBackground(new Color(255, 215, 0));
        painelBuff.setPreferredSize(new Dimension(1200, 160));
        painelBuff.setBorder(BorderFactory.createMatteBorder(0, 0, 8, 0, Color.ORANGE));
        painelBuff.setVisible(false);

        JLabel header = new JLabel("Um deus olhou para a arena!", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 30));
        labelIconeBuff = new JLabel();
        labelIconeBuff.setHorizontalAlignment(SwingConstants.CENTER);
        labelTextoBuff = new JLabel("", SwingConstants.CENTER);
        labelTextoBuff.setFont(new Font("Segoe UI", Font.ITALIC, 20));

        painelBuff.add(header, BorderLayout.NORTH);
        painelBuff.add(labelIconeBuff, BorderLayout.CENTER);
        painelBuff.add(labelTextoBuff, BorderLayout.SOUTH);

        // VS centralizado
        container1v1 = new JPanel(new GridBagLayout());
        container1v1.setOpaque(false);

        // log terminal
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
            for(PainelSemiDeus p : cardsA) matrizA.add(p);
            for(PainelSemiDeus p : cardsB) matrizB.add(p);
            
            // atualiza quem eh a próxima dupla da fila na arena
            atualizarDestaqueNaMatriz(arena.getLadoA().get(dueloAtual), arena.getLadoB().get(dueloAtual));
            
            // troca para a tela da arena 
            cardLayout.show(painelCartoes, "ARENA");
            matrizA.revalidate();
            matrizB.revalidate();
            matrizA.repaint();
            matrizB.repaint();
            cardLayout.show(painelCartoes, "ARENA");
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

        SemiDeus heroiOlimpo = arena.getLadoA().get(dueloAtual);

        SemiDeus heroiSubmundo = controller.escolherAlvo(arena.getLadoB());

        if(heroiOlimpo != null && heroiSubmundo != null){

            prepararDueloFocado(heroiOlimpo, heroiSubmundo);
            cardLayout.show(painelCartoes, "DUELO");
            
        }else{

            JOptionPane.showMessageDialog(this, "Não há combatentes vivos suficientes para iniciar o duelo.");
            return;
        }
    }

    private void prepararDueloFocado(SemiDeus a, SemiDeus b) {
        
        container1v1.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 50, 0, 50);
      

        container1v1.add(cardsA.get(arena.getLadoA().indexOf(a)), gbc);

        JLabel vsLabel = new JLabel("VS");
        vsLabel.setFont(new Font("Segoe UI", Font.BOLD, 100)); // VS Gigante
        vsLabel.setForeground(new Color(220, 50, 50));
        container1v1.add(vsLabel, gbc);

        container1v1.add(cardsB.get(arena.getLadoB().indexOf(b)), gbc);

        painelBuff.setVisible(false);
        container1v1.revalidate();
        container1v1.repaint();
    }

 private void realizarAtaqueERevezar() {

    procurarProximoAtacanteVivo();

    SemiDeus a = arena.getLadoA().get(dueloAtual);
    SemiDeus b = controller.escolherAlvo(arena.getLadoB());
    //SemiDeus b = arena.getLadoB().get(dueloAtual);

    if(a.estaVivo() && b!=null  && b.estaVivo()) {

        prepararDueloFocado(a,b);
        atualizarDestaqueNaMatriz(a, b);

        logTerminal.append("--------------------------------------------------\n"); 

        processarIntervencaoDivina(a);

        double hpB_inicial = b.getPontosvida();
        a.atacar(b);
        double danoB = hpB_inicial - b.getPontosvida();

        logTerminal.append( a.getNome() + " ataca " + b.getNome() + "\n");
        logTerminal.append( b.getNome() + " recebeu " + danoB + " de dano. Vida: " + b.getPontosvida() + "\n");

        if(b.estaVivo()) {
            double hpA_inicial = a.getPontosvida();
            b.atacar(a);
            double danoA = hpA_inicial - a.getPontosvida();
            logTerminal.append( b.getNome() + " contra-ataca!\n");
            logTerminal.append( a.getNome() + " recebeu " + danoA + " de dano. Vida: " + a.getPontosvida() + "\n");
        } else {
            logTerminal.append( b.getNome() + " sucumbiu!\n");
        }
       
        cardsA.get(arena.getLadoA().indexOf(a)).atualizarPainel();
        cardsB.get(arena.getLadoB().indexOf(b)).atualizarPainel();
        
        //atualizarDestaqueNaMatriz(a, b);

        if(!controller.temSobreviventes(arena.getLadoA()) || !controller.temSobreviventes(arena.getLadoB())) {
            boolean olimpoVenceu = controller.temSobreviventes(arena.getLadoA());
            String vencedor = controller.temSobreviventes(arena.getLadoA()) ? "OLIMPO (ZEUS)" : "SUBMUNDO (HADES)";
            
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
        int limite = 0;
        while (!arena.getLadoA().get(dueloAtual).estaVivo() && limite < arena.getLadoA().size()) {
            dueloAtual++;
            if (dueloAtual >= arena.getLadoA().size()) dueloAtual = 0;
            limite++;
        }
    }

    private void atualizarDestaqueNaMatriz(SemiDeus atacanteAtual, SemiDeus defensorAtual) {
        // limpa bordas de todo mundo
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

        
      /* int indexA = arena.getLadoA().indexOf(atacanteAtual);
       int indexB = arena.getLadoB().indexOf(defensorAtual);

       if (indexA != -1) cardsA.get(indexA).setBorder(BorderFactory.createLineBorder(Color.YELLOW, 4));
       if (indexB != -1) cardsB.get(indexB).setBorder(BorderFactory.createLineBorder(Color.RED, 4));*/
    }

    private void processarIntervencaoDivina(SemiDeus s) {
        if (br.com.ascensao.util.ChanceBuff.Chance()) {
           
            String retornoBuff = br.com.ascensao.util.SorteioBuff.aplicarBuffAleatorio(s); 
            
           
            int indexParenteses = retornoBuff.indexOf(" (");
            String efeito = retornoBuff.substring(0, indexParenteses);
            String deus = retornoBuff.substring(retornoBuff.lastIndexOf("de ") + 3, retornoBuff.length() - 1);

            painelBuff.setVisible(true); 
            
           
            labelTextoBuff.setText("A benção de " + deus.toUpperCase() + " foi concedida a " + s.getNome() + "!");
            
           
            logTerminal.append("✨ INTERVENÇÃO: " + efeito + "\n");
            
            
            String file = (deus.toLowerCase().contains("dionisio") ? "maldicao_" : "bencao_") + deus.toLowerCase() + ".png";
            URL url = getClass().getResource("/br/com/ascensao/assets/" + file);
            
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