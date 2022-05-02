package View;

import Game.GameStatus;
import Model.Figures.PossibleFigures.Figure;
import Model.TetrisField;

public record DataForViewer(TetrisField tetrisField, int score, Figure nextFigure, GameStatus gameStatus, boolean isColored) {
}
