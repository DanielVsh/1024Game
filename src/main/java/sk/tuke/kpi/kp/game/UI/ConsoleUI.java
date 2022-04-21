package sk.tuke.kpi.kp.game.UI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.tuke.kpi.kp.game.core.Board;
import sk.tuke.kpi.kp.game.core.Direction;
import sk.tuke.kpi.kp.game.core.GameState;
import sk.tuke.kpi.kp.game.entity.coments.Comment;
import sk.tuke.kpi.kp.game.entity.rating.Rating;
import sk.tuke.kpi.kp.game.entity.score.Score;
import sk.tuke.kpi.kp.game.service.comments.CommentException;
import sk.tuke.kpi.kp.game.service.comments.CommentService;
import sk.tuke.kpi.kp.game.service.rating.RatingException;
import sk.tuke.kpi.kp.game.service.rating.RatingService;
import sk.tuke.kpi.kp.game.service.score.ScoreService;

import java.util.Date;
import java.util.Scanner;


public class ConsoleUI {

  private final Direction direction = Direction.NONE;

  private final Scanner scanner = new Scanner(System.in);

  @Autowired
  private ScoreService scoreService;
  @Autowired
  private CommentService commentService;
  @Autowired
  private RatingService ratingService;

  private final Board board;

  public ConsoleUI(Board board) {
    this.board = board;
  }

  public void play() {
    printTopScore();
    while (board.getGameState() == GameState.PLAYING) {
      Direction.directionBoard(board);
      System.out.println("Score: " + Board.getScore());
      printBoard();
      processInput();
      if (direction.isMoved()) {
        board.spawnRandomNumbers();
      }
      board.checkPossibleMoves();
    }
    switch (board.getGameState()) {
      case SOLVED -> {
        System.out.println("YOU WON ! ! !");
        System.out.println("Score: " + Board.getScore());
        printBoard();
        scoreService.addScore(new Score("1024",
            System.getProperty("user.name"),
            Board.getScore(),
            new Date())
        );
        printTopScore();
        processRatingAndComment();
        System.exit(0);
      }
      case FAILED -> {
        System.out.println("YOU LOSE :(");
        System.out.println("Score: " + Board.getScore());
        printBoard();
        scoreService.addScore(new Score("1024",
            System.getProperty("user.name"),
            Board.getScore(),
            new Date())
        );
        printTopScore();
        processRatingAndComment();
        System.exit(0);
      }
    }
  }

  public void printBoard() {
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
    if (command.equals("F")) {
      Board.setGameState(GameState.FAILED);
    }
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
    var scores = scoreService.getTopScores("1024");

    System.out.println("________________________________________________");
    for (int i = 0; i < scores.size(); i++) {
      var score = scores.get(i);
      System.out.printf("%d %s %d %s\n", i + 1, score.getPlayer(), score.getScore(), score.getPlayedDate());
    }
    System.out.println("________________________________________________");
  }

  private void processRatingAndComment() {
    System.out.println("Do you want to leave a comment? (y/n)");
    if (scanner.next().equalsIgnoreCase("y")) {
      setComment("1024");
    }
    System.out.println("Do you want to rate the game? (y/n)");
    if (scanner.next().equalsIgnoreCase("y")) {
      setRating("1024");
    }
  }

  private void setRating(String game) {
    System.out.println("Enter rating 0-5");
    int rating = 0;
    String s = new Scanner(System.in).nextLine();
    if (s.matches("[0-5]")) {
      rating = Integer.parseInt(s);
    } else {
      System.out.println("Invalid rating");
      setRating(game);
    }
    ratingService.setRating(new Rating(rating, game, System.getProperty("user.name"), new Date()));
  }

  private void setComment(String game) {
    System.out.println("Enter comment ");
    String comment = new Scanner(System.in).nextLine();
    commentService.addComment(new Comment(System.getProperty("user.name"), game, comment, new Date()));
  }

}
