package Model.Actions.PossibleActions;

import Game.GameStatus;
import Model.Model;

public class EndGame implements ActionInterface {
    @Override
    public GameStatus execute(Model model, GameStatus currentStatus) {
        return GameStatus.END;
    }
}
