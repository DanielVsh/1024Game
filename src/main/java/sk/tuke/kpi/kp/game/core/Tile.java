package sk.tuke.kpi.kp.game.core;

public class Tile {
    private int value;

    public Tile(int value) {
        this.value = value;
    }

    public Tile() {
        this.value = 0;
    }

    public void mergeNumbers(Tile number, boolean isZero) {
        if(!isZero) {
            Board.setScore(Board.getScore() + (number.getValue() * 2));
        }
        this.setValue(value + number.getValue());
        number.deleteNumber();
        if(value == 1024) {
            Board.setGameState(GameState.SOLVED);
        }
    }

    public boolean numberEquals(Tile number) {
        return value == number.getValue();
    }

    public void deleteNumber() {
        setValue(0);
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}