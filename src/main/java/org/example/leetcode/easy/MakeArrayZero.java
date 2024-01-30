package org.example.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode #2357. Make Array Zero by Subtracting Equal Amounts
 *
 * https://leetcode.com/problems/make-array-zero-by-subtracting-equal-amounts/
 *
 * TC: O(N)
 * SC: O(N)
 */
public class MakeArrayZero {
    public int minimumOperations(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();

        for(int num : nums) {
            if(num == 0) continue;
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        return countMap.size();
    }
}
