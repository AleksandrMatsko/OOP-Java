package Model.Actions.PossibleActions;

import Game.GameStatus;
import Model.Model;

public class ShowHelp implements ActionInterface {
    private GameStatus prevStatus = GameStatus.PREPARATION;

    @Override
    public GameStatus execute(Model model, GameStatus currentStatus) {
        if (currentStatus != GameStatus.SHOWING_HELP) {
            prevStatus = currentStatus;
            return GameStatus.SHOWING_HELP;
        }
        return prevStatus;
    }
}
