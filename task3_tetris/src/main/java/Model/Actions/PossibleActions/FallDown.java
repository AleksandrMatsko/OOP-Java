package Model.Actions.PossibleActions;

import Game.GameStatus;
import Model.Model;

public class FallDown implements ActionInterface {
    @Override
    public GameStatus execute(Model model, GameStatus currentStatus) {
        if (currentStatus != GameStatus.ACTIVE) {
            //TODO exception
            return currentStatus;
        }
        return (new MoveDown()).execute(model, currentStatus);
    }
}
