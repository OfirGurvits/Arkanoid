// id: 325655058 author: Ofir Gurvits

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level 4 info.
 */
public class Level4info implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        list.add(Velocity.fromAngleAndSpeed(330, 7));
        list.add(Velocity.fromAngleAndSpeed(0, 7));
        list.add(Velocity.fromAngleAndSpeed(30, 7));
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 8;
    }

    @Override
    public int paddleWidth() {
        return 70;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        Rectangle rectangle = new Rectangle(0, 0, 800, 600);
        return new Block(rectangle, new Color(25, 102, 215));
    }

    @Override
    public List<Block> blocks() {
        int numberOfBlocks = 14;
        Color[] colors = {Color.gray, Color.red, Color.yellow, Color.green, Color.white, Color.pink, Color.cyan};
        List<Block> list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < numberOfBlocks; j++) {
                Rectangle rectangle = new Rectangle(50 + j * (700 / numberOfBlocks), 100 + i * 20,
                        700 / numberOfBlocks, 20);
                Block block = new Block(rectangle, colors[i]);
                list.add(block);
            }

        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

    @Override
    public List<Point> ballCords() {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < numberOfBalls(); i++) {
            points.add(new Point(400, 500));
        }
        return points;
    }

    @Override
    public List<Sprite> decorations() {
        List<Sprite> list = new ArrayList<>();
        Block sand = new Block(new Rectangle(50, 570, 700, 50), Color.yellow);
        list.add(sand);
        //sand castle
        Block castle1 = new Block(new Rectangle(80, 520, 30, 50), Color.yellow);
        Block castle2 = new Block(new Rectangle(140, 520, 30, 50), Color.yellow);
        Block castle3 = new Block(new Rectangle(200, 520, 30, 50), Color.yellow);
        Block castle4 = new Block(new Rectangle(80, 545, 150, 25), Color.yellow);
        Block castle5 = new Block(new Rectangle(150, 505, 1, 15), Color.yellow);
        Block dark1 = new Block(new Rectangle(150, 550, 10, 20), Color.black);
        Block dark2 = new Block(new Rectangle(90, 530, 10, 20), Color.black);
        Block dark3 = new Block(new Rectangle(210, 530, 10, 20), Color.black);
        Block banner = new Block(new Rectangle(151, 505, 25, 10), Color.red);
        DrawnBall drawnBall = new DrawnBall(155, 508, 2, Color.yellow, true);

        list.add(castle4);
        list.add(castle1);
        list.add(castle2);
        list.add(castle3);
        list.add(castle5);
        list.add(dark1);
        list.add(dark2);
        list.add(dark3);
        list.add(banner);
        list.add(drawnBall);

        Color yellow2 = new Color(224, 224, 0);
        Color yellow1 = new Color(239, 231, 176);
        Point center = new Point(620, 320);
        DrawnBall sun1 = new DrawnBall(center, 60, yellow1, true);
        DrawnBall sun2 = new DrawnBall(center, 50, yellow2, true);
        DrawnBall sun3 = new DrawnBall(center, 40, Color.yellow, true);
        list.add(sun1);
        list.add(sun2);
        list.add(sun3);
        //cloud
        Color[] colors = {new Color(64, 64, 64), new Color(96, 96, 96),
                new Color(128, 128, 128), new Color(160, 160, 160), new Color(192, 192, 192)};
        List<Sprite> cloudFrames = new ArrayList<>();
        int interval = 20;
        for (int i = -6*(80/interval); i < 5*(80/interval); i++) {
            List<Sprite> fullCloud = new ArrayList<>();
            DrawnBall cloud = new DrawnBall(300 - i * interval, 300, 20, colors[4], true);
            DrawnBall cloud1 = new DrawnBall(330 - i * interval, 290, 30, colors[3], true);
            DrawnBall cloud2 = new DrawnBall(280 - i * interval, 290, 25, colors[2], true);
            DrawnBall cloud3 = new DrawnBall(285 - i * interval, 310, 30, colors[1], true);
            DrawnBall cloud4 = new DrawnBall(310 - i * interval, 310, 30, colors[4], true);
            DrawnBall cloud5 = new DrawnBall(340 - i * interval, 312, 30, colors[2], true);
            DrawnBall cloud6 = new DrawnBall(275 - i * interval, 315, 30, colors[1], true);
            DrawnBall cloud7 = new DrawnBall(330 - i * interval, 320, 30, colors[4], true);
            DrawnBall cloud8 = new DrawnBall(310 - i * interval, 325, 30, colors[0], true);

            fullCloud.add(cloud8);
            fullCloud.add(cloud7);
            fullCloud.add(cloud6);
            fullCloud.add(cloud3);
            fullCloud.add(cloud1);
            fullCloud.add(cloud);
            fullCloud.add(cloud2);
            fullCloud.add(cloud4);
            fullCloud.add(cloud5);
            Sprite cloudy = new SpriteBundle(fullCloud);
            cloudFrames.add(cloudy);
        }

        list.add(new MovingSprite(cloudFrames));
        return list;
    }
}
