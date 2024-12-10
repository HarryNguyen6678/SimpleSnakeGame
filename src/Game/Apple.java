package Game;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Apple {
    private Point position;
    private final int screenWidth;
    private final int screenHeight;
    private final int unitSize;

    public Apple(int screenWidth, int screenHeight, int unitSize) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.unitSize = unitSize;
        spawn();
    }

    public void spawn() {
        Random random = new Random();
        int x = random.nextInt(screenWidth / unitSize) * unitSize;
        int y = random.nextInt(screenHeight / unitSize) * unitSize;
        position = new Point(x, y);
    }

    public Point getPosition() {
        return position;
    }
}
