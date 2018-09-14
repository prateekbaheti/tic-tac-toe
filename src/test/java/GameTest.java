import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {

    @Mock
    private Player player1;

    @Mock
    private Player player2;

    @Mock
    private Board board;

    @Test
    public void shouldKeepPlayingUntilBoardIsFull() {
        Game game = new Game(player1, player2, board);

        when(board.isSpaceAvailable())
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false);

        game.start();

        InOrder inOrder = inOrder(player1, player2, board);
        inOrder.verify(board).isSpaceAvailable();
        inOrder.verify(player1).play(board);
        inOrder.verify(board).isSpaceAvailable();
        inOrder.verify(player2).play(board);
    }

    @Test
    public void shouldReturnPlayer1HasWon() {
        Game game = new Game(player1, player2, board);

        when(board.isSpaceAvailable())
                .thenReturn(true)
                .thenReturn(false);

        when(player1.hasWon(board)).thenReturn(true);

        when(player1.getWinningStatement()).thenReturn("Player 1 Won");

        assertThat(game.start()).isEqualTo("Player 1 Won");
    }




}