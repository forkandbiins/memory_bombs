package flip_n_match.ui.system;

import javax.swing.JPanel;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public abstract class Page extends JPanel {
    private LookAndFeel oldTheme = UIManager.getLookAndFeel();

    public void afterOpen() {
    }

    public void beforeClose() {
    }

    public void themeCheck() {
        if (oldTheme != UIManager.getLookAndFeel()) {
            oldTheme = UIManager.getLookAndFeel();

            SwingUtilities.updateComponentTreeUI(this);
        }
    }

    public void close() {
    }

    public void destroy() {
    }

    public void init() {
    }

    public void open() {
    }

    public void refresh() {
    }
}
