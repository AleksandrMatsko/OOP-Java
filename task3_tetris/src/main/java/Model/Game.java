package Model;

import Controller.KeyboardListener;
import Controller.KeyboardSettings;
import Model.Actions.ActionFactory;
import Model.Actions.PossibleActions.ActionInterface;
import Model.Names.ActionName;

public class Game {
    private Model model;
    private GameStatus status;
    private KeyboardListener keyboardListener;
    private KeyboardSettings keyboardSettings;
    private ModelSettings modelSettings;

    public Game() {
        status = GameStatus.PREPARATION;
        keyboardSettings = new KeyboardSettings();
        keyboardListener = new KeyboardListener(keyboardSettings);
        modelSettings = new ModelSettings();
        model = new Model(modelSettings);
    }

    public void changeSettings() {

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
                    status = action.execute(model);
                }
            }
        }
    }


}
