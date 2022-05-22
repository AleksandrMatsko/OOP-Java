package Model.Actions.PossibleActions;

import Game.GameStatus;
import Model.Model;

public class Restart implements ActionInterface {
    @Override
    public GameStatus execute(Model model, GameStatus currentStatus) {
        model.reset();
        return GameStatus.PREPARATION;
    }
}
