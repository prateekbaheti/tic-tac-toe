public class Game {


    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private final Board board;

    public Game(Player player1, Player player2, Board board) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.board = board;
    }

    public String start() {
        while(board.isSpaceAvailable()) {
            currentPlayer.play(board);
            System.out.println(board.toString());
            if(currentPlayer.hasWon(board)) {
                return currentPlayer.getWinningStatement();
            }
            switchPlayers();
        }
        return null;
    }

    private void switchPlayers() {
        currentPlayer = player1 == currentPlayer ? player2 : player1;
    }
}
