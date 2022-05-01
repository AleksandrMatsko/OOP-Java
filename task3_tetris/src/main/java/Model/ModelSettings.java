package Model;

public class ModelSettings {
    private int heightOfField;
    private int widthOfField;

    public ModelSettings() {
        heightOfField = 20;
        widthOfField = 10;
    }

    public ModelSettings(int widthOfField, int heightOfField) {
        this.widthOfField = widthOfField;
        this.heightOfField = heightOfField;
    }

    public int getHeightOfField() {
        return heightOfField;
    }

    public int getWidthOfField() {
        return widthOfField;
    }


    public void setHeightOfField(int heightOfField) {
        this.heightOfField = heightOfField;
    }

    public void setWidthOfField(int widthOfField) {
        this.widthOfField = widthOfField;
    }

}
