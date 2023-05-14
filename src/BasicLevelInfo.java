// id: 325655058 author: Ofir Gurvits

/**
 * The type Basic level info.
 */
public abstract class BasicLevelInfo {

    /**
     * The constant UPPER_LEFT.
     */
    public static final Point UPPER_LEFT = new Point(0, 0);
    /**
     * The constant WIDTH.
     */
    public static final int WIDTH = 800;
    /**
     * The constant HEIGHT.
     */
    public static final int HEIGHT = 600;
    private static final int BLOCKS_PER_ROW = 16;
    /**
     * The constant WIDTH_OF_BORDER.
     */
    public static final int WIDTH_OF_BORDER = WIDTH / BLOCKS_PER_ROW;
    /**
     * The constant HEIGHT_OF_BLOCK.
     */
    public static final int HEIGHT_OF_BLOCK = 30;
}