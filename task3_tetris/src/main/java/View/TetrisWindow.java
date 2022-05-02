package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class TetrisWindow extends JFrame {

    private FieldPainter fieldPainter;

    public TetrisWindow(KeyListener listener) {
        super("Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(listener);
        fieldPainter = null;
    }

    public void showTetrisField(DataForViewer dataForViewer) {
        if (fieldPainter == null) {
            fieldPainter = new FieldPainter(dataForViewer.tetrisField(), new ViewerSettings(dataForViewer.isColored()));
        }
        else {
            fieldPainter.setTetrisField(dataForViewer.tetrisField());
        }
        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());
        container.add(fieldPainter);
        this.pack();
        this.setVisible(true);
    }
}
