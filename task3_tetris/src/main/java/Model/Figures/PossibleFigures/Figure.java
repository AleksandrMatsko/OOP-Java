package Model.Figures.PossibleFigures;

import Model.Figures.Direction;

public class Figure {
    private final int numOfBlocks;
    private final Block centerOfRotation;
    private final Block[] blocks;
    private final int color;
    private final  boolean isRotatable;

    public Figure(int numOfBlocks, Block centerOfRotation, Block[] blocks, int color, boolean isRotatable) {
        this.numOfBlocks = numOfBlocks;
        if (numOfBlocks != blocks.length) {
            //TODO exception
        }
        this.centerOfRotation = centerOfRotation;
        this.blocks = blocks;
        this.color = color;
        this.isRotatable = isRotatable;
    }

    public int getColor() {
        return color;
    }

    public int getNumOfBlocks() {
        return numOfBlocks;
    }

    public Block[] getBlocks() {
        return blocks;
    }

    public Block getCenterOfRotation() {
        return centerOfRotation;
    }
    public boolean contains(Block block) {
        for (int i = 0; i < numOfBlocks; i++) {
            if (block.equals(blocks[i])) {
                return true;
            }
        }
        return false;
    }

    public Figure rotate(Direction directionToRotate) {
        if (!isRotatable) {
            return this;
        }
        Block[] rotatedBlocks = new Block[numOfBlocks];
        int i = centerOfRotation.getX();
        int j = centerOfRotation.getY();
        if (directionToRotate == Direction.LEFT) {
            for (int k = 0; k < numOfBlocks; k++) {
                rotatedBlocks[k] = new Block(i - (blocks[k].getY() - j), j + (blocks[k].getX() - i));
            }
        }
        else if (directionToRotate == Direction.RIGHT) {
            for (int k = 0; k < numOfBlocks; k++) {
                rotatedBlocks[k] = new Block(i + (blocks[k].getY() - j), j - (blocks[k].getX() - i));
            }
        }
        else if (directionToRotate == Direction.NONE) {
            return this;
        }
        else if (directionToRotate == Direction.DOWN) {
            //exception
        }
        return new Figure(numOfBlocks, new Block(i, j), rotatedBlocks, color, isRotatable);
    }

    public void shiftOnVal(Direction direction, int val) {
        if (direction == Direction.LEFT) {
            for (int i = 0; i < numOfBlocks; i++) {
                blocks[i].setX(blocks[i].getX() - val);
            }
            centerOfRotation.setX(centerOfRotation.getX() - val);
        }
        else if (direction == Direction.RIGHT) {
            for (int i = 0; i < numOfBlocks; i++) {
                blocks[i].setX(blocks[i].getX() + val);
            }
            centerOfRotation.setX(centerOfRotation.getX() + val);
        }
        else if (direction == Direction.DOWN) {
            for (int i = 0; i < numOfBlocks; i++) {
                blocks[i].setY(blocks[i].getY() + val);
            }
            centerOfRotation.setY(centerOfRotation.getY() + val);
        }
    }
}
