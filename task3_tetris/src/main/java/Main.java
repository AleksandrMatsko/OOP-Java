import Controller.KeyboardSettings;
import Game.Game;
import Model.Figures.FigureFactory;
import Model.Names.ActionName;
import Model.Names.FigureName;
import View.TetrisWindow;
import View.Viewer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Viewer viewer = new Viewer(game);
        SwingUtilities.invokeLater(viewer);
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
