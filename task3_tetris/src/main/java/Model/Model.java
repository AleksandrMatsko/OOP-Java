package Model;

public class Model {
    private GameField gameField;
    private ModelSettings settings;

    public Model() {
        settings = new ModelSettings();
        gameField = new GameField(settings.getWidthOfField(), settings.getHeightOfField());
    }

    public GameField getGameField() {
        return gameField;
    }

    void changeSettings(ModelSettings settings) {
        gameField = new GameField(settings.getWidthOfField(), settings.getHeightOfField());
        this.settings = settings;
    }
}
