package View;

import Model.Figures.PossibleFigures.Block;
import Model.Figures.PossibleFigures.Figure;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class NextFigurePainter extends JPanel {
    private ViewerSettings viewerSettings;
    private Figure nextFigure;

    public NextFigurePainter(Figure nextFigure, ViewerSettings viewerSettings) {
        this.viewerSettings = viewerSettings;
        this.nextFigure = nextFigure;
    }

    public void setNextFigure(Figure nextFigure) {
        this.nextFigure = nextFigure;
    }

    private void paintBlock(Graphics2D g2D, int x, int y) {
        g2D.setColor(Color.white);
        int stroke = viewerSettings.getStroke();
        int lenOfBlock = viewerSettings.getLenOfBlock();
        g2D.setStroke(new BasicStroke(stroke));
        g2D.drawRect(x, y, lenOfBlock, lenOfBlock);
        g2D.setColor(viewerSettings.getColorTable().get(nextFigure.getColor()));
        g2D.fillRect(x * lenOfBlock + stroke, y * lenOfBlock + stroke, lenOfBlock - 2 * stroke, lenOfBlock - 2 * stroke);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        for (Block block : nextFigure.getBlocks()) {
            paintBlock(g2D, block.getX(), block.getY());
        }
    }
}
