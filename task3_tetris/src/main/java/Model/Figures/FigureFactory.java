package Model.Figures;

import Model.Figures.PossibleFigures.Figure;
import Model.Figures.PossibleFigures.FigureName;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FigureFactory {
    private final HashMap<FigureName, Figure> figureTable;
    private final ArrayList<FigureName> possibleFigureNames;
    private static FigureFactory figureFactory = null;

    private FigureFactory() {
        figureTable = new HashMap<FigureName, Figure>();
        possibleFigureNames = new ArrayList<>();
        final String configFileName = "src/main/resources/figures.ini";
        Properties properties = new Properties();
        try {
            properties.load(FigureFactory.class.getResourceAsStream(configFileName));
        } catch (IOException ex) {
            //TODO: normal exception
            ex.printStackTrace();
        }
        for (Map.Entry entry : properties.entrySet()) {
            String toFigureName = (String) entry.getKey();
            String className = (String) entry.getValue();
            FigureName figureName;
            //TODO: try catch
            //try {
            figureName = new FigureName(toFigureName);
            //}
            //catch () {}
            try {
                figureTable.put(figureName, (Figure) Class.forName(className).getDeclaredConstructor().newInstance());
            } catch (ClassNotFoundException ex) {
                //TODO: normal exception
                ex.printStackTrace();
            }
            catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException ex) {
                ex.printStackTrace();
            }
            possibleFigureNames.add(figureName);
        }
    }

    public static FigureFactory getInstance() {
        if (figureFactory == null) {
            figureFactory = new FigureFactory();
        }
        return figureFactory;
    }

    public Figure getFigure(FigureName figureName) {
        if (!figureTable.containsKey(figureName)) {
            //TODO: exception
        }
        return figureTable.get(figureName);
    }

    public ArrayList<FigureName> getPossibleFigureNames() {
        if (figureFactory == null) {
            figureFactory = new FigureFactory();
        }
        return possibleFigureNames;
    }
}
