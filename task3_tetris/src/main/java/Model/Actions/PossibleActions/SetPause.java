package Model.Actions.PossibleActions;

import Game.GameStatus;
import Model.Model;

public class SetPause implements ActionInterface {
    @Override
    public GameStatus execute(Model model, GameStatus currentStatus) {
        if (currentStatus == GameStatus.ACTIVE) {
            return GameStatus.PAUSE;
        }
        else if (currentStatus == GameStatus.PAUSE) {
            return GameStatus.ACTIVE;
        }
        model.addNewRecordToTable();
        //TODO exception
        return GameStatus.END;
    }
}
