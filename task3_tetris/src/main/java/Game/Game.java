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
import View.Viewer;
import View.ViewerSettings;

import javax.swing.*;

public class Game {
    private Model model;
    private GameStatus status;
    private final KeyboardListener keyboardListener;
    private final StopWatch stopWatch;
    private int delay;
    private final Viewer viewer;


    public Game() {
        status = GameStatus.PREPARATION;
        keyboardListener = new KeyboardListener(new KeyboardSettings());
        model = new Model(new ModelSettings());
        stopWatch = new StopWatch();
        delay = model.getSettings().getDefaultDelay();
        viewer = new Viewer(new ViewerSettings(model.getTetrisField().isColored()), keyboardListener, model.getSettings());
    }

    private void showImage() {
        viewer.setDataForViewer(new DataForViewer(model.getTetrisField(), model.getScore(), model.getNextFigure(), status, model.getTetrisField().isColored()));
        SwingUtilities.invokeLater(viewer);
    }

    public void start() {
        if (status != GameStatus.ACTIVE) {
            status = GameStatus.ACTIVE;
            status = (new SpawnNewFigure()).execute(model, status);
            stopWatch.start(delay);
            System.err.println("Game started");
            showImage();
        }
        else {
            //TODO exception
        }
        while (status != GameStatus.END) {
            ActionName currentActionName = keyboardListener.getCurrentAction();
            if (currentActionName != null) {
                ActionInterface action = ActionFactory.getInstance().getAction(currentActionName);
                status = action.execute(model, status);
                keyboardListener.dropCurrentAction();
                System.err.println("game action = " + currentActionName.getName());
                showImage();
            }
            else {
                delay = model.getSettings().getDefaultDelay();
            }
            if (status == GameStatus.END) {
                break;
            }
            if (stopWatch.isStop()) {
                status = (new MoveDown()).execute(model, status);
                System.err.println("Model changed");
                showImage();
                stopWatch.start(delay);
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

}
