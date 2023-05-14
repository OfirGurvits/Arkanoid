// id: 325655058 author: Ofir Gurvits

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;
    private boolean stop;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the sensor
     * @param key       the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (this.isAlreadyPressed) {
            this.isAlreadyPressed = false;
        }
        if (key.equals(" ")) {
            if (this.sensor.isPressed(KeyboardSensor.SPACE_KEY) && !this.isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.stop = this.sensor.isPressed(key) && !this.isAlreadyPressed;
        }

    }

    @Override
    public boolean shouldStop() {
        return stop;
    }
}
