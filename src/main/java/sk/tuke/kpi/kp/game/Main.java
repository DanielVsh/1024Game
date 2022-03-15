package sk.tuke.kpi.kp.game;

import sk.tuke.kpi.kp.game.UI.СonsoleUI;
import sk.tuke.kpi.kp.game.core.Board;

public class Main {
    public static void main(String[] args) {

        Board board = new Board(4);

        СonsoleUI сonsoleUi = new СonsoleUI(board);
        сonsoleUi.play();
    }
}
