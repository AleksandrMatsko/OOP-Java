package View;

import Model.TetrisField;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class FieldPainter extends JPanel {
    private final HashMap<Integer, Color> colorTable;
    private TetrisField tetrisField;
    private ViewerSettings viewerSettings;


    public FieldPainter(TetrisField tetrisField, ViewerSettings viewerSettings) {
        this.tetrisField = tetrisField;
        this.viewerSettings = viewerSettings;
        colorTable = viewerSettings.getColorTable();
    }

    public void changeViewerSettings(ViewerSettings viewerSettings) {
        this.viewerSettings = viewerSettings;
    }

    private void paintBlock(Graphics2D g2D, int x, int y) {
        g2D.setColor(Color.white);
        int stroke = viewerSettings.getStroke();
        int lenOfBlock = viewerSettings.getLenOfBlock();
        g2D.setStroke(new BasicStroke(stroke));
        g2D.drawRect(x, y, lenOfBlock, lenOfBlock);
        g2D.setColor(colorTable.get(tetrisField.getCell(x, y)));
        g2D.fillRect(x * lenOfBlock + stroke, y * lenOfBlock + stroke, lenOfBlock - 2 * stroke, lenOfBlock - 2 * stroke);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        for (int y = 0; y < tetrisField.getHeight(); y++) {
            for (int x = 0; x < tetrisField.getWidth(); x++) {
                if (tetrisField.getCell(x, y) != 0) {
                    paintBlock(g2D, x, y);
                }
            }
        }
    }

    public void setTetrisField(TetrisField tetrisField) {
        this.tetrisField = tetrisField;
    }
}
