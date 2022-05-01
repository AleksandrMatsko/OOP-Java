package Model.Actions.PossibleActions;

import Model.Figures.Direction;
import Game.GameStatus;
import Model.Model;

public class RotateRight implements ActionInterface {

    @Override
    public GameStatus execute(Model model, GameStatus currentStatus) {
        if (currentStatus != GameStatus.ACTIVE) {
            //TODO exception
        }
        model.getGameField().rotateCurrentFigureOnField(Direction.RIGHT);
        return GameStatus.ACTIVE;
    }
}
