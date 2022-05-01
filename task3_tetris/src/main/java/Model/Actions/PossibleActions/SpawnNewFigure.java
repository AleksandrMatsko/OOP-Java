package Model.Actions.PossibleActions;

import Model.Figures.FigureRandomizer;
import Game.GameStatus;
import Model.Model;

public class SpawnNewFigure implements ActionInterface {
    @Override
    public GameStatus execute(Model model,GameStatus currentStatus) {
        if (currentStatus != GameStatus.ACTIVE) {
            //TODO exception
        }
        boolean success = model.getGameField().spawnFigure(model.getNextFigure());
        if (success) {
            model.setNextFigure((new FigureRandomizer().getFigure()));
            return GameStatus.ACTIVE;
        }
        return GameStatus.END;
    }
}
