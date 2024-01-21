package org.example.leetcode.prefixSum;

/**
 * LeetCode #152. Maximum Product Subarray
 * https://leetcode.com/problems/maximum-product-subarray/
 *
 * TC: O(N)
 * SC: O(1)
 */
public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        int maxSoFar = nums[0];
        int minSoFar = nums[0];
        int res = nums[0];

        for(int i = 1; i < nums.length; i++) {
            int curr = nums[i];
            int tempMax = Math.max(curr, Math.max(curr * minSoFar, curr * maxSoFar));

            //To get minSofar using the previous maxSoFar we don't directly get maxSofar, just tempMax
            minSoFar = Math.min(curr, Math.min(curr * minSoFar, curr * maxSoFar));
            maxSoFar = tempMax;
            res = Math.max(maxSoFar, res);
        }
        return res;
    }
}
