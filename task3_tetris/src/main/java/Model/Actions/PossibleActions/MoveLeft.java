package Model.Actions.PossibleActions;

import Model.Figures.Direction;
import Game.GameStatus;
import Model.Model;

public class MoveLeft implements ActionInterface {

    @Override
    public GameStatus execute(Model model, GameStatus currentStatus) {
        if (currentStatus != GameStatus.ACTIVE) {
            //TODO exception
        }
        model.getGameField().moveCurrentFigureOnField(Direction.LEFT);
        return GameStatus.ACTIVE;
    }
}
