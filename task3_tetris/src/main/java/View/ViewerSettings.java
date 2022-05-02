package View;

import Model.Figures.FigureFactory;
import Model.ModelSettings;

import java.awt.*;
import java.util.HashMap;
import java.util.Random;

public class ViewerSettings {
    private int lenOfBlock;
    private int stroke;
    private HashMap<Integer, Color> colorTable;

    //TODO colorTable

    public ViewerSettings(boolean isColored) {
        stroke = 2;
        lenOfBlock = 40;
        colorTable = new HashMap<>();
        colorTable.put(0, Color.white);
        if (!isColored) {
            for (int i = 1; i <= FigureFactory.getInstance().getAmountOfFigures(); i++) {
                colorTable.put(i, Color.black);
            }
        }
        else {
            Random random = new Random();
            for (int i = 1; i <= FigureFactory.getInstance().getAmountOfFigures(); i++) {
                colorTable.put(i, new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()));
            }
        }
    }

    public int getLenOfBlock() {
        return lenOfBlock;
    }

    public HashMap<Integer, Color> getColorTable() {
        return colorTable;
    }

    public int getStroke() {
        return stroke;
    }
}
