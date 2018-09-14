import java.util.Scanner;

import static java.lang.String.format;

public interface InputReader {
    Coordinate readCoordinateFor(CellState cellState);
}


class ConsoleReader implements InputReader {

    @Override
    public Coordinate readCoordinateFor(CellState cellState) {
        System.out.print(format("%s> ", cellState.getRepresentation()));
        String[] coordinateStrings = getInputFromScanner();
        return new Coordinate(Integer.parseInt(coordinateStrings[0]), Integer.parseInt(coordinateStrings[1]));
    }

    private String[] getInputFromScanner() {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        return cleanAndSplit(input);
    }

    private String[] cleanAndSplit(String input) {
        return input.replaceAll("\\s", "").split(",");
    }
}

