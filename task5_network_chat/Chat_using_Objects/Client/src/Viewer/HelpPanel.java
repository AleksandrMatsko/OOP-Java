package Viewer;

import javax.swing.*;

public class HelpPanel extends JPanel {
    private final String text = """
            This is help panel
            
            You can use the following commands to interact with chat:
            /users - shows you the users connected
            /exit - live the chat
            
            """;
    private JTextArea textArea;

    public HelpPanel() {
        textArea = new JTextArea(6, 30);
        textArea.setEditable(false);
        textArea.setText(text);
        setBounds(0, 0, 100, 50);
        add(textArea);
    }
}
