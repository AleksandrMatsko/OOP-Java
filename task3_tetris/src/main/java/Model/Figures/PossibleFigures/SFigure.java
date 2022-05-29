package Model.Figures.PossibleFigures;

public class SFigure extends Figure {

    public SFigure() {
        super(new Block(1, 1),
                new Block[]{new Block(0, 1),
                            new Block(1, 1),
                            new Block(1, 0),
                            new Block(2, 0)},
                5, true);
    }
}
