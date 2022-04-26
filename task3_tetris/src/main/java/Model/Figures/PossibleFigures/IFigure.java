package Model.Figures.PossibleFigures;

public class IFigure extends Figure {

    public IFigure() {
        super(4, new Block(3, 3),
                new Block[]{new Block(0, 1),
                            new Block(1, 1),
                            new Block(2, 1),
                            new Block(3, 1)});
    }
}
