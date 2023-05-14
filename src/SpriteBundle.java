// id: 325655058 author: Ofir Gurvits

import biuoop.DrawSurface;

import java.util.List;

/**
 * The type Sprite bundle.
 */
public class SpriteBundle implements Sprite {
    private List<Sprite> sprites;

    /**
     * Instantiates a new Sprite bundle.
     *
     * @param sprites the sprites
     */
    public SpriteBundle(List<Sprite> sprites) {
        this.sprites = sprites;
    }

    @Override
    public void drawOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
