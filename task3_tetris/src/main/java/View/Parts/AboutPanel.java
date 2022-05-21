package View.Parts;

import javax.swing.*;
import java.awt.*;

public class AboutPanel extends JPanel {

    private final String text = """
            This is "Help" panel. Press H to show/hide this panel.

            Controls:
            Left - move figure to the left
            Right - move figure to the right
            Space - accelerate the fall of figure
            Up - rotate figure to the left
            Down - rotate figure to the right
            P - pause

            Interaction outside the game:
            H - show/hide this panel
            Esc - lose game instantly
            Del - close game
            R - restart game
            S - show score table
                        
            """;

    public AboutPanel() {
        JTextArea jTextArea = new JTextArea();
        jTextArea.setFont(new Font(null, Font.BOLD, 16));
        jTextArea.setForeground(Color.black);
        jTextArea.setText(text);

        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(jTextArea, gridBagConstraints);
    }
}
