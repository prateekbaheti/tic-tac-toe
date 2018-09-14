public class TicTacToeApplication {

    public static void main(String[] args) {
        System.out.println(new Game(
                new Player(CellState.X, new ConsoleReader()),
                new Player(CellState.O, new ConsoleReader()),
                new Board())
                .start());
    }
}
