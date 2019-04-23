package assignment;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class UserInputTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private static String helpMessage = "How to use this program:\n" +
            "Please provide the filename of a log including the file ending (e.g. 'timing.log') from the current directory as well as a number.\n" +
            "The program will print out three things:\n" +
            "1) The resources with the highest average request duration, limited by the provided number.\n" +
            "2) A histogram of the hourly number of requests.\n" +
            "3) The number of milliseconds the program ran.";
    private static String invalidInputMessage = "Invalid input. Please check your input or type '-h' for instructions on how to use this program.";

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    void helpFlagReturnsFalse() {
        //given
        String[] arguments = new String[1];
        arguments[0] = "-h";

        //when
        boolean result = UserInput.isValidUserInput(arguments);

        //then
        assertFalse(result);
    }

    @Test
    void helpFlagPrintsHelpMessage() {
        //given
        String[] arguments = new String[1];
        arguments[0] = "-h";

        //when
        UserInput.isValidUserInput(arguments);
        String printedMessage = outContent.toString().trim();

        //then
        assertEquals(helpMessage, printedMessage);
    }

    @Test
    void singleArgumentNotHelpFlagReturnsFalse() {
        //given
        String[] arguments = new String[1];
        arguments[0] = "abc";

        //when
        boolean result = UserInput.isValidUserInput(arguments);

        //then
        assertFalse(result);
    }

    @Test
    void singleArgumentNotHelpFlagPrintsInvalidInputMessage() {
        //given
        String[] arguments = new String[1];
        arguments[0] = "abc";

        //when
        UserInput.isValidUserInput(arguments);
        String printedMessage = outContent.toString().trim();

        //then
        assertEquals(invalidInputMessage, printedMessage);
    }

    @Test
    void logAsFilenameReturnsTrue() {
        //given
        String[] arguments = new String[2];
        arguments[0] = "test.log";
        arguments[1] = "20";

        //when
        boolean result = UserInput.isValidUserInput(arguments);

        //then
        assertTrue(result);
    }

    @Test
    void txtAsFilenameReturnsFalse() {
        //given
        String[] arguments = new String[2];
        arguments[0] = "test.txt";
        arguments[1] = "20";

        //when
        boolean result = UserInput.isValidUserInput(arguments);

        //then
        assertFalse(result);
    }

    @Test
    void numberAsStringReturnsTrue() {
        //given
        String[] arguments = new String[2];
        arguments[0] = "test.log";
        arguments[1] = "20";

        //when
        boolean result = UserInput.isValidUserInput(arguments);

        //then
        assertTrue(result);
    }

    @Test
    void stringWithoutNumberReturnsFalse() {
        //given
        String[] arguments = new String[2];
        arguments[0] = "test.log";
        arguments[1] = "abc";

        //when
        boolean result = UserInput.isValidUserInput(arguments);

        //then
        assertFalse(result);
    }

    @Test
    void invalidUserInputPrintsInvalidInputMessage() {
        //given
        String[] arguments = new String[2];
        arguments[0] = "test.txt";
        arguments[1] = "abc";

        //when
        UserInput.isValidUserInput(arguments);
        String printedMessage = outContent.toString().trim();

        //then
        assertEquals(invalidInputMessage, printedMessage);
    }

    @Test
    void invalidUserInputReturnsFalse() {
        //given
        String[] arguments = new String[2];
        arguments[0] = "test.txt";
        arguments[1] = "abc";

        //when
        boolean result = UserInput.isValidUserInput(arguments);

        //then
        assertFalse(result);
    }

    @Test
    void noInputPrintsInvalidInputMessage() {
        //given
        String[] arguments = new String[0];

        //when
        UserInput.isValidUserInput(arguments);
        String printedMessage = outContent.toString().trim();

        //then
        assertEquals(invalidInputMessage, printedMessage);
    }

    @Test
    void noInputReturnsFalse() {
        //given
        String[] arguments = new String[0];

        //when
        boolean result = UserInput.isValidUserInput(arguments);

        //then
        assertFalse(result);
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}