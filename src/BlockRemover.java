// id: 325655058 author: Ofir Gurvits

/**
 * The type Block remover.
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private final GameLevel game;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     * @param game          the game
     * @param removedBlocks the removed blocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = new Counter(game.getBlockCounter().getValue() - removedBlocks.getValue());
    }

    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.

    /**
     * removes the block getting hit and updates the block counter.
     * @param beingHit the Block being hit
     * @param hitter the ball which hit the BlockRemover
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        remainingBlocks.decrease(1);
        game.getBlockCounter().decrease(1);
    }
}