package org.example.leetcode.twoPointers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * LeetCode #2964. Number of Divisible Triplet Sums
 *
 * leetcode.com/problems/number-of-divisible-triplet-sums/
 *
 * TC: O(N^2)
 */

public class NumberDivisibleTripletSums {
    public int divisibleTripletCount(int[] nums, int d) {
        Map<Integer, List<int[]>> m = new HashMap<>();

        for(int i = 0; i < nums.length - 1; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                int key = (nums[i]+nums[j])% d;
                if(!m.containsKey(key)) m.put(key, new ArrayList<>());
                m.get(key).add(new int[]{i, j});
            }
        }

        int answer = 0;

        for(int l = 0; l < nums.length; l++) {
            int key = (d - nums[l] % d) % d;
            int tmpCount = 0;
            for(int[] remainArr : m.getOrDefault(key, new ArrayList<>())) {
                int remainLeftIdx = remainArr[0];
                if(l < remainLeftIdx) tmpCount++;
            }
            answer += tmpCount;
        }

        return answer;
    }
}
