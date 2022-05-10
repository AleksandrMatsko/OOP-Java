import Game.Game;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
        //PrevViewer viewer = new PrevViewer(game);
        //SwingUtilities.invokeLater(viewer);
        /*TetrisWindow tetrisWindow = new TetrisWindow();
        tetrisWindow.showTetrisField(game.getDataForViewer());*/

        /*Viewer viewer = new Viewer();
        viewer.setVisible(true);*/
        /*KeyboardSettings settings = new KeyboardSettings();
        for (Map.Entry<Integer, ActionName> entry: settings.getActionsProvidedOnKey().entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().getName());
        }

        ArrayList<FigureName> possibleFigureNames = FigureFactory.getInstance().getPossibleFigureNames();
        for (FigureName figureName: possibleFigureNames) {
            System.out.println(figureName.getName() + " " + FigureFactory.getInstance().getFigure(figureName).getColor());
        }*/
    }
}
