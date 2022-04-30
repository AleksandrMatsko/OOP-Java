package Model.Actions.PossibleActions;

import Model.Figures.Direction;
import Model.GameStatus;
import Model.Model;

public class RotateRight implements ActionInterface {

    @Override
    public GameStatus execute(Model model) {
        model.getGameField().rotateCurrentFigureOnField(Direction.RIGHT);
        return GameStatus.ACTIVE;
    }
}
