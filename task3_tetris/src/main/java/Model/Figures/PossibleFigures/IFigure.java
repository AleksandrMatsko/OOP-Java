package Model.Figures.PossibleFigures;

public class IFigure extends Figure {

    public IFigure() {
        super(new Block(2, 2),
                new Block[]{new Block(0, 2),
                            new Block(1, 2),
                            new Block(2, 2),
                            new Block(3, 2)},
                1, true);
    }
}
