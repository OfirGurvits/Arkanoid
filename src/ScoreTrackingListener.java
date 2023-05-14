// id: 325655058 author: Ofir Gurvits
/**
 * The type Score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param counter the counter
     */
    public ScoreTrackingListener(Counter counter) {
        this.currentScore = counter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}