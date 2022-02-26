public class Main {
    public static void main(String[] args) {
        Board board = new Board(4);

        UI ui = new UI(board);
        ui.play();
    }
}
