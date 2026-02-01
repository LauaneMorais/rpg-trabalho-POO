/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
*/

import br.com.view.MenuPrincipal;
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