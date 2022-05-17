package View;

import Model.Figures.FigureFactory;
import Model.Names.FigureName;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Random;

public class ViewerSettings {
    private int lenOfBlock;
    private int stroke;
    private HashMap<Integer, Color> colorTable;
    private int width;
    private int height;

    public ViewerSettings(boolean isColored, int width, int height) {
        this.width = width;
        this.height = height;
        stroke = 2;
        lenOfBlock = 30;
        colorTable = new HashMap<>();
        colorTable.put(0, Color.white);
        if (!isColored) {
            for (int i = 1; i <= FigureFactory.getInstance().getAmountOfFigures(); i++) {
                colorTable.put(i, Color.black);
            }
        }
        else {
            Random random = new Random();
            for (FigureName figureName : FigureFactory.getInstance().getPossibleFigureNames()) {
                try {
                    colorTable.put(FigureFactory.getInstance().getNewFigure(figureName).getColor(),
                            new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()));
                } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException ex) {
                    ex.printStackTrace();
                }
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
