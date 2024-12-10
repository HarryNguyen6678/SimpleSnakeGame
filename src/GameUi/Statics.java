package GameUi;

import javax.swing.*;
import java.awt.*;

public class Statics extends JFrame {
    private int highScore = 0;

    public Statics() {
        JDialog staticsDialog = new JDialog(this, "Statics", true);
        staticsDialog.setSize(300, 200);
        staticsDialog.setLocationRelativeTo(this);
        JLabel scoreLabel = new JLabel("Your High Score: " + highScore, SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        staticsDialog.add(scoreLabel);
        staticsDialog.setVisible(true);
    }
}
