package Game;

import Controller.KeyboardListener;
import Controller.KeyboardSettings;
import Model.Actions.ActionFactory;
import Model.Actions.PossibleActions.ActionInterface;
import Model.Model;
import Model.Names.ActionName;
import Model.ModelSettings;

public class Game {
    private Model model;
    private GameStatus status;
    private final KeyboardListener keyboardListener;

    public Game() {
        status = GameStatus.PREPARATION;
        keyboardListener = new KeyboardListener(new KeyboardSettings());
        model = new Model(new ModelSettings());
    }

    //TODO timer

    public void start() {
        if (status != GameStatus.ACTIVE) {
            status = GameStatus.ACTIVE;
        }
        else {
            //TODO exception
        }
        while (status != GameStatus.END) {
            if (status == GameStatus.ACTIVE) {
                ActionName currentActionName = keyboardListener.getCurrentAction();
                if (currentActionName != null) {
                    ActionInterface action = ActionFactory.getInstance().getAction(currentActionName);
                    status = action.execute(model, status);
                }
            }
        }
    }

    public void restart() {
        status = GameStatus.PREPARATION;
        model = new Model(model.getSettings());
        start();
    }


}
