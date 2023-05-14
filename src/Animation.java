// id: 325655058 author: Ofir Gurvits
import biuoop.DrawSurface;

/**
 * The interface Animation.
 */
public interface Animation {
    /**
     * Do one frame.
     * @param d the d
     */
    void doOneFrame(DrawSurface d);

    /**
     * Should the game stop.
     * @return the boolean
     */
    boolean shouldStop();
}