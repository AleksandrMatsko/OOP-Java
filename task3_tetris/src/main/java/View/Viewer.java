package View;

import Controller.KeyboardListener;
import Model.ModelSettings;
import Model.TetrisField;

import javax.swing.*;
import java.awt.*;

public class Viewer extends JFrame implements Runnable {
    private final ViewerSettings viewerSettings;
    private FieldPainter fieldPainter;
    private DataForViewer dataForViewer;
    private final KeyboardListener keyboardListener;


    public Viewer(ViewerSettings viewerSettings, KeyboardListener keyboardListener, ModelSettings modelSettings) {
        this.viewerSettings = viewerSettings;
        fieldPainter = new FieldPainter(new TetrisField(modelSettings.getWidthOfField(), modelSettings.getHeightOfField(),
                modelSettings.getSizeSpawnArea()), viewerSettings);
        dataForViewer = null;
        this.keyboardListener = keyboardListener;
        addKeyListener(keyboardListener);
        setTitle("Tetris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(modelSettings.getWidthOfField() * viewerSettings.getLenOfBlock() / 2,
                0, 2 * modelSettings.getWidthOfField() * viewerSettings.getLenOfBlock(),
                (modelSettings.getHeightOfField() + 1) * viewerSettings.getLenOfBlock());
        setVisible(true);
        fieldPainter.setBackground(Color.white);
        add(BorderLayout.CENTER, fieldPainter);
    }

    public void setDataForViewer(DataForViewer dataForViewer) {
        this.dataForViewer = dataForViewer;
    }

    @Override
    public void run() {
        if (dataForViewer == null) {
            //TODO exception
        }
        fieldPainter.setTetrisField(dataForViewer.tetrisField());
        fieldPainter.repaint();

    }
}
