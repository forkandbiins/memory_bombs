package flip_n_match.logger;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomLogger {
    private static final Handler HANDLER = new CustomLogHandler();
    private static final Logger PARENT_LOGGER = Logger.getLogger("com.github.tip-aaron.flip_n_match");

    static {
        PARENT_LOGGER.setUseParentHandlers(false);
        PARENT_LOGGER.addHandler(HANDLER);
    }

    public static Logger getDetachedLogger(final Class<?> c) {
        final var logger = getLogger(c);
        logger.setUseParentHandlers(false);
        return logger;
    }

    public static Level getLevel() {
        return PARENT_LOGGER.getLevel();
    }

    public static Logger getLogger(final Class<?> c) {
        final var logger = Logger.getLogger(c.getName());
        logger.setUseParentHandlers(true);
        logger.setParent(PARENT_LOGGER);

        return logger;
    }

    public static <T> T log(final T obj) {
        PARENT_LOGGER.info(String.valueOf(obj));
        return obj;
    }

    public static void setLevel(final Level level) {
        PARENT_LOGGER.setLevel(level);
    }

}
