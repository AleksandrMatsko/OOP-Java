package Model.Figures.PossibleFigures;

public class LFigure extends Figure {

    public LFigure() {
        super(new Block(1, 1),
                new Block[]{new Block(0, 1),
                            new Block(1, 1),
                            new Block(2, 0),
                            new Block(2, 1)},
                3, true);
    }
}
