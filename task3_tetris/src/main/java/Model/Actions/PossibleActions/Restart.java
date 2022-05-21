package Model.Actions.PossibleActions;

import Exceptions.InvalidUserNameException;
import Game.GameStatus;
import Model.Model;
import Model.Names.UserName;

public class Restart implements ActionInterface {
    @Override
    public GameStatus execute(Model model, GameStatus currentStatus) {
        if (currentStatus == GameStatus.ACTIVE) {
            currentStatus = (new SetPause()).execute(model, currentStatus);
        }
        UserName userName = null;
        try {
            //TODO asking userName
            userName = new UserName("Morlandar");
        }
        catch (InvalidUserNameException ex) {
            //TODO normal reaction
        }
        model.reset();
        return GameStatus.PREPARATION;
    }
}
