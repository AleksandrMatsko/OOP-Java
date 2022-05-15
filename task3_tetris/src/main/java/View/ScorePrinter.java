package View;

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
        setText(Integer.toString(score));
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void update() {
        setText(Integer.toString(score));
    }

}
