package org.example.onlineAssessments;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Amazon recently launched a new game, Fruit Crush! In this game, you are allowed to choose two dissimiliar fruits and crush them. Eath type of fruit is represened as an integer in an array. Formally you can choose any two unequal integers in the array and delete them.
 *
 * Given an array fruits of size n, return the minimum possible number of fruits left after the given operation is perfomed any number of times.
 *
 * Example:
 * n = 5, fruits = [3, 3, 1, 1, 2], answer = 2
 * Returns:
 * int: the minimum possible count of fruits left
 *
 * solve method 가 정답..
 *
 * ref: https://leetcode.com/discuss/interview-question/4220278/Amazon-OA
 *
 */
public class FruitCrush {

    public static void main(String[] args) {
        FruitCrush fruitCrush = new FruitCrush();
        int minimumPossibleCount = fruitCrush.solve2(5, new int[]{3, 3, 1, 1, 2});
        int solve = fruitCrush.solve(new int[]{3, 3, 1, 1, 2});
        int[] f1 = new int[]{3, 3, 1, 1, 2};
        int[] f2 = new int[]{1,1, 2,2, 3,3};
        int[] f3 = new int[]{1, 1, 1, 2, 3, 4};
        int[] f4 = new int[]{1, 1, 1, 1, 1};
        int[] f5 = new int[]{1,1, 2,2, 3,3, 4,4};
        int[] f6 = new int[]{1,1};

        int solve1 = fruitCrush.solve(f1);
        int solve2 = fruitCrush.solve(f2);
        int solve3 = fruitCrush.solve(f3);
        int solve4 = fruitCrush.solve(f4);
        int solve5 = fruitCrush.solve(f5);
        int solve6 = fruitCrush.solve(f6);

        int minimumPossibleCount1 = fruitCrush.solve2(5, f1);
        int minimumPossibleCount2 = fruitCrush.solve2(6, f2);
        int minimumPossibleCount3 = fruitCrush.solve2(6, f3);
        int minimumPossibleCount4 = fruitCrush.solve2(5, f4);
        int minimumPossibleCount5 = fruitCrush.solve2(8, f5);
        int minimumPossibleCount6 = fruitCrush.solve2(2, f6);

        System.out.println(minimumPossibleCount);
    }

    public int solve2(int n, int[] fruits) {
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int fruit : fruits) {
            count.put(fruit, count.getOrDefault(fruit, 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            pq.add(entry.getValue());
        }

        while (pq.size() > 1) {
            int freq1 = pq.poll();
            int freq2 = pq.poll();
            int diff = Math.abs(freq1 - freq2);
            if(diff > 0) pq.add(diff);
        }

        return pq.isEmpty() ? 0 : pq.peek();
    }

    public int solve(int[] fruits){
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int fruit : fruits) {
            count.put(fruit, count.getOrDefault(fruit, 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            pq.add(entry.getValue());
        }

        while (pq.size() > 1) {
            int freq1 = pq.poll();
            int freq2 = pq.poll();
            if (--freq1 > 0) pq.add(freq1);
            if (--freq2 > 0) pq.add(freq2);
        }

        return pq.isEmpty() ? 0 : pq.peek();
    }
}
