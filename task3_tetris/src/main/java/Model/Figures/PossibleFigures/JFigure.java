package Model.Figures.PossibleFigures;

public class JFigure extends Figure {

    public JFigure() {
        super(4, new Block(1, 1),
                new Block[]{new Block(0, 0),
                            new Block(0, 1),
                            new Block(1, 1),
                            new Block(2, 1)}, 2);
    }
}
