package sk.tuke.kpi.kp.game.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TileTest {

  Board boardArray = new Board(4);

  Tile[][] array = new Tile[4][4];

  private void loopEmpty() {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        array[i][j] = new Tile(0);
      }
    }
  }

  @Test
  void mergeNumbers() {
    loopEmpty();

    array[1][0] = new Tile(64);
    array[1][2] = new Tile(64);

    boardArray.setTiles(array);

    boardArray.getTile(1, 0).mergeNumbers(boardArray.getTile(1, 2), false);

    assertEquals(128, boardArray.getTile(1, 0).getValue());
  }

  @Test
  void numberEquals() {
    loopEmpty();

    array[1][0] = new Tile(64);
    array[1][2] = new Tile(64);

    boardArray.setTiles(array);

    assertTrue(boardArray.getTile(1, 2).numberEquals(boardArray.getTile(1, 0)));
  }

  @Test
  void deleteNumber() {
    loopEmpty();

    array[1][0] = new Tile(64);

    boardArray.setTiles(array);

    boardArray.getTile(1, 0).deleteNumber();

    assertEquals(0, boardArray.getTile(1, 0).getValue());
  }
}