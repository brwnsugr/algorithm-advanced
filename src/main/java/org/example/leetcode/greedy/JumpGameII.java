package org.example.leetcode.greedy;

/**
 * LeetCode #45. Jump Game II
 *
 */
public class JumpGameII {
    public int jump(int[] nums) {
        if(nums.length == 1) return 0;
        int currEnd = 0;
        int currFar = 0;
        int ans = 0;
        for(int i = 0; i < nums.length; i++) {
            currFar = Math.max(currFar, nums[i] + i);
            if(i == currEnd) {
                ans++;
                currEnd = currFar;
                if(currEnd >= nums.length - 1) return ans;
            }
        }
        return ans;
    }
}
