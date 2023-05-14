// id: 325655058 author: Ofir Gurvits

import biuoop.DrawSurface;

import java.util.List;

/**
 * The type Moving sprite.
 */
public class MovingSprite implements Sprite {
    /**
     * The Sprites.
     */
    private List<Sprite> sprites;
    /**
     * The Current sprite.
     */
    private int currentSprite;
    /**
     * The Counter.
     */
    private int counter;

    /**
     * Instantiates a new Moving sprite.
     *
     * @param sprites the sprites
     */
    public MovingSprite(List<Sprite> sprites) {
        this.sprites = sprites;
        this.currentSprite = 0;
        int counter = 0;
    }

    @Override
    public void drawOn(DrawSurface d) {
        sprites.get(currentSprite).drawOn(d);
    }

    @Override
    public void timePassed() {
        counter++;
        if (counter == 30) {
            currentSprite++;
            currentSprite = currentSprite % sprites.size();
        }
        counter %= 30;
    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

}
