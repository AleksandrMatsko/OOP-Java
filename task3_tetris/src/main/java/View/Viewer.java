package View;

import Controller.KeyboardListener;
import Game.GameStatus;
import Model.ModelSettings;
import View.Parts.*;

import javax.swing.*;
import java.awt.*;

public class Viewer extends JFrame implements Runnable {
    private final ViewerSettings viewerSettings;
    private final JPanel gamePanel;
    private final FieldPainter fieldPainter;
    private final NextFigurePainter nextFigurePainter;
    private final ScorePrinter scorePrinter;
    private final GameOverPanel gameOverPanel;
    private final AboutPanel aboutPanel;
    private final HighScorePanel highScorePanel;
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
                (modelSettings.getHeightOfField() + 2) * viewerSettings.getLenOfBlock());

        gamePanel = new JPanel();
        gamePanel.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        fieldPainter = new FieldPainter(viewerSettings);
        gridBagConstraints.fill = GridBagConstraints.CENTER;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 3;
        gamePanel.add(fieldPainter, gridBagConstraints);

        nextFigurePainter = new NextFigurePainter(viewerSettings);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gamePanel.add(nextFigurePainter, gridBagConstraints);

        scorePrinter = new ScorePrinter(viewerSettings);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gamePanel.add(scorePrinter, gridBagConstraints);
        gamePanel.setVisible(false);

        gameOverPanel = new GameOverPanel(viewerSettings);
        gameOverPanel.setVisible(false);

        aboutPanel = new AboutPanel();
        aboutPanel.setVisible(false);

        highScorePanel = new HighScorePanel();
        highScorePanel.setVisible(false);

        add(BorderLayout.NORTH, gamePanel);
        add(BorderLayout.WEST, gameOverPanel);
        add(BorderLayout.SOUTH, aboutPanel);
        add(BorderLayout.CENTER, highScorePanel);

        setVisible(true);
    }

    public void setDataForViewer(DataForViewer dataForViewer) {
        this.dataForViewer = dataForViewer;
    }

    @Override
    public void run() {
        if (dataForViewer == null) {
            return;
        }
        if (dataForViewer.gameStatus() == GameStatus.END) {
            aboutPanel.setVisible(false);
            gamePanel.setVisible(false);
            highScorePanel.setVisible(false);
            gameOverPanel.setScore(dataForViewer.score());
            gameOverPanel.setVisible(true);
        }
        else if (dataForViewer.gameStatus() == GameStatus.SHOWING_HELP) {
            gamePanel.setVisible(false);
            gameOverPanel.setVisible(false);
            highScorePanel.setVisible(false);
            aboutPanel.setVisible(true);
        }
        else if (dataForViewer.gameStatus() == GameStatus.EXIT) {
            System.exit(0);
        }
        else if (dataForViewer.gameStatus() == GameStatus.SHOWING_HIGH_SCORES) {
            gamePanel.setVisible(false);
            gameOverPanel.setVisible(false);
            aboutPanel.setVisible(false);
            highScorePanel.setText(dataForViewer.highScoreTableText());
            highScorePanel.setVisible(true);
        }
        else {
            aboutPanel.setVisible(false);
            gameOverPanel.setVisible(false);
            highScorePanel.setVisible(false);
            gamePanel.setVisible(true);
            fieldPainter.setTetrisField(dataForViewer.tetrisField());
            fieldPainter.repaint();
            nextFigurePainter.setNextFigure(dataForViewer.nextFigure());
            nextFigurePainter.repaint();
            scorePrinter.update(dataForViewer.score());
        }
    }
}
