package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePannel extends JPanel implements ActionListener {
    private final int screenWidth = 600;
    private final int screenHeight = 600;
    private final int unitSize = 25;
    private Snake snake;
    private Apple apple;
    private boolean running = false;
    private boolean waitingToStart = true;
    private boolean paused = false;
    private Timer timer;
    private int currentScore = 0;
    private int highScore = 0;

    public GamePannel() {
        this.apple = apple;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
    }

    public void startGame() {
        snake = new Snake(5, unitSize);
        apple = new Apple(screenWidth, screenHeight, unitSize);
        running = true;
        waitingToStart = false;
        timer = new Timer(75, this);
        timer.start();
    }

    public void resetGame() {
        currentScore = 0;
        snake = new Snake(5, unitSize);
        apple = new Apple(screenWidth, screenHeight, unitSize);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        if (waitingToStart) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Ink Free", Font.BOLD, 35));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Press Space to Start", (screenWidth - metrics.stringWidth("Press Space to Start")) / 2, screenHeight / 2);
        } else if (paused) {
            drawPauseMenu(g);
        } else if (running) {
            g.setColor(Color.RED);
            Point applePosition = apple.getPosition();
            g.fillOval(applePosition.x, applePosition.y, unitSize, unitSize);

            for (Point part : snake.getBody()) {
                g.setColor(Color.GREEN);
                g.fillRect(part.x, part.y, unitSize, unitSize);
            }

            g.setColor(Color.WHITE);
            g.setFont(new Font("Ink Free", Font.BOLD, 15));
            g.drawString("Score: " + currentScore, 10, 30);
        } else {
            showGameOver(g);
        }
    }

    private void checkCollisions() {
        Point head = snake.getHead();
        if (head.x < 0 || head.x >= screenWidth || head.y < 0 || head.y >= screenHeight || snake.checkCollision()) {
            running = false;
            timer.stop();
        }
    }

    private void drawPauseMenu(Graphics g) {
        g.setColor(new Color(0, 0, 0, 150)); //Background
        g.fillRect(0, 0, screenWidth, screenHeight);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Ink Free", Font.BOLD, 50));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Paused", (screenWidth - metrics.stringWidth("Paused")) / 2, screenHeight / 4);

        //Draw all buttons
        drawButton(g, "Continue (Press C)", screenHeight / 2 - 60);
        drawButton(g, "Restart (Press P)", screenHeight / 2);
        drawButton(g, "Main Menu (Press M)", screenHeight / 2 + 60);
    }

    private void drawButton(Graphics g, String text, int y) {
        g.setColor(Color.WHITE);
        g.fillRect(screenWidth / 2 - 100, y - 30, 200, 50);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Ink Free", Font.BOLD, 25));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString(text, (screenWidth - metrics.stringWidth(text)) / 2, y + 10);
    }

    private void showGameOver(Graphics g) {
        //Current Score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Ink Free", Font.BOLD, 35));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Score: " + currentScore, (screenWidth - metrics.stringWidth("Score: " + currentScore)) / 2, g.getFont().getSize());
        //HighScore
        g.setColor(Color.WHITE);
        g.setFont(new Font("Ink Free", Font.BOLD, 35));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Highscore: " + highScore, (screenWidth - metrics1.stringWidth("Highscore: " + highScore)) / 2, screenHeight / 2 - 230);
        //Game over text
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (screenWidth - metrics2.stringWidth("Game Over")) / 2, screenHeight / 2);
        //Press Spacebar button to restart game
        g.setColor(Color.GREEN);
        g.setFont(new Font("Ink Free", Font.BOLD, 25));
        FontMetrics metrics3 = getFontMetrics(g.getFont());
        g.drawString("Press Space to restart the game", (screenWidth - metrics3.stringWidth("Press Space to restart the game")) / 2, screenHeight / 2 + 150);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            snake.move();
            if (snake.getHead().equals(apple.getPosition())) {
                snake.grow();
                apple.spawn();
                currentScore++;
                if (currentScore > highScore) {
                    highScore = currentScore;
                }
            }
            checkCollisions();
        }
        repaint();
    }

    private class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP -> snake.setDirection('U');
                case KeyEvent.VK_DOWN -> snake.setDirection('D');
                case KeyEvent.VK_LEFT -> snake.setDirection('L');
                case KeyEvent.VK_RIGHT -> snake.setDirection('R');
                case KeyEvent.VK_SPACE -> {
                    if (waitingToStart) {
                        startGame();
                        repaint();
                    } else if (!running) {
                        resetGame();
                        startGame();
                        repaint();
                    }
                }
                case KeyEvent.VK_ESCAPE -> {
                    if (running) {
                        paused = !paused;
                    }
                }
                case KeyEvent.VK_C -> {

                }
                case KeyEvent.VK_R -> {

                }
                case KeyEvent.VK_M -> {

                }
            }
        }
    }
}
