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
        model.getSettings().setDefaultDelay(model.getSettings().getDefaultDelay() / 2);
        return GameStatus.ACTIVE;
    }
}
