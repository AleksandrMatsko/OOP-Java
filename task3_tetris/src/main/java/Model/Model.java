package Model;

import Model.Figures.FigureRandomizer;
import Model.Figures.PossibleFigures.Figure;

public class Model {
    private TetrisField gameField;
    private ModelSettings settings;
    private long totalScore;
    private Figure nextFigure;

    public Model(ModelSettings modelSettings) {
        settings = modelSettings;
        gameField = new TetrisField(settings.getWidthOfField(), settings.getHeightOfField());
        totalScore = 0;
        nextFigure = (new FigureRandomizer()).getFigure();
    }

    public TetrisField getGameField() {
        return gameField;
    }


    public ModelSettings getSettings() {
        return settings;
    }

    public void changeSettings(ModelSettings settings) {
        gameField = new TetrisField(settings.getWidthOfField(), settings.getHeightOfField());
        this.settings = settings;
    }

    public void calcScore(int numRemovedLines) {
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
    }

    public long getScore() {
        return totalScore;
    }

    public Figure getNextFigure() {
        return nextFigure;
    }

    public void setNextFigure(Figure nextFigure) {
        this.nextFigure = nextFigure;
    }

}
