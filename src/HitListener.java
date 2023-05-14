// id: 325655058 author: Ofir Gurvits

/**
 * The interface Hit listener.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit the being hit
     * @param hitter The hitter parameter is the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
