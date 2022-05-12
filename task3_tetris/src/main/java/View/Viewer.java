package View;

import Controller.KeyboardListener;
import Model.Figures.PossibleFigures.Figure;
import Model.Figures.PossibleFigures.LFigure;
import Model.Figures.PossibleFigures.OFigure;
import Model.ModelSettings;
import Model.TetrisField;

import javax.swing.*;
import java.awt.*;

public class Viewer extends JFrame implements Runnable {
    private final ViewerSettings viewerSettings;
    private final FieldPainter fieldPainter;
    private final NextFigurePainter nextFigurePainter;
    private DataForViewer dataForViewer;
    private final KeyboardListener keyboardListener;


    public Viewer(ViewerSettings viewerSettings, KeyboardListener keyboardListener, ModelSettings modelSettings) {
        this.viewerSettings = viewerSettings;
        dataForViewer = null;
        this.keyboardListener = keyboardListener;
        addKeyListener(keyboardListener);
        setTitle("Tetris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(modelSettings.getWidthOfField() * viewerSettings.getLenOfBlock() / 2,
                0, 2 * modelSettings.getWidthOfField() * viewerSettings.getLenOfBlock(),
                (modelSettings.getHeightOfField() + 1) * viewerSettings.getLenOfBlock());
        setVisible(true);

        fieldPainter = new FieldPainter(new TetrisField(modelSettings.getWidthOfField(), modelSettings.getHeightOfField(),
                modelSettings.getSizeSpawnArea()), viewerSettings);
        fieldPainter.setBackground(Color.white);
        this.nextFigurePainter = new NextFigurePainter(new OFigure(), viewerSettings);
        nextFigurePainter.setBackground(Color.white);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.add(fieldPainter);
        mainPanel.add(nextFigurePainter);
        add(BorderLayout.CENTER, mainPanel);
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
        nextFigurePainter.setNextFigure(dataForViewer.nextFigure());
        nextFigurePainter.repaint();

    }
}
