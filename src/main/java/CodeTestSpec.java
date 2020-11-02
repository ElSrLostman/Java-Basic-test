import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;

/*
 *   Please code the tests in the format of reverseArray_returnsExpectedResult. This is an example of how we write our tests at Cardano.
 *
 *   Test 1-4 tests are easy as the function returns a result that can be asserted. Tests 5-7 are more difficult to assert as they are
 *   void, use your knowledge to write a meaningful test.
 *
 *   Feel free to use the internet to help you with you answers but make sure you understand the answer as we will ask you questions.
 */

public class CodeTestSpec {
    @Test
    public void reverseArray_returnsExpectedResult() {
        // arrange
        final String[] EXPECTED = { "x", "y", "z" };

        // act
        final String[] actual = CodeTest.reverseArray(new String[] { "z", "y", "x" });

        // assert
        assertArrayEquals(EXPECTED, actual);
    }

    @Test
    public void uppercaseArray_returnsExpectedResult() {
        // arrange
        final String[] EXPECTED = { "X", "Y", "Z" };

        // act
        final String[] actual = CodeTest.uppercaseArray(new String[] { "x", "y", "z" });

        // assert
        assertArrayEquals(EXPECTED, actual);
    }

    @Test
    public void findWordCount_returnsExpectedResult() {
        final String text = "the cat jumped over the mat";
        final String wordToFind = "the";

        assertEquals(2, CodeTest.findWordCount(text, wordToFind));
    }

    @Test
    public void composeU_returnsExpectedResult() {

        Function<Integer, Integer> times2 = e -> e * 2;

        assertEquals(8, CodeTest.composeU(times2, times2).apply(2).longValue());

    }

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void writeContentsToConsole_returnsExpectedResult() {
        String s;
        try {
            s = new String(Files.readAllBytes(Paths.get("prueba.txt")));
            CodeTest.writeContentsToConsole();
    
            assertEquals(s, outputStreamCaptor.toString().trim());
        } catch (IOException e) {
            System.out.println("File can't be found");
        }

    }

    @Test(expected=ArithmeticException.class)
    public void handleInvalidArgument_returnsExpectedResult() {
        CodeTest.handleInvalidArgument();
    }

    @Test
    public void puzzle_returnsExpectedResult() {

        String input = "12344";
        String output = "1,2,3,4,4,'Snap'";

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        CodeTest.puzzle();

        assertEquals(output, outputStreamCaptor.toString().trim());

    }
}
