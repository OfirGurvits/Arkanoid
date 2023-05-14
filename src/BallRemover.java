// id: 325655058 author: Ofir Gurvits

/**
 * The type Ball remover.
 */
public class BallRemover implements HitListener {
    private final GameLevel game;

    /**
     * Instantiates a new Ball remover.
     * @param game the game
     */
    public BallRemover(GameLevel game) {
        this.game = game;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        game.getBallCounter().decrease(1);
    }
}
