public abstract class MovingObject extends GameObject {

    private int vx;
    private int vy;

    public MovingObject(int x, int y, int width, int height, int vx, int vy) {
        super(x, y, width, height);
        this.vx = vx;
        this.vy = vy;
    }

    public abstract void move();

    public int getVx() {
        return vx;
    }

    public int getVy() {
        return vy;
    }

    public void setVelocity(int vx, int vy) {
        this.vx = vx;
        this.vy = vy;
    }
    
    public void reverseX() {
        vx = -vx;
    }

    public void reverseY() {
        vy = -vy;
    }
}
