package Model.Actions.PossibleActions;

import Model.Figures.FigureFactory;
import Model.GameStatus;
import Model.Model;
import Model.Names.FigureName;

import java.util.ArrayList;
import java.util.Random;

public class SpawnNewFigure implements ActionInterface {
    @Override
    public GameStatus execute(Model model) {
        Random random = new Random();
        ArrayList<FigureName> possibleFigureNames = FigureFactory.getInstance().getPossibleFigureNames();
        int index = random.nextInt(possibleFigureNames.size());
        boolean success = model.getGameField().spawnFigure(FigureFactory.getInstance().getFigure(possibleFigureNames.get(index)));
        if (success) {
            return GameStatus.ACTIVE;
        }
        return GameStatus.END;
    }
}
