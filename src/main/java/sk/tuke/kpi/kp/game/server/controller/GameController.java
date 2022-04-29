package sk.tuke.kpi.kp.game.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import sk.tuke.kpi.kp.game.core.Board;
import sk.tuke.kpi.kp.game.core.Direction;
import sk.tuke.kpi.kp.game.entity.coments.Comment;
import sk.tuke.kpi.kp.game.entity.rating.Rating;
import sk.tuke.kpi.kp.game.entity.score.Score;
import sk.tuke.kpi.kp.game.service.comments.CommentService;
import sk.tuke.kpi.kp.game.service.rating.RatingService;
import sk.tuke.kpi.kp.game.service.score.ScoreService;

import java.util.Date;
import java.util.Optional;

import static sk.tuke.kpi.kp.game.core.GameState.*;


@Controller
@RequestMapping("/game")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class GameController {

  private Board board = new Board(4);

  private String comment;
  private Integer rating;
  private final MainPageController mainPageController;

  private final Direction dir = Direction.NONE;

  @Autowired
  private ScoreService scoreService;
  @Autowired
  private RatingService ratingService;
  @Autowired
  private CommentService commentService;

  @Autowired
  public GameController(MainPageController mainPageController) {
    this.mainPageController = mainPageController;
  }

  @RequestMapping("/new")
  public String newGame() {
    board = new Board(4);
    Board.setGameState(PLAYING);
    return "redirect:/game";
  }

  @GetMapping
  public String getGame(Model model,
                        @RequestParam(required = false) String direction) {
    if (direction != null && board.getGameState() == PLAYING) {
      Direction.directionBoard(board);
      switch (direction.toUpperCase()) {
        case "W" -> dir.moveTo(Direction.UP);
        case "A" -> dir.moveTo(Direction.LEFT);
        case "S" -> dir.moveTo(Direction.DOWN);
        case "D" -> dir.moveTo(Direction.RIGHT);
        default -> dir.moveTo(Direction.NONE);
      }
      play();
    }
    model.addAttribute("comment", comment);
    model.addAttribute("rating", rating);
    return "game";
  }

  @PostMapping("/comment")
  public String addComment(@ModelAttribute("comment") String comment) {
    this.comment = comment;
    System.out.println("comment: " + comment);
    addNewComment();
    return "redirect:/game";
  }

  @PostMapping("/rating")
  public String addRating(@ModelAttribute("rating") Integer rating) {
    this.rating = rating;
    addNewRating();
    System.out.println("rating: " + rating);
    return "redirect:/game";
  }

  private void play() {
    if (board.getGameState() == PLAYING) {
      board.checkPossibleMoves();
      if (dir.isMoved()) {
        board.spawnRandomNumbers();
      }
    }
  }

  public String printGame() {
    StringBuilder sb = new StringBuilder();
    sb.append("<table>\n");
    for (int row = 0; row < board.getSize(); row++) {
      sb.append("<tr>\n");
      for (int column = 0; column < board.getSize(); column++) {
        sb.append("<td>\n");
        sb.append("<img src='/images/game/").append(getImage(row, column)).append(".png'>");
        sb.append("</td>\n");
      }
      sb.append("</tr>\n");
    }
    sb.append("</table>\n");
    return sb.toString();
  }

  public String gameProcessStatement() {
    StringBuilder sb = new StringBuilder();
    switch (board.getGameState()) {
      case FAILED -> {
        sb.append("<h3>YOU LOSE, YOUR SCORE: ").append(Board.getScore())
          .append("</h3>").append("\n");
        addNewScore();
        Board.setGameState(NONE);
      }
      case SOLVED -> {
        sb.append("<h3>YOU WON, YOUR SCORE: ").append(Board.getScore())
          .append("</h3>").append("\n");
        addNewScore();
        Board.setGameState(NONE);
      }
      case NONE -> {
        sb.append("<h3>GAME OVER</h3>").append("\n");
        sb.append("<h4> \"New Game\" to start new game</h4>").append("\n");
        sb.append("<h4>Leave a comment and rate the game</h4>").append("\n");
      }
    }
    return sb.toString();
  }

  public String printScore() {
    StringBuilder sb = new StringBuilder();
    sb.append("<table>\n");
    for (Score score : scoreService.getTopScores("1024")) {
      sb.append("<tr>\n");
      sb.append("<td>\n");
      sb.append(score.getPlayer());
      sb.append("</td>\n");
      sb.append("<td>\n");
      sb.append(score.getScore());
      sb.append("</td>\n");
      sb.append("</tr>\n");
    }
    sb.append("</table>\n");
    return sb.toString();
  }

  public String printRating() {
    if (ratingService.getAverageRating("1024") != null
      && ratingService.getAverageRating("1024") >= 0) {
      return "Rating: " + ratingService.getAverageRating("1024").toString();
    } else {
      return "No ratings yet";
    }
  }

  private void addNewRating() {
    if (mainPageController.getNickname() != null
      && mainPageController.getNickname().length() > 0) {
      ratingService.setRating(new Rating(
        rating,
        "1024",
        mainPageController.getNickname(),
        new Date()));
    }
  }

  private void addNewComment() {
    if (mainPageController.getNickname() != null
      && mainPageController.getNickname().length() > 0) {
      commentService.addComment(new Comment(
        mainPageController.getNickname(),
        "1024",
        comment,
        new Date()));
    }
  }

  private void addNewScore() {
    if (mainPageController.getNickname() != null
      && mainPageController.getNickname().length() > 0) {
      scoreService.addScore(new Score(
        "1024",
        mainPageController.getNickname(),
        Board.getScore(),
        new Date()));
    }
  }

  private String getImage(int row, int column) {
    switch (board.getTile(row, column).getValue()) {
      case 1 -> {
        return "1";
      }
      case 2 -> {
        return "2";
      }
      case 4 -> {
        return "4";
      }
      case 8 -> {
        return "8";
      }
      case 16 -> {
        return "16";
      }
      case 32 -> {
        return "32";
      }
      case 64 -> {
        return "64";
      }
      case 128 -> {
        return "128";
      }
      case 256 -> {
        return "256";
      }
      case 512 -> {
        return "512";
      }
      case 1024 -> {
        return "1024";
      }
      default -> {
        return "0";
      }
    }
  }
}