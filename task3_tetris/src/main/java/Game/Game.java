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
    private final Viewer viewer;


    public Game() {
        status = GameStatus.PREPARATION;
        keyboardListener = new KeyboardListener(new KeyboardSettings());
        model = new Model(new ModelSettings());
        stopWatch = new StopWatch();
        viewer = new Viewer(new ViewerSettings(model.getTetrisField().isColored(),
                model.getSettings().getWidthOfField(), model.getSettings().getHeightOfField()),
                keyboardListener, model.getSettings());
    }

    private void showImage() {
        viewer.setDataForViewer(new DataForViewer(model.getTetrisField(), model.getScore(), model.getNextFigure(), status, model.getTetrisField().isColored()));
        SwingUtilities.invokeLater(viewer);
    }

    public void active() {
        status = GameStatus.PREPARATION;
        showImage();
    }

    public void start() {
        if (status != GameStatus.ACTIVE) {
            status = GameStatus.ACTIVE;
            status = (new SpawnNewFigure()).execute(model, status);
            stopWatch.start(model.getDelay());
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
                showImage();
            }
            if (status == GameStatus.END) {
                break;
            }
            if (stopWatch.isStop()) {
                status = (new MoveDown()).execute(model, status);
                stopWatch.start(model.getDelay());
                showImage();
            }
        }
    }

    public void restart() {
        status = GameStatus.PREPARATION;
        model = new Model(model.getSettings());
        start();
    }

    public KeyboardListener getKeyboardListener() {
        return keyboardListener;
    }

}
