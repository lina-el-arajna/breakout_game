import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private Ball ball;
    private Paddle paddle;
    private List<Brick> bricks;
    private CollisionManager collisionManager;
    private static boolean gameStarted;
    private static boolean gameOver;
    private JButton startButton;

    public GamePanel(JButton startButton) {
        this.startButton = startButton;
        setFocusable(true);
        setPreferredSize(new Dimension(GameConfig.FRAME_WIDTH, GameConfig.FRAME_HEIGHT));
        addKeyListener(this);
        initGame();
        timer = new Timer(GameConfig.TIMER_DELAY, this);
    }

    private void initGame() {
        ball = new Ball(GameConfig.FRAME_WIDTH / 2, GameConfig.FRAME_HEIGHT / 2, GameConfig.BALL_X_SPEED, GameConfig.BALL_Y_SPEED, GameConfig.BALL_DIAMETER);
        paddle = new Paddle(GameConfig.FRAME_WIDTH / 2 - GameConfig.PADDLE_WIDTH / 2, GameConfig.FRAME_HEIGHT - GameConfig.PADDLE_HEIGHT - 10, GameConfig.PADDLE_WIDTH, GameConfig.PADDLE_HEIGHT);
        bricks = createBricks();
        collisionManager = new CollisionManager(ball, paddle, bricks);
        gameStarted = false;
        gameOver = false;
    }

    private List<Brick> createBricks() {
        List<Brick> bricks = new ArrayList<>();
        int row = 0;
        for (Map.Entry<Color, Integer> entry : GameConfig.BRICK_POINTS.entrySet()) {
            Color color = entry.getKey();
            int points = entry.getValue();
            for (int j = 0; j < 10; j++) {
                bricks.add(new Brick(j * GameConfig.BRICK_WIDTH, row * GameConfig.BRICK_HEIGHT, GameConfig.BRICK_WIDTH, GameConfig.BRICK_HEIGHT, color, points));
            }
            row++;
        }
        return bricks;
    }

    public void startGame() {
        gameStarted = true;
        gameOver = false;
        timer.start();
        requestFocusInWindow();
    }

    public void resetGame() {
        timer.stop();
        initGame();
        startButton.setEnabled(true);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!gameStarted) {
            drawStartScreen(g);
        } else if (gameOver) {
            drawGameOverScreen(g);
        } else {
            drawGame(g);
        }
    }

    private void drawStartScreen(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString("Press Start to Begin", GameConfig.FRAME_WIDTH / 2 - 50, GameConfig.FRAME_HEIGHT / 2);
    }

    private void drawGameOverScreen(Graphics g) {
        g.setColor(Color.BLACK);
        String message = bricks.stream().allMatch(Brick::isDestroyed) ? "You Win! Your score is: " + collisionManager.getScore() : "Game Over! Your score is: " + collisionManager.getScore();
        g.drawString(message, GameConfig.FRAME_WIDTH / 2 - 50, GameConfig.FRAME_HEIGHT / 2);
        g.drawString("Press Start to Restart", GameConfig.FRAME_WIDTH / 2 - 50, GameConfig.FRAME_HEIGHT / 2 + 20);
    }

    private void drawGame(Graphics g) {
        ball.draw(g);
        paddle.draw(g);
        for (Brick brick : bricks) {
            brick.draw(g);
        }
        g.setColor(Color.BLACK);
        g.drawString("Lives: " + collisionManager.getLives(), 10, 10);
        g.drawString("Score: " + collisionManager.getScore(), 100, 10);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameStarted && !gameOver) {
            ball.move();
            paddle.move();
            collisionManager.checkCollisions();
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameStarted && !gameOver) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                paddle.setVelocity(-GameConfig.PADDLE_SPEED, 0);
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                paddle.setVelocity(GameConfig.PADDLE_SPEED, 0);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (
            (e.getKeyCode() == KeyEvent.VK_LEFT && paddle.getVx() < 0) ||
            (e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.getVx() > 0)) {
            paddle.setVelocity(0, 0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void setGameOver(boolean isGameOver) {
        gameOver = isGameOver;
    }
}
