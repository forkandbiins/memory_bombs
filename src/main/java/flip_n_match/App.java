package flip_n_match;

import javax.swing.SwingUtilities;

import flip_n_match.ui.themes.ThemeManager;

public class App {
    private static MainFrame mainFrame;

    public static void close() {
        mainFrame.dispose();
        System.exit(0);
    }

    public static void main(final String[] args) {
        ThemeManager.manage();

        SwingUtilities.invokeLater(() -> {
            mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
