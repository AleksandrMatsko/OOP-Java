package Model.Figures.PossibleFigures;

public class TFigure extends Figure {

    public TFigure() {
        super(new Block(1, 1),
                new Block[]{new Block(0, 1),
                            new Block(1, 1),
                            new Block(1, 0),
                            new Block(2, 1)},
                6, true);
    }
}
