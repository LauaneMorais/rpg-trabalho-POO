package br.com.ascensao;

import br.com.ascensao.view.MenuPrincipal;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuPrincipal menu = new MenuPrincipal();
            menu.setLocationRelativeTo(null);
            menu.setVisible(true);
        });
    }
}