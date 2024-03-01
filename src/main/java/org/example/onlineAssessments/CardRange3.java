package org.example.onlineAssessments;

/**
 * Here is a sorting + sliding window solution.
 *
 * Sort the 2n tuples (value, position, type A/B). Consider the range from l to r in this sorted list.
 * We can limit the values on the cards within the range [value[l], value[r]]
 * iff the following conditions are satisfied by tuples in [l, r]:
 *
 * 1. All n positions exist among the tuples (use preSum)
 *
 * 2. There are at least n - m tuples of type A (use preSum)
 *
 * So keep two pointers l, r. For each l, use sliding window to find the minimum r that satisfies the two conditions above.
 * Find the minimum value of value[r] - value[l] among valid pairs and that is the answer.
 *
 * ->
 */
public class CardRange3 {



    public int solve(int n, int m, int[] A, int[] B) {
        return 1;
    }
}
