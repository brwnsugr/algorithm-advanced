package org.example.leetcode.prefixSum;

/**
 * LeetCode #238. Product of Array Except Self
 * https://leetcode.com/problems/product-of-array-except-self
 * TC: O(N)
 * SC: O(N)
 */
public class ProductArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] leftProduct = new int[len];
        int[] rightProduct = new int[len];

        for(int i = 0; i < nums.length; i++) {
            if(i == 0) {
                leftProduct[i] = nums[i];
                rightProduct[len - i - 1] = nums[len - i - 1];
            }

            else {
                leftProduct[i] = nums[i] * leftProduct[i - 1];
                rightProduct[len - i - 1] = nums[len - i - 1] * rightProduct[len - i];
            }
        }

        int[] res = new int[len];

        for(int i = 0; i < nums.length; i++) {
            if(i == 0) res[i] = rightProduct[i + 1];
            else if(i == nums.length - 1) res[i] = leftProduct[i - 1];
            else res[i] = rightProduct[i + 1] * leftProduct[i - 1];
        }

        return res;
    }
}
