package sk.tuke.kpi.kp.game.core;

public enum Direction {
    UP, DOWN, LEFT, RIGHT, NONE;

    private static Board board;

    private boolean isMoved;

    public static void directionBoard(Board boardGame) {
        board = boardGame;
    }

    public void moveTo(Direction direction) {
        isMoved = false;
        switch (direction) {
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
            case NONE:
                break;
        }
    }

    private void moveLeft() {
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                for (int next = col + 1; next < board.getSize(); next++) {
                    if (board.getTile(row, next).getValue() > 0) {
                        if (board.getTile(row, col).getValue() == 0) {
                            board.getTile(row, col).mergeNumbers(board.getTile(row, next), true);
                            isMoved = true;
                        } else {
                            if (board.getTile(row, col).getValue() == (board.getTile(row, next)).getValue()) {
                                board.getTile(row, col).mergeNumbers(board.getTile(row, next), false);
                                isMoved = true;
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    private void moveDown() {
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = board.getSize() - 1; col >= 0; col--) {
                for (int next = col - 1; next >= 0; next--) {
                    if (board.getTile(next, row).getValue() > 0) {
                        if (board.getTile(col, row).getValue() == 0) {
                            board.getTile(col, row).mergeNumbers(board.getTile(next, row), true);
                            isMoved = true;
                        } else {
                            if (board.getTile(col, row).getValue() == (board.getTile(next, row)).getValue()) {
                                board.getTile(col, row).mergeNumbers(board.getTile(next, row), false);
                                isMoved = true;
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    private void moveRight() {
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = board.getSize() - 1; col >= 0; col--) {
                for (int next = col - 1; next >= 0; next--) {
                    if (board.getTile(row, next).getValue() > 0) {
                        if (board.getTile(row, col).getValue() == 0) {
                            board.getTile(row, col).mergeNumbers(board.getTile(row, next), true);
                            isMoved = true;
                        } else {
                            if (board.getTile(row, col).getValue() == (board.getTile(row, next)).getValue()) {
                                board.getTile(row, col).mergeNumbers(board.getTile(row, next), false);
                                isMoved = true;
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    private void moveUp() {
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                for (int next = col + 1; next < board.getSize(); next++) {
                    if (board.getTile(next, row).getValue() > 0) {
                        if (board.getTile(col, row).getValue() == 0) {
                            board.getTile(col, row).mergeNumbers(board.getTile(next, row), true);
                            isMoved = true;
                        } else {
                            if (board.getTile(col, row).getValue() == (board.getTile(next, row)).getValue()) {
                                board.getTile(col, row).mergeNumbers(board.getTile(next, row), false);
                                isMoved = true;
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    public boolean isMoved() {
        return isMoved;
    }

}
