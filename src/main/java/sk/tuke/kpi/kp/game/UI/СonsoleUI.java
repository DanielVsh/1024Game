package sk.tuke.kpi.kp.game.UI;

import sk.tuke.kpi.kp.game.core.Board;
import sk.tuke.kpi.kp.game.core.Direction;
import sk.tuke.kpi.kp.game.core.GameState;
import sk.tuke.kpi.kp.game.entity.Score;
import sk.tuke.kpi.kp.game.service.ScoreService;
import sk.tuke.kpi.kp.game.service.ScoreServiceJDBC;

import java.util.Date;
import java.util.Scanner;

public class СonsoleUI {

    private Direction direction = Direction.NONE;

    private Scanner scanner = new Scanner(System.in);

    ScoreService scoreService = new ScoreServiceJDBC();

    private final Board board;

    public СonsoleUI(Board board) {
        this.board = board;
    }

    public void play() {
        printTopScore();
        while (board.getGameState() == GameState.PLAYING) {
            Direction.directionBoard(board);
            System.out.println("Score: " + Board.getScore());
            printBoard();

            processInput();
            if(direction.isMoved()) {
                board.spawnRandomNumbers();
            }

            board.checkPossibleMoves();
        }
        if (board.getGameState() == GameState.SOLVED) {
            System.out.println("YOU WON ! ! !");
            System.out.println("Score: " + Board.getScore());
            printBoard();
            scoreService.addScore(new Score(System.getProperty("user.name"), Board.getScore(), new Date()));
            printTopScore();
            System.exit(0);
        } else if (board.getGameState() == GameState.FAILED) {
            System.out.println("YOU LOSE :(");
            System.out.println("Score: " + Board.getScore());
            printBoard();
            scoreService.addScore(new Score(System.getProperty("user.name"), Board.getScore(), new Date()));
            printTopScore();
            System.exit(0);
        }
    }

    private void printBoard() {
        for (int row = 0; row < board.getSize(); row++) {
            for (int column = 0; column < board.getSize(); column++) {
                System.out.print(" " + board.getTile(row, column).getValue());
            }
            System.out.println();
        }
    }

    private void processInput() {
        System.out.println("Enter w/a/s/d or exit");
        String command = scanner.next().toUpperCase();
        if (command.equals("EXIT") || command.equals("Q")) {
            System.exit(0);
        }
        switch (command) {
            case "W" -> direction.moveTo(Direction.UP);
            case "A" -> direction.moveTo(Direction.LEFT);
            case "S" -> direction.moveTo(Direction.DOWN);
            case "D" -> direction.moveTo(Direction.RIGHT);
            default -> direction.moveTo(Direction.NONE);
        }

    }

    private void printTopScore() {
        var scoreServiceJDBC = new ScoreServiceJDBC();
        var scores = scoreServiceJDBC.getTopScore();

        System.out.println("________________________________________________");
        for (int i = 0; i < scores.size(); i++) {
            var score = scores.get(i);
            System.out.printf("%d %s %d %s\n", i + 1, score.getPlayer(), score.getScore(), score.getPlayedDate());
        }
        System.out.println("________________________________________________");

    }

}
