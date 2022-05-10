package Model;

import Model.Figures.FigureRandomizer;
import Model.Figures.PossibleFigures.Figure;

public class Model {
    private TetrisField tetrisField;
    private ModelSettings settings;
    private int totalScore;
    private Figure nextFigure;
    private final FigureRandomizer figureRandomizer;

    public Model(ModelSettings modelSettings) {
        settings = modelSettings;
        tetrisField = new TetrisField(settings.getWidthOfField(), settings.getHeightOfField(), settings.getSizeSpawnArea());
        totalScore = 0;
        figureRandomizer = new FigureRandomizer();
        nextFigure = figureRandomizer.getFigure();
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

    public int getScore() {
        return totalScore;
    }

    public Figure getNextFigure() {
        return nextFigure;
    }

    public void prepareNextFigure() {
        nextFigure = figureRandomizer.getFigure();
    }

}
