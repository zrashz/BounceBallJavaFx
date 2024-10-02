import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BouncingBallAnimation extends JPanel implements ActionListener {

    private int ballX = 100, ballY = 100;  // Initial position of the ball
    private int ballDiameter = 50;  // Ball size
    private int xSpeed = 5, ySpeed = 3;  // Speed of the ball
    private int minDiameter = 30, maxDiameter = 100;  // Ball size range
    private boolean increasingSize = true;  // Whether the ball is growing or shrinking
    private Timer timer;

    // Constructor
    public BouncingBallAnimation() {
        this.setBackground(Color.BLACK);
        timer = new Timer(20, this);  // 20 ms timer for smooth animation
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Enable anti-aliasing for smooth edges
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set dynamic color for the ball (random RGB values)
        g2d.setColor(new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));

        // Draw the bouncing ball
        g2d.fillOval(ballX, ballY, ballDiameter, ballDiameter);
    }

    // Logic for bouncing ball animation
    @Override
    public void actionPerformed(ActionEvent e) {
        // Ball movement logic
        ballX += xSpeed;
        ballY += ySpeed;

        // Reverse direction if ball hits edges
        if (ballX <= 0 || ballX + ballDiameter >= getWidth()) {
            xSpeed = -xSpeed;
        }
        if (ballY <= 0 || ballY + ballDiameter >= getHeight()) {
            ySpeed = -ySpeed;
        }

        // Ball size logic (grow and shrink)
        if (increasingSize) {
            ballDiameter += 1;
            if (ballDiameter >= maxDiameter) {
                increasingSize = false;
            }
        } else {
            ballDiameter -= 1;
            if (ballDiameter <= minDiameter) {
                increasingSize = true;
            }
        }

        // Redraw the panel
        repaint();
    }

    // Main method to start the animation
    public static void main(String[] args) {
        JFrame frame = new JFrame("Bouncing Ball Animation");
        BouncingBallAnimation animation = new BouncingBallAnimation();

        frame.add(animation);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
