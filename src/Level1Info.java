// id: 325655058 author: Ofir Gurvits

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level 1 info.
 */
public class Level1Info extends BasicLevelInfo implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity v1 = new Velocity(0, -5);
        List<Velocity> list = new ArrayList<>();
        list.add(v1);
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 70;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        Rectangle rectangle = new Rectangle(0, 0, 800, 600);
        return new Block(rectangle, Color.BLACK);
    }

    @Override
    public List<Block> blocks() {
        Rectangle rectangle = new Rectangle(380, 131, 40, 40);
        Block block = new Block(rectangle, Color.red);
        List<Block> list = new ArrayList<>();
        list.add(block);
        return list;
    }

    @Override
    public List<Point> ballCords() {
        List<Point> pointList = new ArrayList<>();
        pointList.add(new Point(400, 450));
        return pointList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    @Override
    public List<Sprite> decorations() {
        List<Sprite> spriteList = new ArrayList<>();
        spriteList.add(new DrawnBall(400, 151, 120, Color.BLUE, false));
        spriteList.add(new DrawnBall(400, 151, 90, Color.BLUE, false));
        spriteList.add(new DrawnBall(400, 151, 60, Color.BLUE, false));
        Line line1 = new Line(430, 151, 550, 151);
        Line line2 = new Line(370, 151, 250, 151);
        Line line3 = new Line(400, 181, 400, 301);
        Line line4 = new Line(400, 121, 400, 1);
        line1.setColor(Color.BLUE);
        line2.setColor(Color.blue);
        line3.setColor(Color.blue);
        line4.setColor(Color.BLUE);
        spriteList.add(line1);
        spriteList.add(line2);
        spriteList.add(line3);
        spriteList.add(line4);
        return spriteList;
    }

}
