package Model;

public class Model {
    private Field gameField;
    private ModelSettings settings;
    private long totalScore;

    public Model(ModelSettings modelSettings) {
        settings = modelSettings;
        gameField = new Field(settings.getWidthOfField(), settings.getHeightOfField());
        totalScore = 0;
    }

    public Field getGameField() {
        return gameField;
    }


    public void changeSettings(ModelSettings settings) {
        gameField = new Field(settings.getWidthOfField(), settings.getHeightOfField());
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
}
