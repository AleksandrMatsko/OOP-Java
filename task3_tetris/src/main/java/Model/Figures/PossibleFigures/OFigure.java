package Model.Figures.PossibleFigures;

public class OFigure extends Figure {

    public OFigure() {
        super(4, new Block(1, 1),
                new Block[]{new Block(0, 0),
                            new Block(0, 1),
                            new Block(1, 0),
                            new Block(1, 1)});
    }
}
