package org.example.onlineAssessments.y2024q3;

import java.util.*;

/**
 * ref: https://leetcode.com/discuss/interview-question/5618873/Amazon-Coding-Question
 * ref: https://leetcode.com/discuss/interview-question/5618849/Amazon-OA-Most-Recent-Questions
 *
 *
 */
public class GameMaxTotalArea {

    public long getMaxTotalArea(int[] sideLengths) {
        int MOD = (int) 1e9 + 7;
        // Step 1: Count the frequencies of each side length
        Map<Integer, Integer> freq = new HashMap<>();
        for (int length : sideLengths) {
            freq.put(length, freq.getOrDefault(length, 0) + 1);
        }

        // Step 2: Use a priority queue to store pairs in descending order
        List<Integer> pairs = new ArrayList<>();

        // Step 3: Iterate through sorted keys in descending order to form pairs
        List<Integer> lengths = new ArrayList<>(freq.keySet());
        lengths.sort(Collections.reverseOrder());

        for (int length : lengths) {
            int count = freq.get(length);

            // Form as many pairs as possible with the current length
            while (count >= 2) {
                pairs.add(length);
                count -= 2;
            }

            // If we have an extra stick left, check if it can help with a reduced length
            if (count > 0 && length > 1) {
                freq.put(length - 1, freq.getOrDefault(length - 1, 0) + 1);
            }
        }

        // Step 4: Sort the pairs in descending order
        Collections.sort(pairs, Collections.reverseOrder());

        // Step 5: Calculate the maximum total area
        long totalArea = 0;
        for (int i = 1; i < pairs.size(); i += 2) {
            totalArea = (totalArea + (long) pairs.get(i) * pairs.get(i - 1)) % MOD;
        }

        return (int) totalArea;

    }

    public static void main(String[] args) {
        GameMaxTotalArea solution = new GameMaxTotalArea();
        long maxTotalArea = solution.getMaxTotalArea(new int[]{3, 4, 5, 5, 6});
        System.out.println(maxTotalArea);
    }
}
