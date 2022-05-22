package Model.Figures.PossibleFigures;

import Model.Figures.Direction;

public class Figure {
    private final Block centerOfRotation;
    private final Block[] blocks;
    private final int color;
    private final  boolean isRotatable;

    public Figure(Block centerOfRotation, Block[] blocks, int color, boolean isRotatable) {
        this.centerOfRotation = centerOfRotation;
        this.blocks = blocks;
        this.color = color;
        this.isRotatable = isRotatable;
    }

    public int getColor() {
        return color;
    }

    public int getNumOfBlocks() {
        return blocks.length;
    }

    public Block[] getBlocks() {
        return blocks;
    }

    public Block getCenterOfRotation() {
        return centerOfRotation;
    }
    public boolean contains(Block block) {
        for (Block figureBlock : blocks) {
            if (block.equals(figureBlock)) {
                return true;
            }
        }
        return false;
    }

    public Figure rotate(Direction directionToRotate) {
        if (!isRotatable) {
            return this;
        }
        Block[] rotatedBlocks = new Block[blocks.length];
        int i = centerOfRotation.getX();
        int j = centerOfRotation.getY();
        if (directionToRotate == Direction.LEFT) {
            for (int k = 0; k < blocks.length; k++) {
                rotatedBlocks[k] = new Block(i - (blocks[k].getY() - j), j + (blocks[k].getX() - i));
            }
        }
        else if (directionToRotate == Direction.RIGHT) {
            for (int k = 0; k < blocks.length; k++) {
                rotatedBlocks[k] = new Block(i + (blocks[k].getY() - j), j - (blocks[k].getX() - i));
            }
        }
        else {
            return this;
        }
        return new Figure(new Block(i, j), rotatedBlocks, color, isRotatable);
    }

    public void shiftOnVal(Direction direction, int val) {
        if (direction == Direction.LEFT) {
            for (int i = 0; i < blocks.length; i++) {
                blocks[i].setX(blocks[i].getX() - val);
            }
            centerOfRotation.setX(centerOfRotation.getX() - val);
        }
        else if (direction == Direction.RIGHT) {
            for (int i = 0; i < blocks.length; i++) {
                blocks[i].setX(blocks[i].getX() + val);
            }
            centerOfRotation.setX(centerOfRotation.getX() + val);
        }
        else if (direction == Direction.DOWN) {
            for (int i = 0; i < blocks.length; i++) {
                blocks[i].setY(blocks[i].getY() + val);
            }
            centerOfRotation.setY(centerOfRotation.getY() + val);
        }
    }
}
