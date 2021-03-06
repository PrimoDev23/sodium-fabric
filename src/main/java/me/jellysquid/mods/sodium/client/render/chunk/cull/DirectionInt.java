package me.jellysquid.mods.sodium.client.render.chunk.cull;

import net.minecraft.util.math.Direction;

public class DirectionInt {
    private static final Direction[] ENUMS = Direction.values();

    public static final int COUNT = ENUMS.length;

    public static final int NULL = -1;
    public static final int DOWN = 0;
    public static final int UP = 1;
    public static final int NORTH = 2;
    public static final int SOUTH = 3;
    public static final int WEST = 4;
    public static final int EAST = 5;

    private static final int[] ALL = new int[ENUMS.length];

    static {
        for (int i = 0; i < ENUMS.length; i++) {
            ALL[i] = ENUMS[i].ordinal();
        }
    }

    public static Direction toEnum(int dir) {
        return ENUMS[dir];
    }

    public static int getOpposite(int dir) {
        return ((dir >> 31) + ((5 - dir) >> 31) + 1) * ((dir ^ 1) + 1) - 1;
    }

    public static int[] all() {
        return ALL;
    }
}
