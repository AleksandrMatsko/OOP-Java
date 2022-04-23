package Model.Figure;

import java.util.Objects;

public class Block {
    private int x;
    private int y;

    public Block() {
        x = -1;
        y = -1;
    }

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setCoords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        if (x < 0 && y < 0) {
            //exception
        }
        return x;
    }

    public int getY() {
        if (x < 0 && y < 0) {
            //exception
        }
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Block block = (Block) o;
        return x == block.x && y == block.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
