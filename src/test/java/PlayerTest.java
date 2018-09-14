import org.junit.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class PlayerTest {

    @Test
    public void playerShouldPlacePawnOnBoard() {
        Player player = new Player(CellState.X, (cellState) -> new Coordinate(0,0));
        Board board = new Board();
        player.play(board);
        assertThat(board.toString()).isEqualTo("X . .\n. . .\n. . .");
    }

    @Test
    public void playerShouldFinallyPlacePawnOnBoardIfFirstOneIsAlreadyTaken() {
        InputReader inputReader = Mockito.mock(InputReader.class);
        when(inputReader.readCoordinateFor(CellState.X))
                .thenReturn(new Coordinate(0,0))
                .thenReturn(new Coordinate(0,1));
        Player player = new Player(CellState.X, inputReader);
        Board board = BoardBuilder.createBoardForData("O . .\n. . .\n. . .");
        player.play(board);
        assertThat(board.toString()).isEqualTo("O X .\n. . .\n. . .");
    }

    @Test
    public void playerShouldAskAgainIfThereisAnExceptionWhileReadingInput() {
        InputReader inputReader = Mockito.mock(InputReader.class);
        when(inputReader.readCoordinateFor(any(CellState.class)))
                .thenThrow(new RuntimeException())
                .thenReturn(new Coordinate(0,0));

        Player player = new Player(CellState.X, inputReader);
        Board board = BoardBuilder.createBoardForData(". . .\n. . .\n. . .");
        player.play(board);
        assertThat(board.toString()).isEqualTo("X . .\n. . .\n. . .");
    }


    @Test
    public void shouldReturnTrueIfPlayerHasWon() {
        Player player = new Player(CellState.X, (cellState) -> new Coordinate(0,0));
        Board board = BoardBuilder.createBoardForData("X X X\n. . .\n. . .");
        assertThat(player.hasWon(board)).isTrue();
    }

    @Test
    public void shouldReturnFalseIfPlayerHasNotWon() {
        Player player = new Player(CellState.X, (cellState) -> new Coordinate(0,0));
        Board board = BoardBuilder.createBoardForData(". X X\n. . .\n. . .");
        assertThat(player.hasWon(board)).isFalse();
    }

    @Test
    public void shouldReturnWinningStatement() {
        Player player = new Player(CellState.X, (cellState) -> new Coordinate(0,0));
        assertThat(player.getWinningStatement()).isEqualTo("X has won");
    }

}