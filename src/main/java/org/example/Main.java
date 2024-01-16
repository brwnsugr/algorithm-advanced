package org.example;

import org.example.bubbleSort.BubbleSort;
import org.example.runner.AlgorithmRunner;

public class Main {
    public static void main(String[] args) {
        AlgorithmRunner runner = new AlgorithmRunner();
        BubbleSort bubbleSort = new BubbleSort();
        int[] array = {5, 3, 8, 4, 2};
        runner.run(bubbleSort, array);
    }
}