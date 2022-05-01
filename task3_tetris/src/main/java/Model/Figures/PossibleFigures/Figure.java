package Model.Figures.PossibleFigures;

import Model.Figures.Direction;

public class Figure {
    private final int numOfBlocks;
    private final Block centerOfRotation;
    private final Block[] blocks;
    //private final int colour;

    public Figure(int numOfBlocks, Block centerOfRotation, Block[] blocks) {
        this.numOfBlocks = numOfBlocks;
        if (numOfBlocks != blocks.length) {
            //exception
        }
        this.centerOfRotation = centerOfRotation;
        this.blocks = blocks;
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
        Block[] rotatedBlocks = blocks;
        int i = centerOfRotation.getX();
        int j = centerOfRotation.getY();
        if (directionToRotate == Direction.LEFT) {
            for (int k = 0; k < numOfBlocks; k++) {
                rotatedBlocks[k].setX(i - (blocks[k].getY() - j));
                rotatedBlocks[k].setY(j + (blocks[k].getX() - i));
            }
        }
        else if (directionToRotate == Direction.RIGHT) {
            for (int k = 0; k < numOfBlocks; k++) {
                rotatedBlocks[k].setX(i + (blocks[k].getY() - j));
                rotatedBlocks[k].setY(j - (blocks[k].getX() - i));
            }
        }
        else if (directionToRotate == Direction.NONE) {
            return this;
        }
        else if (directionToRotate == Direction.DOWN) {
            //exception
        }
        return new Figure(numOfBlocks, centerOfRotation, rotatedBlocks);
    }

    /*public void rotate(Direction directionToRotate) {
        if (dimension == 1) {
            return;
        }
        boolean[] rotatedShape = new boolean[dimension * dimension];
        if (directionToRotate == Direction.LEFT) {
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    rotatedShape[(dimension - 1 - i) * dimension + j] = shape[j * dimension + i];
                }
            }
        }
        else if (directionToRotate == Direction.RIGHT) {
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    rotatedShape[j * dimension + i] = shape[(dimension - 1 - i) * dimension + j];
                }
            }
        }
        shape = rotatedShape;
    }*/

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
