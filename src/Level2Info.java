// id: 325655058 author: Ofir Gurvits

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level 2 info.
 */
public class Level2Info extends BasicLevelInfo implements LevelInformation {
    private Color lime = new Color(50, 205, 50);
    private Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, lime, Color.BLUE, Color.BLUE, Color.pink, Color.cyan};

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(Velocity.fromAngleAndSpeed(315 + 9 * i, 7));
        }
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        Rectangle rectangle = new Rectangle(0, 0, 800, 600);
        return new Block(rectangle, Color.WHITE);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Rectangle rectangle = new Rectangle(WIDTH_OF_BORDER + i * (WIDTH - 2 * WIDTH_OF_BORDER) / 15, 220,
                    (WIDTH - 2 * WIDTH_OF_BORDER) / 15, 20);
            Block block;
            if (i < 7) {
                block = new Block(rectangle, colors[i / 2]);
            } else if (i < 9) {
                block = new Block(rectangle, colors[3]);
            } else {
                block = new Block(rectangle, colors[(i + 1) / 2]);
            }
            blocks.add(block);
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }

    @Override
    public List<Point> ballCords() {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Point point = new Point(400, 500);
            //Point point = new Point( 500,500);
            points.add(point);
        }
        return points;
    }

    @Override
    public List<Sprite> decorations() {
        int numberOfLines = 150;
        List<Sprite> list = new ArrayList();
        Color yellow2 = new Color(224, 224, 0);
        Color yellow1 = new Color(239, 231, 176);
        Point center = new Point(120, 120);
        DrawnBall sun1 = new DrawnBall(center, 60, yellow1, true);
        DrawnBall sun2 = new DrawnBall(center, 50, yellow2, true);
        DrawnBall sun3 = new DrawnBall(center, 40, Color.yellow, true);
        for (int i = 0; i < numberOfLines - 20; i++) {
            Line line = new Line(center, new Point((WIDTH / numberOfLines) * i, 220));
            line.setColor(yellow1);
            list.add(line);
        }

        Color gray1 = new Color(160, 160, 160);
        Color gray2 = new Color(192, 192, 192);
        Color gray3 = new Color(224, 224, 224);

        DrawnBall cloud1 = new DrawnBall(650, 90, 30, gray1, true);
        DrawnBall cloud2 = new DrawnBall(625, 100, 20, gray1, true);
        DrawnBall cloud3 = new DrawnBall(620, 70, 30, gray2, true);
        DrawnBall cloud4 = new DrawnBall(580, 60, 20, gray3, true);
        DrawnBall cloud5 = new DrawnBall(590, 100, 30, gray3, true);

        list.add(sun1);
        list.add(sun2);
        list.add(sun3);

        list.add(cloud5);
        list.add(cloud4);
        list.add(cloud3);
        list.add(cloud1);
        list.add(cloud2);

        Line line1 = new Line(500, 200, 600, 200);
        Line line2 = new Line(400, 200, 500, 200);
        line2.setColor(Color.BLUE);
        line1.setColor(Color.BLUE);
        return list;
    }
}
