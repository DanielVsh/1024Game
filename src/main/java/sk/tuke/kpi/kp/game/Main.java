package sk.tuke.kpi.kp.game;

import sk.tuke.kpi.kp.game.UI.ConsoleUI;
import sk.tuke.kpi.kp.game.core.Board;

public class Main {
    public static void main(String[] args) {

        Board board = new Board(4);

        ConsoleUI consoleUi = new ConsoleUI(board);
        consoleUi.play();
    }
}
