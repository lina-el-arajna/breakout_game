import java.awt.*;

public class Ball extends MovingObject {
    private int hitCount;

    public Ball(int x, int y, int vx, int vy, int diameter) {
        super(x, y, diameter, diameter,vx, vy);
        this.hitCount = 0;
    }

    @Override
    public void move() {
        x += super.getVx();
        y += super.getVy();
    }

    public void increaseSpeed() {
        super.setVelocity(
            (int)(super.getVx() * 1.5),
            (int)(super.getVy() * 1.5)
        );
    }

    public int getHitCount() {
        return hitCount;
    }

    public void increaseHitCount() {
        this.hitCount++;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval(x, y, width, height);
    }
}
