package View.Parts;

import View.ViewerSettings;

import javax.swing.*;
import java.awt.*;

public class GameOverPanel extends JPanel {
    private ViewerSettings viewerSettings;
    private final ScorePrinter scorePrinter;
    private int score;

    public GameOverPanel(ViewerSettings viewerSettings) {
        setLayout(new GridBagLayout());
        this.viewerSettings = viewerSettings;

        JLabel gameOverLabel = new JLabel();
        gameOverLabel.setVerticalAlignment(JLabel.CENTER);
        gameOverLabel.setHorizontalAlignment(JLabel.CENTER);
        gameOverLabel.setBackground(Color.white);
        gameOverLabel.setForeground(Color.red);
        gameOverLabel.setFont(new Font(null, Font.BOLD, 26));
        gameOverLabel.setText("GAME OVER");
        setPreferredSize(new Dimension(viewerSettings.getLenOfBlock() * 10, viewerSettings.getLenOfBlock() * 5));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 3;
        add(gameOverLabel, gridBagConstraints);

        scorePrinter = new ScorePrinter(viewerSettings);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.gridwidth = 3;
        add(scorePrinter, gridBagConstraints);

        JTextArea jTextArea = new JTextArea();
        jTextArea.setForeground(Color.black);
        jTextArea.setFont(new Font(null, Font.BOLD, 16));
        setPreferredSize(new Dimension(viewerSettings.getLenOfBlock() * 15, viewerSettings.getLenOfBlock() * 5));
        jTextArea.setText("Press Del to close game\nPress R to restart\nPress H for help");

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.gridwidth = 3;
        add(jTextArea, gridBagConstraints);

    }

    public void setScore(int score) {
        this.score = score;
        scorePrinter.update(score);
    }
}
