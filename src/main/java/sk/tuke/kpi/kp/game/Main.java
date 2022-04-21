package sk.tuke.kpi.kp.game;

import sk.tuke.kpi.kp.game.UI.ConsoleUI;
import sk.tuke.kpi.kp.game.core.Board;
import sk.tuke.kpi.kp.game.service.comments.CommentException;
import sk.tuke.kpi.kp.game.service.rating.RatingException;

public class Main {
    public static void main(String[] args){

        var board = new Board(4);

        ConsoleUI consoleUi = new ConsoleUI(board);
        consoleUi.play();
    }
}
