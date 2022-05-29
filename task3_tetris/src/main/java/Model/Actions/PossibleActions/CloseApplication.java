package Model.Actions.PossibleActions;

import Game.GameStatus;
import Model.Model;

public class CloseApplication implements ActionInterface {

    @Override
    public GameStatus execute(Model model, GameStatus currentStatus) {
        model.addNewRecordToTable();
        return GameStatus.EXIT;
    }
}
