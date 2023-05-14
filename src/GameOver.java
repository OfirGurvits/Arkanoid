// id: 325655058 author: Ofir Gurvits

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Game over.
 */
public class GameOver implements Animation {
    private boolean won;
    private Counter counter;

    /**
     * Instantiates a new Game over.
     *
     * @param counter the counter
     * @param won     the won
     */
    public GameOver(Counter counter, boolean won) {
        this.counter = counter;
        this.won = won;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.drawRectangle(0, 0, 800, 600);
        d.setColor(Color.BLACK);
        if (won) {
            d.drawText(400, 300, "You Win! Your score is " + counter.getValue(), 20);
        } else {
            d.drawText(400, 300, "Game Over. Your score is " + counter.getValue(), 20);
        }
    }

    @Override
    public boolean shouldStop() {
        return true;
    }
}
