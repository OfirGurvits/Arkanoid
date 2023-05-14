// id: 325655058 author: Ofir Gurvits

import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Ass 6 game.
 */
public class Ass6Game {
    /**
     * Main.
     * @param args ignored
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
        int level = 0;
        for (String s : args) {
            if (s.equals("1")) {
                levels.add(new Level1Info());
                level++;
            }
            if (s.equals("2")) {
                levels.add(new Level2Info());
                level++;
            }
            if (s.equals("3")) {
                levels.add(new Level3info());
                level++;
            }
            if (s.equals("4")) {
                levels.add(new Level4info());
                level++;
            }
        }
        if (level == 0) {
            levels.add(new Level1Info());
            levels.add(new Level2Info());
            levels.add(new Level3info());
            levels.add(new Level4info());
        }
        GUI gui = new GUI("arkanoid", 800, 600);
        AnimationRunner animationRunner = new AnimationRunner(gui, 60);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        GameFlow gameFlow = new GameFlow(animationRunner, keyboardSensor);
        gameFlow.runLevels(levels);
    }
}
