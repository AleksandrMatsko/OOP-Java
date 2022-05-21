package Game;

import Controller.KeyboardListener;
import Controller.KeyboardSettings;
import Exceptions.InvalidUserNameException;
import Model.Actions.ActionFactory;
import Model.Actions.PossibleActions.ActionInterface;
import Model.Actions.PossibleActions.MoveDown;
import Model.Actions.PossibleActions.SpawnNewFigure;
import Model.Model;
import Model.Names.ActionName;
import Model.ModelSettings;
import Model.Names.UserName;
import Model.StopWatch;
import View.DataForViewer;
import View.Viewer;
import View.ViewerSettings;

import javax.swing.*;

public class Game {
    private final Model model;
    private GameStatus status;
    private final KeyboardListener keyboardListener;
    private final Viewer viewer;


    public Game() {
        status = GameStatus.SHOWING_HELP;
        keyboardListener = new KeyboardListener(new KeyboardSettings());
        model = new Model(new ModelSettings());
        viewer = new Viewer(new ViewerSettings(model.getTetrisField().isColored(),
                model.getSettings().getWidthOfField(), model.getSettings().getHeightOfField()),
                keyboardListener, model.getSettings());
    }

    private void showImage() {
        viewer.setDataForViewer(new DataForViewer(model.getTetrisField(),
                model.getScore(),
                model.getNextFigure(),
                status,
                model.getTetrisField().isColored(),
                model.getHighScoreTableAsString()));
        SwingUtilities.invokeLater(viewer);
    }

    public void start() {
        showImage();
        while (status != GameStatus.EXIT) {
            if (status == GameStatus.PREPARATION) {
                status = GameStatus.ACTIVE;
                status = (new SpawnNewFigure()).execute(model, status);
                model.getStopWatch().start(model.getDelay());
                showImage();
            }
            ActionName currentActionName = keyboardListener.getCurrentAction();
            if (currentActionName != null) {
                ActionInterface action = ActionFactory.getInstance().getAction(currentActionName);
                status = action.execute(model, status);
                keyboardListener.dropCurrentAction();
                showImage();
            }
            if (model.getStopWatch().isStop() && status == GameStatus.ACTIVE) {
                status = (new MoveDown()).execute(model, status);
                model.getStopWatch().start(model.getDelay());
                showImage();
            }
        }
        showImage();
    }

}
