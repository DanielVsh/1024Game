package daniel.vishnievskyi.core;

import java.io.Serializable;
import java.util.Random;


public class Board implements Serializable {

  private final Random random = new Random();

  private static GameState gameState;

  private static int score;
  private final int size;
  private Tile[][] tiles;

  public Board(int size) {
    this.size = size;
    tiles = new Tile[size][size];
    score = 0;
    gameState = GameState.PLAYING;
    generate();
  }

  private void generate() {
    spawnEmptySpace();
    spawnRandomNumbers();
  }

  private void spawnEmptySpace() {
    for (int row = 0; row < getSize(); row++) {
      for (int col = 0; col < getSize(); col++) {
        if (getTile(row, col) == null) {
          tiles[row][col] = new Tile();
        }
      }
    }
  }

  public void spawnRandomNumbers() {
    if (countOfEmptyTiles() > 1) {
      randomLoop();
      randomLoop();
    } else if (countOfEmptyTiles() == 1) {
      randomLoop();
    }
  }

  public int countOfEmptyTiles() {
    int count = 0;
    for (int row = 0; row < getSize(); row++) {
      for (int col = 0; col < getSize(); col++) {
        if (getTile(row, col).getValue() == 0) {
          count++;
        }
      }
    }
    return count;
  }

  public void checkPossibleMoves() {
    if (countOfEmptyTiles() > 0) {
      return;
    }

    for (int row = 0; row < getSize() - 1; row++) {
      for (int col = 1; col < getSize(); col++) {
        if (getTile(row, col).numberEquals(getTile(row, col - 1))) {
          return;
        }
      }
    }

    for (int row = 0; row < getSize(); row++) {
      for (int col = 0; col < getSize() - 1; col++) {
        if (getTile(row, col).numberEquals(getTile(row, col + 1))) {
          return;
        }
      }
    }

    for (int row = 1; row < getSize(); row++) {
      for (int col = 0; col < getSize() - 1; col++) {
        if (getTile(row, col).numberEquals(getTile(row - 1, col))) {
          return;
        }
      }
    }

    for (int row = 0; row < getSize() - 1; row++) {
      for (int col = 0; col < getSize(); col++) {
        if (getTile(row, col).numberEquals(getTile(row + 1, col))) {
          return;
        }
      }
    }

    setGameState(GameState.FAILED);
  }

  private void randomLoop() {
    int row = random.nextInt(size);
    int columns = random.nextInt(size);
    if (getTile(row, columns).getValue() == 0) {
      tiles[row][columns] = new Tile(1);
    } else {
      randomLoop();
    }
  }

  public static int getScore() {
    return score;
  }

  public static void setScore(int score) {
    Board.score = score;
  }

  public Tile getTile(int row, int column) {
    return tiles[row][column];
  }

  public void setTiles(Tile[][] tiles) {
    this.tiles = tiles;
  }

  public int getSize() {
    return size;
  }

  public static void setGameState(GameState gameStateA) {
    gameState = gameStateA;
  }

  public GameState getGameState() {
    return gameState;
  }
}
