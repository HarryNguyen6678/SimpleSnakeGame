package Game;

import java.awt.*;
import java.util.ArrayList;

public class Snake {
    private ArrayList<Point> body = new ArrayList<>();
    private char direction = 'R'; // Hướng ban đầu của rắn
    private final int unitSize;

    public Snake(int startLength, int unitSize) {
        this.unitSize = unitSize;
        for (int i = 0; i < startLength; i++) {
            body.add(new Point(0, 0)); // Vị trí khởi đầu
        }
    }

    public void move() {
        for (int i = body.size() - 1; i > 0; i--) {
            body.set(i, new Point(body.get(i - 1)));
        }
        Point head = body.get(0);
        switch (direction) {
            case 'U': head.translate(0, -unitSize); break;
            case 'D': head.translate(0, unitSize); break;
            case 'L': head.translate(-unitSize, 0); break;
            case 'R': head.translate(unitSize, 0); break;
        }
        body.set(0, head);
    }

    public void grow() {
        body.add(new Point(body.get(body.size() - 1)));
    }

    public boolean checkCollision() {
        Point head = body.get(0);
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) {
                return true;
            }
        }
        return false;
    }

    public void setDirection(char newDirection) {
        // Không cho phép rắn quay ngược lại
        if ((direction == 'L' && newDirection != 'R') ||
                (direction == 'R' && newDirection != 'L') ||
                (direction == 'U' && newDirection != 'D') ||
                (direction == 'D' && newDirection != 'U')) {
            direction = newDirection;
        }
    }

    public ArrayList<Point> getBody() {
        return body;
    }

    public Point getHead() {
        return body.get(0);
    }
}
