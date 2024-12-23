import java.awt.*;

public class Brick extends GameObject {
    private final Color color;
    private final int points;
    private boolean destroyed;

    public Brick(int x, int y, int width, int height, Color color, int points) {
        super(x, y, width, height);
        this.color = color;
        this.points = points;
        this.destroyed = false;
    }

    public int getPoints() {
        return points;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void destroy() {
        destroyed = true;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void draw(Graphics g) {
        if (!destroyed) {
            g.setColor(color);
            g.fillRect(x, y, width, height);
        }
    }
}
