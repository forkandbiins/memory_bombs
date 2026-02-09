package flip_n_match.config;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.prefs.Preferences;

import lombok.Getter;
import lombok.ToString;

@ToString
public class UserSettings {
    private static UserSettings instance;

    private static final String KEY_THEME = "app_theme";
    private static final String KEY_DIFFICULTY = "app_difficulty";
    private static final String KEY_VOLUME = "app_volume";

    @Getter
    private final GameTheme DEFAULT_THEME = GameTheme.DARK;
    @Getter
    private final GameDifficulty DEFAULT_DIFFICULTY = GameDifficulty.EASY;
    @Getter
    private final int DEFAULT_VOLUME = 75;

    @Getter
    private GameTheme currentTheme;
    @Getter
    private GameDifficulty currentDifficulty;
    @Getter
    private int volume;

    private final PropertyChangeSupport support;
    private final Preferences prefs;

    private UserSettings() {
        support = new PropertyChangeSupport(this);
        prefs = Preferences.userNodeForPackage(UserSettings.class);

        loadSettings();
    }

    private void loadSettings() {
        final String themeName = prefs.get(KEY_THEME, DEFAULT_THEME.name());

        try {
            currentTheme = GameTheme.valueOf(themeName);
        } catch (final IllegalArgumentException e) {
            currentTheme = GameTheme.DARK;
        }

        final String difficultyName = prefs.get(KEY_DIFFICULTY, DEFAULT_DIFFICULTY.name());

        try {
            currentDifficulty = GameDifficulty.valueOf(difficultyName);
        } catch (final IllegalArgumentException e) {
            currentDifficulty = GameDifficulty.EASY;
        }

        volume = prefs.getInt(KEY_VOLUME, DEFAULT_VOLUME);
    }

    public static synchronized UserSettings getInstance() {
        if (instance == null) {
            instance = new UserSettings();
        }

        return instance;
    }

    public void addPropertyChangeListener(final PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(final PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    public void setTheme(final GameTheme newTheme) {
        final GameTheme oldTheme = currentTheme;
        currentTheme = newTheme;

        prefs.put(KEY_THEME, newTheme.name());
        support.firePropertyChange("theme", oldTheme, newTheme); // Notify UI
    }

    public void setDifficulty(final GameDifficulty newDifficulty) {
        final GameDifficulty oldDifficulty = currentDifficulty;
        currentDifficulty = newDifficulty;

        prefs.put(KEY_DIFFICULTY, newDifficulty.name());
        support.firePropertyChange("difficulty", oldDifficulty, newDifficulty);
    }

    public void setVolume(int newVolume) {
        if (newVolume < 0) {
            newVolume = 0;
        } else if (newVolume > 100) {
            newVolume = 100;
        }

        final int oldVolume = volume;
        volume = newVolume;

        prefs.putInt(KEY_VOLUME, newVolume);
        support.firePropertyChange("volume", oldVolume, newVolume);
    }
}
