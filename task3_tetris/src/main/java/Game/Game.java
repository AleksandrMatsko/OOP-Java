package Game;

import Controller.KeyboardListener;
import Controller.KeyboardSettings;
import Model.Actions.ActionFactory;
import Model.Actions.PossibleActions.ActionInterface;
import Model.Actions.PossibleActions.MoveDown;
import Model.Actions.PossibleActions.SpawnNewFigure;
import Model.Model;
import Model.Names.ActionName;
import Model.ModelSettings;
import Model.StopWatch;
import View.DataForViewer;

public class Game {
    private Model model;
    private GameStatus status;
    private final KeyboardListener keyboardListener;
    private final StopWatch stopWatch;
    private int delay;
    private boolean isModelChanged;
    //private DataForViewer dataForViewer;


    public Game() {
        status = GameStatus.PREPARATION;
        keyboardListener = new KeyboardListener(new KeyboardSettings());
        model = new Model(new ModelSettings());
        stopWatch = new StopWatch();
        delay = model.getSettings().getDefaultDelay();
    }

    public void start() {
        if (status != GameStatus.ACTIVE) {
            status = GameStatus.ACTIVE;
            status = (new SpawnNewFigure()).execute(model, status);
            stopWatch.start(delay);
            System.err.println("Game started");
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
                    isModelChanged = true;
                }
                else {
                    delay = model.getSettings().getDefaultDelay();
                }
                if (stopWatch.isStop() && status != GameStatus.END) {
                    status = (new MoveDown()).execute(model, status);
                    System.err.println("Model changed");
                    isModelChanged = true;
                    stopWatch.start(delay);
                }
            }
        }
    }

    public void restart() {
        status = GameStatus.PREPARATION;
        model = new Model(model.getSettings());
        delay = model.getSettings().getDefaultDelay();
        start();
    }

    public KeyboardListener getKeyboardListener() {
        return keyboardListener;
    }

    public boolean isModelChanged() {
        return isModelChanged;
    }

    public void setModelChanged(boolean modelChanged) {
        isModelChanged = modelChanged;
    }

    public synchronized DataForViewer getDataForViewer() {
        while (!isModelChanged) {
            try {
                wait();
            }
            catch (InterruptedException ex) {}
        }
        isModelChanged = false;
        notify();
        return new DataForViewer(model.getTetrisField(), model.getScore(), model.getNextFigure(), status, model.getTetrisField().isColored());
    }
}
