package Model;

public class Field {
    private final int width;
    private final int height;
    private int[][] fieldData;

    public Field(int width, int height) {
        this.width = width;
        this.height = height + 4;
        fieldData = new int[width][height];
    }

    public boolean containFilledLines() {
        for (int i = 0; i < height; i++) {
            int counter = 0;
            for (int j = 0; j < width; j++) {
                if (fieldData[i][j] == 0) {
                    break;
                }
                else {
                    counter += 1;
                }
            }
            if (counter == width) {
                return true;
            }
        }
        return false;
    }

    private void shiftDown(int lineIndex) {
        for (int i = lineIndex; i > 0; i++) {
            if (width >= 0) {
                System.arraycopy(fieldData[i - 1], 0, fieldData[i], 0, width);
            }
        }
        for (int i = 0; i < width; i++) {
            fieldData[0][i] = 0;
        }
    }

    public int removeFilledLines() {
        int removedLines = 0;
        for (int i = 0; i < height; i++) {
            int counter = 0;
            for (int j = 0; j < width; j++) {
                if (fieldData[i][j] == 0) {
                    break;
                }
                else {
                    counter += 1;
                }
            }
            if (counter == width) {
                shiftDown(i);
                removedLines += 1;
            }
        }
        return removedLines;
    }
}
