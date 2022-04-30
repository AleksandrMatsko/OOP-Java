package Model.Actions.PossibleActions;

import Model.GameStatus;
import Model.Model;

public class EndGame implements ActionInterface {
    @Override
    public GameStatus execute(Model model) {
        return GameStatus.END;
    }
}
