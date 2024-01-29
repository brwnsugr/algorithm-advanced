package org.example.leetcode.binarySearch;

/**
 * LeetCode 2055. Plates Between Candles
 * https://leetcode.com/problems/plates-between-candles/
 *
 * TC: O(N + QlogN)
 */

public class PlatesBetweenCandles {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        // store prefixSum // and[11112222]//
        // 1. leftMost pos that prefixSum increase
        // 2. rightMost pos that prefixSum increase
        int[] prefixCandles = new int[s.length()];
        prefixCandles[0] = s.charAt(0) == '|' ? 1 : 0;
        for(int i = 1; i < s.length(); i++) {
            if(s.charAt(i) == '|') prefixCandles[i] = prefixCandles[i - 1] + 1;
            else prefixCandles[i] = prefixCandles[i - 1];
        }

        int[] ans = new int[queries.length];
        for(int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            int leftTarget = s.charAt(l) == '|' ? prefixCandles[l] : prefixCandles[l] + 1;

            int leftPos = binarySearch(l, r, leftTarget, prefixCandles);
            int rightTarget = prefixCandles[r];
            int rightPos = binarySearch(l, r, rightTarget, prefixCandles);
            if(leftPos >= rightPos) ans[i] = 0;
            else if(leftPos < rightPos) ans[i] = rightPos - leftPos - (prefixCandles[rightPos] - prefixCandles[leftPos]);
        }

        return ans;
    }

    private int binarySearch(int l, int r, int target, int[] prefixSum) {
        // leftMost index of target
        // [1,2]
        while(l < r) {
            int m = l + (r - l) / 2;
            if(prefixSum[m] >= target) {
                r = m;
            }
            else {
                l = m + 1;
            }
        }
        return l;
    }
}
