package View;

import Model.TetrisField;

import javax.swing.*;
import java.awt.*;

public class FieldPainter extends JPanel {
    private TetrisField tetrisField;
    private ViewerSettings viewerSettings;


    public FieldPainter(ViewerSettings viewerSettings) {
        this.tetrisField = null;
        this.viewerSettings = viewerSettings;
        setBackground(Color.white);
        setPreferredSize(new Dimension(viewerSettings.getWidth() * viewerSettings.getLenOfBlock(),
                viewerSettings.getHeight() * viewerSettings.getLenOfBlock()));
    }

    public void changeViewerSettings(ViewerSettings viewerSettings) {
        this.viewerSettings = viewerSettings;
    }

    private void paintBlock(Graphics2D g2D, int x, int y) {
        g2D.setColor(Color.white);
        int stroke = viewerSettings.getStroke();
        int lenOfBlock = viewerSettings.getLenOfBlock();
        g2D.setColor(viewerSettings.getColorTable().get(tetrisField.getCell(x, y + tetrisField.getSizeSpawnArea())));
        g2D.fillRect(x * lenOfBlock + stroke, y * lenOfBlock + stroke, lenOfBlock - 2 * stroke, lenOfBlock - 2 * stroke);
    }

    private void paintCanvas(Graphics2D g2D) {
        g2D.setColor(Color.lightGray);
        for (int x = 1; x < viewerSettings.getWidth(); x++) {
            for (int y = 1; y < viewerSettings.getHeight(); y++) {
                g2D.drawLine(x * viewerSettings.getLenOfBlock(), 0,
                        x * viewerSettings.getLenOfBlock(), viewerSettings.getHeight() * viewerSettings.getLenOfBlock());
                g2D.drawLine(0, y * viewerSettings.getLenOfBlock(),
                        viewerSettings.getWidth() * viewerSettings.getLenOfBlock(), y * viewerSettings.getLenOfBlock());
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (tetrisField == null) {
            //TODO exception
        }
        Graphics2D g2D = (Graphics2D) g;
        paintCanvas(g2D);
        for (int y = 0; y < viewerSettings.getHeight(); y++) {
            for (int x = 0; x < viewerSettings.getWidth(); x++) {
                if (tetrisField.getCell(x, y + tetrisField.getSizeSpawnArea()) != 0) {
                    paintBlock(g2D, x, y);
                }
            }
        }
    }

    public void setTetrisField(TetrisField tetrisField) {
        this.tetrisField = tetrisField;
    }
}
