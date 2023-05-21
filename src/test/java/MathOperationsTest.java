import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class MathOperationsTest {

    @Test
    public void testAddition() {
        // Define the inputs
        int a = 5;
        int b = 10;

        // Perform the addition
        int result = a + b;

        // Assert the expected result
        assertEquals(15, result);
    }

    @Test
    public void testMultiplication() {
        // Define the inputs
        int a = 3;
        int b = 4;

        // Perform the multiplication
        int result = a * b;

        // Assert the expected result
        assertEquals(12, result);
    }

    @Test
    public void concatenateArgs() {
        String[] args = {" Hello ", "Who", "Is", "This "};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            sb.append(args[i]);
            if (i < args.length - 1) {
                sb.append(" ");
            }
        }
        String result = sb.toString();
        assertEquals(" Hello  Who Is This ", result);
    }

}