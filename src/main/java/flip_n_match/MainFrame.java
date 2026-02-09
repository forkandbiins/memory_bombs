package flip_n_match;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.formdev.flatlaf.FlatClientProperties;

import flip_n_match.constants.Metadata;
import flip_n_match.ui.system.PageHandler;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle(String.format("%s v%s", Metadata.APP_TITLE, Metadata.VERSION));

        getRootPane().putClientProperty(FlatClientProperties.FULL_WINDOW_CONTENT, true);
        PageHandler.install(this);

        setPreferredSize(new Dimension(1280, 720));
        setSize(getPreferredSize());
        setLocationRelativeTo(null);
    }
}
