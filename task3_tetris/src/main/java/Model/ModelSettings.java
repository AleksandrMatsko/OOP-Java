package Model;

public class ModelSettings {
    private int heightOfField;
    private int widthOfField;
    private int defaultDelay;
    private int sizeSpawnArea;
    private int sizeHighScoreTable;

    public ModelSettings() {
        heightOfField = 20;
        widthOfField = 10;
        defaultDelay = 700;
        sizeSpawnArea = 3;
        sizeHighScoreTable = 15;
    }

    public int getHeightOfField() {
        return heightOfField;
    }

    public int getWidthOfField() {
        return widthOfField;
    }

    public int getDefaultDelay() {
        return defaultDelay;
    }

    public int getSizeSpawnArea() {
        return sizeSpawnArea;
    }

    public int getSizeHighScoreTable() {
        return sizeHighScoreTable;
    }
}
