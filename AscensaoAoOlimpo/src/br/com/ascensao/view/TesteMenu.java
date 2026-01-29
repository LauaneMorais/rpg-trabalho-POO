package br.com.ascensao.view;

import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;

public class TesteMenu {

    public static void main(String[] args) {
        // O SwingUtilities garante que a janela abra na thread correta do Java
        SwingUtilities.invokeLater(() -> {
            try {
                // Cria o menu
                MenuPrincipal menu = new MenuPrincipal();
                
                // Centraliza na tela do computador
                menu.setLocationRelativeTo(null); 
                
                // Torna visível
                menu.setVisible(true);
                
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, 
                    "Erro ao abrir o menu: " + e.getMessage());
            }
        });
    }
}