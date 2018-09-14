import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class BoardTest {

    @Test
    public void shouldInitialiseWithEmpty() {
        Board board = new Board();
        assertThat(board.toString()).isEqualTo(". . .\n. . .\n. . .");
    }

    @Test
    public void shouldSetCellStateAt0_0ToXSuccessfully() {
        Board board = new Board();
        assertThat(board.set(new Coordinate(0, 0), CellState.X)).isTrue();
        assertThat(board.toString()).isEqualTo("X . .\n. . .\n. . .");
    }

    @Test
    public void shouldNotSetCellStateAt0_0ToXIfOIsSetAlready() {
        Board board = new Board();
        assertThat(board.set(new Coordinate(0, 0), CellState.O)).isTrue();
        assertThat(board.set(new Coordinate(0, 0), CellState.X)).isFalse();
        assertThat(board.toString()).isEqualTo("O . .\n. . .\n. . .");
    }

    @Test
    public void shouldReturnTrueIfSpaceIsAvailable() {
        Board board = BoardBuilder.createBoardForData("X . .\n. . .\n. . .");
        assertThat(board.isSpaceAvailable()).isTrue();
    }

    @Test
    public void shouldReturnFalseIfSpaceIsNotAvailable() {
        Board board = BoardBuilder.createBoardForData("X X X\nX X X\nX X X");
        assertThat(board.isSpaceAvailable()).isFalse();
    }

    @Test
    public void shouldReturnTrueIfXHasWonOnTheFirstRow() {
        Board board = BoardBuilder.createBoardForData("X X X\n. . .\n. . .");
        assertThat(board.hasWon(CellState.X)).isTrue();
    }

    @Test
    public void shouldReturnTrueIfOHasWonOnTheSecondRow() {
        Board board = BoardBuilder.createBoardForData("X O X\nO O O\nX . O");
        assertThat(board.hasWon(CellState.O)).isTrue();
    }

    @Test
    public void shouldReturnFalseIfXHasNotWonOnTheFirstRow() {
        Board board = BoardBuilder.createBoardForData("X . X\n. . .\n. . .");
        assertThat(board.hasWon(CellState.X)).isFalse();
    }

    @Test
    public void shouldReturnTrueIfOHasWonOnTheSecondColumn() {
        Board board = BoardBuilder.createBoardForData("X O X\nX O O\nX O O");
        assertThat(board.hasWon(CellState.O)).isTrue();
    }

    @Test
    public void shouldReturnFalseIfXHasNotWonOnTheSecondColumn() {
        Board board = BoardBuilder.createBoardForData("X . X\n. . .\n. . .");
        assertThat(board.hasWon(CellState.X)).isFalse();
    }

    @Test
    public void shouldReturnTrueIfXHasWonOnTheFirstDiagnol() {
        Board board = BoardBuilder.createBoardForData("X . X\n. X .\n. . X");
        assertThat(board.hasWon(CellState.X)).isTrue();
    }

    @Test
    public void shouldReturnTrueIfXHasWonOnTheSecondDiagnol() {
        Board board = BoardBuilder.createBoardForData(". . X\n. X .\nX . .");
        assertThat(board.hasWon(CellState.X)).isTrue();
    }

}