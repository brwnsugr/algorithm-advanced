package bubbleSort;

import org.example.bubbleSort.BubbleSort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class BubbleSortTest {

    @Test
    public void testBubbleSort() {
        // Initialize an instance of your BubbleSort class
        BubbleSort bubbleSort = new BubbleSort();

        // Define a test case
        int[] input = {5, 3, 8, 4, 2};
        int[] expectedOutput = {2, 3, 4, 5, 8};

        // Run BubbleSort on the input
        int[] output = bubbleSort.sort(input);

        // Assert that the output matches the expected output
        assertArrayEquals(expectedOutput, output);
    }
}
