import java.awt.*;

public class Paddle extends MovingObject{

    public Paddle(int x, int y, int width, int height) {
        super(x, y, width, height,0,0);
    }

    @Override
    public void move() {
        if (x + super.getVx() > 0 && x + super.getVx() + width < GameConfig.FRAME_WIDTH) {
            x += super.getVx() ;
        }
    }

    public void shrink() {
        int minWidth = 10;
        if (width > minWidth) {
            width = Math.max(width / 2, minWidth);
        }
    }


    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);
    }
}
