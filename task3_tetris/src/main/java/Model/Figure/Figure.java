package Model.Figure;

public class Figure {
    private final int numOfBlocks;
    private Block centerOfRotation;
    private Block[] blocks;

    public Figure(int numOfBlocks, Block centerOfRotation, Block[] blocks) {
        this.numOfBlocks = numOfBlocks;
        if (numOfBlocks != blocks.length) {
            //exception
        }
        this.centerOfRotation = centerOfRotation;
        this.blocks = blocks;
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
        Block[] rotatedBlocks = new Block[numOfBlocks];
        int i = centerOfRotation.getX();
        int j = centerOfRotation.getY();
        if (directionToRotate == Direction.NONE) {
            return this;
        }
        else if (directionToRotate == Direction.LEFT) {
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


}
