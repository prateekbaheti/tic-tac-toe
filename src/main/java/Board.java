import java.util.HashMap;
import java.util.Map;

public class Board {

    private final int MAX_ROWS_AND_COLUMNS = 3;
    private final int GRID_TOTAL_COUNT = MAX_ROWS_AND_COLUMNS * MAX_ROWS_AND_COLUMNS;

    private Map<Coordinate, CellState> mapOfCoordinates;

    public Board() {
        this.mapOfCoordinates = new HashMap<>();
        initialiseWithEmptyCells();
    }

    private void initialiseWithEmptyCells() {
        for (int i = 0; i < GRID_TOTAL_COUNT; i++) {
            mapOfCoordinates.put(new Coordinate(i / MAX_ROWS_AND_COLUMNS, i % MAX_ROWS_AND_COLUMNS),
                                 CellState.EMPTY);
        }
    }

    public boolean set(Coordinate coordinate, CellState cellState) {
        if(mapOfCoordinates.get(coordinate) != CellState.EMPTY) {
            return false;
        }
        mapOfCoordinates.put(coordinate, cellState);
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < GRID_TOTAL_COUNT; i++) {
            int columnIndex = i % MAX_ROWS_AND_COLUMNS;
            builder.append(mapOfCoordinates.get(new Coordinate(i / MAX_ROWS_AND_COLUMNS, columnIndex))
                            .getRepresentation());
            if(columnIndex != 2) {
                builder.append(" ");
            }
            if(columnIndex == 2 && i != GRID_TOTAL_COUNT - 1) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }


    public boolean isSpaceAvailable() {
        for (int i = 0; i < GRID_TOTAL_COUNT; i++) {
            if(CellState.EMPTY ==
                    mapOfCoordinates.get(new Coordinate(i / MAX_ROWS_AND_COLUMNS, i % MAX_ROWS_AND_COLUMNS))) {
                return true;
            }
        }
        return false;
    }

    public boolean hasWon(CellState cellState) {
        return satisfiesRowWiseCheck(cellState) ||
                satisfiesColumnWiseCheck(cellState) ||
                satisfiesDiagnolWiseCheck(cellState);
    }

    private boolean satisfiesRowWiseCheck(CellState cellState) {
        row:
        for (int row = 0; row < MAX_ROWS_AND_COLUMNS; row++) {
            for (int column = 0; column < MAX_ROWS_AND_COLUMNS; column++) {
                if(cellState != mapOfCoordinates.get(new Coordinate(row, column))) continue row;
            }
            return true;
        }
        return false;
    }

    private boolean satisfiesColumnWiseCheck(CellState cellState) {
        column:
        for (int column = 0; column < MAX_ROWS_AND_COLUMNS; column++) {
            for (int row = 0; row < MAX_ROWS_AND_COLUMNS; row++) {
               if(cellState != mapOfCoordinates.get(new Coordinate(row, column))) continue column;
            }
            return true;
        }
        return false;
    }

    private boolean satisfiesDiagnolWiseCheck(CellState cellState) {
       return satisfiesFirstDiagnolCheck(cellState) || satisfiesSecondDiagnolCheck(cellState);
    }


    private boolean satisfiesFirstDiagnolCheck(CellState cellState) {
        for (int i = 0; i < MAX_ROWS_AND_COLUMNS; i++) {
            if(cellState != mapOfCoordinates.get(new Coordinate(i,i))) return false;
        }
        return true;
    }

    private boolean satisfiesSecondDiagnolCheck(CellState cellState) {
        for (int row = 0, column = MAX_ROWS_AND_COLUMNS - 1; row < MAX_ROWS_AND_COLUMNS; row++, column--) {
            if(cellState != mapOfCoordinates.get(new Coordinate(row,column))) return false;
        }
        return true;
    }
}
