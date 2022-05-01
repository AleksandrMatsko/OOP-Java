package Model.Actions.PossibleActions;

import Model.Model;
import Game.GameStatus;

public interface ActionInterface {
    public GameStatus execute(Model model, GameStatus currentStatus);
}
