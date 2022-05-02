package View;


import Game.Game;
import Game.GameStatus;

public class Viewer implements Runnable {
    private TetrisWindow tetrisWindow;
    private Menu menu;
    //private DataForViewer dataForViewer;
    private final Game game;

    public Viewer(Game game) {
        tetrisWindow = new TetrisWindow(game.getKeyboardListener());
        menu = new Menu();
        this.game = game;
        game.start();
        //this.dataForViewer = dataForViewer;
    }

    @Override
    public void run() {
        if (game.getDataForViewer().gameStatus() != GameStatus.ACTIVE) {

        }
        else {
            tetrisWindow.showTetrisField(game.getDataForViewer());
            System.err.println("Drawing tetris field");
        }

    }
}
