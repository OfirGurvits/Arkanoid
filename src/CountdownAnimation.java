// id: 325655058 author: Ofir Gurvits

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Countdown animation.
 */
// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) seconds, before
// it is replaced with the next one.
public class CountdownAnimation implements Animation {
    private int countFrom;
    private int numOfSeconds;
    private long startTime;
    private SpriteCollection gamescreen;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.numOfSeconds = (int) numOfSeconds;
        this.gamescreen = gameScreen;
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        gamescreen.drawAllOn(d);
        long timeElapsed = System.currentTimeMillis() - startTime;
        d.setColor(Color.BLUE);
        d.drawText(385, 275, String.valueOf(this.countFrom - timeElapsed / 1000), 50);
    }

    @Override
    public boolean shouldStop() {
        long timeElapsed = System.currentTimeMillis() - startTime;
        return this.countFrom - timeElapsed / 1000 < countFrom - numOfSeconds + 1;
    }
}