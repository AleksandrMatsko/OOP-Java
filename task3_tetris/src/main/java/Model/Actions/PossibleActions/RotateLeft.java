package Model.Actions.PossibleActions;

import Model.Figures.Direction;
import Model.GameStatus;
import Model.Model;

public class RotateLeft implements ActionInterface {
    @Override
    public GameStatus execute(Model model) {
        model.getGameField().rotateCurrentFigureOnField(Direction.LEFT);
        return GameStatus.ACTIVE;
    }
}
