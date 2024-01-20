package org.example.leetcode.hash;

import java.util.HashSet;
import java.util.Set;

public class MaximumSizeSetAfterRemovals {
    /**
     * LeetCode #3002. Maximum Size of a Set After Removals
     * TC: O(N) - N is the length of nums1 and nums2
     * SC: O(N)
     */
    public int maximumSetSize(int[] nums1, int[] nums2) {
        Set<Integer> s1 = new HashSet<>();
        Set<Integer> s2 = new HashSet<>();
        Set<Integer> union = new HashSet<>();

        for(int num1 : nums1) {
            s1.add(num1);
            union.add(num1);
        }

        for(int num2 : nums2) {
            s2.add(num2);
            union.add(num2);
        }

        int halfLen = nums1.length / 2;

        int leftCount = Math.min(s1.size(), halfLen);
        int rightCount = Math.min(s2.size(), halfLen);

        return Math.min(leftCount + rightCount, union.size());
    }
}
