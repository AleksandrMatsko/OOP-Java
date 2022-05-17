package View;

import Controller.KeyboardListener;
import Game.GameStatus;
import Model.ModelSettings;

import javax.swing.*;
import java.awt.*;

public class Viewer extends JFrame implements Runnable {
    private final MenuPanel menuPanel;
    private final ViewerSettings viewerSettings;
    private final FieldPainter fieldPainter;
    private final NextFigurePainter nextFigurePainter;
    private final ScorePrinter scorePrinter;
    private DataForViewer dataForViewer;
    private final KeyboardListener keyboardListener;


    public Viewer(ViewerSettings viewerSettings, KeyboardListener keyboardListener, ModelSettings modelSettings) {
        menuPanel = new MenuPanel(viewerSettings);
        this.viewerSettings = viewerSettings;
        dataForViewer = null;
        this.keyboardListener = keyboardListener;
        addKeyListener(keyboardListener);
        setTitle("Tetris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(modelSettings.getWidthOfField() * viewerSettings.getLenOfBlock() / 2,
                0, 3 * modelSettings.getWidthOfField() * viewerSettings.getLenOfBlock(),
                (modelSettings.getHeightOfField() + 2) * viewerSettings.getLenOfBlock());
        setVisible(true);

        fieldPainter = new FieldPainter(viewerSettings);

        nextFigurePainter = new NextFigurePainter(viewerSettings);

        scorePrinter = new ScorePrinter(viewerSettings);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.add(fieldPainter);
        mainPanel.add(nextFigurePainter);
        mainPanel.add(scorePrinter);
        //mainPanel.add(menuPanel);
        add(BorderLayout.CENTER, mainPanel);
    }

    public void setDataForViewer(DataForViewer dataForViewer) {
        this.dataForViewer = dataForViewer;
    }

    @Override
    public void run() {
        if (dataForViewer == null) {
            return;
        }
        fieldPainter.setTetrisField(dataForViewer.tetrisField());
        fieldPainter.repaint();
        nextFigurePainter.setNextFigure(dataForViewer.nextFigure());
        nextFigurePainter.repaint();
        scorePrinter.setScore(dataForViewer.score());
        scorePrinter.update();
    }
}
