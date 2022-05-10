package Model.Actions.PossibleActions;

import Model.Figures.FigureRandomizer;
import Game.GameStatus;
import Model.Model;

public class SpawnNewFigure implements ActionInterface {
    @Override
    public GameStatus execute(Model model,GameStatus currentStatus) {
        if (currentStatus != GameStatus.ACTIVE) {
            //TODO exception
            return currentStatus;
        }
        boolean success = model.getTetrisField().spawnFigure(model.getNextFigure());
        if (success) {
            model.prepareNextFigure();
            return GameStatus.ACTIVE;
        }
        return GameStatus.END;
    }
}
