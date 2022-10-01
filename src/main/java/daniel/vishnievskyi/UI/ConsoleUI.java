package daniel.vishnievskyi.UI;

import daniel.vishnievskyi.core.Direction;
import daniel.vishnievskyi.entity.coments.Comment;
import daniel.vishnievskyi.entity.rating.Rating;
import daniel.vishnievskyi.entity.score.Score;
import daniel.vishnievskyi.server.service.comments.CommentServiceRestClient;
import daniel.vishnievskyi.server.service.score.ScoreServiceRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import daniel.vishnievskyi.core.Board;
import daniel.vishnievskyi.core.GameState;
import daniel.vishnievskyi.server.service.rating.RatingServiceRestClient;

import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class ConsoleUI {

  private final Direction direction = Direction.NONE;

  private final Scanner scanner = new Scanner(System.in);

  private final ScoreServiceRestClient scoreService;
  private final CommentServiceRestClient commentService;
  private final RatingServiceRestClient ratingService;

  private final Board board;

  @Autowired
  public ConsoleUI(ScoreServiceRestClient scoreService,
                   CommentServiceRestClient commentService,
                   RatingServiceRestClient ratingService,
                   Board board) {
    this.scoreService = scoreService;
    this.commentService = commentService;
    this.ratingService = ratingService;
    this.board = board;
  }

  public void play() {
    printTopScore();
    printComments();
    printRating();
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
      System.out.printf("%d %s %d %s\n",
        i + 1, score.getPlayer(), score.getScore(), score.getPlayedDate());
    }
    System.out.println("________________________________________________");
  }

  private void printComments() {
    System.out.println("Comments:");
    List<Comment> comments = commentService.getComments("1024");
    for (Comment comment : comments) {
      System.out.println(comment.getComment());
    }
    System.out.println("------------------------------");
  }

  private void printRating() {
    System.out.println("Rating: " + ratingService.getAverageRating("1024"));
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
    ratingService.setRating(new Rating(
      rating,
      game,
      System.getProperty("user.name"),
      new Date()));
  }

  private void setComment(String game) {
    System.out.println("Enter comment ");
    String comment = new Scanner(System.in).nextLine();
    commentService.addComment(new Comment(System.getProperty("user.name"), game, comment, new Date()));
  }

}
