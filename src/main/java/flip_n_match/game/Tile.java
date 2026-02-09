package flip_n_match.game;

import lombok.Data;

@Data
public class Tile {
    public static enum TileType {
        BOMB,
        NORMAL;
    }

    private final int value;
    private final TileType type;

    public Tile(final TileType type, final int value) {
        this.type = type;
        this.value = value;
    }
}
