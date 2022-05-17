package View;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JFrame {
    private ViewerSettings viewerSettings;

    public MenuPanel(ViewerSettings viewerSettings) {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.PAGE_AXIS));
        menuPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton continueButton = new JButton("Continue");
        JButton newGameButton = new JButton("New game");
        JButton aboutButton = new JButton("About");
        JButton highScoresButton = new JButton("High scores");
        JButton exitButton = new JButton("Exit");

        menuPanel.add(continueButton);
        menuPanel.add(aboutButton);
        menuPanel.add(newGameButton);
        menuPanel.add(highScoresButton);
        menuPanel.add(exitButton);

        setBounds(viewerSettings.getWidth() * viewerSettings.getLenOfBlock() / 2, 0,
                viewerSettings.getWidth() * viewerSettings.getLenOfBlock(),
                viewerSettings.getHeight() * viewerSettings.getLenOfBlock() / 2);

        add(BorderLayout.CENTER, menuPanel);

    }
}
