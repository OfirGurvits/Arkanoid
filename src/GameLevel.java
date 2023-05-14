// id: 325655058 author: Ofir Gurvits

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;


/**
 * The type Game.
 */
public class GameLevel implements Animation {
    /**
     * The constant UPPER_LEFT.
     */
    public static final Point UPPER_LEFT = new Point(0, 0);
    /**
     * The default WIDTH.
     */
    public static final int WIDTH = 800;
    /**
     * The default HEIGHT.
     */
    public static final int HEIGHT = 600;
    //amount of blocks in row
    private static final int BLOCKS_PER_ROW = 16;
    /**
     * The constant WIDTH_OF_BORDER.
     */
    public static final int WIDTH_OF_BORDER = WIDTH / BLOCKS_PER_ROW;

    private SpriteCollection sprites;
    private GameEnvironment environment;
    public static final int FIRST_ROW_Y = 30;
    /**
     * The constant HEIGHT_OF_BLOCK.
     */
    public static final int HEIGHT_OF_BLOCK = 30;
    private Counter scoreCounter;
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;
    private Counter blockCounter;
    private Counter ballCounter;

    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboardSensor, AnimationRunner animationRunner,
                     Counter scoreCounter) {
        this.scoreCounter = scoreCounter;
        this.keyboard = keyboardSensor;
        this.runner = animationRunner;
        this.levelInformation = levelInformation;
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Balls (and Paddle) and add them to the game.
     */
    public void initialize() {
        //the fields are initialized
        this.environment = new GameEnvironment();
        this.blockCounter = new Counter(levelInformation.blocks().size());
        this.ballCounter = new Counter(levelInformation.numberOfBalls());
        HitListener blockRemover = new BlockRemover(this, blockCounter);
        HitListener ballRemover = new BallRemover(this);
        HitListener scoreTracking = new ScoreTrackingListener(scoreCounter);
        this.sprites = new SpriteCollection();
        addSprite(levelInformation.getBackground());
        for (Sprite sprite : levelInformation.decorations()) {
            this.addSprite(sprite);
            //sprite.addToGame(this);
        }
        //create the paddle
        Paddle paddle = new Paddle(keyboard, (int) ((WIDTH - UPPER_LEFT.getX()) / 2),
                (int) (HEIGHT - UPPER_LEFT.getY() - 2 * HEIGHT_OF_BLOCK),
                levelInformation.paddleSpeed(), levelInformation.paddleWidth());
        paddle.addToGame(this);
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball(levelInformation.ballCords().get(i), 5, Color.WHITE, environment);
            ball.setVelocity(levelInformation.initialBallVelocities().get(i));
            ball.addToGame(this);
        }
        for (Block block : levelInformation.blocks()) {
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTracking);
            block.addToGame(this);
        }
        Block top = new Block(UPPER_LEFT.getX(), UPPER_LEFT.getY() + (double) HEIGHT_OF_BLOCK / 2,
                WIDTH, (double) HEIGHT_OF_BLOCK / 2, Color.GRAY);
        Block left = new Block(UPPER_LEFT, WIDTH_OF_BORDER, HEIGHT);
        Rectangle rightRectangle = new Rectangle(new Point(750, UPPER_LEFT.getY()),
                WIDTH_OF_BORDER, HEIGHT);
        Block right = new Block(rightRectangle);
        top.addToGame(this);
        left.addToGame(this);
        right.addToGame(this);
        Rectangle bottomRectangle = new Rectangle(new Point(UPPER_LEFT.getX(), UPPER_LEFT.getY() + HEIGHT),
                WIDTH, 1);
        Block bottom = new Block(bottomRectangle);
        bottom.addHitListener(ballRemover);
        bottom.addToGame(this);
        ScoreIndicator scoreIndicator = new ScoreIndicator(scoreCounter);
        scoreIndicator.addToGame(this);
        NameIndicator nameIndicator = new NameIndicator(levelInformation.levelName());
        nameIndicator.addToGame(this);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.initialize();
        this.runner.run(new CountdownAnimation(2, 3, sprites));
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * Gets collidbles.
     *
     * @return the collidbles
     */
    public java.util.List<Collidable> getCollidbles() {
        return environment.getObstacles();
    }

    /**
     * Gets block counter.
     *
     * @return the block counter
     */
    public Counter getBlockCounter() {
        return blockCounter;
    }

    /**
     * Gets ball counter.
     *
     * @return the ball counter
     */
    public Counter getBallCounter() {
        return ballCounter;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //obstacles and balls are drawn
        this.sprites.drawAllOn(d);
        this.environment.drawObstacles(d);
        //the new frame is calculated
        this.sprites.notifyAllTimePassed();
        if (blockCounter.getValue() == 0) {
            scoreCounter.increase(100);
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard, " ", new PauseScreen()));
        }
    }

    @Override
    public boolean shouldStop() {
        return ballCounter.getValue() == 0 || blockCounter.getValue() == 0;
    }
}