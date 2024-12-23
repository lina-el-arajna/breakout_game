import java.awt.Color;
import java.util.List;

public class CollisionManager {
    private Ball ball;
    private Paddle paddle;
    private List<Brick> bricks;
    private int lives;
    private int score;

    public CollisionManager(Ball ball, Paddle paddle, List<Brick> bricks) {
        this.ball = ball;
        this.paddle = paddle;
        this.bricks = bricks;
        this.lives = 3;
        this.score = 0;
    }

    public void checkCollisions() {
        checkWallCollisions();
        checkPaddleCollisions();
        checkBrickCollisions();
    }

    private void checkWallCollisions() {
        if (ball.getX() <= 0 || ball.getX() + ball.getWidth() >= GameConfig.FRAME_WIDTH) {
            ball.reverseX();
        }
        if (ball.getY() <= 0) {
            paddle.shrink();
            ball.reverseY();
        }
        if (ball.getY() + ball.getHeight() >= GameConfig.FRAME_HEIGHT) {
            lives--;
            if (lives > 0) {
                resetBallAndPaddle();
            } else {
                GamePanel.setGameOver(true);
            }
        }
    }

    private void checkPaddleCollisions() {
        if (ball.getHitbox().intersects(paddle.getHitbox())) {
            ball.reverseY();
        }
    }

    private void checkBrickCollisions() {
        for (Brick brick : bricks) {
            if (!brick.isDestroyed() && ball.getHitbox().intersects(brick.getHitbox())) {
                ball.reverseY();
                score += brick.getPoints();
                brick.destroy();
                ball.increaseHitCount();
                if (ball.getHitCount() == 4 || ball.getHitCount() == 12 || brick.getColor() == Color.ORANGE || brick.getColor() == Color.RED) {
                    ball.increaseSpeed();
                }
            }
        }

        if (bricks.stream().allMatch(Brick::isDestroyed)) {
            GamePanel.setGameOver(true);
        }
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    private void resetBallAndPaddle() {
        ball.setPosition(GameConfig.FRAME_WIDTH / 2, GameConfig.FRAME_HEIGHT / 2);
        ball.setVelocity(GameConfig.BALL_X_SPEED, GameConfig.BALL_Y_SPEED);
        paddle.setPosition(GameConfig.FRAME_WIDTH / 2 - paddle.getWidth() / 2, GameConfig.FRAME_HEIGHT - paddle.getHeight() - 10);
    }
}
