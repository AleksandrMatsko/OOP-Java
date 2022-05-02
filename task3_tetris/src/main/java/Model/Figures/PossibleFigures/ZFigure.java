package Model.Figures.PossibleFigures;

public class ZFigure extends Figure {

    public ZFigure(int color) {
        super(4, new Block(1, 1),
                new Block[]{new Block(0, 0),
                            new Block(1, 0),
                            new Block(1, 1),
                            new Block(2, 1)}, color);
    }
}
