package Model.Figures;

import Exceptions.NotFoundExceptions.FigureNotFoundException;
import Exceptions.NameExceptions.InvalidFigureNameException;
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
            throw new RuntimeException("Problems with figures.properties");
        }
        for (Map.Entry entry : properties.entrySet()) {
            String toFigureName = (String) entry.getKey();
            String className = (String) entry.getValue();
            FigureName figureName = null;
            try {
                figureName = new FigureName(toFigureName);
            }
            catch (InvalidFigureNameException ex) {
                throw new RuntimeException("Invalid format for FigureName in figures.properties");
            }
            classForFigureName.put(figureName, className);
            possibleFigureNames.add(figureName);
        }
    }

    public static FigureFactory getInstance() {
        if (figureFactory == null) {
            figureFactory = new FigureFactory();
        }
        return figureFactory;
    }

    public Figure getNewFigure(FigureName figureName) throws FigureNotFoundException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (!classForFigureName.containsKey(figureName)) {
            throw new FigureNotFoundException();
        }
        return (Figure) Class.forName(classForFigureName.get(figureName)).getDeclaredConstructor().newInstance();
    }

    public ArrayList<FigureName> getPossibleFigureNames() {
        if (figureFactory == null) {
            figureFactory = new FigureFactory();
        }
        if (possibleFigureNames.isEmpty()) {
            throw new RuntimeException("No figures registered");
        }
        return possibleFigureNames;
    }

    public int getAmountOfFigures() {
        if (figureFactory == null) {
            figureFactory = new FigureFactory();
        }
        if (possibleFigureNames.isEmpty()) {
            throw new RuntimeException("No figures registered");
        }
        return possibleFigureNames.size();
    }
}
