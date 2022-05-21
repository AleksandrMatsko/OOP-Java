package Model;

import Exceptions.InvalidUserNameException;
import Model.Figures.FigureRandomizer;
import Model.Figures.PossibleFigures.Figure;
import Model.Names.UserName;
import Model.ScoreTable.HighScoreTable;

import java.lang.reflect.InvocationTargetException;

public class Model {
    private TetrisField tetrisField;
    private ModelSettings settings;
    private int totalScore;
    private Figure nextFigure;
    private final FigureRandomizer figureRandomizer;
    private int delay;
    private final HighScoreTable highScoreTable;
    private UserName userName;
    private StopWatch stopWatch;

    public Model(ModelSettings modelSettings) {
        settings = modelSettings;
        //TODO ask userName
        try {
            this.userName = new UserName("Morlandar");
        }
        catch (InvalidUserNameException ex) {
            //TODO reaction
        }
        tetrisField = new TetrisField(settings.getWidthOfField(), settings.getHeightOfField(), settings.getSizeSpawnArea());
        totalScore = 0;
        figureRandomizer = new FigureRandomizer();
        delay = modelSettings.getDefaultDelay();
        highScoreTable = new HighScoreTable(modelSettings.getSizeHighScoreTable());
        stopWatch = new StopWatch();
        try {
            nextFigure = figureRandomizer.getFigure();
        }
        catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException ex) {
            //TODO normal reaction
            ex.printStackTrace();
        }
    }

    public void reset() {
        tetrisField = new TetrisField(settings.getWidthOfField(), settings.getHeightOfField(), settings.getSizeSpawnArea());
        totalScore = 0;
        stopWatch = new StopWatch();
        delay = settings.getDefaultDelay();
        try {
            nextFigure = figureRandomizer.getFigure();
        }
        catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException ex) {
            //TODO normal reaction
            ex.printStackTrace();
        }
    }

    public TetrisField getTetrisField() {
        return tetrisField;
    }


    public ModelSettings getSettings() {
        return settings;
    }

    public void changeSettings(ModelSettings settings) {
        tetrisField = new TetrisField(settings.getWidthOfField(), settings.getHeightOfField(), settings.getSizeSpawnArea());
        this.settings = settings;
    }

    public void calcScore(int numRemovedLines) {
        int prevScore = totalScore;
        if (numRemovedLines == 1) {
            totalScore += 100;
        }
        else if (numRemovedLines == 2) {
            totalScore += 300;
        }
        else if (numRemovedLines == 3) {
            totalScore += 700;
        }
        else if (numRemovedLines == 4) {
            totalScore += 1500;
        }
        if (prevScore / 2000 < totalScore / 2000) {
            delay = (int) (delay * 0.9);
        }
    }

    public int getScore() {
        return totalScore;
    }

    public Figure getNextFigure() {
        return nextFigure;
    }

    public void prepareNextFigure() {
        try {
            nextFigure = figureRandomizer.getFigure();
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException ex) {
            //TODO normal reaction
            ex.printStackTrace();
        }
    }

    public int getDelay() {
        return delay;
    }

    public void addNewRecordToTable() {
        highScoreTable.add(userName, totalScore);
    }

    public StopWatch getStopWatch() {
        return stopWatch;
    }

    public String getHighScoreTableAsString() {
        return highScoreTable.getTableAsString();
    }
}
