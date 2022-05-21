package View.Parts;

import javax.swing.*;
import java.awt.*;

public class HighScorePanel extends JPanel {
    private final JTextArea jTextArea;

    public HighScorePanel() {
        jTextArea = new JTextArea();
        jTextArea.setForeground(Color.black);
        jTextArea.setFont(new Font(null, Font.BOLD, 16));

        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(jTextArea, gridBagConstraints);
    }

    public void setText(String text) {
        jTextArea.setText("High score table:\n" + text);
    }
}
