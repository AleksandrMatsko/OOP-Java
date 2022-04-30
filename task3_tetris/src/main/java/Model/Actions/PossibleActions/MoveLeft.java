package Model.Actions.PossibleActions;

import Model.Figures.Direction;
import Model.GameStatus;
import Model.Model;

public class MoveLeft implements ActionInterface {

    @Override
    public GameStatus execute(Model model) {
        model.getGameField().moveCurrentFigureOnField(Direction.LEFT);
        return GameStatus.ACTIVE;
    }
}
