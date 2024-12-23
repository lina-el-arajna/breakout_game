import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameConfig {
    public static final int FRAME_WIDTH = 400;
    public static final int FRAME_HEIGHT = 300;
    public static final int BALL_DIAMETER = 10;
    public static final int BALL_X_SPEED = 2;
    public static final int BALL_Y_SPEED = 3;
    public static final int PADDLE_WIDTH = 60;
    public static final int PADDLE_HEIGHT = 10;
    public static final int PADDLE_SPEED = 8;
    public static final int BRICK_WIDTH = 40;
    public static final int BRICK_HEIGHT = 20;
    public static final int TIMER_DELAY = 30;

    public static final Map<Color, Integer> BRICK_POINTS = new LinkedHashMap<>();

    static {
        BRICK_POINTS.put(Color.RED, 7);
        BRICK_POINTS.put(Color.ORANGE, 5);
        BRICK_POINTS.put(Color.GREEN, 3);
        BRICK_POINTS.put(Color.YELLOW, 1);
    }
}
