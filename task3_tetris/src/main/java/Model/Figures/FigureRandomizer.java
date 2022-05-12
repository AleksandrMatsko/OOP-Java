package Model.Figures;

import Model.Figures.PossibleFigures.Figure;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class FigureRandomizer {
    private final Random random = new Random();

    public Figure getFigure() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        int index = random.nextInt(FigureFactory.getInstance().getAmountOfFigures());
        return FigureFactory.getInstance().getNewFigure(FigureFactory.getInstance().getPossibleFigureNames().get(index));
    }

}
