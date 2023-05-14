// id: 325655058 author: Ofir Gurvits

import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {
    private final KeyboardSensor ks;
    private final AnimationRunner ar;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar the ar
     * @param ks the ks
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.ar = ar;
        this.ks = ks;
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        GUI gui = ar.getGui();
        Counter scoreCounter = new Counter(0);
        GameOver gameOver;
        boolean hasWon = true;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.ks, this.ar, scoreCounter);

            level.initialize();

            while (!level.shouldStop()) {
                level.run();
            }

            if (level.getBallCounter().getValue() == 0) {
                hasWon = false;
                break;
            }

        }
        gameOver = new GameOver(scoreCounter, hasWon);
        KeyPressStoppableAnimation keyPressStoppableAnimation = new KeyPressStoppableAnimation(this.ks,
                " ", gameOver);
        ar.run(keyPressStoppableAnimation);
        gui.close();
    }
}