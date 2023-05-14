// id: 325655058 name: Ofir Gurvits

import biuoop.DrawSurface;

/**
 * The interface Sprite.
 */
public interface Sprite {
    /**
     * Draws the sprite on the given surface.
     * @param d the d
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * Add to game.
     * @param game the game
     */
    void addToGame(GameLevel game);
}
