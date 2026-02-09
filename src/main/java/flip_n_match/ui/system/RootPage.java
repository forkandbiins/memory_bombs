package flip_n_match.ui.system;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;

public class RootPage extends JPanel {
    private final JPanel mainPanel;

    public RootPage() {
        setLayout(new MigLayout("filly, insets 16, gap 0, al center center", "[grow]", "[grow]"));

        mainPanel = new JPanel(new MigLayout("filly, insets 0, gap 0, al center center", "[grow]", "[grow]"));

        add(mainPanel, "grow");
    }

    public void setPage(final Page page) {
        if (mainPanel.getComponentOrientation().isLeftToRight() != page.getComponentOrientation().isLeftToRight()) {
            applyComponentOrientation(mainPanel.getComponentOrientation());
        }

        SwingUtilities.invokeLater(() -> {
            mainPanel.removeAll();
            mainPanel.add(page, "grow");
            mainPanel.repaint();
            mainPanel.revalidate();
        });
    }
}
