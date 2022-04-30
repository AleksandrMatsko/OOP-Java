package Model.Actions.PossibleActions;

import Model.Figures.Direction;
import Model.GameStatus;
import Model.Model;

public class MoveDown implements ActionInterface {
    @Override
    public GameStatus execute(Model model) {
        boolean success = model.getGameField().moveCurrentFigureOnField(Direction.DOWN);
        if (success) {
            return GameStatus.ACTIVE;
        }
        int numRemovedLines = model.getGameField().removeFilledLines();
        model.calcScore(numRemovedLines);
        return (new SpawnNewFigure()).execute(model);
    }
}
