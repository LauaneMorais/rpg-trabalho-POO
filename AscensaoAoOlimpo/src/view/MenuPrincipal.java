package view;

import model.Equipes;
import model.SemiDeus;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MenuPrincipal extends JFrame {

    private CardLayout cardLayout;
    private JPanel painelPrincipal;
    private String nomeSenhorDaGuerra = "Visitante"; 

    public MenuPrincipal() {
        setTitle("Ascensão ao Olimpo!");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        cardLayout = new CardLayout();
        painelPrincipal = new JPanel(cardLayout);

        painelPrincipal.add(inicializarMenu(), "MENU");
        painelPrincipal.add(inicializarHistoria(), "HISTORIA");
        painelPrincipal.add(inicializarSenhorGuerra(), "SENHOR_GUERRA");

        add(painelPrincipal);

        cardLayout.show(painelPrincipal, "MENU");
        setLocationRelativeTo(null);
    }
    
    private JPanel inicializarMenu() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(new Color(20, 20, 20)); 

        JPanel topo = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 50));
        topo.setOpaque(false);
        JLabel titulo = new JLabel("ASCENSÃO AO OLIMPO! 🏛️");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 48));
        titulo.setForeground(new Color(218, 165, 32)); 
        topo.add(titulo);
        painel.add(topo, BorderLayout.NORTH);
        
        JPanel centro = new JPanel(new GridBagLayout());
        centro.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 0, 15, 0);
        gbc.gridx = 0;

        JButton btnIniciar = criarBotaoEstilizado("INICIAR JORNADA ⚔️");
        btnIniciar.addActionListener(e -> cardLayout.show(painelPrincipal, "HISTORIA"));
        
        JButton btnCreditos = criarBotaoEstilizado("CRÉDITOS 📜");
        btnCreditos.addActionListener(e -> mostrarCreditos());
        
        JButton btnSair = criarBotaoEstilizado("SAIR ❌");
        btnSair.addActionListener(e -> System.exit(0));

        gbc.gridy = 0; centro.add(btnIniciar, gbc);
        gbc.gridy = 1; centro.add(btnCreditos, gbc);
        gbc.gridy = 2; centro.add(btnSair, gbc);

        painel.add(centro, BorderLayout.CENTER);
        return painel;
    }

    private JPanel inicializarHistoria() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(new Color(10, 10, 15));

        JTextArea textoHistoria = new JTextArea();
        textoHistoria.setText(
            "\n\n       NOS TEMPOS ANTIGOS...\n\n" +
            "   No Monte Olimpo, Zeus e Hades, em sua eterna rivalidade,\n" +
            "   decidiram que o destino dos mortais não seria decidido por deuses,\n" +
            "   mas por seus filhos.\n\n" +
            "   Uma arena sagrada foi erguida entre o céu e o tártaro.\n" +
            "   Guerreiros, Magos e Caçadores foram convocados.\n\n" +
            "   Apenas um lado prevalecerá.\n   Apenas um tomará o destino do Olimpo.\n\n" +
            "   Prepare-se... O torneio vai começar.\n\n" +
            "   Quem vencerá? Hades ou Zeus?"
        );

        textoHistoria.setFont(new Font("Segoe UI", Font.ITALIC, 22));
        textoHistoria.setForeground(new Color(200, 200, 200));
        textoHistoria.setBackground(new Color(10, 10, 15));
        textoHistoria.setEditable(false);
        textoHistoria.setLineWrap(true);
        textoHistoria.setWrapStyleWord(true);
        
        textoHistoria.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        painel.add(new JScrollPane(textoHistoria), BorderLayout.CENTER);

        JButton btnContinuar = criarBotaoEstilizado("CONTINUAR >>");
        btnContinuar.addActionListener(e -> cardLayout.show(painelPrincipal, "SENHOR_GUERRA"));
        
        JPanel rodape = new JPanel();
        rodape.setBackground(new Color(10, 10, 15));
        rodape.add(btnContinuar);
        painel.add(rodape, BorderLayout.SOUTH);

        return painel;
    }

    private JPanel inicializarSenhorGuerra() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(new Color(30, 30, 35));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;

        JLabel lblImagem = new JLabel();
        ImageIcon icon = carregarImagemSenhorGuerra();
        if(icon != null) {
            lblImagem.setIcon(icon);
        } else {
            lblImagem.setText("[IMAGEM SENHOR DA GUERRA]");
            lblImagem.setForeground(Color.CYAN);
            lblImagem.setFont(new Font("Arial", Font.BOLD, 20));
        }
        gbc.gridy = 0; painel.add(lblImagem, gbc);

        JLabel lblPergunta = new JLabel("Qual é o seu nome, estrategista?");
        lblPergunta.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblPergunta.setForeground(Color.WHITE);
        gbc.gridy = 1; painel.add(lblPergunta, gbc);

        JTextField txtNome = new JTextField(15);
        txtNome.setFont(new Font("Segoe UI", Font.BOLD, 16));
        txtNome.setHorizontalAlignment(JTextField.CENTER);
        gbc.gridy = 2; painel.add(txtNome, gbc);

        JButton btnConfirmar = criarBotaoEstilizado("ENTRAR NA ARENA");
        btnConfirmar.setPreferredSize(new Dimension(250, 50));
        btnConfirmar.addActionListener(e -> {
            String nomeDigitado = txtNome.getText().trim();
            if(!nomeDigitado.isEmpty()) {
                nomeSenhorDaGuerra = nomeDigitado;
                confirmarInicioBatalha();
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, identifique-se!");
            }
        });
        gbc.gridy = 3; 
        gbc.insets = new Insets(30, 0, 0, 0); 
        painel.add(btnConfirmar, gbc);

        return painel;
    }

    private JButton criarBotaoEstilizado(String texto) {
        JButton btn = new JButton(texto);
        btn.setPreferredSize(new Dimension(300, 60));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btn.setBackground(new Color(50, 50, 50));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(new Color(218, 165, 32), 2));
        
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(80, 80, 80));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(50, 50, 50));
            }
        });
        return btn;
    }

    private ImageIcon carregarImagemSenhorGuerra() {
        try {
            URL url = getClass().getResource("/assets/senhor_guerra.png");
            if (url != null) {
                ImageIcon iconOriginal = new ImageIcon(url);
                Image img = iconOriginal.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                return new ImageIcon(img);
            }
        } catch (Exception e) {
            System.err.println("Não foi possível carregar a imagem do Senhor da Guerra.");
        }
        return null; 
    }

    private void confirmarInicioBatalha() {
        Equipes arena = new Equipes();
        int totalSorteado = arena.formarEquipes(); 

        JOptionPane.showMessageDialog(this, 
            "O Destino decidiu!\n" +
            "O Torneio terá " + totalSorteado + " combatentes de cada lado.\n" +
            "Prepare seu coração, " + nomeSenhorDaGuerra + "!");

        // Inicia a ArenaFrame passando os dados criados
        ArenaFrame arenaFinal = new ArenaFrame(arena, nomeSenhorDaGuerra);
        arenaFinal.setVisible(true);

        this.dispose();
    }

    private void mostrarCreditos() {
        JTextArea creditos = new JTextArea(
            "Espero que goste de Ascensão ao Olimpo!\n\n" +
            "Desenvolvido por:\nLarissa Cena\nLauane Morais\nLuiza Accioly\nMaciele Ramos\n\n\n" + 
            "Disciplina: Programação Orientada a Objetos.\n\n" +
            "Agradecimentos:\nProf. Maily\nProf André Yoshiaki\nDeuses do Olimpo"
        );

        creditos.setFont(new Font("Segoe UI", Font.BOLD, 14));
        creditos.setForeground(new Color(218, 165, 32)); 
        creditos.setBackground(new Color(30, 30, 35)); 
        creditos.setEditable(false);
        creditos.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scroll = new JScrollPane(creditos);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(218, 165, 32)));
        scroll.setPreferredSize(new Dimension(400, 250));

        JOptionPane.showMessageDialog(this, scroll, "CRÉDITOS DO PROJETO", JOptionPane.PLAIN_MESSAGE);
    }
}