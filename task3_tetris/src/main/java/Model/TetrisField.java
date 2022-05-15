package Model;

import Model.Figures.PossibleFigures.Block;
import Model.Figures.Direction;
import Model.Figures.PossibleFigures.Figure;

public class TetrisField {
    private final int width;
    private final int height;
    private final int sizeSpawnArea;
    private final int[][] fieldData;
    private Figure currentFigure;
    private int maxLevelOfCells;
    private boolean colored;

    public TetrisField(int width, int height, int sizeSpawnArea) {
        this.width = width;
        this.height = height + sizeSpawnArea;
        this.sizeSpawnArea = sizeSpawnArea;
        fieldData = new int[this.width][this.height];
        currentFigure = null;
        maxLevelOfCells = this.height - 1;
        colored = true;
    }

    public int getSizeSpawnArea() {
        return sizeSpawnArea;
    }

    public boolean isColored() {
        return colored;
    }

    public void setColored(boolean colored) {
        this.colored = colored;
    }

    public int getCell(int x, int y) {
        return fieldData[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isContinue() {
        return maxLevelOfCells >= sizeSpawnArea;
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
                if (fieldData[j][i] == 0) {
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
        for (int i = lineIndex; i > 0; i--) {
            for (int j = 0; j < width; j++) {
                fieldData[j][i] = fieldData[j][i - 1];
            }
        }
        for (int i = 0; i < width; i++) {
            fieldData[i][0] = 0;
        }
    }

    public int removeFilledLines() {
        if (!containFilledLines()) {
            return 0;
        }
        int removedLines = 0;
        for (int i = 0; i < height; i++) {
            int counter = 0;
            for (int j = 0; j < width; j++) {
                if (fieldData[j][i] == 0) {
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

    private boolean couldBeSpawn(Figure figure) {
        for (Block block: figure.getBlocks()) {
            if (fieldData[block.getX()][block.getY()] != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean spawnFigure(Figure figure) {
        if (!isContinue()) {
            return false;
        }
        figure.shiftOnVal(Direction.RIGHT, width / 2 - 1);
        /*if (!couldBeSpawn(figure)) {
            return false;
        }*/
        currentFigure = figure;
        setCurrentFigureOnField();
        return true;
    }

    public boolean isMovableOnVal(Direction direction, int val) {
        if (currentFigure == null) {
            //TODO exception
            return false;
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
        if (!isMovableOnVal(Direction.DOWN, 1)) {
            calcMaxLevelOfCells();
            currentFigure = null;
        }
        return false;
    }

    public boolean isRotatable(Direction direction) {
        Figure rotatedFigure = currentFigure.rotate(direction);
        System.err.println("isRotatable");
        for (Block block : currentFigure.getBlocks()) {
            System.err.println(block.getX() + " " + block.getY());
        }
        System.err.println("---------");
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
        System.err.println("testing rotation");
        for (Block block : currentFigure.getBlocks()) {
            System.err.println(block.getX() + " " + block.getY());
        }
        System.err.println("---------");
        if (isRotatable(direction)) {
            for (Block block : currentFigure.getBlocks()) {
                System.err.println(block.getX() + " " + block.getY());
            }
            System.err.println("---------");
            deleteCurrentFigureOnField();
            currentFigure = currentFigure.rotate(direction);
            for (Block block : currentFigure.getBlocks()) {
                System.err.println(block.getX() + " " + block.getY());
            }
            setCurrentFigureOnField();
            return true;
        }
        return false;
    }

    private void setCurrentFigureOnField() {
        for (Block block : currentFigure.getBlocks()) {
            if (block.getX() >= width) {
                System.err.println("=======");
                for (Block damagedBlock : currentFigure.getBlocks()) {
                    System.err.println(damagedBlock.getX() + " " + damagedBlock.getY());
                }
            }
            fieldData[block.getX()][block.getY()] = currentFigure.getColor();
        }
    }

    private void deleteCurrentFigureOnField() {
        for (Block block : currentFigure.getBlocks()) {
            fieldData[block.getX()][block.getY()] = 0;
        }
    }
}
