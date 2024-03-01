package org.example.onlineAssessments;

import java.util.*;

/**
 * Given a collection of n cards. The i- th card (1 ≤ i ≤ n) has a number Ai on its front and a number Bi on its back. At the start, all the cards are facing upwards.He wants to minimize the range of numbers (i. e. the difference between the maximum and minimum values) on the face- up side. He is allowed to flip a maximum of m cards. Flipping a card will transition Bi to the face up side and Ai to the back.Help him find the minimum possible range after using at most m flips.
 *
 * InputThe first line of the input consists of 2 integers n and m.The next line contains n integers, i-th of which denotes Ai.The next line contains n integers, i-th of which denotes Bi.
 *
 * Constraints:3 <= n <= 200000
 * 1 <= m <= n
 * 1 <= Ai, Bi <= 107
 * Output
 * Output a single integer, the minimum possible range.ExampleInput:5 21 2 17 16 93 4 5 6 11
 *
 * Output:8
 *
 * By flipping card 3 and 4, we get the face up numbers {1, 2, 5, 6, 9}. This makes range=9-1=8.
 *
 * I started in this way.
 *
 * If the value of m is 1 we can try all possiblities with the help of priority queue.
 *
 * If the value m is greater than 1 then since we can try at most m flips. Then we have
 *
 * ref: https://www.reddit.com/r/leetcode/comments/18sm9nq/amazon_oa_need_help_with_this_greedy_problem/
 */
public class CardRange {


    public static void main(String[] args) {
        int n = 7, m = 3;
        int[] A = {1, 2, 16, 9, 17, 3, 13};
        int[] B = {10, 12, 5, 6, 11, 8, 4};

        int n2 = 3, m2 = 3;
        int[] A2 = {1, 3, 6};
        int[] B2 = {101, 102, 103};

        int n3 = 5; int m3 = 2;
        int[] A3 = new int[]{1 ,2, 17, 16, 9};
        int[] B3 = new int[]{3, 4, 5, 6, 11};

        int n4 = 6; int m4 = 2;
        int[] A4 = new int[]{1,2,5,6,9,10};
        int[] B4 = new int[]{3,13,11,4,7,12};

        System.out.println(solution(n4, m4, A4, B4));
    }

    public static int solution(int n, int m, int[] A, int[] B) {
        // Copy A and sort
        int[] sortedA = Arrays.copyOf(A, A.length);
        Arrays.sort(sortedA);

        // Create smallestA and biggestA
        int[] smallestA = Arrays.copyOfRange(sortedA, 0, m);
        Set<Integer> smallSet = new HashSet<>();
        for(int item : smallestA) smallSet.add(item);

        int[] biggestA = Arrays.copyOfRange(sortedA, n - m, n);
        Set<Integer> largeSet = new HashSet<>();
        for(int item : biggestA) largeSet.add(item);

        // Placeholder
        int[] arr1 = new int[n];
        int[] arr2 = new int[n];

        // Flip m times for smallest value Cards
        for (int i = 0; i < n; i++) {
            if (largeSet.contains(A[i])) {
                if (A[i] > B[i]) {
                    arr1[i] = B[i];
                } else {
                    arr1[i] = A[i];
                }
            } else arr1[i] = A[i];
        }

        // Flip m times for biggest value Cards
        for (int i = 0; i < n; i++) {
            if (smallSet.contains(A[i])) {
                if (A[i] < B[i]) {
                    arr2[i] = B[i];
                } else {
                    arr2[i] = A[i];
                }
            } else arr2[i] = A[i];
        }

        // Compute ranges for both scenarios and return the smaller one
        int range1 = Arrays.stream(arr1).max().getAsInt() - Arrays.stream(arr1).min().getAsInt();
        int range2 = Arrays.stream(arr2).max().getAsInt() - Arrays.stream(arr2).min().getAsInt();
        return Integer.min(range1, range2);
    }





}


