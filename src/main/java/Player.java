import static java.lang.String.format;

public class Player {

    private CellState pawn;
    private InputReader inputReader;

    public Player(CellState pawn, InputReader inputReader) {
        this.pawn = pawn;
        this.inputReader = inputReader;
    }

    public void play(Board board) {
        try {
            Coordinate coordinate = inputReader.readCoordinateFor(pawn);
            if(!board.set(coordinate, pawn)) {
                play(board);
            }
        } catch (Exception e) {
            play(board);
        }
    }

    public boolean hasWon(Board board) {
        return board.hasWon(pawn);
    }

    public String getWinningStatement() {
        return format("%s has won", pawn.getRepresentation());
    }
}
