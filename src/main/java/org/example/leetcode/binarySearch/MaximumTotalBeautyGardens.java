package org.example.leetcode.binarySearch;

import java.util.Arrays;

/**
 *  2234. Maximum Total Beauty of the Gardens
 *  https://leetcode.com/problems/maximum-total-beauty-of-the-gardens
 *
 *  TC: O(N * logN)
 */

public class MaximumTotalBeautyGardens {
    public long maximumBeauty(int[] flowers, long newFlowers, int target, int full, int partial) {

        int len = flowers.length;
        Arrays.sort(flowers);
        if(flowers[0] == target) return (long) full * len;

        for(int i = 0; i < len; i++) {
            flowers[i] = Math.min(target, flowers[i]);
        }

        // Build array cost
        long[] cost = new long[len];
        for(int i = 1; i < cost.length; i++) {
            cost[i] = cost[i - 1] + (flowers[i] - flowers[i - 1]) * i;
        }

        if(newFlowers >= cost[len-1] + (target - flowers[len-1]) * len) {
            return Math.max((long)full * len, (long)full * (len  - 1) + (long)partial * (target - 1));
        }

        int j = len - 1;
        while(flowers[j] == target) j--;
        long ans = 0;

        while(newFlowers >= 0) {

            int idx = Arrays.binarySearch(cost, 0, j+1, newFlowers);
            if(idx < 0) idx = -idx-2;

            long currMinimum = flowers[idx] + (newFlowers - cost[idx]) / (idx + 1);
            ans = Math.max(ans, currMinimum * partial + (long)full * (len - j - 1));

            newFlowers -= (target - flowers[j]);
            j--;
        }

        return ans;
    }
}
