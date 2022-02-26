import java.util.Scanner;

public class UI {

    private Direction direction = Direction.NONE;

    private Scanner scanner = new Scanner(System.in);

    private final Board board;

    public UI(Board board) {
        this.board = board;
    }

    public void play() {
        while (board.getGameState() == GameState.PLAYING) {
            Direction.directionBoard(board);
            printBoard();
            processInput();
            board.spawnRandomNumbers();
            System.out.println("Score: " + Board.getScore());
            board.isPossibleMove();
        }
        if (board.getGameState() == GameState.SOLVED) {
            printBoard();
            System.out.println("YOU WON ! ! !");
            System.out.println("Score: " + Board.getScore());
            System.exit(0);
        } else if (board.getGameState() == GameState.FAILED) {
            System.out.println("YOU LOSE :(");
            System.out.println("Score: " + Board.getScore());
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

}
