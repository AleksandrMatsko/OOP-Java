package Model;

public class ModelSettings {
    private int heightOfField = 20;
    private int widthOfField = 10;
    private boolean colored = true;

    public int getHeightOfField() {
        return heightOfField;
    }

    public int getWidthOfField() {
        return widthOfField;
    }

    public boolean isColored() {
        return colored;
    }

    public void setHeightOfField(int heightOfField) {
        this.heightOfField = heightOfField;
    }

    public void setWidthOfField(int widthOfField) {
        this.widthOfField = widthOfField;
    }

    public void setColored(boolean colored) {
        this.colored = colored;
    }
}
