import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsoleReaderTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(System.out);
        System.setIn(System.in);
    }

    @Test
    public void shouldPrintXOnTerminal() throws UnsupportedEncodingException {
        ConsoleReader consoleReader = new ConsoleReader();
        System.setIn(new ByteArrayInputStream("0,0".getBytes("UTF-8")));
        consoleReader.readCoordinateFor(CellState.X);
        assertThat(outContent.toString()).isEqualTo("X> ");
    }


    @Test
    public void shouldReadStringAndConvertIntoCoordinate() throws UnsupportedEncodingException {
        ConsoleReader consoleReader = new ConsoleReader();
        System.setIn(new ByteArrayInputStream("0,0".getBytes("UTF-8")));
        assertThat(consoleReader.readCoordinateFor(CellState.X)).isEqualTo(new Coordinate(0, 0));
    }

    @Test
    public void shouldReadStringWithSpacesAndConvertIntoCoordinate() throws UnsupportedEncodingException {
        ConsoleReader consoleReader = new ConsoleReader();
        System.setIn(new ByteArrayInputStream("0, 0".getBytes("UTF-8")));
        assertThat(consoleReader.readCoordinateFor(CellState.X)).isEqualTo(new Coordinate(0, 0));
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowARuntTimeExceptionWhenInputIsInvalid() throws UnsupportedEncodingException {
        ConsoleReader consoleReader = new ConsoleReader();
        System.setIn(new ByteArrayInputStream("z, y".getBytes("UTF-8")));
        consoleReader.readCoordinateFor(CellState.X);
    }

}