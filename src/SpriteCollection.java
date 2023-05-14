// id: 325655058 author: Ofir Gurvits

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Sprite collection.
 */
public class SpriteCollection {
    private java.util.List<Sprite> spriteList = new ArrayList<Sprite>();

    /**
     * Adds sprite.
     *
     * @param s the sprite
     */
    public void addSprite(Sprite s) {
        spriteList.add(s);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        spriteList.remove(s);
    }

    /**
     * Notify all the sprites that time passed.
     */
    public void notifyAllTimePassed() {
        List<Sprite> sprites = new ArrayList<Sprite>(this.spriteList);
        //goes over all the sprites
        for (Sprite sprite : sprites) {
            //notifies each sprite
            sprite.timePassed();
        }
    }

    /**
     * Draws all sprites on screen.
     * @param d the d
     */
    public void drawAllOn(DrawSurface d) {
        //goes over all the sprites
        for (Sprite sprite : spriteList) {
            //draws each sprite
            sprite.drawOn(d);
        }
    }
}
