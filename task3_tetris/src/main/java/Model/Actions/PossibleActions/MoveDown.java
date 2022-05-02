package Model.Actions.PossibleActions;

import Model.Figures.Direction;
import Game.GameStatus;
import Model.Model;

public class MoveDown implements ActionInterface {
    @Override
    public GameStatus execute(Model model, GameStatus currentStatus) {
        if (currentStatus != GameStatus.ACTIVE) {
            //TODO exception
        }
        boolean success = model.getTetrisField().moveCurrentFigureOnField(Direction.DOWN);
        if (success) {
            return GameStatus.ACTIVE;
        }
        int numRemovedLines = model.getTetrisField().removeFilledLines();
        model.calcScore(numRemovedLines);
        return (new SpawnNewFigure()).execute(model, currentStatus);
    }
}
