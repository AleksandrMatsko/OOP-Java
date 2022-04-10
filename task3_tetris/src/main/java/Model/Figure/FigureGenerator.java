package Model.Figure;

import java.util.ArrayList;
import java.util.HashMap;

public class FigureGenerator {
    private final HashMap<FigureName, Figure> allFigures;
    private final ArrayList<FigureName> figureNames;

    public FigureGenerator() {
        allFigures = new HashMap<FigureName, Figure>();
        figureNames = new ArrayList<FigureName>();

        FigureName name = new FigureName("I");
        Block center = new Block(3, 3);
        Block[] blocks = new Block[4];
        blocks[0].setCoords(0, 1);
        blocks[1].setCoords(1, 1);
        blocks[2].setCoords(2, 1);
        blocks[3].setCoords(3, 1);
        Figure figure = new Figure(4, center, blocks);
        allFigures.put(name, figure);
        figureNames.add(name);

        name = new FigureName("J");
        center.setCoords(1, 1);
        blocks[0].setCoords(0, 0);
        blocks[1].setCoords(0, 1);
        blocks[2].setCoords(1, 1);
        blocks[3].setCoords(2, 1);
        figure = new Figure(4, center, blocks);
        allFigures.put(name, figure);
        figureNames.add(name);

        name = new FigureName("L");
        center.setCoords(1, 1);
        blocks[0].setCoords(0, 1);
        blocks[1].setCoords(1, 1);
        blocks[2].setCoords(2, 0);
        blocks[3].setCoords(2, 1);
        figure = new Figure(4, center, blocks);
        allFigures.put(name, figure);
        figureNames.add(name);

        name = new FigureName("O");
        center.setCoords(1, 1);
        blocks[0].setCoords(0, 0);
        blocks[1].setCoords(0, 1);
        blocks[2].setCoords(1, 0);
        blocks[3].setCoords(1, 1);
        figure = new Figure(4, center, blocks);
        allFigures.put(name, figure);
        figureNames.add(name);

        name = new FigureName("S");
        center.setCoords(1, 1);
        blocks[0].setCoords(0, 1);
        blocks[1].setCoords(1, 1);
        blocks[2].setCoords(1, 0);
        blocks[3].setCoords(2, 0);
        figure = new Figure(4, center, blocks);
        allFigures.put(name, figure);
        figureNames.add(name);

        name = new FigureName("T");
        center.setCoords(1, 1);
        blocks[0].setCoords(0, 1);
        blocks[1].setCoords(1, 1);
        blocks[2].setCoords(1, 0);
        blocks[3].setCoords(2, 1);
        figure = new Figure(4, center, blocks);
        allFigures.put(name, figure);
        figureNames.add(name);

        name = new FigureName("Z");
        center.setCoords(1, 1);
        blocks[0].setCoords(0, 0);
        blocks[1].setCoords(1, 0);
        blocks[2].setCoords(1, 1);
        blocks[3].setCoords(2, 1);
        figure = new Figure(4, center, blocks);
        allFigures.put(name, figure);
        figureNames.add(name);
    }

    public HashMap<FigureName, Figure> getAllFigures() {
        return allFigures;
    }

    public ArrayList<FigureName> getFigureNames() {
        return figureNames;
    }
}
