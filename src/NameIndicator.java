// id: 325655058 author: Ofir Gurvits

import biuoop.DrawSurface;
import java.awt.Color;


/**
 * The type Score indicator.
 */
public class NameIndicator implements Sprite {
    private String name;

    /**
     * Instantiates a new Score indicator.
     *
     * @param name the name
     */
    public NameIndicator(String name) {
        this.name = name;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(2 * GameLevel.WIDTH / 3, GameLevel.HEIGHT_OF_BLOCK / 2,
                "Name: " + String.valueOf(name), GameLevel.HEIGHT_OF_BLOCK / 2);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
