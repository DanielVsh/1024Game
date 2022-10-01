package daniel.vishnievskyi.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {

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
  void spawnRandomNumbers() {
    loopEmpty();

    boardArray.setTiles(array);

    boardArray.spawnRandomNumbers();

    int count = 0;
    for (int i = 0; i < boardArray.getSize(); i++) {
      for (int j = 0; j < boardArray.getSize(); j++) {
        if (boardArray.getTile(i, j).getValue() == 1) {
          count++;
        }
      }
    }
    assertEquals(2, count);
  }
}
