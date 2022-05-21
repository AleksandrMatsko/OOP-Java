package Model.Actions.PossibleActions;

import Game.GameStatus;
import Model.Model;

public class ShowHighScoreTable implements ActionInterface {
    private GameStatus prevStatus = null;

    @Override
    public GameStatus execute(Model model, GameStatus currentStatus) {
        if (currentStatus != GameStatus.SHOWING_HIGH_SCORES) {
            prevStatus = currentStatus;
            return GameStatus.SHOWING_HIGH_SCORES;
        }
        return prevStatus;
    }
}
