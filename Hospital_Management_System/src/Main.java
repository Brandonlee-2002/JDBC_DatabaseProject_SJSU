import UI.MainMenu;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Always run Swing GUIs on the Event Dispatch Thread (best practice)
        SwingUtilities.invokeLater(() -> {
            new MainMenu().setVisible(true);
        });
    }
}
