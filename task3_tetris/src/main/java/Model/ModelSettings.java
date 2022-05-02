package Model;

public class ModelSettings {
    private int heightOfField;
    private int widthOfField;
    private int defaultDelay;

    public ModelSettings() {
        heightOfField = 20;
        widthOfField = 10;
        defaultDelay = 700;
    }

    public ModelSettings(int widthOfField, int heightOfField, int defaultDelay) {
        this.widthOfField = widthOfField;
        this.heightOfField = heightOfField;
        this.defaultDelay = defaultDelay;
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

    public void setDefaultDelay(int defaultDelay) {
        this.defaultDelay = defaultDelay;
    }

    public void setHeightOfField(int heightOfField) {
        this.heightOfField = heightOfField;
    }

    public void setWidthOfField(int widthOfField) {
        this.widthOfField = widthOfField;
    }

}
