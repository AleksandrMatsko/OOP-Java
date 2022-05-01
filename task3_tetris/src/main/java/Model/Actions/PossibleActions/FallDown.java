package Model.Actions.PossibleActions;

import Game.GameStatus;
import Model.Model;

public class FallDown implements ActionInterface {
    @Override
    public GameStatus execute(Model model, GameStatus currentStatus) {
        if (currentStatus != GameStatus.ACTIVE) {
            //TODO exception
        }
        GameStatus status = (new MoveDown()).execute(model, currentStatus);
        if (status != GameStatus.ACTIVE) {
            return status;
        }
        return (new MoveDown()).execute(model, currentStatus);
    }
}
