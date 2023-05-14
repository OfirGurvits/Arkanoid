// id: 325655058 author: Ofir Gurvits

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    private Counter counter;

    /**
     * Instantiates a new Score indicator.
     *
     * @param counter the counter
     */
    public ScoreIndicator(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT_OF_BLOCK / 2);
        d.setColor(Color.BLACK);
        d.drawText(GameLevel.WIDTH / 3, GameLevel.HEIGHT_OF_BLOCK / 2,
                "Score: " + String.valueOf(counter.getValue()), GameLevel.HEIGHT_OF_BLOCK / 2);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
