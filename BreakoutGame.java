import javax.swing.*;
import java.awt.*;

public class BreakoutGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Breakout Game");
        JButton startButton = new JButton("Start");

        GamePanel gamePanel = new GamePanel(startButton);

        startButton.addActionListener(e -> {
            startButton.setEnabled(false);
            gamePanel.resetGame();
            gamePanel.startGame();
        });

        frame.add(gamePanel, BorderLayout.CENTER);
        frame.add(startButton, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
