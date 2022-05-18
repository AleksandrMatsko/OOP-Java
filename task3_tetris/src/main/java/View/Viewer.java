package View;

import Controller.KeyboardListener;
import Game.GameStatus;
import Model.ModelSettings;

import javax.swing.*;
import java.awt.*;

public class Viewer extends JFrame implements Runnable {
    //private final MenuFrame menuFrame;
    private final ViewerSettings viewerSettings;
    private final FieldPainter fieldPainter;
    private final NextFigurePainter nextFigurePainter;
    private final ScorePrinter scorePrinter;
    private DataForViewer dataForViewer;
    private final KeyboardListener keyboardListener;


    public Viewer(ViewerSettings viewerSettings, KeyboardListener keyboardListener, ModelSettings modelSettings) {
        //menuFrame = new MenuFrame(viewerSettings);
        this.viewerSettings = viewerSettings;
        dataForViewer = null;
        this.keyboardListener = keyboardListener;
        addKeyListener(keyboardListener);
        setTitle("Tetris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(modelSettings.getWidthOfField() * viewerSettings.getLenOfBlock() / 2,
                0, 2 * modelSettings.getWidthOfField() * viewerSettings.getLenOfBlock(),
                (modelSettings.getHeightOfField() + 2) * viewerSettings.getLenOfBlock());
        setVisible(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        fieldPainter = new FieldPainter(viewerSettings);
        gridBagConstraints.fill = GridBagConstraints.CENTER;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 3;
        mainPanel.add(fieldPainter, gridBagConstraints);

        nextFigurePainter = new NextFigurePainter(viewerSettings);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        mainPanel.add(nextFigurePainter, gridBagConstraints);

        scorePrinter = new ScorePrinter(viewerSettings);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        mainPanel.add(scorePrinter, gridBagConstraints);

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
        if (dataForViewer.gameStatus() != GameStatus.EXIT) {
            fieldPainter.setTetrisField(dataForViewer.tetrisField());
            fieldPainter.repaint();
            nextFigurePainter.setNextFigure(dataForViewer.nextFigure());
            nextFigurePainter.repaint();
            scorePrinter.update(dataForViewer.score());
        }
        else {
            System.exit(0);
        }
    }
}
