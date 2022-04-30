package Model.Actions.PossibleActions;

import Model.Model;
import Model.GameStatus;

public interface ActionInterface {
    public GameStatus execute(Model model);
}
