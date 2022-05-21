package View.Parts;

import Model.Figures.PossibleFigures.Block;
import Model.Figures.PossibleFigures.Figure;
import View.ViewerSettings;

import javax.swing.*;
import java.awt.*;

public class NextFigurePainter extends JPanel {
    private ViewerSettings viewerSettings;
    private Figure nextFigure;

    public NextFigurePainter(ViewerSettings viewerSettings) {
        this.viewerSettings = viewerSettings;
        this.nextFigure = null;
        setPreferredSize(new Dimension(viewerSettings.getLenOfBlock() * 7, viewerSettings.getLenOfBlock() * 7));
        setBackground(Color.white);
    }

    public void setNextFigure(Figure nextFigure) {
        this.nextFigure = nextFigure;
    }

    private void paintBlock(Graphics2D g2D, int x, int y) {
        g2D.setColor(Color.white);
        int stroke = viewerSettings.getStroke();
        int lenOfBlock = viewerSettings.getLenOfBlock();
        //g2D.setStroke(new BasicStroke(stroke));
        //g2D.drawRect(x, y, lenOfBlock, lenOfBlock);
        g2D.setColor(viewerSettings.getColorTable().get(nextFigure.getColor()));
        g2D.fillRect((x + 2) * lenOfBlock + stroke, (y + 2) * lenOfBlock + stroke, lenOfBlock - 2 * stroke, lenOfBlock - 2 * stroke);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (nextFigure == null) {
            return;
        }
        Graphics2D g2D = (Graphics2D) g;
        for (Block block : nextFigure.getBlocks()) {
            paintBlock(g2D, block.getX(), block.getY());
        }
    }
}
