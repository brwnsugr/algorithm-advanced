package org.example.runner;

public class AlgorithmRunner {


    public interface SortAlgorithm {
        int[] sort(int[] array);
    }

    public void run(SortAlgorithm sortAlgorithm, int[] array) {
        int[] sortedArray = sortAlgorithm.sort(array);
        System.out.println("Sorted array: " + java.util.Arrays.toString(sortedArray));
    }
}
