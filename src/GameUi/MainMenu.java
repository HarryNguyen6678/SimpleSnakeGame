package GameUi;

import Game.GameFrame;
import Game.GamePannel;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    public MainMenu() {
        //JFrame modify
        setTitle("Main Menu");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        //Create JPannel for UI
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(Color.WHITE);

        //add top space
        menuPanel.add(Box.createVerticalStrut(100));

        //Generate buttons
        JButton startButton = createButton("Start New Game");
        JButton staticsButton = createButton("Statics");
        JButton quitButton = createButton("Quit Game");

        //Start button modify
        startButton.addActionListener(e -> {
            new GameFrame();
        });

        //Statics button modify
        staticsButton.addActionListener(e -> {
            new Statics();
        });

        //Quit button modify
        quitButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        // Thêm các nút vào panel
        menuPanel.add(startButton);
        menuPanel.add(Box.createVerticalStrut(30));
        menuPanel.add(staticsButton);
        menuPanel.add(Box.createVerticalStrut(30));
        menuPanel.add(quitButton);

        //Add Pannel to JFrame
        add(menuPanel);

        setVisible(true);
    }

    //Button Design
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(200, 50));
        button.setMaximumSize(new Dimension(200, 50));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        return button;
    }

    public static void main(String[] args) {
        //Start the UI
        SwingUtilities.invokeLater(MainMenu::new);
    }
}
