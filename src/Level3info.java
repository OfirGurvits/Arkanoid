// id: 325655058 author: Ofir Gurvits

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level 3 info.
 */
public class Level3info implements LevelInformation {
    /**
     * The Colors.
     */
    private Color[] colors = {new Color(0, 0, 102), new Color(0, 102, 204),
            new Color(0, 128, 255), new Color(153, 204, 255), new Color(204, 255, 255)};

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        list.add(Velocity.fromAngleAndSpeed(30, 7));
        list.add(Velocity.fromAngleAndSpeed(330, 7));
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 90;
    }

    @Override
    public String levelName() {
        return "Green";
    }

    @Override
    public Sprite getBackground() {
        Rectangle rectangle = new Rectangle(0, 0, 800, 600);
        return new Block(rectangle, new Color(32, 32, 32));

    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = i; j < 10; j++) {
                Rectangle rectangle = new Rectangle(350 + j * 45, 200 + i * 20, 45, 20);
                Block block = new Block(rectangle, colors[i]);
                list.add(block);
            }
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }

    @Override
    public List<Point> ballCords() {
        List<Point> list = new ArrayList<>();
        list.add(new Point(500, 400));
        list.add(new Point(300, 400));
        return list;
    }

    @Override
    public List<Sprite> decorations() {
        List<Sprite> list = new ArrayList<>();
        list.add(new Block(new Rectangle(50, 580, 700, 50), new Color(139, 69, 19)));
        list.add(new Block(new Rectangle(50, 570, 700, 10), Color.green));
        list.add(new Block(new Rectangle(80, 420, 90, 150), Color.BLACK));
        list.add(new Block(new Rectangle(90, 350, 70, 70), new Color(192, 192, 192)));
        Line line = new Line(95, 390, 155, 390);
        line.setColor(Color.BLUE);
        list.add(line);
        Line line1 = new Line(125, 360, 95, 390);
        line1.setColor(Color.BLUE);
        list.add(line1);
        Line line2 = new Line(125, 360, 155, 390);
        line2.setColor(Color.BLUE);
        list.add(line2);
        Line line3 = new Line(95, 370, 155, 370);
        line3.setColor(Color.BLUE);
        list.add(line3);
        Line line4 = new Line(95, 370, 125, 400);
        line4.setColor(Color.BLUE);
        list.add(line4);
        Line line5 = new Line(125, 400, 155, 370);
        line5.setColor(Color.BLUE);
        list.add(line5);
        DrawnBall drawnBall = new DrawnBall(150, 100, 40, Color.WHITE, true);
        list.add(drawnBall);
        Color color = new Color(32, 32, 32);
        DrawnBall drawnBall1 = new DrawnBall(160, 100, 30, color, true);
        list.add(drawnBall1);
        List<Sprite> starAnimation = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            List<Sprite> spriteList = new ArrayList<>();
            DrawnBall drawnBall2 = new DrawnBall(720 - i * 120, 47 + 53 * i, 10, Color.YELLOW, true);
            Line trace1 = new Line(730 - i * 120, 51 + 53 * i, 810 - i * 120, 17 + 53 * i);
            Line trace2 = new Line(720 - i * 120, 47 + 53 * i, 810 - i * 120, 7 + 53 * i);
            Line trace3 = new Line(710 - i * 120, 43 + 53 * i, 800 - i * 120, 2 + 53 * i);
            trace3.setColor(Color.YELLOW);
            trace2.setColor(Color.YELLOW);
            trace1.setColor(Color.YELLOW);
            spriteList.add(trace1);
            spriteList.add(drawnBall2);
            spriteList.add(trace2);
            spriteList.add(trace3);
            SpriteBundle spriteBundle = new SpriteBundle(spriteList);
            starAnimation.add(spriteBundle);
        }
        MovingSprite movingSprite = new MovingSprite(starAnimation);
        list.add(movingSprite);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                Block block = new Block(new Rectangle(90 + 15 * j, 430 + 30 * i, 10, 20), Color.cyan);
                list.add(block);
            }
        }
        return list;
    }
}
