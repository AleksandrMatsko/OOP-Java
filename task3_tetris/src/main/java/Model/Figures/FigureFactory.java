package Model.Figures;

import Model.Figures.PossibleFigures.Figure;
import Model.Names.FigureName;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FigureFactory {
    private final HashMap<FigureName, String> classForFigureName;
    private final ArrayList<FigureName> possibleFigureNames;
    private static FigureFactory figureFactory = null;

    private FigureFactory() {
        classForFigureName = new HashMap<>();
        possibleFigureNames = new ArrayList<>();
        final String configFileName = "/figures.properties";
        Properties properties = new Properties();
        try {
            properties.load(FigureFactory.class.getResourceAsStream(configFileName));
        } catch (IOException ex) {
            //TODO normal exception
            ex.printStackTrace();
        }
        int color = 1;
        for (Map.Entry entry : properties.entrySet()) {
            String toFigureName = (String) entry.getKey();
            String className = (String) entry.getValue();
            FigureName figureName;
            //TODO: try catch
            //try {
            figureName = new FigureName(toFigureName);
            //}
            //catch () {}
            classForFigureName.put(figureName, className);
            possibleFigureNames.add(figureName);
            System.err.println(figureName.getName() + " = " + className);
            color += 1;
        }
    }

    public static FigureFactory getInstance() {
        if (figureFactory == null) {
            figureFactory = new FigureFactory();
        }
        return figureFactory;
    }

    public Figure getNewFigure(FigureName figureName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (!classForFigureName.containsKey(figureName)) {
            //TODO: exception
        }
        return (Figure) Class.forName(classForFigureName.get(figureName)).getDeclaredConstructor().newInstance();
    }

    public ArrayList<FigureName> getPossibleFigureNames() {
        if (figureFactory == null) {
            figureFactory = new FigureFactory();
        }
        return possibleFigureNames;
    }

    public int getAmountOfFigures() {
        return possibleFigureNames.size();
    }
}
