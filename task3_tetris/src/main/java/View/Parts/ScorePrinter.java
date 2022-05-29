package View.Parts;

import View.ViewerSettings;

import javax.swing.*;
import java.awt.*;

public class ScorePrinter extends JLabel {
    private ViewerSettings viewerSettings;
    private int score;

    public ScorePrinter(ViewerSettings viewerSettings) {
        this.viewerSettings = viewerSettings;
        setVerticalAlignment(JLabel.CENTER);
        setHorizontalAlignment(JLabel.CENTER);
        score = 0;
        setBackground(Color.white);
        setForeground(Color.black);
        setFont(new Font(null, Font.BOLD, 16));
        setText("Score: " + score);
        setPreferredSize(new Dimension(viewerSettings.getLenOfBlock() * 4, viewerSettings.getLenOfBlock() * 4));
    }

    public void update(int score) {
        this.score = score;
        setText("Score: " + this.score);
    }

}
