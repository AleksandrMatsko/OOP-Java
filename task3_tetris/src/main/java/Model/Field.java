package Model;

import Model.Figures.PossibleFigures.Block;
import Model.Figures.Direction;
import Model.Figures.PossibleFigures.Figure;

public class Field {
    private final int width;
    private final int height;
    private final int[][] fieldData;
    private Figure currentFigure;
    private int maxLevelOfCells;

    public Field(int width, int height) {
        this.width = width;
        this.height = height + 4;
        fieldData = new int[width][height];
        currentFigure = null;
        maxLevelOfCells = 0;
    }

    public boolean isContinue() {
        return maxLevelOfCells < height - 4;
    }

    private void calcMaxLevelOfCells() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (fieldData[j][i] > 0) {
                    maxLevelOfCells = i;
                    return;
                }
            }
        }
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
        calcMaxLevelOfCells();
        return removedLines;
    }

    public boolean spawnFigure(Figure figure) {
        if (maxLevelOfCells >= height - 4) {
            return false;
        }
        figure.shiftOnVal(Direction.RIGHT, width / 2 - 1);
        currentFigure = figure;
        setCurrentFigureOnField();
        return true;
    }

    public boolean isMovableOnVal(Direction direction, int val) {
        if (currentFigure == null) {
            //exception
        }
        Block[] blocks = currentFigure.getBlocks();
        if (direction == Direction.DOWN) {
            for (int i = 0; i < currentFigure.getNumOfBlocks(); i++) {
                Block checkingBlock = new Block(blocks[i].getX(), blocks[i].getY() + val);
                if (checkingBlock.getY() >= height) {
                    return false;
                }
                if (fieldData[checkingBlock.getX()][checkingBlock.getY()] > 0 && !currentFigure.contains(checkingBlock)) {
                    return false;
                }
            }
        }
        else if (direction == Direction.LEFT) {
            for (int i = 0; i < currentFigure.getNumOfBlocks(); i++) {
                Block checkingBlock = new Block(blocks[i].getX() - val, blocks[i].getY());
                if (checkingBlock.getX() < 0) {
                    return false;
                }
                if (fieldData[checkingBlock.getX()][checkingBlock.getY()] > 0 && !currentFigure.contains(checkingBlock)) {
                    return false;
                }
            }
        }
        else if (direction == Direction.RIGHT) {
            for (int i = 0; i < currentFigure.getNumOfBlocks(); i++) {
                Block checkingBlock = new Block(blocks[i].getX() + val, blocks[i].getY());
                if (checkingBlock.getX() >= width) {
                    return false;
                }
                if (fieldData[checkingBlock.getX()][checkingBlock.getY()] > 0 && !currentFigure.contains(checkingBlock)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean moveCurrentFigureOnField(Direction direction) {
        if (isMovableOnVal(direction, 1)) {
            deleteCurrentFigureOnField();
            currentFigure.shiftOnVal(direction, 1);
            setCurrentFigureOnField();
            return true;
        }
        calcMaxLevelOfCells();
        currentFigure = null;
        return false;
    }

    public boolean isRotatable(Direction direction) {
        Figure rotatedFigure = currentFigure.rotate(direction);
        Block[] rotatedBlocks = rotatedFigure.getBlocks();
        for (int i = 0; i < rotatedFigure.getNumOfBlocks(); i++) {
            if (rotatedBlocks[i].getX() < 0 || rotatedBlocks[i].getX() >= width ||
                    rotatedBlocks[i].getY() >= height ||
                    (!currentFigure.contains(rotatedBlocks[i]) &&
                            fieldData[rotatedBlocks[i].getX()][rotatedBlocks[i].getY()] > 0)) {
                return false;
            }
        }
        return true;
    }

    public boolean rotateCurrentFigureOnField(Direction direction) {
        if (isRotatable(direction)) {
            deleteCurrentFigureOnField();
            currentFigure = currentFigure.rotate(direction);
            setCurrentFigureOnField();
            return true;
        }
        return false;
    }

    private void setCurrentFigureOnField() {
        Block[] blocks = currentFigure.getBlocks();
        for (int i = 0; i < currentFigure.getNumOfBlocks(); i++) {
            fieldData[blocks[i].getX()][blocks[i].getY()] = 1;
        }
    }

    private void deleteCurrentFigureOnField() {
        Block[] blocks = currentFigure.getBlocks();
        for (int i = 0; i < currentFigure.getNumOfBlocks(); i++) {
            fieldData[blocks[i].getX()][blocks[i].getY()] = 0;
        }
    }
}
