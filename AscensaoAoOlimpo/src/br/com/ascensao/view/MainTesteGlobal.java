package br.com.ascensao.view;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class MainTesteGlobal {

    public static void main(String[] args) {
        // Garante que a interface gráfica rode na Thread correta do Java (EDT)
        SwingUtilities.invokeLater(() -> {
            try {
                // Tenta deixar o visual com a cara do sistema operacional (mais bonito)

                // Opções para o usuário escolher o que testar
                String[] opcoes = {
                    "1. Fluxo Completo (Menu -> Batalha 1v1)", 
                    "2. Testar ArenaFrame (Multi-Batalha)",
                    "Cancelar"
                };

                int escolha = JOptionPane.showOptionDialog(
                        null,
                        "Qual parte do Front-end você deseja testar agora?",
                        "Central de Testes - Ascensão ao Olimpo",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcoes,
                        opcoes[0]
                );

                switch (escolha) {
                    case 0:
                        iniciarFluxoMenu();
                        break;
                    case 1:
                        iniciarArenaFrame();
                        break;
                    default:
                        System.exit(0);
                }

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro fatal ao iniciar teste: " + e.getMessage());
            }
        });
    }

    // --- OPÇÃO 1: Testa MenuPrincipal + TelaBatalha + PainelSemiDeus ---
    private static void iniciarFluxoMenu() {
        System.out.println(">>> Iniciando Menu Principal...");
        MenuPrincipal menu = new MenuPrincipal();
        menu.setLocationRelativeTo(null); // Centraliza na tela
        menu.setVisible(true);
    }

    // --- OPÇÃO 2: Testa ArenaFrame + PainelSemiDeus (Sem passar pelo menu) ---
    private static void iniciarArenaFrame() {
        System.out.println(">>> Iniciando ArenaFrame isolada...");
        try {
            ArenaFrame arena = new ArenaFrame();
            arena.setLocationRelativeTo(null);
            arena.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                "Erro ao abrir ArenaFrame.\nVerifique se você tem a classe 'BatalhaController' criada!\nErro: " + e.getMessage());
        }
    }
}