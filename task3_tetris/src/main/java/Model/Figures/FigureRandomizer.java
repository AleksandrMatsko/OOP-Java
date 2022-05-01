package Model.Figures;

import Model.Figures.PossibleFigures.Figure;
import java.util.Random;

public class FigureRandomizer {
    private final Random random = new Random();

    public Figure getFigure() {
        int index = random.nextInt(FigureFactory.getInstance().getAmountOfFigures());
        return FigureFactory.getInstance().getFigure(FigureFactory.getInstance().getPossibleFigureNames().get(index));
    }

}
