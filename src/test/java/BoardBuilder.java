public class BoardBuilder {
    public static Board createBoardForData(String data) {
        Board board = new Board();
        String[] lines = data.split("\n");
        for(int row=0; row<lines.length; row++) {
            String line = lines[row];
            String[] columns = line.split(" ");
            for(int column=0; column<columns.length; column++) {
                switch (columns[column]) {
                    case ".":
                        board.set(new Coordinate(row, column), CellState.EMPTY);
                        break;
                    case "X":
                        board.set(new Coordinate(row, column), CellState.X);
                        break;
                    default:
                        board.set(new Coordinate(row, column), CellState.O);
                        break;
                }
            }
        }
        return board;
    }
}
