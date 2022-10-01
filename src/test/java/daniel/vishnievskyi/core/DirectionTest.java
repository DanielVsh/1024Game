package daniel.vishnievskyi.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectionTest {

  Board boardArray = new Board(4);
  Board boardResult = new Board(4);

  Tile[][] array = new Tile[4][4];
  Tile[][] result = new Tile[4][4];

  private void loopEmpty() {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        array[i][j] = new Tile(0);
        result[i][j] = new Tile(0);
      }
    }
  }


  @Test
  void moveUp() {
    loopEmpty();

    array[0][0] = new Tile(1);
    array[1][0] = new Tile(1);

    result[0][0] = new Tile(2);

    boardResult.setTiles(result);
    boardArray.setTiles(array);

    Direction direction = Direction.NONE;
    Direction.directionBoard(boardArray);
    direction.moveTo(Direction.UP);

    assertEquals(boardArray.getTile(0, 0).getValue(),
      boardResult.getTile(0, 0).getValue());

  }

  @Test
  void moveUp2() {
    loopEmpty();

    array[0][0] = new Tile(4);
    array[0][1] = new Tile(2);
    array[0][2] = new Tile(4);
    array[0][3] = new Tile(1);
    array[1][0] = new Tile(2);
    array[1][1] = new Tile(1);
    array[1][2] = new Tile(16);
    array[1][3] = new Tile(1);
    array[2][0] = new Tile(1);
    array[2][1] = new Tile(8);
    array[2][2] = new Tile(2);
    array[2][3] = new Tile(1);
    array[3][0] = new Tile(4);
    array[3][1] = new Tile(2);
    array[3][2] = new Tile(1);
    array[3][3] = new Tile(2);

    boardArray.setTiles(array);
//        ConsoleUI consoleUI = new ConsoleUI(scoreService, commentService, ratingService, boardArray);
//        consoleUI.printBoard();

    Direction direction = Direction.NONE;
    Direction.directionBoard(boardArray);
    direction.moveTo(Direction.UP);
    boardArray.checkPossibleMoves();

    assertEquals(GameState.PLAYING, boardArray.getGameState());
  }

  @Test
  void moveDown() {
    loopEmpty();

    array[0][0] = new Tile(1);
    array[2][0] = new Tile(1);

    result[3][0] = new Tile(2);

    boardResult.setTiles(result);
    boardArray.setTiles(array);

    Direction direction = Direction.NONE;
    Direction.directionBoard(boardArray);
    direction.moveTo(Direction.DOWN);


    assertEquals(boardArray.getTile(3, 0).getValue() == 2,
      boardResult.getTile(3, 0).getValue() == 2);
  }

  @Test
  void moveRight() {
    loopEmpty();

    array[0][0] = new Tile(1);
    array[0][2] = new Tile(1);

    result[0][3] = new Tile(2);

    boardResult.setTiles(result);
    boardArray.setTiles(array);

    Direction direction = Direction.NONE;
    Direction.directionBoard(boardArray);
    direction.moveTo(Direction.RIGHT);


    assertEquals(boardArray.getTile(0, 3).getValue(),
      boardResult.getTile(0, 3).getValue());
  }

  @Test
  void moveLeft() {
    loopEmpty();

    array[1][1] = new Tile(1);
    array[1][2] = new Tile(1);

    result[1][0] = new Tile(2);

    boardResult.setTiles(result);
    boardArray.setTiles(array);

    Direction direction = Direction.NONE;
    Direction.directionBoard(boardArray);
    direction.moveTo(Direction.LEFT);

    assertEquals(boardArray.getTile(1, 0).getValue(),
      boardResult.getTile(1, 0).getValue());
  }

  @Test
  void winGame() {
    loopEmpty();

    array[1][3] = new Tile(512);
    array[1][0] = new Tile(512);

    boardArray.setTiles(array);

    Direction direction = Direction.NONE;
    Direction.directionBoard(boardArray);
    direction.moveTo(Direction.LEFT);

    assertEquals(GameState.SOLVED, boardArray.getGameState());
  }

  @Test
  void loseGame() {
    loopEmpty();
    for (int i = 0; i < boardArray.getSize(); i++) {
      for (int j = 0, k = 1; j < boardArray.getSize(); j++, k *= 2) {
        array[i][j] = new Tile(k);
      }
    }

    boardArray.setTiles(array);

    Direction direction = Direction.NONE;
    Direction.directionBoard(boardArray);
    direction.moveTo(Direction.UP);

    assertEquals(GameState.FAILED, GameState.FAILED);
  }
}
