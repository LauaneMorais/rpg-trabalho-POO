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

        this.setPreferredSize(new Dimension(140, 200));

        inicializarComponentes();
        atualizarPainel(); // chama uma vez para pintar a tela inicial
    }

    private void inicializarComponentes() {

        //nome do personagem que vai em cima no layout

        nomePersonagem = new JLabel(semiDeus.getNome(), SwingConstants.CENTER); //pega nome
        nomePersonagem.setForeground(Color.WHITE); //texto branquinho
        this.add(labelNome, BorderLayout.NORTH); //nome em cima


        //imagem do personagem que vai no meio do layout

        imagemPersonagem = new JLabel();

        ImageIcon icone = carregarIconePersonagem(); 
        if (icone != null) {
            labelImagem.setIcon(icone); // só cola a imagem se tiver achado 
        }
        this.add(labelImagem, BorderLayout.CENTER); //imagem no meio da tela

        //vida e status que vai la embaixo

        //status
        JPanel painelInferior = new JPanel(new GridLayout(2,1));
        labelStats = new JLabel("Ataque ⚔ : " + (int)semiDeus.getAtaqueBase() + " Defesa ⛨ : " + (int)semiDeus.getDefesaBase());
        painelInferior.add(labelStats);

        //vida
        barraVida = new JProgressBar(0, (int) semiDeus.getPontosvidaMax) //vai de 0 ate o valor max permitido do personagem
        barraVida.setValue((int) semiDeus.getPontosvida());
        barraVida.setStringPainted(true);
        painelInferior.add(barraVida);

        this.add(painelInferior, BorderLayout.SOUTH); 

        //tem que ter um grid layout poque o borderlayout so aceita um elemento por espaço, aí tem q criar um subelemento pra ele encaixar
    }

    public void atualizarPainel() {

        //atualiza atributos
        int vidaAtual = (int) semiDeus.getPontosvida();
        int vidaMaxima = (int) semiDeus.getPontosvidaMax; 

        //atualiza barra de vida
        barraVida.setValue(vidaAtual);
        barraVida.setString(vidaAtual+ "/" + vidaMaxima); //ex: 76/120

        if (vidaAtual > vidaMax * 0.6) {
            barraVida.setForeground(new Color(50, 205, 50)); // barra de vida verde, muita vida
        } else if (vidaAtual > vidaMax * 0.3) { 
            barraVida.setForeground(new Color(255, 165, 0)); // barra laranja
        } else { 
            barraVida.setForeground(new Color(220, 20, 60)); // barra vermelha, pouca vida
        }


        if (!semiDeus,estaVivo()) {
            marcarComoMorto();
        }
    }


 //a parte abaixo foi gerada por IA e eu não conferi, depois tento entender melhor, gerei porque queria tentar testar logo

    private ImageIcon carregarIconeDoPersonagem() {
        String nomeArquivo = "default.png"; 
        
        // Pergunta para o Java: "Qual é o nome da classe desse objeto?"
        // Se for um objeto da classe FilhoHefesto, retorna "FilhoHefesto"
        String nomeClasse = semiDeus.getClass().getSimpleName();

        // Verifica pedaços do nome
        if (nomeClasse.contains("Hefesto")) {
            nomeArquivo = "tanque.png";
            // ... define cor da borda ...
        } else if (nomeClasse.contains("Hecate")) {
            nomeArquivo = "mago.png";
        } 
        // ... outros ifs ...

        // Tenta achar o arquivo na pasta
        try {
            // getClass().getResource é o comando seguro para achar arquivos dentro do projeto
            URL url = getClass().getResource("/assets/" + nomeArquivo);
            
            // ... carrega e redimensiona a imagem ...
            
        } catch (Exception e) {
            return null; // Se der erro, retorna nada
        }
    }
}